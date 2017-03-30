# Stock API libjs

## Overview
This is a Javascript implementation of the various APIs provided by the Stock services.

## Usage
### AdobeStock
#### Instantiation
* The `AdobeStock` class requires that:
  * `apiKey` be passed in to set x-api-key header for Stock Api calls
  * `product` be passed in to set x-product header for Stock Api calls
  * `targetEnv` be passed in to determine stack of Stock Api endpoints. It is optional if not passed Stage stack is set by default.

#### Methods
* The `AdobeStock` class allows you to:
  * `ENVIRONMENT` - Get environment constant which is used to defined the stack of Stock Apis endpoints
  * `SEARCH_PARAMS` - Get the different search_parameters which can be used for creating search_parameters object
  * `SEARCH_PARAMS_ORDER` - Get the valid strings for order search parameter
  * `SEARCH_PARAMS_HAS_RELEASES` - Get the valid strings for has_releases filter search parameter
  * `SEARCH_PARAMS_3D_TYPES` - Get the valid values for 3D type filter for the 3D asset
  * `SEARCH_PARAMS_TEMPLATE_CATEGORIES` - Get the valid values for template_category_id array filter search parameter
  * `SEARCH_PARAMS_TEMPLATE_TYPES` - Get the valid values for template_type_id array filter search parameter
  * `SEARCH_PARAMS_THUMB_SIZES` - Get the valid values for thumbnail_size filter search parameter
  * `SEARCH_PARAMS_AGE` - Get the valid string values for age filter search parameter
  * `SEARCH_PARAMS_VIDEO_DURATION` - Get the valid string values for video_duration filter search parameter
  * `RESULT_COLUMNS` - Get the list of result columns supported to be used for passing with `searchFiles` which to be included in the search results
  * `searchFiles` - Get the search files iterator which can be used to iterate over the searchFiles results.
    * Requires:
      * `accessToken` - the accessToken string or can be null if is_licensed result column not requested in the results. (Required)
      * `queryParams` - the object of query parameters. (Required)
      * `resultColumns` - the list of result columns required in search results. (Optional)
    * Returns:
      * Returns object of `SearchFilesIterator` class
    * Example:

      ```
      const accessToken = 'fdkgnio4isoknzklnvw409jknvzksnvai3289r4209tjaornuivn34nivh3jt340fjvn9304jt';
      const queryParams = {
        locale: 'en-US',
        search_parameters: {
          words: 'tree house',
          limit: 10,
          offset: 10,
          filters_template_category_id: [
            AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
            AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
          ],
          filters_area_pixels: '0-2500',
        },
      };
      const stock = new AdobeStock('Stock_Client_Api_key', 'Stock Client/1.0.0', AdobeStock.ENVIRONMENT.STAGE);
      const iterator = stock.searchFiles(accessToken,
                                          queryParams,
                                          null);
      iterator.next().then(() => {
        const response = iterator.getResponse();
        console.log(response.files.length);
      });
      ```

  * `searchFilesByCategory` - It's a variant of searchFiles method. It takes the categoryId, locale, filters as arguments instead of queryParams object. Get the search files iterator which can be used to iterate over the searchFiles results of the provided category.
    * Requires:
      * `accessToken` - the accessToken string or can be null if is_licensed result column not requested in the results. (Required)
      * `categoryId` - the value of stock category id for which searchFiles needs to perform. (Required)
      * `locale` - the locale for query parameter. (Optional)
      * `filters` - the array of filters search parameters. If `filters` contains `category` then `filters.category` will override the `categoryId` passed as a separate argument. (Optional)
      * `resultColumns` - the list of result columns required in search results. (Optional)
    * Returns:
      * Returns object of `SearchFilesIterator` class
    * Example:

      ```
      const accessToken = 'fdkgnio4isoknzklnvw409jknvzksnvai3289r4209tjaornuivn34nivh3jt340fjvn9304jt';
      const filters = {
        filters_template_category_id: [
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
        ],
        filters_area_pixels: '0-2500',
      };
      const resultColumns = [
        AdobeStock.RESULT_COLUMNS.ID,
        AdobeStock.RESULT_COLUMNS.TITLE,
        AdobeStock.RESULT_COLUMNS.NB_RESULTS,
      ];
      const stock = new AdobeStock('Stock_Client_Api_key', 'Stock Client/1.0.0', AdobeStock.ENVIRONMENT.STAGE);
      const iterator = stock.searchFilesByCategory(accessToken,
                                          695, // category
                                          'en-US',
                                          filters,
                                          resultColumns);
      iterator.next().then(() => {
        const response = iterator.getResponse();
        console.log(response.files.length);
      });
      ```

  * `searchSimilarFilesById` - It's a variant of searchFiles method. It takes the mediaId, locale, filters as arguments instead of queryParams object. Get the search files iterator which can be used to iterate over the similar to media id searchFiles results.
    * Requires:
      * `accessToken` - the accessToken string or can be null if is_licensed result column not requested in the results. (Required)
      * `mediaId` - the value of stock media id for which searchFiles needs to perform. (Required)
      * `locale` - the locale for query parameter. (Optional)
      * `filters` - the array of filters search parameters. If `filters` contains `mediaId` then `filters.mediaId` will override the `mediaId` passed as a separate argument. (Optional)
      * `resultColumns` - the list of result columns required in search results. (Optional)
    * Returns:
      * Returns object of `SearchFilesIterator` class
    * Example:

      ```
      const accessToken = 'fdkgnio4isoknzklnvw409jknvzksnvai3289r4209tjaornuivn34nivh3jt340fjvn9304jt';
      const filters = {
        filters_template_category_id: [
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
        ],
        filters_area_pixels: '0-2500',
      };
      const resultColumns = [
        AdobeStock.RESULT_COLUMNS.ID,
        AdobeStock.RESULT_COLUMNS.TITLE,
        AdobeStock.RESULT_COLUMNS.NB_RESULTS,
      ];
      const stock = new AdobeStock('Stock_Client_Api_key', 'Stock Client/1.0.0', AdobeStock.ENVIRONMENT.STAGE);
      const iterator = stock.searchSimilarFilesById(accessToken,
                                          13244222, // media id
                                          'en-US',
                                          filters,
                                          resultColumns);
      iterator.next().then(() => {
        const response = iterator.getResponse();
        console.log(response.files.length);
      });
      ```

  * `searchFilesByKeywords` - It's a variant of searchFiles method. It takes the keywords, locale, filters as arguments instead of queryParams object. Get the search files iterator which can be used to iterate over the searchFiles results for the search keywords provided.
    * Requires:
      * `accessToken` - the accessToken string or can be null if is_licensed result column not requested in the results. (Required)
      * `keywords` - the search keywords for which searchFiles needs to perform. (Required)
      * `locale` - the locale for query parameter. (Optional)
      * `filters` - the array of filters search parameters. If `filters` contains `words` then `filters.words` will override the `keywords` passed as a separate argument. (Optional)
      * `resultColumns` - the list of result columns required in search results. (Optional)
    * Returns:
      * Returns object of `SearchFilesIterator` class
    * Example:

      ```
      const accessToken = 'fdkgnio4isoknzklnvw409jknvzksnvai3289r4209tjaornuivn34nivh3jt340fjvn9304jt';
      const filters = {
        filters_template_category_id: [
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
          AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
        ],
        filters_area_pixels: '0-2500',
      };
      const resultColumns = [
        AdobeStock.RESULT_COLUMNS.ID,
        AdobeStock.RESULT_COLUMNS.TITLE,
        AdobeStock.RESULT_COLUMNS.NB_RESULTS,
      ];
      const stock = new AdobeStock('Stock_Client_Api_key', 'Stock Client/1.0.0', AdobeStock.ENVIRONMENT.STAGE);
      const iterator = stock.searchFilesByKeywords(accessToken,
                                          'tree house', // search keywords
                                          'en-US',
                                          filters,
                                          resultColumns);
      iterator.next().then(() => {
        const response = iterator.getResponse();
        console.log(response.files.length);
      });
      ```

