import { expect } from 'chai';
import sinon from 'sinon';
import Constants from './../src/constants/constants';
import StockApis from './../src/api/stockApis';
import Utils from './../src/utils/utils';
import DownSamplingUtils from './../src/utils/downSamplingUtils';
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
      const downsampleAndFixOrientationImage = sinon.stub(
            DownSamplingUtils, 'downsampleAndFixOrientationImage').callsFake(
        resizedFile => new Promise((resolve, reject) => {
          // force call to resolve
          if (resizedFile) {
            resolve(resizedFile);
          } else {
            reject('error');
          }
          done();
        }));

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
      this.queryParams.similar_image = 'TestData';

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
      sinon.assert.notCalled(makeGetAjaxCall);

      makeGetAjaxCall.restore();
      makeMultiPartAjaxCall.restore();
      downsampleAndFixOrientationImage.restore();
    });

    it('should call back callbackError with error JSON if makeMultiPartAjaxCall returns with error', function (done) {
      const downsampleAndFixOrientationImage = sinon.stub(
            DownSamplingUtils, 'downsampleAndFixOrientationImage').callsFake(
        resizedFile => new Promise((resolve, reject) => {
          if (resizedFile) {
            resolve('resizedFile');
          } else {
            reject('Error');
          }
          done();
        }));
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
      this.queryParams.similar_image = 'TestData';

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
      downsampleAndFixOrientationImage.restore();
    });

    it('should callback with error if downsampleAndFixOrientationImage fails', function (done) {
      const downsampleAndFixOrientationImage = sinon.stub(
            DownSamplingUtils, 'downsampleAndFixOrientationImage').callsFake(
        resizedFile => new Promise((resolve, reject) => {
          if (!resizedFile) {
            resolve('resizedFile');
          } else {
            reject('downsampling failed!');
          }
          done();
        }));
      const makeMultiPartAjaxCall = sinon.spy(Utils, 'makeMultiPartAjaxCall');

      this.queryParams.search_parameters.similar_image = 1;
      this.queryParams.similar_image = 'TestData';

      this.stockApis.searchFiles(this.accessToken, this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error).to.equal('downsampling failed!');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      sinon.assert.notCalled(makeMultiPartAjaxCall);
      makeMultiPartAjaxCall.restore();
      downsampleAndFixOrientationImage.restore();
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
  // Tests for searchCategory function
  describe('searchCategory', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.queryParams = {
        locale: 'en_us',
        category_id: 1043,
      };
    });

    it('should call makeGetAjaxCall to make GET ajax request for category search query', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategory(this.queryParams)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.searchCategory(this.queryParams)
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
          if (!url.includes(this.config.endpoints.category)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategory(this.queryParams)
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

    it('should resolve the promise with success JSON with queryParams is empty', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategory({})
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
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.searchCategory(this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });
  // Tests for searchCategoryTree function
  describe('searchCategoryTree', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.queryParams = {
        locale: 'en_us',
        category_id: 1043,
      };
    });

    it('should call makeGetAjaxCall to make GET ajax request for category search query', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category_tree)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategoryTree(this.queryParams)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category_tree)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.searchCategoryTree(this.queryParams)
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
          if (!url.includes(this.config.endpoints.category_tree)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategoryTree(this.queryParams)
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

    it('should resolve the promise with success JSON with queryParams is empty', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.category_tree)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.searchCategoryTree({})
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
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.searchCategoryTree(this.queryParams)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });

  // Tests for accessMemberProfile function
  describe('accessMemberProfile', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.locale = 'en_us';
      this.contentId = 1234;
      this.license = 'STANDARD';
    });

    it('should call makeGetAjaxCall', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.user_profile)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license, this.locale)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.user_profile)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license, this.locale)
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
          if (!url.includes(this.config.endpoints.user_profile)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license, this.locale)
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

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.user_profile)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license)
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
          if (!url.includes(this.config.endpoints.user_profile)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license)
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

    it('should reject promise with error JSON if makeGetAjaxCall returns with error', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.accessMemberProfile(this.accessToken,
        this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });

  // Tests for memberAbandon function
  describe('memberAbandon', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.contentId = 1234;
      this.state = 'STANDARD';
    });

    it('should call makeGetAjaxCall', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.abandon)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.memberAbandon(this.accessToken, this.contentId, this.state)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.abandon)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.memberAbandon(this.accessToken, this.contentId, this.state)
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
          if (!url.includes(this.config.endpoints.abandon)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.memberAbandon(this.accessToken, this.contentId, this.state)
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

    it('should reject promise with error JSON if makeGetAjaxCall returns throws error', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.memberAbandon(this.accessToken, this.contentId, this.state)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });

  // Tests for licenseInfo function
  describe('licenseInfo', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.contentId = 1234;
      this.license = 'STANDARD';
    });

    it('should call makeGetAjaxCall', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.license_info)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.licenseInfo(this.accessToken, this.contentId, this.license)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.license_info)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.licenseInfo(this.accessToken, this.contentId, this.license)
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
          if (!url.includes(this.config.endpoints.license_info)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.licenseInfo(this.accessToken, this.contentId, this.license)
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

    it('should reject promise with error JSON if makeGetAjaxCall throws error', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.licenseInfo(this.accessToken, this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });

  // Tests for requestLicense function
  describe('requestLicense', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.contentId = 1234;
      this.license = 'STANDARD';
    });

    it('should call makeGetAjaxCall', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.license)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.requestLicense(this.accessToken, this.contentId, this.license)
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

      makeGetAjaxCall.restore();
    });

    it('should resolve promise with success JSON response if makeGetAjaxCall returns with success', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').callsFake(
        (url, headers) => new Promise((resolve, reject) => {
          headers;
          // force call to resolve
          if (url.includes(this.config.endpoints.license)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.stockApis.requestLicense(this.accessToken, this.contentId, this.license)
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
          if (!url.includes(this.config.endpoints.license)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.requestLicense(this.accessToken, this.contentId, this.license)
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

    it('should reject promise with error JSON if makeGetAjaxCall throws error', function (done) {
      const makeGetAjaxCall = sinon.stub(Utils, 'makeGetAjaxCall').throws(new TypeError('Error'));

      this.stockApis.requestLicense(this.accessToken, this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal('Error');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      makeGetAjaxCall.restore();
    });
  });

  // Tests for downloadAsset function
  describe('downloadAsset', () => {
    beforeEach(function () {
      this.config = new Config('TestAPIKey', 'TestingProduct', Constants.ENVIRONMENT.STAGE);
      this.stockApis = new StockApis(this.config);
      this.callbackSuccess = sinon.spy();
      this.callbackError = sinon.spy();
      this.accessToken = 'sometestaccesstoken';
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
      this.contentId = 1234;
      this.license = 'STANDARD';
      this.requestLicenseCall = null;
      this.accessMemberProfile = null;
    });

    it('should resolve promise error if license info API resolves with Error', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to reject
          if (!accessToken) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
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

      licenseInfoCall.restore();
    });

    it('should resolve promise error if requestLicense API resolves with Error', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"state":"purchased"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.requestLicenseCall = sinon.stub(this.stockApis, 'requestLicense').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (!accessToken) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
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

      licenseInfoCall.restore();
      this.requestLicenseCall = null;
    });

    it('should resolve promise success if requestLicense API resolves with URL', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"state":"purchased"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.requestLicenseCall = sinon.stub(this.stockApis, 'requestLicense').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"url":"https://di.com"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.equal('https://di.com?token=sometestaccesstoken');
                        done();
                      }, (error) => {
                        expect(error).to.to.not.be.ok;
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      licenseInfoCall.restore();
      this.requestLicenseCall = null;
    });

    it('should resolve promise error if accessMemberProfile API resolves with error', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"state":"not_purchased"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.accessMemberProfileCall = sinon.stub(this.stockApis, 'accessMemberProfile').callsFake(
          (accessToken, contentId, license) => new Promise((resolve, reject) => {
            contentId; license;
            // force call to resolve
            if (!accessToken) {
              resolve('{"contents":{"1234":{"purchase_details":{"url":"https://di.com"}}}}');
            } else {
              reject(this.resErr);
            }
          }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
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

      licenseInfoCall.restore();
      this.accessMemberProfileCall = null;
    });

    it('should resolve promise error if asset not licensed but quota available ', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"state":"not_purchased"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.accessMemberProfileCall = sinon.stub(this.stockApis, 'accessMemberProfile').callsFake(
          (accessToken, contentId, license) => new Promise((resolve, reject) => {
            contentId; license;
            // force call to resolve
            if (accessToken) {
              resolve('{"available_entitlement":{"quota": 2}}');
            } else {
              reject(this.resErr);
            }
          }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal(
                          'Content not licensed but have enough quota or overage plan, so first buy the license!');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      licenseInfoCall.restore();
      this.accessMemberProfileCall = null;
    });

    it('should resolve promise error if accessMemberProfile API return quota 0', function (done) {
      const licenseInfoCall = sinon.stub(this.stockApis, 'licenseInfo').callsFake(
        (accessToken, contentId, license) => new Promise((resolve, reject) => {
          contentId; license;
          // force call to resolve
          if (accessToken) {
            resolve('{"contents":{"1234":{"purchase_details":{"state":"not_purchased"}}}}');
          } else {
            reject(this.resErr);
          }
        }));

      this.accessMemberProfileCall = sinon.stub(this.stockApis, 'accessMemberProfile').callsFake(
          (accessToken, contentId, license) => new Promise((resolve, reject) => {
            contentId; license;
            // force call to resolve
            if (accessToken) {
              resolve('{"available_entitlement":{"quota": 0}, "purchase_options":{"state": "x"}}');
            } else {
              reject(this.resErr);
            }
          }));

      this.stockApis.downloadAsset(this.accessToken, this.contentId, this.license)
                      .then((response) => {
                        expect(response).to.not.be.ok;
                        done();
                      }, (error) => {
                        expect(error.message).to.equal(
                          'Content not licensed and you do not have enough quota or overage plan!');
                        done();
                      })
                      .catch((error) => {
                        done(error);
                      });

      licenseInfoCall.restore();
      this.accessMemberProfileCall = null;
    });
  });
});
