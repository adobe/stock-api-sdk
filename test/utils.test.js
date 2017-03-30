import { expect } from 'chai';
import sinon from 'sinon';
import Utils from './../src/utils/utils';
import Http from './../src/utils/http';

describe('Utils', () => {
  // Tests for doesArrayContainValue function
  describe('doesArrayContainValue', () => {
    const array = ['a', 'b', 'c'];
    const testCases = [
      { value: 'a', expected: true },
      { value: 'd', expected: false },
    ];

    testCases.forEach((test) => {
      it(`should return ${test.expected} since value ${test.expected ? 'exists' : 'does\'nt exist'} in array`, () => {
        expect(Utils.doesArrayContainValue(array, test.value)).to.equal(test.expected);
      });
    });
  });

  // Tests for isInteger function
  describe('isInteger', () => {
    const testCases = [
      { value: 12, expected: true },
      { value: '12', expected: false },
      { value: 'a', expected: false },
      { value: '21a', expected: false },
    ];

    testCases.forEach((test) => {
      it(`should return ${test.expected} since value ${test.expected ? 'is' : 'isn\'t'} integer`, () => {
        expect(Utils.isInteger(test.value)).to.equal(test.expected);
      });
    });
  });

  // Tests for isArray function
  describe('isArray', () => {
    const testCases = [
      { value: [{}, {}], expected: true },
      { value: ['a', 'b'], expected: true },
      { value: { a: 'b', c: 0 }, expected: false },
      { value: { a: ['a'] }, expected: false },
    ];

    testCases.forEach((test) => {
      it(`should return ${test.expected} since value ${test.expected ? 'is' : 'isn\'t'} Array`, () => {
        expect(Utils.isArray(test.value)).to.equal(test.expected);
      });
    });
  });

  // Tests for isObject function
  describe('isObject', () => {
    const testCases = [
      { value: [{}, {}], expected: false },
      { value: ['a', 'b'], expected: false },
      { value: { a: 'b', c: 0 }, expected: true },
      { value: { a: ['a'] }, expected: true },
    ];

    testCases.forEach((test) => {
      it(`should return ${test.expected} since value ${test.expected ? 'is' : 'isn\'t'} Object`, () => {
        expect(Utils.isObject(test.value)).to.equal(test.expected);
      });
    });
  });

    // Tests for makeGetAjaxCall function
  describe('makeGetAjaxCall', () => {
    beforeEach(function () {
      this.headers = null;
      this.urlSucc = 'https://success/api?test=1';
      this.urlErr = 'https://error/api?test=1';
      this.doXhr = sinon.stub(Http, 'doXhr').callsFake(
        settings => new Promise((resolve, reject) => {
          if (settings.url.match(/success/)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.resSucc = '{ nb_results: 100, files: [ { "id": 123213, "title": "Some test title" } ] }';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
    });

    afterEach(function () {
      this.doXhr.restore();
    });

    it('should resolve the promise with success JSON response if doXhr respond with success', function (done) {
      Utils.makeGetAjaxCall(this.urlSucc, this.headers)
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
    });

    it('should reject the promise with error JSON if doXhr respond with error if doXhr respond with error', function (done) {
      Utils.makeGetAjaxCall(this.urlErr, this.headers)
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
    });
  });

  // Tests for makeGetAjaxCall function
  describe('makePostAjaxCall', () => {
    beforeEach(function () {
      this.headers = null;
      this.urlSucc = 'https://success/api?test=1';
      this.urlErr = 'https://error/api?test=1';
      this.doXhr = sinon.stub(Http, 'doXhr').callsFake(
        settings => new Promise((resolve, reject) => {
          if (settings.url.match(/success/)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.data = 'some test data';
      this.resSucc = '{ nb_results: 100, files: [ { "id": 123213, "title": "Some test title" } ] }';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
    });

    afterEach(function () {
      this.doXhr.restore();
    });

    it('should resolve the promise with success JSON response if doXhr respond with success', function (done) {
      Utils.makePostAjaxCall(this.urlSucc, this.headers, this.data)
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
    });

    it('should reject the promise with error JSON if doXhr respond with error if doXhr respond with error', function (done) {
      Utils.makePostAjaxCall(this.urlErr, this.headers, this.data)
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
    });
  });

  // Tests for makeGetAjaxCall function
  describe('makeMultiPartAjaxCall', () => {
    beforeEach(function () {
      this.headers = null;
      this.urlSucc = 'https://success/api?test=1';
      this.urlErr = 'https://error/api?test=1';
      this.doXhr = sinon.stub(Http, 'doXhr').callsFake(
        settings => new Promise((resolve, reject) => {
          if (settings.url.match(/success/)) {
            resolve(this.resSucc);
          } else {
            reject(this.resErr);
          }
        }));
      this.data = 'some test data';
      this.resSucc = '{ nb_results: 100, files: [ { "id": 123213, "title": "Some test title" } ] }';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
    });

    afterEach(function () {
      this.doXhr.restore();
    });

    it('should resolve the promise with success JSON response if doXhr respond with success', function (done) {
      Utils.makeMultiPartAjaxCall(this.urlSucc, this.headers, this.data)
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
    });

    it('should reject the promise with error JSON if doXhr respond with error if doXhr respond with error', function (done) {
      Utils.makeMultiPartAjaxCall(this.urlErr, this.headers, this.data)
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
    });
  });
});
