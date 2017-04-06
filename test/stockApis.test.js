import { expect } from 'chai';
import sinon from 'sinon';
import Constants from './../src/constants/constants';
import StockApis from './../src/api/stockApis';
import Utils from './../src/utils/utils';
import Config from './../src/config/config';
import SearchParamsUtils from './../src/utils/searchParamsUtils';

describe('StockApis', () => {
  // Tests for searchFiles function
  describe('searchFiles', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.queryParams = {
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
      };
      global.FormData = function () {
        this.append = (key, value) => {
          value;
        };
      };
    });

    it('should call makeGetAjaxCall if queryParams doesn\'t contain similar_image param', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      const makeMultiPartAjaxCall = sinon.spy(Utils, 'makeMultiPartAjaxCall');

      this.queryParams.search_parameters.similar_image = 0;

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.equal(this.resSucc);
                        done();
                      }, (error) => {
                        expect(error).to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      sinon.assert.calledOnce(makeGetAjaxCall);
      sinon.assert.notCalled(makeMultiPartAjaxCall);

      makeGetAjaxCall.restore();
      makeMultiPartAjaxCall.restore();
    });

    it('should call makeMultiPartAjaxCall if queryParams contains similar_image param', function (done) {
      const makeGetAjaxCall = sinon.spy(Utils, 'makeGetAjaxCall');
      const makeMultiPartAjaxCall = sinon.stub(Utils, 'makeMultiPartAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.queryParams.search_parameters.similar_image = 1;

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.equal(this.resSucc);
                        done();
                      }, (error) => {
                        expect(error).to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });
      sinon.assert.calledOnce(makeMultiPartAjaxCall);
      sinon.assert.notCalled(makeGetAjaxCall);

      makeGetAjaxCall.restore();
      makeMultiPartAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.equal(this.resSucc);
                        done();
                      }, (error) => {
                        expect(error).to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });
      makeGetAjaxCall.restore();
    });

    it('should reject promise with error JSON if makeGetAjaxCall returns with error', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call reject
          if (!url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error).to.equal(this.resErr);
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeMultiPartAjaxCall returns with success', function (done) {
      const makeMultiPartAjaxCall = sinon.stub(Utils, 'makeMultiPartAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.queryParams.search_parameters.similar_image = 1;

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.equal(this.resSucc);
                        done();
                      }, (error) => {
                        expect(error).to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeMultiPartAjaxCall.restore();
    });

    it('should call back callbackError with error JSON if makeMultiPartAjaxCall returns with error', function (done) {
      const makeMultiPartAjaxCall = sinon.stub(Utils, 'makeMultiPartAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call reject
          if (!url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.queryParams.search_parameters.similar_image = 1;

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error).to.equal(this.resErr);
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeMultiPartAjaxCall.restore();
    });

    it('should reject the promise with error JSON if searchParamsEncodeURI throws an error', function (done) {
      const searchParamsEncodeURI = sinon.stub(SearchParamsUtils, 'encodeURI');
      const er = new TypeError('Undefined Test Error');
      searchParamsEncodeURI.throws(er);

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error).to.be.an('error');
                        expect(error.message).to.equal('Undefined Test Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      searchParamsEncodeURI.restore();
    });

    it('should resolve the promise with success JSON with queryParams is empty', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.search)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchFiles(null, {})
                      .then((response) => {
                        expect(response).to.equal(this.resSucc);
                        done();
                      }, (error) => {
                        expect(error).to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });
});
