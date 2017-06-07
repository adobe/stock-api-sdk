package com.adobe.stock.apis;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rits.cloning.Cloner;

/**
 * Utilities related to Http requests execution.
 */
final class HttpUtils {
    /**
     * HTTP Get Method for http requests.
     */
    public static final String HTTP_GET = "GET";
    /**
     * HTTP Post Method for http requests.
     */
    public static final String HTTP_POST = "POST";

    /**
     * The divisor for consolidating the http status code handling.
     */
    private static final int HTTP_STATUS_CODE_DIVISOR = 100;

    /**
     * Constant for 4XX status code handling.
     */
    private static final int HTTP_STATUS_CODE_API_ERROR = 4;

    /**
     * Constant for 5XX status code handling.
     */
    private static final int HTTP_STATUS_CODE_SERVER_ERROR = 5;

    /**
     * The http client instance for Http requests excutions.
     */
    private static HttpClient sHttpClient;

    /**
     * The time out constant for connection request as well as socket.
     */
    private static final int TIME_OUT = 3 * 1000;

    /**
     * The default constructor for HttpUtils.
     */
    private HttpUtils() {
    }

    /**
     * Initializes Http Client.
     *
     * @return Instance of HttpClient
     */
    private static HttpClient initialize() {
        PoolingHttpClientConnectionManager connManager
                = new PoolingHttpClientConnectionManager();
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT).build();
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(config).build();
        return httpClient;
    }

    /**
     * Executes get http request to an endpoint with provided headers.
     *
     * @param uri
     *            Endpoint that needs to be hit
     * @param headers
     *            Key value pair of headers
     * @return Return response body after executing GET
     * @throws StockException
     *             if api doesn't return with success code or when null/empty
     *             endpoint is passed in uri
     */
    public static String doGet(final String uri,
            final Map<String, String> headers) throws StockException {

        if (sHttpClient == null) {
            sHttpClient = HttpUtils.initialize();
        }

        HttpResponse response = null;
        String responseBody = null;
        HttpGet request = new HttpGet();

        if (uri == null || uri.isEmpty()) {
            throw new StockException(-1, "URI cannot be null or Empty");
        }

        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try {
            request.setURI(new URI(uri));
            response = sHttpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseBody = EntityUtils.toString(response.getEntity());
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR
                        == HTTP_STATUS_CODE_API_ERROR) {
                responseBody = EntityUtils.toString(response.getEntity());
                throw new StockException(response.getStatusLine()
                        .getStatusCode(), responseBody);
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR
                        == HTTP_STATUS_CODE_SERVER_ERROR) {
                throw new StockException(response.getStatusLine()
                        .getStatusCode(), "API returned with Server Error");

            }

        } catch (StockException se) {
            throw se;
        } catch (Exception ex) {
            throw new StockException(ex.getMessage());
        }

        return responseBody;
    }

    /**
     * Executes POST Http request to an endpoint with provided request post body
     * and headers.
     *
     * @param uri
     *            Endpoint which needs to be hit
     * @param headers
     *            map of headers and their corresponding values
     * @param postData
     *            request body data in byte array
     * @param contentType
     *            content type of request body data like json, xml etc
     * @return return response body as string after executing POST
     * @throws StockException
     *             if api doesn't return with success code or when null/empty
     *             endpoint is passed in uri
     */
    public static String doPost(final String uri,
            final Map<String, String> headers, final byte[] postData,
            final ContentType contentType) throws StockException {
        if (sHttpClient == null) {
            sHttpClient = HttpUtils.initialize();
        }

        HttpResponse response = null;
        String responseBody = null;

        if (uri == null || uri.isEmpty()) {
            throw new StockException(-1, "URI cannot be null or Empty");
        }

        HttpPost request = new HttpPost(uri);

        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try {

            if (postData != null) {
                ByteArrayEntity entity = new ByteArrayEntity(postData,
                        contentType);
                request.setEntity(entity);
            }
            response = sHttpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                    || response.getStatusLine().getStatusCode()
                        == HttpStatus.SC_CREATED) {
                responseBody = EntityUtils.toString(response.getEntity());
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR == HTTP_STATUS_CODE_API_ERROR) {
                responseBody = EntityUtils.toString(response.getEntity());

                throw new StockException(response.getStatusLine()
                        .getStatusCode(), responseBody);
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR
                        == HTTP_STATUS_CODE_SERVER_ERROR) {
                throw new StockException(response.getStatusLine()
                        .getStatusCode(), "API returned with Server Error");
            }

        } catch (StockException se) {
            throw se;
        } catch (Exception ex) {
            throw new StockException(-1, ex.getMessage());
        }

        return responseBody;
    }

    /**
     * Executes MultiPart upload request to an endpoint with provided headers.
     *
     * @param uri
     *            endpoint that needs to be hit
     * @param file
     *            file to be uploaded
     * @param headers
     *            Key Value pair of headers
     * @return Response Body after executing Multipart
     * @throws StockException
     *             if api doesnt return with success code or when null/empty
     *             endpoint is passed in uri
     */
    public static String doMultiPart(final String uri, final byte[] file,
            final Map<String, String> headers) throws StockException {
        if (sHttpClient == null) {
            sHttpClient = HttpUtils.initialize();
        }

        HttpResponse response = null;
        String responseBody = null;

        if (uri == null || uri.isEmpty()) {
            throw new StockException(-1, "URI cannot be null or Empty");
        }

        HttpPost request = new HttpPost(uri);

        if (headers != null) {
            for (Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        if (file != null) {
            builder.addBinaryBody("file", file);
        }

        HttpEntity entity = builder.build();
        request.setEntity(entity);

        try {
            response = sHttpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                    || response.getStatusLine().getStatusCode()
                        == HttpStatus.SC_CREATED) {
                responseBody = EntityUtils.toString(response.getEntity());
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR
                        == HTTP_STATUS_CODE_API_ERROR) {
                responseBody = EntityUtils.toString(response.getEntity());
                throw new StockException(response.getStatusLine()
                        .getStatusCode(), responseBody);
            } else if (response.getStatusLine().getStatusCode()
                    / HTTP_STATUS_CODE_DIVISOR
                        == HTTP_STATUS_CODE_SERVER_ERROR) {
                throw new StockException(response.getStatusLine()
                        .getStatusCode(), "API returned with Server Error");

            }

        } catch (StockException se) {
            throw se;
        } catch (Exception ex) {
            throw new StockException(-1, ex.getMessage());
        }

        return responseBody;

    }

}

