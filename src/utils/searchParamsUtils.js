import Utils from './utils';
import Constants from './../constants/constants';

const DECIMAL_RADIX = 10;

const searchParametersConfig = {
  words: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    toString: '[words]',
  },
  limit: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    max: 64,
    min: 1,
    toString: '[limit]',
  },
  offset: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    toString: '[offset]',
  },
  order: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    exists_in: Constants.SEARCH_PARAMS_ORDER,
    toString: '[order]',
  },
  creator_id: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[creator_id]',
  },
  media_id: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[media_id]',
  },
  model_id: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[model_id]',
  },
  serie_id: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[serie_id]',
  },
  similar: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[similar]',
  },
  similar_image: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[similar_image]',
  },
  similar_url: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    toString: '[similar_url]',
  },
  category: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    toString: '[category]',
  },
  filters_3d_type_id: {
    type: Constants.SEARCH_PARAMS_TYPE.ARRAY,
    exists_in: Constants.SEARCH_PARAMS_3D_TYPES,
    toString: '[filters][3d_type_id][]',
  },
  filters_age: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    exists_in: Constants.SEARCH_PARAMS_AGE,
    toString: '[filters][age]',
  },
  filters_area_pixels: {
    type: Constants.SEARCH_PARAMS_TYPE.RANGE,
    toString: '[filters][area_pixels]',
  },
  filters_colors: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    toString: '[filters][colors]',
  },
  filters_content_type_3d: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:3d]',
  },
  filters_content_type_all: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:all]',
  },
  filters_content_type_illustration: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:illustration]',
  },
  filters_content_type_photo: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:photo]',
  },
  filters_content_type_vector: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:vector]',
  },
  filters_content_type_video: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:video]',
  },
  filters_content_type_template: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][content_type:template]',
  },
  filters_has_releases: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    exists_in: Constants.SEARCH_PARAMS_HAS_RELEASES,
    toString: '[filters][has_releases]',
  },
  filters_isolated_on: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][isolated:on]',
  },
  filters_offensive_2: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][offensive:2]',
  },
  filters_orientation: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    exists_in: Constants.SEARCH_PARAMS_ORIENTATION,
    toString: '[filters][orientation]',
  },
  filters_panoramic_on: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    min: 0,
    max: 1,
    toString: '[filters][panoramic:on]',
  },
  filters_template_category_id: {
    type: Constants.SEARCH_PARAMS_TYPE.ARRAY,
    exists_in: Constants.SEARCH_PARAMS_TEMPLATE_CATEGORIES,
    toString: '[filters][template_category_id][]',
  },
  filters_template_type_id: {
    type: Constants.SEARCH_PARAMS_TYPE.ARRAY,
    exists_in: Constants.SEARCH_PARAMS_TEMPLATE_TYPES,
    toString: '[filters][template_type_id][]',
  },
  filters_video_duration: {
    type: Constants.SEARCH_PARAMS_TYPE.STRING,
    exists_in: Constants.SEARCH_PARAMS_VIDEO_DURATION,
    toString: '[filters][video_duration]',
  },

  thumbnail_size: {
    type: Constants.SEARCH_PARAMS_TYPE.INTEGER,
    exists_in: Constants.SEARCH_PARAMS_THUMB_SIZES,
    toString: '[filters][thumbnail_size]',
  },
};

export default class SearchParamsUtils {
  /**
   * Validates the search query parameters
   * @param {object} params object reference of search_parameters
   */
  static validate(params) {
    Object.keys(params).forEach((param) => {
      const value = params[param];
      const config = searchParametersConfig[param];

      const supportedParams = Object.keys(Constants.SEARCH_PARAMS)
                                  .map(key => Constants.SEARCH_PARAMS[key]);

      if (config === undefined
          || !Utils.doesArrayContainValue(supportedParams, param)) {
        throw new Error(`Search parameter '${param}' not supported!`);
      }

      let permittedValues = [];
      if (config.exists_in && Utils.isObject(config.exists_in)) {
        permittedValues = Object.keys(config.exists_in).map(key => config.exists_in[key]);
      }

      switch (config.type) {
        case Constants.SEARCH_PARAMS_TYPE.STRING: {
          if (!Utils.isString(value)) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have string value.`);
          }

          if (config.exists_in && !Utils.doesArrayContainValue(permittedValues, value)) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have value from [ ${permittedValues.join(', ')} ].`);
          }
          break;
        }

        case Constants.SEARCH_PARAMS_TYPE.INTEGER: {
          if (!Utils.isInteger(value)) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have integers.`);
          }

          if (config.min !== undefined && parseInt(value, DECIMAL_RADIX) < config.min) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have integers >= ${config.min}.`);
          }

          if (config.max !== undefined && parseInt(value, DECIMAL_RADIX) > config.max) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have integers <= ${config.max}.`);
          }

          break;
        }

        case Constants.SEARCH_PARAMS_TYPE.RANGE: {
          const matchResults = value.match(/^([0-9]*)-([0-9]*)$/);
          if (!matchResults) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have range values. For e.g. 0-250000.`);
          }

          if (matchResults.length < 3 || matchResults[1] > matchResults[2]) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have range values from lower to higher. For e.g. 0-250000.`);
          }
          break;
        }

        case Constants.SEARCH_PARAMS_TYPE.ARRAY: {
          if (!Utils.isArray(value)) {
            throw new Error(`Invalid value of Search Parameter '${param}'. It can only have array.`);
          }

          if (config.exists_in) {
            value.forEach((item) => {
              if (!Utils.doesArrayContainValue(permittedValues, item)) {
                throw new Error(`Invalid value of Search Parameter '${param}'. It can only have values from [ ${permittedValues.join(', ')} ].`);
              }
            });
          }

          break;
        }

        default:
      }
    });
  }

  /**
   * Encodes the search query parameters object into uri
   * @param {object} params object reference of search_parameters
   * @returns {string} encoded uri string
   */
  static encodeURI(params) {
    const uri = [];
    Object.keys(params).forEach((param) => {
      const value = params[param];
      const config = searchParametersConfig[param];
      const paramToStr = `search_parameters${config.toString}`;

      switch (config.type) {
        case Constants.SEARCH_PARAMS_TYPE.STRING:
        case Constants.SEARCH_PARAMS_TYPE.INTEGER:
        case Constants.SEARCH_PARAMS_TYPE.RANGE:
          uri.push(`${encodeURIComponent(paramToStr)}=${encodeURIComponent(value)}`);
          break;
        case Constants.SEARCH_PARAMS_TYPE.ARRAY:
          params[param].forEach((item) => {
            uri.push(`${encodeURIComponent(paramToStr)}=${encodeURIComponent(item)}`);
          });
          break;
        default:
      }
    });

    return uri.join('&');
  }
}