### SearchFilesIterator
  * It maintains the current state of searchFiles response. Initially, the state is pointed before the first searchFiles response. The `next` method moves the state to next page and fetch the response for the same. The `previous` and `skipTo` methods can be used to move one page behind and skip to a particular search page index respectively. Actually, it implements the pagination of the searchFiles results for you.
  This class can't be instantiated from outside. The `AdobeStock` searchFiles methods can be used to create the object of `SearchFilesIterator` class as per the arguments provided.

#### Methods
  * The  `SearchFilesIterator` class allows you to:
    * `totalSearchFiles` - Get the total number of search files available with this iterator. Initially, since the state is pointing before the first response, it returns -1.
    * `totalSearchPages` - Get the total number of search pages available with this iterator. Initially, since the state is pointing before the first response, it returns -1.
    * `currentSearchPageIndex` - Get the current search page index of searchFiles response available from recently performed `next` or `previous` or `skipTo` method. Initially, since the state is pointing before the first response, it returns -1.
    * `getResponse` - Get the response object of recently performed searchFiles api call either by using `next` or `previous` or `skipTo`. Initially, this method will return empty object since it is pointing to before first searchFiles response.
    * `next` - It moves the state to next page and fetch the searchFiles response for the same. It returns a promise where it resolves the promise if searchFiles api returns with success and rejects if there is any failure while searchFiles api or if it already hit the last searchFiles page results.
    * `previous` - It moves the state to previous page and fetch the searchFiles response for the same. It returns a promise where it resolves the promise if searchFiles api returns with success and rejects if there is any failure while searchFiles api or if it already hit the first searchFiles page results or if the iterator is pointing before the first searchFiles response.
    * `skipTo` - It moves the state to provided search page and fetch the searchFiles response for the same. It returns a promise where it resolves the promise if searchFiles api returns with success and rejects if there is any failure while searchFiles api or if the provided search page index is out of total search pages available.
      * Requires:
        * `pageIndex` - It requires search page index to skip to. It is zero-based index.

