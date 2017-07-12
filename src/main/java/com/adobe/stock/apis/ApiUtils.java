package com.adobe.stock.apis;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.imageio.ImageIO;

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

import com.adobe.stock.config.StockConfig;
import com.adobe.stock.exception.StockException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
                    == HttpStatus.SC_NO_CONTENT) {
                responseBody = String.valueOf(HttpStatus.SC_NO_CONTENT);
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
    /**
     * Parses the array of given class type object to JSON string.
     *
     * @param obj Object that needs to be converted
     * @return Json String
     * @throws StockException
     *            if some error occurs while parsing
     */
    static String parseObjectToJson(final Object obj)
            throws StockException {
        getMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = "";
            try {
                jsonString = getMapper().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new StockException(
                        "Could not parse the given object to JSON");
            }
        return jsonString;
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


/**
 * Utilities related to Stock APIs.
 */
final class ApiUtils {
    /**
     * The api key header name.
     */
    private static final String X_API_KEY = "x-api-key";
    /**
     * The product header name.
     */
    private static final String X_PRODUCT = "x-product";
    /**
     * The product location header name.
     */
    private static final String X_PRODUCT_LOCATION = "x-product-location";
    /**
     * The request identifier used to trace the request in logs.
     */
    private static final String X_REQUEST_ID = "x-request-id";
    /**
     * The authorization header name.
     */
    private static final String AUTHORIZATION = "Authorization";

    /**
     * The bearer string constant for authorization header. To be appended with
     * access token.
     */
    private static final String BEARER = "Bearer ";

    /**
     * The default constructor for API utils.
     */
    private ApiUtils() {
    }

    /**
     * Generates a map of commonly used headers which is used inside
     * {@link HttpUtils} for Stock API access.
     *
     * @param config Stock api configuration
     * @param accessToken
     *            Access token string to be used with api calls
     * @return headers map containing all the common API headers
     */
    static Map<String, String> generateCommonAPIHeaders(
            final StockConfig config, final String accessToken) {
        Map<String, String> headers = new HashMap<String, String>();
        if (config.getApiKey() != null) {
            headers.put(X_API_KEY, config.getApiKey());
        }
        if (config.getProduct() != null) {
            headers.put(X_PRODUCT, config.getProduct());
        }
        if (config.getProductLocation() != null) {
            headers.put(X_PRODUCT_LOCATION, config.getProductLocation());
        }
        if (accessToken != null) {
            headers.put(AUTHORIZATION, BEARER + accessToken);
        }
        // Generate random unique identifier for request id.
        String requestID = UUID.randomUUID().toString();
        headers.put(X_REQUEST_ID, requestID);
        return headers;
    }

}

/**
 * This class have width and height
 *  of downsampled image.
 */
final class Dimension {
    /**
     * Width of downsampled image.
     */
    private int mWidth;

     /**
     * @return width of downsampled image;
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * @param width set width of downsampled image;
     */
    public void setWidth(final int width) {
        this.mWidth = width;
    }
    /**
     * @return height of downsampled image;
     */
    public int getHeight() {
        return mHeight;
    }
    /**
     * @param height sets height of downsampled image;
     */
    public void setHeight(final int height) {
        this.mHeight = height;
    }
    /**
     * Height of downsampled image.
     */
    private int mHeight;

}


/** DownSampleUtil class provides utility methods to scale down the
 *  image for multi part upload to find visualy similar images.
 * @author pragatijohri
 *
 */
final class DownSampleUtil {

    /**
     * Maximum pixels size that can be downsampled.
     * If image is bigger than 23000 pixels, utility method will throw
     * an error.
     */
     static final int LONGEST_SIDE_MAXIMUM = 23000;

    /**
     * Maximum image size that can be uploaded to upload similar image
     * to search for visualy same images.
     */

     static final int LONGEST_SIDE_DOWNSAMPLE_TO = 1000;

    /**
     * Default constructor for this class.
     */
    private DownSampleUtil() {

    }

/** Calculate width and height to which image to be downsampled.
 * @param originalWidth Width of original image.
 * @param originalHeight Height of original image.
 * @return object of Dimension that have width and height of downsampled image.
 * @throws StockException if original image is bigger than 23000 pixels.
 */
private static Dimension calculateResizeParameters(final int originalWidth,
                          final int originalHeight) throws StockException {
    Dimension dimension = new Dimension();
    if (Math.max(originalWidth, originalHeight) > LONGEST_SIDE_MAXIMUM) {
      throw new StockException("Image is too large for visual search!");
    } else {
        float aspectRatio = (float) originalWidth / originalHeight;
        if (originalWidth > originalHeight) {
            if (originalWidth > LONGEST_SIDE_DOWNSAMPLE_TO) {
                dimension.setWidth(LONGEST_SIDE_DOWNSAMPLE_TO);
                dimension.setHeight((int) (LONGEST_SIDE_DOWNSAMPLE_TO
                                                / aspectRatio));
                }
            } else if (originalHeight > LONGEST_SIDE_DOWNSAMPLE_TO) {
                dimension.setWidth((int) (LONGEST_SIDE_DOWNSAMPLE_TO
                                                  * aspectRatio));
                dimension.setHeight(LONGEST_SIDE_DOWNSAMPLE_TO);
                }
        }
    if (dimension.getHeight() == 0) {
        dimension.setHeight(originalHeight);
        }
    if (dimension.getWidth() == 0) {
        dimension.setWidth(originalWidth);
        }
return dimension;
}


/**
 * @param sourceImage original image in the form of byte array.
 * @return downscaled image in the form of byte array.
 * @throws StockException if original image is bigger than 23000 pixels.
 * @throws IOException if file is not read/write properly.
 */

 static byte[] downSampleImageUtil(final byte[] sourceImage)
        throws IOException, StockException {
    if (sourceImage == null) {
        throw new StockException("Image cannot be null");
    }
    ByteArrayOutputStream byteArrayOutputStream = null;
    try {
        InputStream stream = new ByteArrayInputStream(sourceImage);
        BufferedImage src = ImageIO.read(stream);
        int width         = src.getWidth();
        int height        = src.getHeight();
        Dimension dimension = calculateResizeParameters(width, height);
        Image img = src.getScaledInstance(dimension.getWidth(),
                dimension.getHeight(), Image.SCALE_SMOOTH);
        width = img.getWidth(null);
        height = img.getHeight(null);
        BufferedImage buffered = toBufferedImage(img);
        byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(buffered, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInBytes = byteArrayOutputStream.toByteArray();
        return imageInBytes;
    } catch (IOException e) {
        throw new StockException("Could not downsample the given image");
    } finally {
        if (byteArrayOutputStream != null) {
            byteArrayOutputStream.close();
        }
    }
}

/**
 * Converts a given Image into a BufferedImage.
 *
 * @param img The Image to be converted
 * @return The converted BufferedImage
 */
private static BufferedImage toBufferedImage(final Image img) {
    BufferedImage bimage = new BufferedImage(img.getWidth(null),
            img.getHeight(null), BufferedImage.TYPE_INT_RGB);

    // Draw the image on to the buffered image
    Graphics2D g2 = bimage.createGraphics();
    boolean x = false;
    while (!x) {
        x = g2.drawImage(img, 0, 0, null);
    }
    g2.dispose();

    // Return the buffered image
    return bimage;
}
}

