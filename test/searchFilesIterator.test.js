/**
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
import { expect } from 'chai';
import sinon from 'sinon';
import Constants from './../src/constants/constants';
import SearchFilesIterator from './../src/models/searchFilesIterator';
import StockApis from './../src/api/stockApis';
import Config from './../src/config/config';

const DECIMAL_RADIX = 10;

describe('SearchFilesIterator', () => {
  beforeEach(function () {
    this.config = new Config('testApiKey', 'testProduct', Constants.ENVIRONMENT.STAGE);
    this.stockApis = new StockApis(this.config);
    this.queryParams = {
      search_parameters: {
        words: 'mobile',
        limit: 5,
        filters_content_type_video: 1,
        filters_template_category_id: [
          Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.MOBILE,
          Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES.WEB,
        ],
      },
      result_columns: [
        Constants.RESULT_COLUMNS.ID,
        Constants.RESULT_COLUMNS.TITLE,
      ],
    };
    this.iterator = new SearchFilesIterator(this.stockApis,
      null,
      this.queryParams,
      false);
    this.callbackSuccess = sinon.spy();
    this.callbackError = sinon.spy();
    this.searchFilesResponse = {
      nb_results: 100,
      files: [
        { id: 1, title: 'first file' },
        { id: 2, title: 'second file' },
        { id: 3, title: 'third file' },
        { id: 4, title: 'fourth file' },
        { id: 5, title: 'fifth file' },
      ],
    };
    this.expectedSearchFilesResponse = {};
    this.expectedSearchFilesResponse.files = this.searchFilesResponse.files;
    this.searchFilesErrorResponse = 'error';
    this.searchFiles = sinon.stub(this.stockApis, 'searchFiles').callsFake(
      (accessToken, queryParams) => new Promise((resolve, reject) => {
        // force call to resolve
        if (!queryParams.error) {
          resolve(this.searchFilesResponse);
        } else {
          reject(this.searchFilesErrorResponse);
        }
      }));

    this.next = null;

    this.Expectation = {
      Success: 0,
      Failure: 1,
    };

    this.onSuccess = (test, endCondition, done, doOnTestSuccess, doOnTestFailure, testFunction) => {
      if (test === this.Expectation.Success) {
        doOnTestSuccess();
        if (endCondition()) {
          done();
        } else if (testFunction) {
          testFunction(test,
            endCondition,
            done,
            doOnTestSuccess,
            doOnTestFailure);
        }
      } else if (test === this.Expectation.Failure) {
        doOnTestFailure();
        done();
      }
    };

    this.onFailure = (test, endCondition, done, error,
                      doOnTestSuccess, doOnTestFailure, testFunction) => {
      if (test === this.Expectation.Success) {
        doOnTestFailure(error);
        done();
      } else if (test === this.Expectation.Failure) {
        doOnTestSuccess(error);
        // expect(error).to.be.ok;
        if (endCondition()) {
          done();
        } else if (testFunction) {
          testFunction(test,
            endCondition,
            done,
            doOnTestSuccess,
            doOnTestFailure);
        }
      }
    };

    this.next = (test, endCondition, done, doOnTestSuccess, doOnTestFailure) => {
      this.iterator.next()
        .then(() => {
          this.onSuccess(test,
                        endCondition,
                        done,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.next);
        },
        (error) => {
          this.onFailure(test,
                        endCondition,
                        done,
                        error,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.next);
        })
        .catch((e) => {
          done(e);
        });
    };

    this.previous = (test, endCondition, done, doOnTestSuccess, doOnTestFailure) => {
      this.iterator.previous()
        .then(() => {
          this.onSuccess(test,
                        endCondition,
                        done,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.previous);
        },
        (error) => {
          this.onFailure(test,
                        endCondition,
                        done,
                        error,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.previous);
        })
        .catch((e) => {
          done(e);
        });
    };

    this.previous = (test, endCondition, done, doOnTestSuccess, doOnTestFailure) => {
      this.iterator.previous()
        .then(() => {
          this.onSuccess(test,
                        endCondition,
                        done,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.previous);
        },
        (error) => {
          this.onFailure(test,
                        endCondition,
                        done,
                        error,
                        doOnTestSuccess,
                        doOnTestFailure,
                        this.previous);
        })
        .catch((e) => {
          done(e);
        });
    };

    this.skipTo = (test, skipNo, endCondition, done, doOnTestSuccess, doOnTestFailure) => {
      this.iterator.skipTo(skipNo)
        .then(() => {
          this.onSuccess(test,
                        endCondition,
                        done,
                        doOnTestSuccess,
                        doOnTestFailure);
        },
        (error) => {
          this.onFailure(test,
                        endCondition,
                        done,
                        error,
                        doOnTestSuccess,
                        doOnTestFailure);
        })
        .catch((e) => {
          done(e);
        });
    };
  });

  afterEach(function () {
    this.searchFiles.restore();
  });

  describe('totalSearchFiles', () => {
    it('should return error if next/previous/skipTo not called at all', function () {
      expect(this.iterator.totalSearchFiles())
        .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
    });

    it('should return total search files available in response with first next method responding with success', function (done) {
      this.iterator.next().then(() => {
        expect(this.iterator.totalSearchFiles()).to.equal(this.searchFilesResponse.nb_results);
        done();
      }, (error) => {
        expect(error).to.not.be.ok;
        done();
      })
      .catch((error) => {
        done(error);
      });
    });

    it('should return error with first next method responding with error', function (done) {
      this.iterator.queryParams.error = 1;
      this.iterator.next().then(() => {
        expect(true).to.be.false;
        done();
      }, (error) => {
        expect(error).to.be.ok;
        expect(this.iterator.totalSearchFiles())
          .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
        done();
      })
      .catch((error) => {
        done(error);
      });
    });

    it('should return total search files available from last success response with next method responding with error', function (done) {
      this.iterator.queryParams.error = 0;
      this.iterator.next().then(() => {
        this.iterator.queryParams.error = 1;
        this.iterator.next().then(() => {
          expect(true).to.be.false;
          done();
        }, (e) => {
          expect(e).to.be.ok;
          expect(this.iterator.totalSearchFiles()).to.equal(this.searchFilesResponse.nb_results);
          done();
        })
        .catch((e) => {
          done(e);
        });
      }, (e) => {
        expect(e).to.not.be.ok;
        done();
      })
      .catch((e) => {
        done(e);
      });
    });
  });

  describe('totalSearchPages', () => {
    it('should return error with next/previous/skipTo not called at all', function () {
      expect(this.iterator.totalSearchPages())
        .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
    });

    it('should return total search pages available in response with next method responding with success', function (done) {
      const expectedPages = this.searchFilesResponse.nb_results
                              / this.queryParams.search_parameters.limit;
      this.iterator.next().then(() => {
        expect(this.iterator.totalSearchPages()).to.equal(expectedPages);
        done();
      }, (e) => {
        expect(e).to.not.be.ok;
        done();
      })
      .catch((e) => {
        done(e);
      });
    });

    it('should return error with next method responding with error', function (done) {
      this.iterator.queryParams.error = 1;
      this.iterator.next().then(() => {
        expect(true).to.be.false;
        done();
      }, (e) => {
        expect(e).to.be.ok;
        expect(this.iterator.totalSearchPages())
          .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
        done();
      })
      .catch((e) => {
        done(e);
      });
    });

    it('should return total search pages available from last success response with next method responding with error', function (done) {
      const expectedPages = this.searchFilesResponse.nb_results
                              / this.queryParams.search_parameters.limit;
      this.iterator.queryParams.error = 0;
      this.iterator.next().then(() => {
        this.iterator.queryParams.error = 1;
        this.iterator.next().then(() => {
          expect(true).to.be.false;
          done();
        }, (e) => {
          expect(e).to.be.ok;
          expect(this.iterator.totalSearchPages()).to.equal(expectedPages);
          done();
        })
        .catch((e) => {
          done(e);
        });
      }, (e) => {
        expect(e).to.not.be.ok;
        done();
      })
      .catch((e) => {
        done(e);
      });
    });
  });

  describe('currentSearchPageIndex', () => {
    it('should return error with next/previous/skipTo not called at all', function () {
      expect(this.iterator.currentSearchPageIndex())
        .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
    });

    it('should return current search page index of response with next method responding with success', function (done) {
      let expectedPageIndex = -1;
      const totalPages = this.searchFilesResponse.nb_results
                        / this.queryParams.search_parameters.limit;
      const randNext = parseInt((Math.random() * totalPages), DECIMAL_RADIX);

      const doOnTestSuccess = () => {
        expectedPageIndex += 1;
        expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
      };

      const doOnTestFailure = () => {
        expect(true).to.be.false;
      };

      const condition = () => expectedPageIndex === randNext;

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success, condition, done, doOnTestSuccess, doOnTestFailure);
    });

    it('should return current search page index of last success response with next/previous/skipTo method responding with error', function (done) {
      let expectedPageIndex = -1;
      const totalPages = this.searchFilesResponse.nb_results
                        / this.queryParams.search_parameters.limit;
      const randNext = parseInt((Math.random() * (totalPages - 2)) + 2, DECIMAL_RADIX);
      const randPrev = (randNext + 1) -
                        parseInt(((Math.random() * (randNext - 2)) + 2), DECIMAL_RADIX);
      const randSkip = parseInt((Math.random() * (totalPages - 1)), DECIMAL_RADIX);

      // should return the current search page index randSkip since the skipTo
      // returned with success to move on page randSkip
      const testSkipToSuccessMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 0;
          this.skipTo(this.Expectation.Success,
            randSkip,
            () => true,
            done,
            () => {
              expect(this.iterator
                          .currentSearchPageIndex())
                          .to.equal(randSkip);
            },
            (errSkip) => {
              expect(errSkip).to.not.be.ok;
            });
        }
      };

      // should return the current search page index from the last response with previous
      // failing for now
      const testPrevFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.previous(this.Expectation.Failure,
            () => true,
            (err) => {
              testSkipToSuccessMethod(err);
            },
            (errPrev1) => {
              expect(errPrev1).to.be.ok;
              expect(this.iterator
                          .currentSearchPageIndex())
                          .to.equal(expectedPageIndex);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      // should return current page index correctly with previous returning with success
      const testPrevSuccessMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 0;
          this.previous(this.Expectation.Success,
            () => expectedPageIndex === randPrev,
            (err) => {
              testPrevFailureMethod(err);
            },
            () => {
              expectedPageIndex -= 1;
              expect(this.iterator
                          .currentSearchPageIndex())
                          .to.equal(expectedPageIndex);
            },
            (errPrev1) => {
              expect(errPrev1).to.not.be.ok;
            });
        }
      };

      // should return current page index from last success response with next failing this time
      const testNextFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.next(this.Expectation.Failure,
            () => true,
            (er) => {
              testPrevSuccessMethod(er);
            },
            (err) => {
              expect(err).to.be.ok;
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      // should return current page index with next succeeding
      const testNextSuccMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 0;
          this.next(this.Expectation.Success,
            () => expectedPageIndex === randNext,
            (er) => {
              testNextFailureMethod(er);
            },
            () => {
              expectedPageIndex += 1;
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            (er) => {
              expect(er).to.not.be.ok;
            });
        }
      };

      // should return error with next failing at beginning
      this.iterator.queryParams.error = 1;
      this.next(this.Expectation.Failure,
        () => true,
        (error) => {
          testNextSuccMethod(error);
        },
        (error) => {
          expect(error).to.be.ok;
          expect(this.iterator.currentSearchPageIndex())
            .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
        },
        () => {
          expect(true).to.be.false;
        });
    });
  });

  describe('getResponse', () => {
    it('should return empty object with next/previous/skipTo not called at all', function () {
      expect(this.iterator.getResponse()).to.be.empty;
    });

    it('should return JSON object with next method responding with success', function (done) {
      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => true, // run for one iteration only
        done,
        () => {
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });

    it('should return JSON object from last success response with with next method responding with error', function (done) {
      // should return current page index with next succeeding
      const testNextFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.next(this.Expectation.Failure,
            () => true,
            done,
            (err) => {
              expect(err).to.be.ok;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => true,
        (error) => {
          testNextFailureMethod(error);
        },
        () => {
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });
  });

  describe('next', () => {
    it('should reject the promise if some other search is already in progress for the same iterator', function (done) {
      this.iterator.queryParams.error = 0;
      this.iterator.next()
              .then(() => {
                expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
                expect(this.iterator.currentSearchPageIndex()).to.equal(0);
              },
              (er) => {
                expect(er).to.not.be.ok;
              })
              .catch((error) => {
                done(error);
              });

      this.iterator.next()
          .then(() => {
            expect(true).to.be.false;
            done();
          },
          (er) => {
            expect(er).to.be.an('error');
            expect(er.message).to.equal('Some other search is already in progress!');
            done();
          })
          .catch((error) => {
            done(error);
          });
    });

    it('should reject the promise if the iterator is instantiated with null stockApis object', function (done) {
      const iterator = new SearchFilesIterator(null,
                              null,
                              this.queryParams,
                              false);

      iterator.next()
          .then(() => {
            expect(true).to.be.false;
            done();
          },
          (er) => {
            expect(er).to.be.an('error');
            expect(er.message).to.equal('Library couldn\'t initialize properly! Please initialize the library first.');
            done();
          })
          .catch((error) => {
            done(error);
          });
    });

    it('should set the response and resolve the promise if stockApis.searchFiles return with success', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                        / this.queryParams.search_parameters.limit;
      const randNext = parseInt((Math.random() * (totalPages - 1)) + 1, DECIMAL_RADIX);
      let expectedPageIndex = -1;

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => expectedPageIndex === randNext,
        done,
        () => {
          expectedPageIndex += 1;
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });

    it('should reject the promise if stockApis.searchFiles return with error and restore the last success state', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;
      const randNext = parseInt((Math.random() * (totalPages - 1)) + 1, DECIMAL_RADIX);
      let expectedPageIndex = -1;

      // should return current page index from last success response with next failing this time
      const testNextFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.next(this.Expectation.Failure,
            () => true,
            done,
            (err) => {
              expect(err).to.be.ok;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      // should return current page index with next succeeding
      const testNextSuccMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 0;
          this.next(this.Expectation.Success,
            () => expectedPageIndex === randNext,
            (er) => {
              testNextFailureMethod(er);
            },
            () => {
              expectedPageIndex += 1;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            (er) => {
              expect(er).to.not.be.ok;
            });
        }
      };

      // should return error with next failing at beginning
      this.iterator.queryParams.error = 1;
      this.next(this.Expectation.Failure,
        () => true,
        (error) => {
          testNextSuccMethod(error);
        },
        (error) => {
          expect(error).to.be.ok;
          expect(this.iterator.getResponse()).to.empty;
          expect(this.iterator.currentSearchPageIndex())
            .to.equal(Constants.SEARCH_FILES_ITERATOR_RETURN_ERROR);
        },
        () => {
          expect(true).to.be.false;
        });
    });

    it('should reject the promise with error No more search results available if called at last search page', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;
      let expectedPageIndex = -1;

      const testNextFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.next(this.Expectation.Failure,
            () => true,
            done,
            (err) => {
              expect(err).to.be.ok;
              expect(err.message).to.match(/No more search results available/);
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            (er) => {
              expect(er).to.not.be.ok;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => expectedPageIndex === (totalPages - 1),
        (er) => {
          testNextFailureMethod(er);
        },
        () => {
          expectedPageIndex += 1;
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });
  });

  describe('previous', () => {
    it('should reject the promise with error No more search results available if called first than next/skipTo', function (done) {
      this.iterator.queryParams.error = 1;
      this.previous(this.Expectation.Failure,
        () => true,
        done,
        (error) => {
          expect(error).to.be.ok;
          expect(error).to.be.an('error');
          expect(error.message).to.match(/No more search results available/);
        },
        () => {
          expect(true).to.be.false;
        });
    });

    it('should set the response and resolve the promise if stockApis.searchFiles return with success', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;

      const randNext = parseInt((Math.random() * (totalPages - 2)) + 2, DECIMAL_RADIX);
      const randPrev = (randNext + 1) -
                        parseInt(((Math.random() * (randNext - 2)) + 2), DECIMAL_RADIX);
      let expectedPageIndex = -1;

      const testPrevSuccessMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 0;
          this.previous(this.Expectation.Success,
            () => expectedPageIndex === randPrev,
            done,
            () => {
              expectedPageIndex -= 1;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            (er) => {
              expect(er).to.not.be.ok;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => expectedPageIndex === randNext,
        (error) => {
          testPrevSuccessMethod(error);
        },
        () => {
          expectedPageIndex += 1;
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });

    it('should reject the promise if stockApis.searchFiles return with error and restore the last success state', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;
      const randNext = parseInt((Math.random() * (totalPages - 2)) + 2, DECIMAL_RADIX);
      let expectedPageIndex = -1;

      const testPrevFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.previous(this.Expectation.Failure,
            () => true,
            done,
            (er) => {
              expect(er).to.be.ok;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => expectedPageIndex === randNext,
        (error) => {
          testPrevFailureMethod(error);
        },
        () => {
          expectedPageIndex += 1;
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(expectedPageIndex);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });
  });

  describe('skipTo', () => {
    it('should reject the promise with error Page index out of bounds if called with negative page number', function (done) {
      this.skipTo(this.Expectation.Failure,
        -10,
        () => true,
        done,
        (error) => {
          expect(error).to.be.ok;
          expect(error).to.be.an('error');
          expect(error.message).match(/Page index out of bounds/);
        },
        () => {
          expect(true).to.be.false;
        });
    });

    it('should reject the promise with error Page index out of bounds if called with page index higher than total search pages available', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;
      const randPageIndex = (Math.random() * 100) + totalPages;

      const testSkipToSuccessMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.skipTo(this.Expectation.Failure,
            randPageIndex,
            () => true,
            done,
            (err) => {
              expect(err).to.be.ok;
              expect(err).to.be.an('error');
              expect(err.message).match(/Page index out of bounds/);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.next(this.Expectation.Success,
        () => true,
        (error) => {
          testSkipToSuccessMethod(error);
        },
        () => {
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(0);
        },
        (er) => {
          expect(er).to.not.be.ok;
        });
    });

    it('should set the response and resolve the promise if stockApis.searchFiles return with success', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                        / this.queryParams.search_parameters.limit;
      const randPageIndex = parseInt(Math.random() * totalPages, DECIMAL_RADIX);

      this.iterator.queryParams.error = 0;
      this.skipTo(this.Expectation.Success,
        randPageIndex,
        () => true,
        done,
        () => {
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(randPageIndex);
        },
        (errSkip) => {
          expect(errSkip).to.not.be.ok;
        });
    });

    it('should reject the promise if stockApis.searchFiles return with error and restore the last success state', function (done) {
      const totalPages = this.searchFilesResponse.nb_results
                          / this.queryParams.search_parameters.limit;
      const randPageIndex = parseInt(Math.random() * totalPages, DECIMAL_RADIX);
      const errorRandPageIndex = parseInt(Math.random() * totalPages, DECIMAL_RADIX);

      const testSkipToFailureMethod = (error) => {
        if (error) {
          done(error);
        } else {
          this.iterator.queryParams.error = 1;
          this.skipTo(this.Expectation.Failure,
            errorRandPageIndex,
            () => true,
            done,
            (err) => {
              expect(err).to.be.ok;
              expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
              expect(this.iterator.currentSearchPageIndex()).to.equal(randPageIndex);
            },
            () => {
              expect(true).to.be.false;
            });
        }
      };

      this.iterator.queryParams.error = 0;
      this.skipTo(this.Expectation.Success,
        randPageIndex,
        () => true,
        (error) => {
          testSkipToFailureMethod(error);
        },
        () => {
          expect(this.iterator.getResponse()).to.deep.equal(this.expectedSearchFilesResponse);
          expect(this.iterator.currentSearchPageIndex()).to.equal(randPageIndex);
        },
        (errSkip) => {
          expect(errSkip).to.not.be.ok;
        });
    });
  });
});
