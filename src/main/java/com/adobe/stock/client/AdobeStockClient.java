package com.adobe.stock.client;

import java.util.ArrayList;

import com.adobe.stock.apis.SearchCategory;
import com.adobe.stock.apis.SearchFiles;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.SearchParameters;
import com.adobe.stock.models.StockFileCategory;

public class AdobeStockClient {

    public static void testSearchFiles() {

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
            e.printStackTrace();
        }
    }

    public static void testSearchCategory() {
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
            e.printStackTrace();
        }
    }

    public static void print(String key, Object val) {
        System.out.println(key + " : " + val.toString());
    }

    public static void main(String[] args) {
        testSearchFiles();
        testSearchCategory();
    }
}