/**
 * Utilities related to JSON deserialization.
 */
final class JsonUtils {

    /**
     * Singleton {@link ObjectMapper} object for reading and writing json from
     * POJOs.
     */
    private static ObjectMapper sMapper;

    /**
     * The default constructor for JsonUtils.
     */
    private JsonUtils() {
    }

    /**
     * Returns instance of singleton {@link ObjectMapper} object.
     *
     * @return the object mapper
     */
    private static ObjectMapper getMapper() {
        if (sMapper == null) {
            sMapper = new ObjectMapper();
        }
        return sMapper;
    }

    /**
     * Parses the given json string to POJO of given class type.
     *
     * @param objClass
     *            class type of POJO
     * @param jsonString
     *            the json string which needs to be parsed
     * @return POJO of given class type
     * @throws StockException
     *            if some error occurs while parsing
     */
    static Object parseJson(final Class<?> objClass, final String jsonString)
            throws StockException {
        Object obj = null;
        try {
            obj = getMapper().readValue(jsonString, objClass);
        } catch (JsonParseException e) {
            throw new StockException("Could not parse the given json string");
        } catch (IOException e) {
            throw new StockException(
                    "Could not map the given json string to response object");
        }
        return obj;
    }

}

/**
 * Utilities related to Models classes.
 */
final class ModelsUtil {

    /**
     * The object of Cloner class for creating the deep cloned objects.
     */
    private static Cloner sCloner = new Cloner();

    /**
     * The default constructor for ModelsUtil.
     */
    private ModelsUtil() {
    }

    /**
     * Method to deep clone an object.
     *
     * @param source
     *            OldObject whose fields are required to be copied into
     *            newObject fields.
     * @return cloned Object
     */
    public static Object deepClone(final Object source) {
        return sCloner.deepClone(source);
    }

}