#### Example

```
const accessToken = 'fdkgnio4isoknzklnvw409jknvzksnvai3289r4209tjaornuivn34nivh3jt340fjvn9304jt';
const queryParams = {
locale: 'en-US',
search_parameters: {
  words: 'tree house',
  limit: 10,
  offset: 10,
  filters_template_category_id: [
    AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
    AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
  ],
  filters_area_pixels: '0-2500',
},
};
const stock = new AdobeStock('Stock_Client_Api_key', 'Stock Client/1.0.0', AdobeStock.ENVIRONMENT.STAGE);
const iterator = stock.searchFiles(accessToken,
                                  queryParams,
                                  null);
// returns with error
iterator.previous()
      .then(() => {})
      .catch((error) => {
        console.error(error);
      });

// returns with success  
iterator.next().then(() => {
  let response = iterator.getResponse();
  console.log(response.files.length);
  console.log('total search files: ' + iterator.totalSearchFiles());
  console.log('total search pages: ' + iterator.totalSearchPages());
  console.log('current search page: ' + iterator.currentSearchPageIndex());

  // still returns with error since we are on the first page
  iterator.previous()
          .then(() => {})
          .catch((error) => {
            console.error(error);
          });

  iterator.next().then(() => {
    // now previous returns with success since we are on the second page
    iterator.previous().then(() => {
      response = iterator.getResponse();
      console.log(response.files.length);

      // skip the searchFiles results to a particular search results page index
      iterator.skipTo(5).then(() => {
        response = iterator.getResponse();
        console.log(response.files.length);
      });
    });
  });

  // you can create as many iterators as you want for different search queries
  const iterator2 = stock.searchFilesByKeywords(accessToken,
                                      'tree house',
                                      'en-US',
                                      null,
                                      null);

  iterator2.next().then(() => {
    // make sure that you are using correct iterator in the promise resolve methods.
    let response = iterator2.getResponse();
    // let response = iterator.getResponse(); //will return the response from old iterator
    console.log(response.files.length);
    console.log('total search files: ' + iterator2.totalSearchFiles());
    console.log('total search pages: ' + iterator2.totalSearchPages());
    console.log('current search page: ' + iterator2.currentSearchPageIndex());
  });
});
```

