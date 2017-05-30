package com.adobe.stock.client;

import com.adobe.stock.apis.SearchFiles;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.enums.AssetTemplateCategory;
import com.adobe.stock.enums.AssetTemplatesType;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.SearchParameters;

public class AdobeStockClient {

    public static void test() {

        try {
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0")
                    .setTargetEnvironment(Environment.STAGE);
            AssetTemplateCategory[] cats = {
                    AssetTemplateCategory.FILM,
                    AssetTemplateCategory.PHOTO };
            AssetTemplatesType[] templateIds = {AssetTemplatesType.PSDT};
            ResultColumn[] columns = { ResultColumn.STOCK_ID,
                    ResultColumn.MEDIA_TYPE_ID, ResultColumn.NB_RESULTS,
                    ResultColumn.WIDTH, ResultColumn.COUNTRY_NAME , ResultColumn.TEMPLATE_TYPE_ID};

            SearchParameters params = new SearchParameters()
                    .setWords("color").setLimit(10).setOffset(10).setFilterContentTypeTemplate(true);
            SearchFilesRequest request = new SearchFilesRequest().setSearchParams(params)
                    .setResultColumns(columns);
            SearchFiles searchFile = new SearchFiles(config, null, request);
            SearchFilesResponse response = searchFile.getNextResponse();
            System.out.println(response.getNbResults());
            System.out.println(response.getFiles().get(0).getStockId());
            System.out.println(response.getFiles().get(0).getAssetTypeId());
            System.out.println(response.getFiles().get(0).getWidth());
            System.out.println(response.getFiles().get(0).getCountryName());
            System.out.println(response.getFiles().get(0).getTemplateTypeId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
