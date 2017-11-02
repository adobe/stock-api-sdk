import uuid from 'uuid';
import Constants from './../constants/constants';


const DECIMAL_RADIX = 10;

export default class Http {
  /**
   * Creates XMLHttpRequest object as per browser support
   * @returns {XMLHttpRequest} XMLHttpRequest object reference
   */
  static getXhr() {
    let xhr = null;

    try {
      // Chrome, Opera, Firefox, Safari
      xhr = new XMLHttpRequest();
    } catch (e1) {
      // Internet Explorer Browsers
      try {
        xhr = new window.ActiveXObject('Msxml2.XMLHTTP');
      } catch (e2) {
        try {
          xhr = new window.ActiveXObject('Microsoft.XMLHTTP');
        } catch (e3) {
          // do nothing
        }
      }
    }

    return xhr;
  }

  /**
   * Does Ajax call as per settings provided
   * @param {object} settings object for ajax request
   * @param {string} accessToken to be sent with Authorization header
   * @returns {promise} returns promise
   */
  static doXhr(settings) {
    return new Promise((onSuccess, onError) => {
      try {
        const defaultSettings = {
          async: true,
          method: Constants.HTTPMETHOD.GET,
          crossDomain: false,
          contentType: 'application/x-www-form-urlencoded',
          headers: {
            'cache-control': 'no-cache',
            'x-request-id': uuid(),
          },
        };

        const resolvedSettings = Object.assign({}, defaultSettings, settings);

        const xhr = Http.getXhr();

        if (!xhr) {
          throw new Error('Browser doesn\'t support XHR!');
        }

        if (settings.headers) {
          resolvedSettings.headers = Object.assign({}, defaultSettings.headers, settings.headers);
        }

        xhr.onreadystatechange = function onReadyStateChanged() {
          if (this.readyState === 4 && this.status === 200) {
            if (onSuccess) {
              onSuccess(this.response);
            }
          } else if (this.readyState === 4 && parseInt(this.status / 100, DECIMAL_RADIX) === 4) {
            if (onError) {
              onError(this.response);
            }
          } else if (this.readyState === 4 && this.status !== 0) {
            if (onError) {
              onError(this.statusText);
            }
          } else if (this.readyState === 4) {
            if (onError) {
              onError(new Error('Request got aborted!'));
            }
          }
        };

        xhr.open(resolvedSettings.method, resolvedSettings.url, resolvedSettings.async);

        // set the headers
        Object.keys(resolvedSettings.headers).forEach((header) => {
          xhr.setRequestHeader(header, resolvedSettings.headers[header]);
        });

        if (typeof resolvedSettings.contentType === 'string' && resolvedSettings.contentType) {
          xhr.setRequestHeader('Content-type', resolvedSettings.contentType);
        }

        if (resolvedSettings.crossDomain) {
          xhr.withCredentials = true;
        }

        xhr.responseType = 'json';

        if (resolvedSettings.method === Constants.HTTPMETHOD.GET) {
          xhr.send(null);
        } else {
          xhr.send(resolvedSettings.data);
        }
      } catch (er) {
        if (onError) {
          onError(er);
        }
      }
    });
  }
}
