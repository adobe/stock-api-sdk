package com.adobe.stock.client;

import java.util.ArrayList;

import com.adobe.stock.apis.License;
import com.adobe.stock.apis.SearchCategory;
import com.adobe.stock.apis.SearchFiles;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseReference;
import com.adobe.stock.models.LicenseRequest;
import com.adobe.stock.models.LicenseResponse;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.SearchParameters;
import com.adobe.stock.models.StockFileCategory;

public class AdobeStockClient {

    public static void testSearchFiles() throws StockException {

        try {
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0")
                    .setTargetEnvironment(Environment.STAGE)
                    .setProductLocation("Libraries/1.0.0 ");
            ResultColumn[] columns = { ResultColumn.STOCK_ID,
                    ResultColumn.MEDIA_TYPE_ID, ResultColumn.NB_RESULTS,
                    ResultColumn.WIDTH, ResultColumn.COUNTRY_NAME };
            SearchParameters params = new SearchParameters().setWords("tree")
                    .setLimit(10).setOffset(10);
            SearchFilesRequest request = new SearchFilesRequest()
                    .setSearchParams(params).setResultColumns(columns);
            SearchFiles searchFile = new SearchFiles(config, null, request);
            SearchFilesResponse response = searchFile.getNextResponse();
            System.out.println("Search Files Response:");
            print("total results", response.getNbResults());
            print("stock id", response.getFiles().get(0).getStockId());
            print("asset id", response.getFiles().get(0).getAssetTypeId());
            print("width", response.getFiles().get(0).getWidth());
            print("country", response.getFiles().get(0).getCountryName());
            System.out.println("");
        } catch (Exception e) {
            throw new StockException("error in search files");
        }
    }

    public static void testSearchCategory() throws StockException{
        try {
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            SearchCategoryRequest request = new SearchCategoryRequest()
                    .setCategoryId(1043);
            SearchCategory searchCategory = new SearchCategory(config);
            StockFileCategory cat = searchCategory.getCategory(request);
            System.out.println("Category Response:");
            print("name", cat.getName());
            print("link", cat.getLink());
            print("id", cat.getId());
            System.out.println("");

            ArrayList<StockFileCategory> catTree = searchCategory
                    .getCategoryTree(request);
            System.out.println("\nCategoryTree Response:");
            print("name", catTree.get(0).getName());
            print("link", catTree.get(0).getLink());
            print("id", catTree.get(0).getId());
        } catch (Exception e) {
            throw new StockException("error in search category");
        }
    }

