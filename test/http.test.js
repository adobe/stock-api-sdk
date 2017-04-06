import { expect } from 'chai';
import sinon from 'sinon';
import Http from './../src/utils/http';
import Constants from './../src/constants/constants';

describe('Http', () => {
  // Tests for doXhr function
  describe('doXhr', () => {
    beforeEach(function () {
      this.requests = [];
      this.getXhr = sinon.stub(Http, 'getXhr').callsFake(() => {
        const fakeXhr = sinon.useFakeXMLHttpRequest();
        fakeXhr.onCreate = (xhr) => {
          this.requests.push(xhr);
        };
        return new fakeXhr();
      });
      this.settings = {
        url: 'https://sometesturl.com/api?test=1',
      };
      this.defaultHeaders = {
        'cache-control': 'no-cache',
      };
      this.resSucc = '[{ "id": 12, "comment": "Hey there" }]';
      this.resErr = '{ error: "Invalid access token", code: 10 }';
    });

    afterEach(function () {
      this.getXhr.restore();
    });

    it('should resolve the promise with response if server respond success response', function (done) {
      Http.doXhr(this.settings)
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
      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
    });

    it('should reject the promise with error if server respond with error', function (done) {
      Http.doXhr(this.settings)
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
      this.requests[0].respond(400, { 'Content-Type': 'application/json' },
                        this.resErr);
    });

    it('should reject the promise with error if server respond with internal server error', function (done) {
      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.not.be.ok;
              done();
            }, (error) => {
              expect(error).to.equal('Internal Server Error');
              done();
            })
            .catch((error) => {
              done(error);
            });
      this.requests[0].respond(500, { 'Content-Type': 'application/json' },
                        this.resErr);
    });

    it('should reject the promise with error Browser doesn\'t support XHR if getXhr returns null', function (done) {
      this.getXhr.restore();
      this.getXhr = sinon.stub(Http, 'getXhr').callsFake(() => null);

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.not.be.ok;
              done();
            }, (error) => {
              expect(error).to.be.an('error');
              expect(error.message).to.match(/Browser doesn't support XHR!/);
              done();
            })
            .catch((error) => {
              done(error);
            });
    });

    it('should reject the promise if xhr get aborted', function (done) {
      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.not.be.ok;
              done();
            }, (error) => {
              expect(error).to.be.an('error');
              expect(error.message).to.match(/Request got aborted!/);
              done();
            })
            .catch((error) => {
              done(error);
            });
      this.requests[0].abort();
    });

    it('should set the headers to xhr as provided in settings argument as well as default headers', function () {
      let headers = null;

      this.settings.headers = {
        // user defined header
        'x-api-key': 'test-api-key',
        'x-product': 'test-product-name',
        'x-header': 'test',
        'x-request-id': 'xyz-abc-001',
      };

      Http.doXhr(this.settings)
      .then((response) => {
        expect(response).to.equal(this.resSucc);
      }, (error) => {
        expect(error).to.not.be.ok;
      });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);

      headers = this.requests[0].requestHeaders;

      // test the default headers
      expect(headers).to.have.property('cache-control', 'no-cache');

      expect(headers).to.have.property('x-api-key', 'test-api-key');

      expect(headers).to.have.property('x-product', 'test-product-name');

      expect(headers).to.have.property('x-request-id', 'xyz-abc-001');

      expect(headers).to.have.property('x-header', 'test');
    });

    it('should set the content-type header as provided in contentType settings', function () {
      let headers = null;

      this.settings.contentType = 'application/json';

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      headers = this.requests[0].requestHeaders;

      expect(headers).to.include.keys('Content-type');
      expect(headers['Content-type']).to.match(/^application\/json/);
    });

    it('should not set the content-type header if contentType is set to false in settings', function () {
      let headers = null;
      this.settings.contentType = false;

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      headers = this.requests[0].requestHeaders;

      expect(headers).to.not.include.keys('Content-type');
    });

    it('should set the default content-type header to application/x-www-form-urlencoded if not provided in settings', function () {
      let headers = null;

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      headers = this.requests[0].requestHeaders;

      expect(headers).to.include.keys('Content-type');
      expect(headers['Content-type']).to.match(/^application\/x-www-form-urlencoded/);
    });

    it('should send request to the url as provided in settings', function () {
      Http.doXhr(this.settings)
      .then((response) => {
        expect(response).to.equal(this.resSucc);
      }, (error) => {
        expect(error).to.not.be.ok;
      });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      expect(this.requests[0].url).to.equal(this.settings.url);
    });

    it('should send default GET request if method is not provided', function () {
      if (this.settings.method) {
        delete this.settings.method;
      }

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      expect(this.requests[0].method).to.equal(Constants.HTTPMETHOD.GET);
    });

    Object.keys(Constants.HTTPMETHOD).forEach((method) => {
      it(`should make '${method}' request if method defined in settings is ${method}`, function () {
        this.settings.method = method;
        Http.doXhr(this.settings)
        .then((response) => {
          expect(response).to.equal(this.resSucc);
        }, (error) => {
          expect(error).to.not.be.ok;
        });

        this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                          this.resSucc);
        expect(this.requests[0].method).to.equal(this.settings.method);
      });
    });

    it('should do async request by default if async is not defined in settings', function () {
      if (this.settings.async) {
        delete this.settings.async;
      }

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      expect(this.requests[0].async).to.be.true;
    });

    [false, true].forEach((async) => {
      it(`should ${(async ? '' : 'not ')}make async request if async settings set to ${async}`, function () {
        this.settings.async = async;
        Http.doXhr(this.settings)
              .then((response) => {
                expect(response).to.equal(this.resSucc);
              }, (error) => {
                expect(error).to.not.be.ok;
              });

        this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                          this.resSucc);
        expect(this.requests[0].async).to.equal(this.settings.async);
      });
    });

    it('should do cross origin request if crossDomain is set to true in settings', function () {
      this.settings.crossDomain = true;

      Http.doXhr(this.settings)
            .then((response) => {
              expect(response).to.equal(this.resSucc);
            }, (error) => {
              expect(error).to.not.be.ok;
            });

      this.requests[0].respond(200, { 'Content-Type': 'application/json' },
                        this.resSucc);
      expect(this.requests[0].withCredentials).to.be.true;
    });
  });

  // Tests for getXhr function
  describe('getXhr', () => {
    beforeEach(function () {
      this.xhr = sinon.useFakeXMLHttpRequest();
      global.XMLHttpRequest = this.xhr;
      this.requests = [];

      this.xhr.onCreate = (xhr) => {
        this.requests.push(xhr);
      };
    });

    afterEach(function () {
      this.xhr.restore();
    });

    it('should return Xhr object reference', function () {
      const xmlHttp = Http.getXhr();
      expect(xmlHttp).to.not.be.null;
      expect(this.requests).to.have.lengthOf(1);
    });
  });
});
