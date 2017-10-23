import { expect } from 'chai';
import SearchParamsUtils from './../src/utils/searchParamsUtils';
import Utils from './../src/utils/utils';
import Constants from './../src/constants/constants';

describe('SearchParamsUtils', () => {
  describe('validate', () => {
    const invalidParamTypeTestcases = [
      {
        test: {
          words: 'test',
          limit: 10,
          offset: 50,
          filters_age: Constants.SEARCH_PARAMS_AGE.ONE_WEEK,
          filters_template_category_id: [
            Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.MOBILE,
            Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.WEB,
          ],
          filters_area_pixels: '100-2000',
        },
        error: false,
        desc: 'all the input search params are correct',
      },
      { test: { creator_id: 'abc' }, error: true, desc: 'the creator_id(integer) contains string value' },
      { test: { media_id: [] }, error: true, desc: 'the media_id(integer) is an array' },
      { test: { model_id: {} }, error: true, desc: 'the model_id(integer) is an object' },
      { test: { filters_3d_type_id: 10 }, error: true, desc: 'the filters_3d_type_id(array) is an integer' },
      { test: { filters_template_category_id: 'abc' }, error: true, desc: 'the filters_template_category_id(array) is a string' },
      { test: { filters_template_type_id: {} }, error: true, desc: 'the filters_template_type_id(array) is an object' },
      { test: { filters_area_pixels: 10 }, error: true, desc: 'the filters_area_pixels(range) is an integer' },
      { test: { filters_area_pixels: 'abc' }, error: true, desc: 'the filters_area_pixels(range) is a string' },
      { test: { filters_area_pixels: {} }, error: true, desc: 'the filters_area_pixels(range) is an object' },
      { test: { filters_area_pixels: [] }, error: true, desc: 'the filters_area_pixels(range) is an array' },
    ];

    const integerMinMaxTestcases = [
      { test: { limit: 10 }, error: false, desc: 'the limit param is within allowed range' },
      { test: { limit: 70 }, error: true, desc: 'the limit param is beyond max allowed value' },
      { test: { limit: 0 }, error: true, desc: 'the limit param is lesser than min allowed value' },
      { test: { offset: -10 }, error: true, desc: 'the offset is lesser than min allowed value' },
    ];

    const stringParamAllowedValuesTestcases = [
      { test: { order: Constants.SEARCH_PARAMS_ORDER.CREATION }, error: false, desc: 'the order param has allowed value' },
      { test: { filters_has_releases: 'test' }, error: true, desc: 'the filters_has_releases has value which is not allowed' },
    ];

    const paramsSupportTestcases = [
      { test: { order: Constants.SEARCH_PARAMS_ORDER.CREATION }, error: false, desc: 'the order search param is supported' },
      { test: { paramTest: 'test' }, error: true, desc: 'the paramTest search param is not supported' },
    ];

    const rangeParamTestcases = [
      { test: { filters_area_pixels: '0-350' }, error: false, desc: 'the filters_area_pixels contains valid range' },
      { test: { filters_area_pixels: '350-0' }, error: true, desc: 'the filters_area_pixels contains invalid range 350-0' },
      { test: { filters_area_pixels: '350' }, error: true, desc: 'the filters_area_pixels contains invalid range 350' },
    ];

    const arrayParamAllowedValuesTestcases = [
      {
        test: { filters_template_type_id: [
          Constants.SEARCH_PARAMS_TEMPLATE_TYPES.PSDT,
          Constants.SEARCH_PARAMS_TEMPLATE_TYPES.AIT,
        ],
        },
        error: false,
        desc: 'the filters_template_type_id array param has allowed values within',
      },
      {
        test: { filters_3d_type_id: [
          Constants.SEARCH_PARAMS_3D_TYPES.MODELS,
          'test',
        ],
        },
        error: true,
        desc: 'the filters_3d_type_id array param contains test value which is not allowed',
      },
    ];

    const testSuites = [
      { testcases: invalidParamTypeTestcases, errorType: 'invalid parameter type' },
      { testcases: integerMinMaxTestcases, errorType: 'value out of range' },
      { testcases: stringParamAllowedValuesTestcases, errorType: 'value not allowed' },
      { testcases: paramsSupportTestcases, errorType: 'param not supported' },
      { testcases: rangeParamTestcases, errorType: 'invalid range value' },
      { testcases: arrayParamAllowedValuesTestcases, errorType: 'array element\'s value not allowed' },
    ];

    testSuites.forEach((testSuit) => {
      testSuit.testcases.forEach((testcase) => {
        it(`should ${(testcase.error ? 'throw' : 'not throw')} error ['${testSuit.errorType}'] since ${testcase.desc}`, () => {
          const func = function () {
            SearchParamsUtils.validate(testcase.test);
          };

          if (testcase.error) {
            expect(func).to.throw(Error);
          } else {
            expect(func).to.not.throw(Error);
          }
        });
      });
    });
  });

  describe('validateLicenseHistory', () => {
    const invalidParamTypeTestcases = [
      {
        test: {
          limit: 10,
          offset: 50,
          thumbnail_size: 160,
        },
        error: false,
        desc: 'all the input search params are correct',
      },
    ];

    const integerMinMaxTestcases = [
      { test: { limit: 10 }, error: false, desc: 'the limit param is within allowed range' },
      { test: { limit: 110 }, error: true, desc: 'the limit param is beyond max allowed value' },
      { test: { limit: 0 }, error: true, desc: 'the limit param is lesser than min allowed value' },
      { test: { offset: -10 }, error: true, desc: 'the offset is lesser than min allowed value' },
    ];

    const paramsSupportTestcases = [
      { test: { thumbnail_size: 160 }, error: false, desc: 'the thumbnail_size search param is supported' },
      { test: { paramTest: 'test' }, error: true, desc: 'the paramTest search param is not supported' },
    ];

    const testSuites = [
      { testcases: invalidParamTypeTestcases, errorType: 'invalid parameter type' },
      { testcases: integerMinMaxTestcases, errorType: 'value out of range' },
      { testcases: paramsSupportTestcases, errorType: 'param not supported' },
    ];

    testSuites.forEach((testSuit) => {
      testSuit.testcases.forEach((testcase) => {
        it(`should ${(testcase.error ? 'throw' : 'not throw')} error ['${testSuit.errorType}'] since ${testcase.desc}`, () => {
          const func = function () {
            SearchParamsUtils.validateLicenseHistory(testcase.test);
          };

          if (testcase.error) {
            expect(func).to.throw(Error);
          } else {
            expect(func).to.not.throw(Error);
          }
        });
      });
    });
  });

  // testcases for encodeURI
  describe('encodeURI', () => {
    const testcases = [
      {
        test: {
          words: 'test keywords',
          offset: 10,
        },
        encodedURI: {
          'search_parameters[words]': 'test keywords',
          'search_parameters[offset]': '10',
        },
      },
      {
        test: {
          order: Constants.SEARCH_PARAMS_ORDER.POPULARITY,
          media_id: 3423423,
          similar_url: 'http://sometesturl/similar.jpg',
        },
        encodedURI: {
          'search_parameters[order]': Constants.SEARCH_PARAMS_ORDER.POPULARITY,
          'search_parameters[media_id]': '3423423',
          'search_parameters[similar_url]': 'http://sometesturl/similar.jpg',
        },
      },
      {
        test: {
          category: 695,
          filters_3d_type_id: [
            Constants.SEARCH_PARAMS_3D_TYPES.MODELS,
            Constants.SEARCH_PARAMS_3D_TYPES.LIGHTS,
          ],
          filters_area_pixels: '100-300',
        },
        encodedURI: {
          'search_parameters[category]': '695',
          'search_parameters[filters][3d_type_id][]': [
            Constants.SEARCH_PARAMS_3D_TYPES.MODELS.toString(),
            Constants.SEARCH_PARAMS_3D_TYPES.LIGHTS.toString(),
          ],
          'search_parameters[filters][area_pixels]': '100-300',
        },
      },
    ];

    testcases.forEach((testcase) => {
      it(`should output encoded uri for test params ${JSON.stringify(testcase.test)}`, () => {
        const uri = SearchParamsUtils.encodeURI(testcase.test);
        const opTest = {};
        uri.split('&').forEach((pair) => {
          const [key, value] = pair.split('=');

          if (testcase.encodedURI[decodeURIComponent(key)]
            && Utils.isArray(testcase.encodedURI[decodeURIComponent(key)])) {
            expect(testcase.encodedURI).to.include.keys(decodeURIComponent(key));
            expect(
              testcase.encodedURI[decodeURIComponent(key)]).to.include(decodeURIComponent(value));
            if (!opTest[decodeURIComponent(key)]) {
              opTest[decodeURIComponent(key)] = [];
            }

            opTest[decodeURIComponent(key)].push(decodeURIComponent(value));
          } else {
            expect(testcase.encodedURI).to.have.property(decodeURIComponent(key),
                                                          decodeURIComponent(value));
            opTest[decodeURIComponent(key)] = decodeURIComponent(value);
          }
        });

        expect(Object.keys(opTest)).to.lengthOf(Object.keys(testcase.encodedURI).length);
      });
    });
  });
});