### Query Parameter Object
In order to simplify the passing of query parameter object to searchFiles methods, we have mapped the actual URL parameters of searchFiles api to simpler property names. User can use the below tabular mapping of URL parameters with Query Parameter Property names for creating query parameter object:

| URL Parameter         | Query Parameter Property | Query Parameter Property Key |
|-----------------------|--------------------------|------------------------------|
| locale                | locale                   | AdobeStock.QUERY_PARAMS_PROPS.LOCALE            |
| search_parameters[*]  | search_parameters        | AdobeStock.QUERY_PARAMS_PROPS.SEARCH_PARAMETERS |
| similar_image         | similar_image            | AdobeStock.QUERY_PARAMS_PROPS.SIMILAR_IMAGE     |

search_parameters property of query parameter is in itself an object and it stores the corresponding URL parameters as per the mapping mentioned below:

| URL Parameter                                             | search_parameters Property | search_parameters Property Key |
|-------------------------------------------------------|----------------------------|--------------------------------|
| search_parameters[words]                              | words                      | AdobeStock.SEARCH_PARAMS.WORDS |
| search_parameters[limit]                              | limit                      | AdobeStock.SEARCH_PARAMS.LIMIT |
| search_parameters[offset]                             | offset                     | AdobeStock.SEARCH_PARAMS.OFFSET|
| search_parameters[order]                              | order                      | AdobeStock.SEARCH_PARAMS.ORDER |
| search_parameters[creator_id]                         | creator_id                 | AdobeStock.SEARCH_PARAMS.CREATOR_ID |
| search_parameters[media_id]                           | media_id                   | AdobeStock.SEARCH_PARAMS.MEDIA_ID |
| search_parameters[model_id]                           | model_id                   | AdobeStock.SEARCH_PARAMS.MODEL_ID |
| search_parameters[serie_id]                           | serie_id                   | AdobeStock.SEARCH_PARAMS.SERIE_ID |
| search_parameters[similar]                            | similar                    | AdobeStock.SEARCH_PARAMS.SIMILAR |
| search_parameters[similar_url]                        | similar_url                | AdobeStock.SEARCH_PARAMS.SIMILAR_URL |    
| search_parameters[similar_image]                      | similar_image              | AdobeStock.SEARCH_PARAMS.SIMILAR_IMAGE |
| search_parameters[category]                           | category                   | AdobeStock.SEARCH_PARAMS.CATEGORY |
| search_parameters[thumbnail_size]                     | thumbnail_size             | AdobeStock.SEARCH_PARAMS.THUMBNAIL_SIZE |
| search_parameters[filters][area_pixels]               | filters_area_pixels        | AdobeStock.SEARCH_PARAMS.FILTERS_AREA_PIXELS |
| search_parameters[filters][3d_type_id][]              | filters_3d_type_id         | AdobeStock.SEARCH_PARAMS.FILTERS_3D_TYPE_ID |
| search_parameters[filters][template_type_id][]        | filters_template_type_id   | AdobeStock.SEARCH_PARAMS.FILTERS_TEMPLATE_TYPE_ID |
| search_parameters[filters][template_category_id][]    | filters_template_category_id | AdobeStock.SEARCH_PARAMS.FILTERS_TEMPLATE_CATEGORY_ID |
| search_parameters[filters][has_releases]              | filters_has_releases       | AdobeStock.SEARCH_PARAMS.FILTERS_HAS_RELEASES |
| search_parameters[filters][content_type:photo]        | filters_content_type_photo | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE_PHOTO |
| search_parameters[filters][content_type:illustration] | filters_content_type_illustration | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE_ILLUSTRATION |
| search_parameters[filters][content_type:vector]       | filters_content_type_vector | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE_VECTOR |
| search_parameters[filters][content_type:video]        | filters_content_type_video | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE.VIDEO |
| search_parameters[filters][content_type:3d]           | filters_content_type_3d    | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE_3D |
| search_parameters[filters][content_type:all]          | filters_content_type_all   | AdobeStock.SEARCH_PARAMS.FILTERS_CONTENT_TYPE_ALL |
| search_parameters[filters][offensive:2]               | filters_offensive_2        | AdobeStock.SEARCH_PARAMS.FILTERS_OFFENSIVE_2 |
| search_parameters[filters][isolated:on]               | filters_isolated_on        | AdobeStock.SEARCH_PARAMS.FILTERS_ISOLATED_ON |
| search_parameters[filters][panoramic:on]              | filters_panoramic_on       | AdobeStock.SEARCH_PARAMS.FILTERS_PANORAMIC_ON |
| search_parameters[filters][orientation]               | filters_orientation        | AdobeStock.SEARCH_PARAMS.FILTERS_ORIENTATION |
| search_parameters[filters][age]                       | filters_age                | AdobeStock.SEARCH_PARAMS.FILTERS_AGE |
| search_parameters[filters][video_duration]            | filters_video_duration     | AdobeStock.SEARCH_PARAMS.FILTERS_VIDEO_DURATION |
| search_parameters[filters][colors]                    | filters_colors             | AdobeStock.SEARCH_PARAMS.FILTERS_COLORS |

