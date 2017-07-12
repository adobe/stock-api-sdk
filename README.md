# Stock API Java SDK

## Overview
This is a Java implementation of the various APIs provided by the Adobe Stock services.


## Getting Started
This is a maven project. Following steps are needed for getting started with this project:
### Clean
```
mvn clean
```

### Build
To build the project, you can run the below command on console -
```
mvn package
```
The packages jar can be found at `target/` folder with the name `stockapissdk-<version>.jar`. This will also download the depenedent jars into `target/libs/` folder.

The above command will by default run the linting, test cases and the code coverage along with building the project. The linting results will be shown within the console output itself and if there are any issues the build will stop and fail instantly. If there are no linting issues found, the build will continue to build the project. It will also generate the detailed test case and coverage reports for you.
* The test reports can be found at `<project directory>/target/surefire-reports/index.html`
* The coverage report can be found at `<project directory>/target/jacoco/index.html`
Note - Since the test and coverage steps come later in the build process than linting, so if linting fails, you won't get the test cases and coverage reports.

While running the above command if clean project is not run, it may throw some random errors. So it is advisable to run below command which does cleaning as well as building in the same step -
```
mvn clean package
```

### Lint with Checkstyle
This project uses Checkstyle for linting. By default, checkstyle offers two global check configurations -
* [Sun Code Conventions](http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
* [Google Code Styles](http://checkstyle.sourceforge.net/reports/google-java-style-20170228.html)

We are using Sun Code Conventions here.

Linting checks are enforced with the build step itself. By default, the linting will run first and if there are any issues the build will fail. However, if you want to run the checkstyle checks separately, you can run below command to get the checkstyle results in console -
```
mvn checkstyle:check
```

You can also generate the reports of the checkstyle results. For the reports, please run the regular site command -
```
mvn site
```

### Running Test Suites
However, the building project will run the tests automatically but if you need to run the tests separately, please run the below command in console -
```
mvn test
```
### Coverage
Just run the build project as mentioned above, it should generate the coverage reports along with the test results and building the project. As mentioned above coverage reports can be found at `<project directory>/target/jacoco/index.html`

### Generate Maven Project Reports
To generate the maven project report, please run the below command -
```
mvn site
```

For now, it should generate the Dependencies report and the checkstyle results reports for you.

## Integration Guide
To start using Java SDK, you need to add `stockapissdk-[version].jar` into your project build path. You can download the source code and compile locally with maven command as mentioned in the Getting Started section above to generate the latest `stockapissdk-[version].jar`. (Please install [maven](https://maven.apache.org/index.html) if it's not already there.)

When you build the project, you will find the dependent jars in the `target/libs/` folder. If these are not already in your environment, you'll need to add them to your build path. Below is the list of dependent jars -
* `cloning-[version].jar`
* `commons-codec-[version].jar`
* `commons-logging-[version].jar`
* `httpclient-[version].jar`
* `httpcore-[version].jar`
* `httpmime-[version].jar`
* `jackson-annotations-[version].jar`
* `jackson-core-[version].jar`
* `jackson-databind-[version].jar`
* `json-[version].jar`
* `objenesis-[version].jar`

## Usage
### StockConfig
`StockConfig` maintains the configuration of stock apis. In order to use the Stock APIs, one must create the object of `StockConfig`.

#### Methods
* `StockConfig` class allows you to -
    * `setApiKey` - Sets api key configuration which is used to header x-api-key while hitting the Stock API. It will throw StockException if tried to set the null value. The input argument must be a valid api key.
    * `setProduct` - Sets product configuration which is used to header x-product while hitting the Stock API. It will throw StockException if tried to set the null value. The input argument must be a valid product name.
    * `setTargetEnvironment` - Sets the stack of Stock Api endpoints to be used. It is optional if not passed Stage stack is set by default.
    * `setProductLocation` - Sets location name configuration within product which is used to set x-product-location header while hitting the Stock API. This is an optional header.

#### Example
Below is the sample how you can instantiate the StockConfig and initialize it -
```

StockConfig config = new StockConfig().setApiKey("TestApiKey").setProduct("TestProduct").setProductLocation("Libraries/1.0.0 ");

```

### Accessing SearchFiles
#### SearchFiles
`SearchFiles` api class will allow you to access the Search/Files Stock Api. You can query Adobe Stock for assets that meet your specified search criteria. You can construct the `SearchFilesRequest` object to set filters, sort order, set search keywords etc. for the Search/Files api.

The `SearchFiles` provides paginated interface which allows you to call its methods (for e.g. `getNextResponse`, `getPreviousResponse` etc.) multiple times to retrieve the subsequent search results in order. It maintains the current state of searchFiles request and initially, the state is pointing invalid search files results. As soon as, the `getNextReponse` method is called, it makes Search/Files api call and returns the results with `SearchFilesReponse` object. The `getNextResponse` moves the state to next page and fetch the response for the same. Similarly, the `getPreviousResponse` and `getResponsePage` methods can be used to move one page behind and skip to a particular search page index respectively.

##### Instantiation
You can construct the object of this class with below arguments -
* Requires:
    * `config` - the stock configuration object of `StockConfig` type.
    * `accessToken` - the adobe ims user access token. It must be a valid access token if is_licensed result column is requested with the results. Otherwise, it can be null.
    * `request` - the request object of `SearchFilesRequest` consisting the locale, results column, search parameters etc.

* Returns:
    * The response object (`SearchFilesResponse`) containing the search files api results matching the request object.

##### Example
Sample code to instantiate the SearchFiles Api -

``` Java
public static void main(String[] args) {
    try {
        //List of result columns
        ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                ResultColumn.ID, ResultColumn.TITLE };

        //Instantiating and initializing StockConfig
        StockConfig config = new StockConfig().setApiKey("StockClientApiKey").setProduct(
                "Stock Client/1.0.0");

        //Constructing SearchParameters
        SearchParameters params = new SearchParameters()
                        .setWords("Tree House")
                        .setFilterOrientation(AssetOrientation.HORIZONTAL);

        //Constructing SearchFilesRequest
        SearchFilesRequest searchRequest = new SearchFilesRequest()
                            .setSearchParams(params)
                            .setResultColumns(resultColumns);

        //Getting hold of SearchFiles API object
        SearchFiles searchFiles = new SearchFiles(config, accessToken, searchRequest);

        //Now, you can call getNextResponse to get the search results
        SearchFilesResponse response = searchFiles.getNextReponse();

    } catch (StockException e) {
        e.printStackTrace();
    }
}

```
More examples can be found at the end of this document.

##### Methods
* `SearchFiles` Methods can throw StockException if there are no search results available. It allows you to -
    * `getNextResponse` - Method to get next search files response page. It moves the state to next page and fetch the searchFiles response for the same. If the api returns with error or if there are no more search results available for the search request, the method will throw the StockException.
    * `getPreviousResponse` -  Method to get previous search files response page. It moves the state to previous page and fetch the searchFiles response for the same. If the api returns with error or if there are no more search results available for the search request or the state is pointing to invalid state, the method will throw the StockException.
    * `getResponsePage` - Method to skip to a specific search files response page. It moves the state to provided search page and fetch the searchFiles response for the same. It will throw StockException if there is any failure while searchFiles api or if the provided search page index is out of total search pages available.
    * `getLastResponse` -  Get the response object of recently performed searchFiles api call either by using `getNextReponse` or `getPreviousResponse` or `getResponsePage`. Initially, this method will return null since it is pointing to invalid state and no response available at this point.
    * `currentSearchPageIndex` - Get the current search page index of searchFiles response available from recently performed `getNextReponse` or `getPreviousResponse` or `getResponsePage` method. Initially, since the state is pointing to invalid state, it returns -1.
    * `totalSearchPages` - Get the total number of search pages available from recently performed `getNextReponse` or `getPreviousResponse` or `getResponsePage` method. Initially, since the state is pointing to invalid state, it returns -1.
    * `totalSearchFiles` - Get the total number of search files available from recently performed `getNextReponse` or `getPreviousResponse` or `getResponsePage` method. Initially, since the state is pointing to invalid state, it returns -1.


#### SearchFilesRequest
In order to make SearchFiles API call, you need to create a SearchFileRequest object to define the search criterion for search files results. You can set the various search parameters, locale and required result columns supported by Stock Search/Files api here.

Here is the mapping of Search/Files api query parameters with the setters methods that you can use to set the corresponding parameters in Java Stock SDK -

|API URL Query Parameter| Setter Methods in SearchFilesRequest |Description|
|---|---|---|
|locale|setLocale|Sets location language code. For e.g. "en-US", "fr-FR" etc.|
|search_parameters[*]|setSearchParams|Sets An object of `SearchParameters` where one can set all supported search_parameters|
|similar_image|setSimilarImage| Sets an image data for visual similarity search. It will only be considered if similar image in `SearchParameters` is set to true. |
|result_columns[]| setResultColumns | Allows to set the list of result columns required in the search results. If you are not setting result columns, it will set all default columns in result_column array at api level. For more details, read Result Columns section below.|

#### SearchParameters
`SearchParameters` allows to set the various search_parameters (URL query parameters) supported by Search/Files Stock api. This is the class where you can actually set the search keywords, limit, sort order, filters, media_id etc.

Mapping of query parameter search_parameters[*] with SearchParameters class setter methods -

|Search Parameter| Setter Methods | Related Enums (If applicable)    |Description|
|---|---|---|---|
|search_parameters[words]|setWords|    |Allows to set the key words that you want to search|
|search_parameters[limit]|setLimit|        |Allows to set maximum number of assets to return in the call.|
|search_parameters[offset]|setOffset|        |Allows to set the start position in search results. |
|search_parameters[order]|setOrder|    AssetsOrder|Allows to set sorting order in which it will return found assets|
|search_parameters[creator_id]|setCreatorId|    |Allows to search by a specific asset creator's ID|
|search_parameters[media_id]|setMediaId|     |Allows to search for one specific asset by its unique identifier (media_id)|
|search_parameters[model_id]|setModelId|    |Allows to search for assets that portray a specific person (model) using the model's ID|
|search_parameters[serie_id]|setSerieId|    |Allows to search for assets in the specified series using the series ID|
|search_parameters[gallery_id]|setGalleryId|    |Allows to search with a specific galleryId filter|
|search_parameters[similar]|setSimilar|     |Allows to search for assets that are similar in appearance to an asset with a specific media ID|
|search_parameters[similar_url]|setSimilarURL|    |Allows to search for assets that are similar in appearance to an image at a specific URL|
|search_parameters[category]|setCategory|    |Allows to search for assets with a specific category ID|
|search_parameters[thumbnail_size]|setThumbnailSize|    AssetThumbSize|Allows to set the size of thumbnail(in pixels) to return for each found asset|
|search_parameters[filters][area_pixels]|setFilterAreaPixels|    |Allows to set image sizes in pixels for returned assets|
|search_parameters[filters][3d_type_id][]|setFilter3DTypeIds|Asset3DType[]|Allows to set array specifying which 3D types to return|
|search_parameters[filters][template_type_id][]|setFilterTemplateTypes | AssetTemplatesType[] | Allows to set array specifying which template types to return|
|search_parameters[filters][template_category_id][]|setFilterTemplateCategoryIds|    |Allows to set array specifying which template categories to return|
|search_parameters[filters][has_releases]|setFilterHasReleases|AssetHasReleases|Allows to return only that assets which has model or property releases|
|search_parameters[filters][content_type:photo]|setFilterContentTypePhoto|    |Allows to include found assets that are photos|
|search_parameters[filters][content_type:illustration]|setFilterContentTypeIllustration|    |Allows to include found assets that are illustrations|
|search_parameters[filters][content_type:vector]|setFilterContentTypeVector|    |Allows to include found assets that are vectors|
|search_parameters[filters][content_type:video]|setFilterContentTypeVideo|    |Allows to include found assets that are videos|
|search_parameters[filters][content_type:3d]|setFilterContentType3D|    |Allows to include found assets that are 3D items|
|search_parameters[similar_image]|setSimilarImage|    |Allows to set whether to use similar_image data for visual similarity search|
|search_parameters[filters][content_type:all]|setFilterContentTypeAll|    |Allows to include found assets of all content_types|
|search_parameters[filters][offensive:2]|setFilterOffensive2|    |Allows to return found assets only if they are flagged as including Explicit/Nudity/Violence|
|search_parameters[filters][isolated:on]|setFilterIsolatedOn|    |Allows to return found assets only if the subject is isolated from the background by being on a uniformly colored background|
|search_parameters[filters][panoramic:on]|setFilterPanoromicOn|    |Allows to return found assets only if they are panoramic|
|search_parameters[filters][orientation]|setFilterOrientation| AssetOrientation    |Allows to return found assets of the specified orientation|
|search_parameters[filters][age]|setFilterAge|AssetAge|Allows to return found assets of the specified age|
|search_parameters[filters][video_duration]|setFilterVideoDuration|AssetVideoDuration|Alows to return videos whose duration is no longer than the specified duration in seconds|
|search_parameters[filters][Premium]|setPremium|AssetPremiumCategory|Allows to return found assets with premium (pricing) level|
|search_parameters[filters][colors]|setFilterColors|    |Allows to return only found assets that contain the specified colors|
|search_parameters[filters][Editorial]|setFilterEditorial|    |Allows to return only found assets that are editorial|
|search_parameters[filters][content_type:template]|setFilterContentTypeTemplate|    |Allows to include found assets that are of template types|

#### Result Columns
You can create array of ResultColumn enums to define columns that you want to include in your search results.

##### Example
```
ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS, ResultColumn.ID, ResultColumn.TITLE };
```
##### Note
If you are not setting result columns, it will set following columns in result_column array by default.
* Default Result Columns -
    * `NB_RESULTS`
    * `ID`
    * `TITLE`
    * `CREATOR_NAME`
    * `CREATOR_ID`
    * `WIDTH`
    * `HEIGHT`
    * `THUMBNAIL_URL`
    * `THUMBNAIL_HTML_TAG`
    * `THUMBNAIL_WIDTH`
    * `THUMBNAIL_HEIGHT`
    * `MEDIA_TYPE_ID`
    * `CATEGORY`
    * `CATEGORY_HIERARCHY`
    * `VECTOR_TYPE`
    * `CONTENT_TYPE`
    * `PREMIUM_LEVEL_ID`
#### SearchFilesResponse
It represents the search results returned with Stock Search/Files API. The `SearchFiles` class methods for e.g. `getNextResponse` returns the object of `SearchFilesResponse` initialized with the results returned from the Search/Files api.
SearchFilesResponse allows you to -
* `getNbResults` - Get the value of 'nb_results' column from the search response
* `getFiles` - Get the list of `StockFile` returned by search files api

#### Making a SearchFilesRequest and Calling search files api
These are the complete examples showing how a search request is created and then search api is called, which in turn returns search results in the form of serchFileRequest.
* Example to get search results by calling getNextResponse method:

``` Java
public static void main(String[] args) {
    try {
        //List of result columns
        ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                ResultColumn.ID, ResultColumn.TITLE };

        //Instantiating and initializing StockConfig
        StockConfig config = new StockConfig().setApiKey("StockClientApiKey").setProduct(
                "Stock Client/1.0.0");

        //Constructing SearchParameters
        SearchParameters params = new SearchParameters()
                        .setWords("Tree House")
                        .setFilterOrientation(AssetOrientation.HORIZONTAL);

        //Constructing SearchFilesRequest
        SearchFilesRequest searchRequest = new SearchFilesRequest()
                            .setSearchParams(params)
                            .setResultColumns(resultColumns);

        //Getting hold of SearchFiles API object
        SearchFiles searchFiles = new SearchFiles(config, accessToken, searchRequest);

        //Now, you can call getNextResponse to get the search results
        SearchFilesResponse response = searchFiles.getNextReponse();

        //Now, you can use these results to access total number of results
        System.out.println(response.getNbResults());

        //or, you can get various  stock files information
        System.out.println(response.getFiles().get(0).getTitle());
        System.out.println(response.getFiles().get(0).getCategory());
        System.out.println(response.getFiles().get(0).getCreatorName());

    } catch (StockException e) {
        e.printStackTrace();
    }
}
```
* Example to get previous search results by calling getPreviousResponse method:

``` Java
public static void main(String[] args) {
    try {
        //List of result columns
        ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS,
                        ResultColumn.ID, ResultColumn.THUMBNAIL_110_URL,
                        ResultColumn.THUMBNAIL_110_WIDTH };

        //Instantiating and initializing StockConfig
        StockConfig config = new StockConfig().setApiKey("StockClientApiKey").setProduct(
                "Stock Client/1.0.0");

        //Constructing SearchParameters
        SearchParameters params = new SearchParameters().setThumbnailSize(AssetThumbSize.BIG)
                        .setFilterContentTypePhoto(true);

        //Constructing SearchFilesRequest
        SearchFilesRequest searchRequest = new SearchFilesRequest()
                            .setSearchParams(params)
                            .setResultColumns(resultColumns);

        //Getting hold of SearchFiles API object
        SearchFiles searchFiles = new SearchFiles(config, accessToken, searchRequest);

        //Now, you can call getNextResponse to get the search results
        SearchFilesResponse response = searchFiles.getNextReponse();

        //Now, you can get various fields of stock files
        System.out.println(response.getFiles().get(0).getThumbnail110Url());

        //Now, you can call getNextResponse to get the next search results
        SearchFilesResponse response = searchFiles.getNextReponse();

        //Perform some operation

        //Now, you can call getPreviousResponse to get the previous search results
        SearchFilesResponse response = searchFiles.getPreviousResponse();

        //Now, you can use previous results for getting title or other info from stock file.
        System.out.println(response.getFiles().get(0).getIsLicensed());

    } catch (StockException e) {
        e.printStackTrace();
    }
}
```
* Example to skip to specific page of results by calling getResponsePage method:

``` Java
public static void main(String[] args) {
    try {
        //List of result columns
        ResultColumn[] resultColumns = { ResultColumn.NB_RESULTS, ResultColumn.KEYWORDS,
                            ResultColumn.HAS_RELEASES, ResultColumn.COMP_URL};

        //Instantiating and initializing StockConfig
        StockConfig config = new StockConfig().setApiKey("StockClientApiKey").setProduct(
                "Stock Client/1.0.0");

        //Constructing SearchParameters
        SearchParameters params = new SearchParameters()
                        .setWords("City")
                        .setFilterContentTypeVector(true)
                        .setFilterHasReleases(AssetHasReleases.TRUE);

        //Constructing SearchFilesRequest
        SearchFilesRequest searchRequest = new SearchFilesRequest()
                            .setSearchParams(params)
                            .setResultColumns(resultColumns);

        //Getting hold of SearchFiles API object
        SearchFiles searchFiles = new SearchFiles(config, accessToken, searchRequest);

        //Now, you can call getResponsePage to skip to a specific search files response page
        SearchFilesResponse response = searchFiles.getResponsePage(2);

        //Now, you can use these results for getting various info from stock file.
        System.out.println(response.getFiles().get(0).getCompUrl());
        System.out.println(response.getFiles().get(0).getHasReleases());


    } catch (StockException e) {
        e.printStackTrace();
    }
}
```
### Accessing SearchCategory
#### SearchCategory
 `SearchCategory` API class allows you to access the Search/Category and Search/CategoryTree Stock APIs. Each stock asset is placed into a category that classifies the asset, such as "Travel" or "Hobbies and Leisure" and each category has a unique identifying number, a name, and a path that you can use to access other assets in the same category.

 You can construct the `SearchCategoryRequest` object to set category identifier and locale information. Then you can call `getCategory` method to get information about a category of stock assets in the form of `StockFileCategory` object. You can also call `getCategoryTree` method to retrieve information for zero or more category identifiers in the form of list of `StockFileCategory` object.

##### Instantiation
You can construct the object of this class with below arguments -
* Requires:
    * `config` - the stock configuration object of `StockConfig` type.

* Returns:
    * `StockFileCategory` - The response object containing the search category API results matching the request object returned by `getCategory` method.
    * `ArrayList<StockFileCategory>` - The list of response object containing the search category tree API results matching the request object returned by `getCategoryTree` method.

##### Example
Sample code to instantiate the SearchCategory API -

``` Java
public static void main(String[] args) {
    try {

        //Instantiating and initializing StockConfig
        StockConfig config = new StockConfig().setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");

        //Constructing SearchCategoryRequest
        SearchCategoryRequest request = new SearchCategoryRequest()
                    .setCategoryId(1043);

        //Getting hold of SearchCategory API object
        SearchCategory searchCategory = new SearchCategory(config);

        //Now you can call getCategory to get category information
        StockFileCategory categoryResponse = searchCategory.getCategory(request);

        //You can also call getCategoryTree to get information about list of categories
        ArrayList<StockFileCategory> categoryTreeResponse = searchCategory
                    .getCategoryTree(request);

    } catch (StockException e) {
        e.printStackTrace();
    }
}

```

##### Methods
* `SearchCategory` Methods can throw StockException if request is not valid or API returns with an error. It allows you to -
    * `getCategory` - Method to get information about a category of Stock assets, such as travel or animals for a specified category identifier, optionally localized. You need to pass `SearchCategoryRequest` object containing category identifier and locale(optional) parameters. If the request object is not valid or API returns with error, the method will throw the StockException.
    * `getCategoryTree` - Method to get category information for zero or more category identifiers.  You need to pass `SearchCategoryRequest` object containing category identifier and locale parameters (both optional). If request object does not contain category identifier, this returns a list of all stock categories. If the request object is not valid or API returns with error, the method will throw the StockException.


#### SearchCategoryRequest
In order to make SearchCategory/SearchCategoryTree API call, you need to create a `SearchCategoryRequest` object to define the search criterion for search category results. You can set category identifier and location language code supported by Stock Search Category/Category Tree API here.

Here is the mapping of Search Category/CategoryTree API query parameters with the setters methods that you can use to set the corresponding parameters in Java Stock SDK -

|API URL Query Parameter| Setter Methods in SearchCategoryRequest |Description|
|---|---|---|
|locale|setLocale|Sets location language code. For e.g. "en-US", "fr-FR" etc.|
|category_id|setCategoryId|Sets unique identifier for an existing category for e.g 1043|

#### StockFileCategory
It represents the search result returned from Stock Search/Category API. The `SearchCategory` class methods for e.g. `getCategory` returns the object of `StockFileCategory` initialized with the results returned from the Search/Category API.
`StockFileCategory` allows you to -
* `getName` - Get localised name of the category returned by search/category API
* `getId` - Get unique identifier of the category returned by search/category API
* `getLink` - Get path of the category returned by search/category API

### Accessing License 
##### License
`License` class allows you to purchase an asset, information about purchasing the asset, information about a user's licensing (entitlement) status, determine whether the user has an existing license for an asset,for notifying the system when a user     abandons a licensing operation, request a license for an asset for that user if user have authorization for licensing assets and fetch the URL of the asset if it is already licensed.

* This is an overview of the process:
    * Call `getContentInfo` to determine whether the asset is already licensed. If not, call `getMemberProfile` to get your user's purchase options.
    * If the user opts to continue with the purchase, call `getContentLicense`.
    * If the user cancels out of the purchase, call `abandonLicense`.
    * Call `downloadAsset` to fetch the URL of the asset if it is already licensed.

* To license Adobe Stock images :
    * Call `SearchFiles` to find an asset that you want to license. 
    * Search returns the `asset's identifier` in the id field.
    * Get an `access token` for the user.  
    * Call various License APIs using params like content id ,license state, purchase state, locale to perform these        operations.

#### Instantiation
You can construct the object of this class with below arguments -
* Requires:
    * `config` - the stock configuration object of `StockConfig` type.

* Returns:
    * `LicenseResponse` - The response object containing the asset content id, purchase details, license state results matching the request object returned by `getContentInfo` , `getContentLicense`, `getMemberProfile`,`abandonLicense` method.
    
#### License Request
 In order to call `License` APIs you need to create `LicenseRequest` object for licensing assets, for getting licensing information about a specific asset for specific user, for notifying the system when a user abandons a licensing operation, for getting the licensing capabilities for a specific user.
 
|Request Parameter| Setter Methods | Related Enums (If applicable)    |Description|
|---|---|---|---|
|content_id|setContentId| |Asset's unique identifer.You can get this from a Search response's id attribute|
|license|setLicenseState|AssetLicenseState|Use only with Content/Info, Content/License, and Member/Profile. The Adobe Stock licensing state for the asset.|
|locale    |setLocale| |Use only with Member/Profile.Optional. Location language code for the API to use when returning localized messages. The API can usually get the user's default locale through the Authorization header. This value overrides that or provides a locale if not available through Authorization.|
|state |setPurchaseState|AssetPurchaseState|Use only with Member/Abandon.The purchase_options.state from the Member/Profile results.|
|cce_agency |setLicenseReference| |Array of license references of type `LicenseReference`. Use only with Content/License API.|
 
##### License State 
Adobe Stock licensing state for the asset.    
* Types of License States : 
    
    * For images, photos, or illustrations you can request:
        * `Standard` - Licenses the full-resolution image
        * `Standard_M` - Licenses a medium-sized image that is approximately 1600x1200 pixels
        * `Extended` - Extended license for the full-resolution image
    * For video you can request:
        * `Video_HD` - Licenses the HD-resolution video
        * `Video_4K` - Licenses the 4K-resolution video  
    * For vector assets: `Standard` or `Extended`
    * For 3D assets:  `Standard`  
    * For templates: `Standard`

##### Purchase States
User's purchase relationship to an asset.
* Various Purchase States :
    * `NOT_PURCHASED` -  User has not at any time in the past purchased the asset.
    * `PURCHASED` - User has at some time in the past purchased the asset.
    * `CANCELLED` - User attempted to buy the asset and for some reason the order did not go through.
    * `NOT_POSSIBLE` - User must go to the Adobe Stock site to buy plan or asset.
    * `JUST_PURCHASED` - User bought asset within the current session.
    * `OVERAGE` - Adobe Stock has a payment instrument on file for the user and can bill the user for additional purchases.
   
##### Creating License Request and Initializing Licensing API
```Java
public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
    
            //Creating License Request
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
            
            //Initialize License Apis  
            License license = new License(config);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
#### License Response
After calling various APIs in `License` class, reponse is returned in the form of `LicenseResponse`. It contains following fields. All class objects used in response are defined below.

|Request Parameter| Getter Methods | Related Class     |Description|
|---|---|---|---|
|available_entitlement |getEntitlement|LicenseEntitlement|Information about licenses available for the user. See LicenseEntitlement|
|purchase_options|getPurchaseOptions|LicensePurchaseOptions|Information about the user's purchasing options for the asset. See LicensePurchaseOptions|
|member|getMemberinfo|LicenseMemberInfo|Information about the user. See LicenseMemberInfo|
|license_references|getLicenseReferences|LicenseReferenceResponse|List of license references of the user. See LicenseReferenceResponse|
|contents|getContentMap|LicenseContent|Mapping from Asset unique identifier to Asset Licensing information. See LicenseContent|

##### LicenseEntitlement
* LicenseEntitlement gives Information about licenses available for the user.
    * `Quota` : Quantity of remaining licenses available for the user.
     * `License Type Id`: Stock Internal ID to know which kind of product can be used for licensing.
    * `Has Credit Model`: true if the selected entitlement is for an organization and this organization is generation 2.
    * `Has Agency Model`: true if the selected entitlement is for an organization and this organization is generation 3.
    * `Is CCE`: true if the selected entitlement for purchasing is one of an organization.
    * `Full Entitlement Quota`: Full quota of the user available entitlements.

##### LicensePurchaseOptions
* Information about the user's purchasing options for the asset.
    * `Purchase State` : User's purchase relationship to an asset.
    * `Requires Checkout` : Whether a purchase in process requires going to the Adobe Stock site for completion.
    * `Message` : Message to display to your user in response to a licensing API query.
    * `PurchaseUrl` : The URL to see purchase options plan.
    
##### LicenseMemberInfo
* Information about the user
    * `StockId` : User's unique Stock member identifier.
    
##### LicenseReferenceResponse
* License references marked as "required" must be submitted when licensing the image using the corresponding "id" attributes.
    * `Id` : License reference id.
    * `Text` : License reference description.
    * `Required` : Whether license reference must be submitted when licensing the image.
    
##### LicenseContent
* Licensing information for an asset for the user contained in the query response.
    * `Content Id` : Asset's unique identifier.
    * `Purchase Details` : Information about the user's purchase/license of this asset.
    * `Size` : The size of the asset, indicating whether it is the free complementary size or the original full-sized asset.
    * `Comp` : Information about the complementary or watermarked asset.
    * `Thumbnail` : Information about the asset thumbnail.

#### Methods
* `License` API allows you to call these four methods related to licensing stock assets. It can throw StockException if response is null or there is some API error.
    * `getContentInfo` requests licensing information about a specific asset for a specific user. You need to pass ims user `accessToken` and  `LicenseRequest` object containing content identifier, license state and locale(optional) parameters. If the request object is not valid or API returns with error, the method will throw the StockException.
    
    * `getContentLicense` requests a license for an asset for a specific user. You need to pass ims user `accessToken` and `LicenseRequest` object containing content_id ,License Reference and license. If the request object is not valid or API returns with error, the method will throw the StockException.
    
    * `getMemberProfile` returns the user's available purchase quota, the member identifier, and information that you can use to present licensing options to the user when the user next requests an asset purchase. In this 3 cases can occur -
        *  User has enough quota to license the next asset.
        *  User doesn't have enough quota and is set up to handle overage.
        *  User doesn't have quota and there is no overage plan.
You need to pass ims user `accessToken` and `LicenseRequest` object containing content_id, license state and locale. If the request object is not valid or API returns with error, the method will throw the StockException.
     
    * `abandonLicense` notifies the system when a user cancels a licensing operation. It can be used if the user refuses the opportunity to purchase or license the requested asset. You need to pass ims user `accessToken` and `LicenseRequest` object containing content_id and license state. If the request object is not valid or API returns with error, the method will throw the StockException.
     
    * `downloadAsset` provides the URL of the asset if it is already licensed otherwise throws StockException showing a message whether user has enough quota and can buy the license or not. You need to pass ims user `accessToken` and `LicenseRequest` object containing content_id and license state. If request is not valid or asset is not licensed or licensing information is not present for the asset or API returns with an error, the method will throw the StockException.

#### Examples
Examples showing how all methods are called with `LicenseRequest` and return `LicenseResponse`.

#### getContentInfo Example
``` Java
public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
    
            //Creating License Request
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
        
            //Initializing License Object to call getContentInfo method
            License license = new License(config);
        
            //Calling getContentInfo and returning LicenseResponse Object
            LicenseResponse licenseResponse = license.getContentInfo(request,accessToken);
        
            System.out.println("Content Info Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   ```
  #### getContentLicense Example
  ``` Java
  public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
    
        
            LicenseReference ref = new LicenseReference();
            ref.setLicenseReferenceId(1).setLicenseReferenceValue("Trees");
            LicenseReference refArray[] = {ref};
        
            //Creating License Request
            LicenseRequest request = new LicenseRequest().setContentId(84071201)
                    .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                    .setPurchaseState(AssetPurchaseState.PURCHASED);
        
            //Initializing License Object to call getContentLicense method
            License license = new License(config);
        
            //Calling getContentLicense and returning LicenseResponse Object
            LicenseResponse licenseResponse = license.getContentLicense(request,accessToken);
        
            System.out.println("Content License Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  ```
#### getMemberProfile Example
``` Java
public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
    
            //Creating License Request
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
        
            //Initializing License Object to call getMemberProfile method
            License license = new License(config);
        
            //Calling getMemberProfile
            LicenseResponse response = license.getMemberProfile(request, accessToken);
        
            System.out.println("Member Profile Response:");
            print("Entitlement Quota:",response.getEntitlement().getQuota() );
            print("Purchase Options Message", response.getPurchaseOptions().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

#### abandonLicense Example
``` Java
public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
    
            //Creating License Request
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
        
            //Initializing License Object to call abandonLicense method
            License license = new License(config);
        
            //Calling getMemberProfile
            license.abandonLicense(request, accessToken);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
#### downloadAsset Example
``` Java
public static void main(String args[]) {
        try {
            //Valid access token for stock files.
            String accessToken = "Ims Token";
        
            //Initialize config
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            
            //Creating License Request
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
            
            //Initializing License Object to call downloadAsset method
            License license = new License(config);
        
            //Calling downloadAsset
            String assetUrl = license.downloadAsset(request, accessToken);
        
            System.out.println("Asset Download Response:");
            print("Asset URL", assetUrl);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
