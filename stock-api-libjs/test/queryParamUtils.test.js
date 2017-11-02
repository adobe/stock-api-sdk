import { expect } from 'chai';
import Constants from './../src/constants/constants';
import QueryParamsUtils from './../src/utils/queryParamsUtils';

describe('QueryParamsUtils', () => {
  // Tests for encodeURI function
  describe('isNBResultsColumnPreset', () => {
    const testCases = [
      {
        queryParams: {
          result_columns: [
            Constants.RESULT_COLUMNS.ID,
            Constants.RESULT_COLUMNS.TITLE,
          ],
        },
        expected: false,
      },
      {
        queryParams: {
          result_columns: [
            Constants.RESULT_COLUMNS.ID,
            Constants.RESULT_COLUMNS.TITLE,
            Constants.RESULT_COLUMNS.NB_RESULTS,
          ],
        },
        expected: true,
      },
      {
        queryParams: {
        },
        expected: true,
      },
    ];

    testCases.forEach((testcase) => {
      it(`should return ${testcase.expected} since ${Constants.RESULT_COLUMNS.NB_RESULTS} is ${testcase.expected ? 'present' : 'not present'} in query params`, () => {
        expect(QueryParamsUtils.isNBResultsColumnPresent(testcase.queryParams))
        .to.equal(testcase.expected);
      });
    });
  });

  // Tests for addResultColumnNBResults function
  describe('addResultColumnNBResults', () => {
    const testCases = [
      {
        test: {
          result_columns: [
            Constants.RESULT_COLUMNS.ID,
            Constants.RESULT_COLUMNS.TITLE,
          ],
        },
      },
      {
        test: {
          result_columns: [
            Constants.RESULT_COLUMNS.ID,
            Constants.RESULT_COLUMNS.TITLE,
            Constants.RESULT_COLUMNS.NB_RESULTS,
          ],
        },
      },
    ];

    it('should return true as result_colums now contain nb_results', () => {
      expect(QueryParamsUtils.isNBResultsColumnPresent(
        QueryParamsUtils.addResultColumnNBResults(testCases[0].test))).to.equal(true);
    });

    it('should throw an error as result_colums already contain nb_results', () => {
      expect(() => QueryParamsUtils.addResultColumnNBResults(testCases[1].test)).to.throw(
      'Query parameter already contains nb_results in result_columns');
    });
  });

  // Tests for validateSearchFileQueryParams function
  describe('validateSearchFileQueryParams', () => {
    const testCases = [
      {
        test: {
          locale: 'en_us',
          category_id: 1043,
        },
      },
      {
        test: {
          locale: 'en_us',
          result_columns: [
            Constants.RESULT_COLUMNS.IS_LICENSED,
          ],
        },
      },
      {
        test: {
          locale: 'en_us',
          search_parameters: {
            similar_image: 1,
          },
        },
      },
      {
        test: {
          locale: 'en_us',
          search_parameters: {
            category: 1,
            similar_image: 0,
            filters_template_category_id: [
              Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PHOTO,
              Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.PRINT,
            ],
            filters_area_pixels: '0-25000',
          },
          result_columns: [
            Constants.RESULT_COLUMNS.ID,
            Constants.RESULT_COLUMNS.TITLE,
            Constants.RESULT_COLUMNS.NB_RESULTS,
          ],
        },
      },
    ];

    it('should throw error as category_id is invalid param for search file query', () => {
      expect(() => QueryParamsUtils.validateSearchFileQueryParams(testCases[0].test)).to.throw('Invalid query parameter \'category_id\'! for file search query');
    });

    it('should throw error as access token is missing from query param for search file query', () => {
      expect(() => QueryParamsUtils.validateSearchFileQueryParams(testCases[1].test)).to.throw('Access Token missing! Result Column \'is_licensed\' requires authentication.');
    });

    it('should throw error as image data is missing from query param for similar image query', () => {
      expect(() => QueryParamsUtils.validateSearchFileQueryParams(testCases[2].test)).to.throw('Image Data missing! Search parameter similar_image requires similar_image in query parameters');
    });

    it('shouldn\'t throw error as query param is valid for file search query', () => {
      expect(() => QueryParamsUtils.validateSearchFileQueryParams(testCases[3].test))
      .to.not.throw(Error);
    });

    it('should throw error as locale is not a valid string value', () => {
      const queryParams = {
        locale: undefined,
        category_id: '1043',
      };

      let testFn = () => QueryParamsUtils.validateSearchFileQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = 1;
      testFn = () => QueryParamsUtils.validateSearchFileQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = {};
      testFn = () => QueryParamsUtils.validateSearchFileQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);
    });
  });

  // Tests for validateSearchCategoryQueryParams function
  describe('validateSearchCategoryQueryParams', () => {
    const testCases = [
      {
        test: {
          locale: 'en_us',
          category_id: 1043,
        },
      },
      {
        test: {
          locale: 'en_us',
          category_id: 1043,
          search_parameters: {

          },
        },
      },
      {
        test: {
          locale: 'en_us',
          category_id: '1043',
        },
      },
      {
        test: {
          locale: 'en_us',
        },
      },
    ];

    it('should not throw any error as query params is valid', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryQueryParams(testCases[0].test))
      .to.not.throw(Error);
    });

    it('should throw error as search_parameters is invalid param for category search query', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryQueryParams(testCases[1].test)).to.throw('Invalid query parameter \'search_parameters\'! for category search query!');
    });

    it('should throw error as category_id isn\'t a valid integer value', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryQueryParams(testCases[2].test)).to.throw('category_id expects integer only. For e.g. 1043, 4526 etc.');
    });

    it('should throw error as category_id is missing from query params', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryQueryParams(testCases[3].test)).to.throw('category_id missing! category search requires category_id in query parameters.');
    });

    it('should throw error as locale is not a valid string value', () => {
      const queryParams = {
        locale: undefined,
        category_id: '1043',
      };

      let testFn = () => QueryParamsUtils.validateSearchCategoryQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = 1;
      testFn = () => QueryParamsUtils.validateSearchCategoryQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = {};
      testFn = () => QueryParamsUtils.validateSearchCategoryQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error queryParams expects Object if queryParams argument passed is not object', () => {
      let testFunc = () => QueryParamsUtils.validateSearchCategoryQueryParams(1);
      expect(testFunc).to.throw(/queryParams expects Object!/);

      testFunc = () => QueryParamsUtils.validateSearchCategoryQueryParams([]);
      expect(testFunc).to.throw(/queryParams expects Object!/);

      testFunc = () => QueryParamsUtils.validateSearchCategoryQueryParams('test');
      expect(testFunc).to.throw(/queryParams expects Object!/);
    });
  });

  // Tests for validateSearchCategoryTreeQueryParams function
  describe('validateSearchCategoryTreeQueryParams', () => {
    const testCases = [
      {
        test: {
          locale: 'en_us',
          category_id: 1043,
        },
      },
      {
        test: {
          locale: 'en_us',
          category_id: 1043,
          search_parameters: {

          },
        },
      },
      {
        test: {
          locale: 'en_us',
          category_id: '1043',
        },
      },
      {
        test: {
          locale: 'en_us',
        },
      },
    ];

    it('should not throw any error as query params is valid', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryTreeQueryParams(testCases[0].test))
      .to.not.throw(Error);
    });

    it('should throw error as search_parameters is invalid param for category search query', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryTreeQueryParams(testCases[1].test)).to.throw('Invalid query parameter \'search_parameters\'! for category search query!');
    });

    it('should throw error as category_id isn\'t a valid integer value', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryTreeQueryParams(testCases[2].test)).to.throw('category_id expects integer only. For e.g. 1043, 4526 etc.');
    });

    it('should n\'t throw error as category_id is optional for categoryTree query', () => {
      expect(() => QueryParamsUtils.validateSearchCategoryTreeQueryParams(testCases[3].test))
      .to.not.throw(Error);
    });

    it('should throw error as locale is not a valid string value', () => {
      const queryParams = {
        locale: undefined,
        category_id: '1043',
      };

      let testFn = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = 1;
      testFn = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);

      queryParams.locale = {};
      testFn = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams(queryParams);
      expect(testFn).to.throw(/locale expects string only/);
    });

    it('should throw error queryParams expects Object if queryParams argument passed is not object', () => {
      let testFunc = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams(1);
      expect(testFunc).to.throw(/queryParams expects Object!/);

      testFunc = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams([]);
      expect(testFunc).to.throw(/queryParams expects Object!/);

      testFunc = () => QueryParamsUtils.validateSearchCategoryTreeQueryParams('test');
      expect(testFunc).to.throw(/queryParams expects Object!/);
    });
  });
});