#### Example:

```
const queryParams = {
  locale: 'en-US',
  search_parameters: {
    words: 'tree house',
    media_id: 123324324,
    filters_content_type_photo: 1,
    filters_age: AdobeStock.SEARCH_PARAMS_AGE.TWO_YEAR,
    filters_template_category_id: [
      AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
      AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.MOBILE,
    ],
    similar_image: 1,
  },
  similar_image: 'data',
}
```

or

```
const queryParams = {};
const qProps = AdobeStock.QUERY_PARAMS_PROPS;
queryParams[qProps.LOCALE] = 'en-US';
queryParams[qProps.SIMILAR_IMAGE] = 'data';

const searchParams = {};
const sProps = AdobeStock.SEARCH_PARAMS;
searchParams[sProps.WORDS] = 'tree house';
searchParams[sProps.MEDIA_ID] = 123324324;
searchParams[sProps.FILTERS_CONTENT_TYPE_PHOTO] = 1;
searchParams[sProps.FILTERS_AGE] = AdobeStock.SEARCH_PARAMS_AGE.TWO_YEAR;
searchParams[sProps.FILTERS_TEMPLATE_TYPE_ID] = [
  AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
  AdobeStock.SEARCH_PARAMS_TEMPLATE_CATEGORIES.MOBILE,
];

queryParams[qProps.SEARCH_PARAMETERS] = searchParams;

```

### Result Columns
The `AdobeStock` allows you to access the list of result columns which can be used to create result columns array. For e.g.

```
const resultColumns = [
  AdobeStock.RESULT_COLUMNS.ID,
  AdobeStock.RESULT_COLUMNS.TITLE,
  AdobeStock.RESULT_COLUMNS.THUMBNAIL_URL,
  AdobeStock.RESULT_COLUMNS.WIDTH,
  AdobeStock.RESULT_COLUMNS.HEIGHT,
  AdobeStock.RESULT_COLUMNS.CREATION_DATE,
  AdobeStock.RESULT_COLUMNS.KEYWORDS,
];
```

## Install Dependencies
- `npm install`

## Running the Test Suite
- `npm test` ( eslint, mocha )

### Linting
- `npm run lint`

### Mocha ( Unit )
- `npm run test:unit`

### Code coverage ( istanbul )
- `npm run coverage`
Please not to have this work you may need to have nyc (https://github.com/istanbuljs/nyc) install in global.
You can do so by doing
- `npm install -g nyc`

## Build
- `npm run build`