    public static void testContentInfo() throws StockException{
        try {
            String accessToken = "eyJ4NXUiOiJpbXNfbmExLXN0ZzEta2V5LTEuY2VyIiwiYWxnIjoiUlMyNTYifQ.eyJmZyI6IlJRUTJGVENGVjM3RVA3VFRWUlMzTUdZQUxBPT09PT09IiwiYyI6IlFCMXE2VldpQTNUYXhXUUVvMFdoblE9PSIsIm1vaSI6IjY0MTVhOWQwIiwiY3JlYXRlZF9hdCI6IjE0OTc3MDM4MTk3ODQiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50X2lkIjoiQWRvYmVTdG9ja0NsaWVudDIiLCJzaWQiOiIxNDk3NzAzODAyODMxLTU2NDA5NmVmLTNkYWQtNGIxNC04YWZiLTljZjFkYThkNzUyZSIsImFzIjoiaW1zLW5hMS1zdGcxIiwidXNlcl9pZCI6IjNDMkQyQkFDNTkzNjUwREEwQTQ5NDEzM0BBZG9iZUlEIiwic2NvcGUiOiJBZG9iZUlELG9wZW5pZCxjcmVhdGl2ZV9jbG91ZCxjcmVhdGl2ZV9zZGssY2NfcHJpdmF0ZSxnbmF2LHNhby5zdG9jayxhZGRpdGlvbmFsX2luZm8uYWRkcmVzcy5tYWlsX3RvLGFkZGl0aW9uYWxfaW5mby5kb2IscmVhZF9vcmdhbml6YXRpb25zLHJlYWRfcGMuc3RvY2sscmVhZF9wYy5zdG9ja19jcmVkaXRzLGFkZGl0aW9uYWxfaW5mby5yb2xlcyxzYW8uY2NlX3ByaXZhdGUiLCJpZCI6IjE0OTc3MDM4MTk3ODQtYmFhNTg2OGItMGRmYi00NTc5LWFjNjYtMmJlMTkxNTUzZmQ4Iiwic3RhdGUiOiJ7XCJhY1wiOlwic3RvY2suYWRvYmUuY29tXCIsXCJhdlwiOm51bGwsXCJkaVwiOm51bGwsXCJtY1wiOm51bGwsXCJwbFwiOm51bGx9IiwiZXhwaXJlc19pbiI6Ijg2NDAwMDAwIn0.Hy3GNq1MG6YjRl5YTg5lDHp0CcFbgWRH9nk5YHUUyQGAr0S4YMh0A-7xb4RnlIAhowauxNSrpBc1Ci4H-1zKdmUfTZCRDHOAaOs-MH8nUt4xSpSpfRmYMiQmbwQF_kBQKYiHbwD6cPQ_m9FEOnUHWEXPL4uk7mVYoVZF3m8QEClcAu87EA9BIMGhHpZZBpzElPmGnNxmqow8hCKK0a-e5d4CtKHF4abG-JKtoILTzzYbbq-fx2QszQjCCKeV82djPvixUaoyRGgF5HNAmo54WmC6iYxXzwAb5orqF8RKy1XOcls1ZgPNdaXf5VZPNO3zmOUQxR3_x8qVGxrwVBcrCw";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201);
            License license = new License(config);
            LicenseResponse licenseResponse = license.getContentInfo(request,accessToken);
            System.out.println("Content Info Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }

    public static void testContentLicensePost() throws StockException{
        try {
            String accessToken = "eyJ4NXUiOiJpbXNfbmExLXN0ZzEta2V5LTEuY2VyIiwiYWxnIjoiUlMyNTYifQ.eyJmZyI6IlJRWTVaNlI0NzM3VVA3VFRWUlMzTUdaT0g0PT09PT09IiwiYXMiOiJpbXMtbmExLXN0ZzEiLCJjIjoicFpCc0IwWTZhMDA1U2MxcmNKckkrUT09IiwidXNlcl9pZCI6IjI3OUU1ODFGNTk0M0NDREIwQTQ5NDEwQ0BBZG9iZUlEIiwibW9pIjoiYTE4NDhmNDQiLCJzY29wZSI6IkFkb2JlSUQsb3BlbmlkLGNyZWF0aXZlX2Nsb3VkLGNyZWF0aXZlX3NkayxjY19wcml2YXRlLGduYXYsc2FvLnN0b2NrLGFkZGl0aW9uYWxfaW5mby5hZGRyZXNzLm1haWxfdG8sYWRkaXRpb25hbF9pbmZvLmRvYixyZWFkX29yZ2FuaXphdGlvbnMscmVhZF9wYy5zdG9jayxyZWFkX3BjLnN0b2NrX2NyZWRpdHMsYWRkaXRpb25hbF9pbmZvLnJvbGVzLHNhby5jY2VfcHJpdmF0ZSIsImNyZWF0ZWRfYXQiOiIxNDk3OTUzMDc0OTkyIiwiaWQiOiIxNDk3OTUzMDc0OTkyLTJiMzZiMDc3LTRmNWUtNDQ1MC1iOWE3LTcyYmMyY2ZmNzI2NSIsInR5cGUiOiJhY2Nlc3NfdG9rZW4iLCJleHBpcmVzX2luIjoiODY0MDAwMDAiLCJjbGllbnRfaWQiOiJBZG9iZVN0b2NrQ2xpZW50MiIsInNpZCI6IjE0OTc2MTU2NzYwODYtZThiZjUyMzgtZmRiMC00YTQ4LTkwMDYtNTI2OTEyMDI0MTJjIn0.W-VvWxTa49xcAZkBE0jpUuhDgElaUl0mDHKhQ9LRiA4RGwjtmDxgquEEBM6E2xZ4u9JdPnmoDUFIjqcD1lwLEPeP24QfYMcdVxgHHzo-Mbn5p0csI3Rbw5W2WaX_XUs5nn6qjSnC065ys4sqoGazm2-xIwYW2A8vr0zyg4q2FS2TEyWi7XO_p3eiEYAls663bzFutSTybE8Pk2TAZu_2PbiW6J9GJnivjQzE4PqHGzvMItaSRey7Z9lkN0BVgMT1Jd9BQrLoWghlIIqijjNxrIz-fByfP7LNNRm1wKWu1VwBwFTjJOkcAIdN535LX7dnT5ZktSW9hoMezWJ1d44qKw";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseReference ref = new LicenseReference();
            ref.setLicenseReferenceId(1).setLicenseReferenceValue("Trees");
            LicenseReference refArray[] = {ref};
            LicenseRequest request = new LicenseRequest().setContentId(84071201)
                    .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                    .setPurchaseState(AssetPurchaseState.PURCHASED);
            License license = new License(config);
            LicenseResponse licenseResponse = license.getContentLicense(request,accessToken);
            System.out.println("Content Info Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }
    
    public static void testMemberInfo() throws StockException{
        try{
            String accessToken = "eyJ4NXUiOiJpbXNfbmExLXN0ZzEta2V5LTEuY2VyIiwiYWxnIjoiUlMyNTYifQ.eyJmZyI6IlJRM1NWNlI0NDNURVA1UlQ1UVMzT0dZQVc0PT09PT09IiwiYyI6IlZIeDBWemdwcmxLdEoyS01qb1AvbFE9PSIsIm1vaSI6ImFiMjQyOGM3IiwiY3JlYXRlZF9hdCI6IjE0OTgwMzQ1NTg3OTIiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50X2lkIjoiQWRvYmVTdG9ja0NsaWVudDIiLCJzaWQiOiIxNDk4MDM0NTA4MzA2LTIzMDBkODE2LTg3ZmEtNGY4YS1hNjI0LTAxNzFiMWUzMzllZSIsImFzIjoiaW1zLW5hMS1zdGcxIiwidXNlcl9pZCI6IjRFRDM5OTQ2NTkzNjg2RDcwQTQ5NDIyMUBBZG9iZUlEIiwic2NvcGUiOiJBZG9iZUlELG9wZW5pZCxjcmVhdGl2ZV9jbG91ZCxjcmVhdGl2ZV9zZGssY2NfcHJpdmF0ZSxnbmF2LHNhby5zdG9jayxhZGRpdGlvbmFsX2luZm8uYWRkcmVzcy5tYWlsX3RvLGFkZGl0aW9uYWxfaW5mby5kb2IscmVhZF9vcmdhbml6YXRpb25zLHJlYWRfcGMuc3RvY2sscmVhZF9wYy5zdG9ja19jcmVkaXRzLGFkZGl0aW9uYWxfaW5mby5yb2xlcyxzYW8uY2NlX3ByaXZhdGUiLCJpZCI6IjE0OTgwMzQ1NTg3OTItNDc3ZDk4YTItYmU5NC00MjMwLWFkZWYtYmNhNWVkMmNmZWI0Iiwic3RhdGUiOiJ7XCJhY1wiOlwic3RvY2suYWRvYmUuY29tXCIsXCJhdlwiOm51bGwsXCJkaVwiOm51bGwsXCJtY1wiOm51bGwsXCJwbFwiOm51bGx9IiwiZXhwaXJlc19pbiI6Ijg2NDAwMDAwIn0.Tf6m1Xlvv-o72CPt_QVMbrNOvStdgPMcNaHbSrkQ-nAiC0X7XxDGQsn-7E8Di_bc6KFa7I3Fy3VY9DLzYbk8dMo_cntVZZwJJhVMsiv5eAGJitYK27TGRpmRS9bN3omaxTlRFVWfxKWmeW70r-5FqB6lJJ4JjTVW0HKhkDzO3cLqSqUbtl7vajbe9C4W1OHN9nxgrG01r6CDwBii8VBfuUYkVzHO7Mc5GncOZ7HvUtKoPcDarT8P6l1-UNjwfVYfd94f4kyYWZi9ha8pAVpLCwEnYut5JmOOdyyCP7xO_w2mtasQJZPecmSAwBqiWRnm4a1Plrp1389n5IpMSekhSQ";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
            License license = new License(config);
            LicenseResponse response = license.getMemberProfile(request, accessToken);
            System.out.println("Member Info Response:");
            print("Entitlement Quota:",response.getEntitlement().getQuota() );
            print("Purchase Options Message", response.getPurchaseOptions().getMessage());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }
    
    public static void testDownloadAsset() throws StockException{
        try{
            String accessToken = "eyJ4NXUiOiJpbXNfbmExLXN0ZzEta2V5LTEuY2VyIiwiYWxnIjoiUlMyNTYifQ.eyJpZCI6IjE1MDkwODc0Mjg4NjVfZDUxZjg3MzQtMWNkZi00ZmQzLTljYTEtOWYyMWNiMWIyYjFhX3VlMSIsImNsaWVudF9pZCI6IkFkb2JlU3RvY2tDbGllbnQyIiwidXNlcl9pZCI6IjExNDAzNjRENTk4RDc5RkIwQTQ5NDAyRUBBZG9iZUlEIiwic3RhdGUiOiJ7XCJhY1wiOlwic3RvY2suYWRvYmUuY29tXCIsXCJhdlwiOm51bGwsXCJkaVwiOm51bGwsXCJtY1wiOm51bGwsXCJwbFwiOm51bGx9IiwidHlwZSI6ImFjY2Vzc190b2tlbiIsImFzIjoiaW1zLW5hMS1zdGcxIiwiZmciOiJSNERNRDZSNFM3VE9QNTVENVVDSkdDWUFLRT09PT09PSIsIm1vaSI6IjUzMTFkNzVlIiwiYyI6IkdyWkJKbFViTnN4RXJPTXNxS2lJNGc9PSIsImV4cGlyZXNfaW4iOiI4NjQwMDAwMCIsInNjb3BlIjoiQWRvYmVJRCxvcGVuaWQsY3JlYXRpdmVfY2xvdWQsY3JlYXRpdmVfc2RrLGNjX3ByaXZhdGUsZ25hdixzYW8uc3RvY2ssYWRkaXRpb25hbF9pbmZvLmFkZHJlc3MubWFpbF90byxhZGRpdGlvbmFsX2luZm8uZG9iLHJlYWRfb3JnYW5pemF0aW9ucyxyZWFkX3BjLnN0b2NrLHJlYWRfcGMuc3RvY2tfY3JlZGl0cyxhZGRpdGlvbmFsX2luZm8ucm9sZXMsc2FvLmNjZV9wcml2YXRlIiwiY3JlYXRlZF9hdCI6IjE1MDkwODc0Mjg4NjUifQ.Ft9tDgzblrAbGxnYG0hxCKh1oAz1L_KpFNNNgIxSdezSyP_h59W2VAwH-vcGBJG9Shs0p7xAOux99NNQ6xvhoeXr82yCLcaA1AHR_TpSEvbAMg2zOqC5kSmEbofjKTeaxlKwJq0oUoqsGFWt9e5__yW65Sn5EipHFXsvdkLeV99bxM76thhlq5UmpJtlhc4JSqQmlQCz1o55E1ZAzj_7yxfVaoN7E8zFapm5jg1wrbaXB-zxXQQY4cOVPvhfYMDMHS3oeNt8F5EVW8b_QFy8sFSvI7FWN04Mnu0cbvCT9HvMSeXcVDZjivLRsCXmedCvERqlb_BAsxgGRiiQSO_tCQ";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(117425772).setLicenseState(AssetLicenseState.STANDARD);
            License license = new License(config);
            String assetUrl = license.downloadAsset(request, accessToken);
            System.out.println("Asset Download Response:");
            print("Asset URL", assetUrl);
        } catch (Exception e) {
            throw new StockException("error in downloading");
        }
    }
    public static void print(String key, Object val) {
        System.out.println(key + " : " + val.toString());
    }
    public static void main(String[] args) throws StockException {
        testDownloadAsset();
    }
}
