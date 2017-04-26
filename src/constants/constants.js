// Query params properties
const QUERY_PARAMS_PROPS = {
  LOCALE: 'locale',
  SEARCH_PARAMETERS: 'search_parameters',
  RESULT_COLUMNS: 'result_columns',
  SIMILAR_IMAGE: 'similar_image',
  CATEGORY: 'category_id',
};

// http methods
const HTTPMETHOD = {
  GET: 'GET',
  POST: 'POST',
};

// environment
const ENVIRONMENT = {
  PROD: 1,
  STAGE: 2,
};

// search parameters
const SEARCH_PARAMS = {
  WORDS: 'words',
  LIMIT: 'limit',
  OFFSET: 'offset',
  ORDER: 'order',
  CREATOR_ID: 'creator_id',
  MEDIA_ID: 'media_id',
  MODEL_ID: 'model_id',
  SERIE_ID: 'serie_id',
  SIMILAR: 'similar',
  SIMILAR_IMAGE: 'similar_image',
  SIMILAR_URL: 'similar_url',
  CATEGORY: 'category',
  FILTERS_3D_TYPE_ID: 'filters_3d_type_id',
  FILTERS_AGE: 'filters_age',
  FILTERS_AREA_PIXELS: 'filters_area_pixels',
  FILTERS_COLORS: 'filters_colors',
  FILTERS_CONTENT_TYPE_3D: 'filters_content_type_3d',
  FILTERS_CONTENT_TYPE_ALL: 'filters_content_type_all',
  FILTERS_CONTENT_TYPE_ILLUSTRATION: 'filters_content_type_illustration',
  FILTERS_CONTENT_TYPE_PHOTO: 'filters_content_type_photo',
  FILTERS_CONTENT_TYPE_VECTOR: 'filters_content_type_vector',
  FILTERS_CONTENT_TYPE_VIDEO: 'filters_content_type_video',
  FILTERS_CONTENT_TYPE_TEMPLATE: 'filters_content_type_template',
  FILTERS_HAS_RELEASES: 'filters_has_releases',
  FILTERS_ISOLATED_ON: 'filters_isolated_on',
  FILTERS_OFFENSIVE_2: 'filters_offensive_2',
  FILTERS_ORIENTATION: 'filters_orientation',
  FILTERS_PANORAMIC_ON: 'filters_panoramic_on',
  FILTERS_TEMPLATE_CATEGORY_ID: 'filters_template_category_id',
  FILTERS_TEMPLATE_TYPE_ID: 'filters_template_type_id',
  FILTERS_VIDEO_DURATION: 'filters_video_duration',
  THUMBNAIL_SIZE: 'thumbnail_size',
};

const SEARCH_PARAMS_ORDER = {
  RELEVANCE: 'relevance',
  CREATION: 'creation',
  POPULARITY: 'popularity',
  NB_DOWNLOADS: 'nb_downloads',
  UNDISCOVERED: 'undiscovered',
};

const SEARCH_PARAMS_ORIENTATION = {
  HORIZONTAL: 'horizontal',
  VERTICAL: 'vertical',
  SQUARE: 'square',
  ALL: 'all',
};

const SEARCH_PARAMS_HAS_RELEASES = {
  TRUE: 'true',
  FALSE: 'false',
  ALL: 'all',
};

const SEARCH_PARAMS_3D_TYPES = {
  MODELS: 1,
  LIGHTS: 2,
  MATERIALS: 3,
};

const SEARCH_PARAMS_TEMPLATE_CATEGORIES = {
  MOBILE: 1,
  WEB: 2,
  PRINT: 3,
  PHOTO: 4,
  FILM: 5,
  ART: 6,
};

const SEARCH_PARAMS_TEMPLATE_TYPES = {
  PSDT: 1,
  AIT: 2,
};

const SEARCH_PARAMS_THUMB_SIZES = {
  MEDIUM: 110,
  BIG: 160,
  XL: 500,
  XXL: 1000,
};

const SEARCH_PARAMS_AGE = {
  ONE_WEEK: '1w',
  ONE_MONTH: '1m',
  SIX_MONTH: '6m',
  ONE_YEAR: '1y',
  TWO_YEAR: '2y',
  ALL: 'all',
};

const SEARCH_PARAMS_VIDEO_DURATION = {
  TEN: '10',
  TWENTY: '20',
  THIRTY: '30',
  ABOVE_THIRTY: '30-',
  ALL: 'all',
};

const SEARCH_PARAMS_TYPE = {
  STRING: 0,
  INTEGER: 1,
  RANGE: 2,
  ARRAY: 3,
};

// result columns
const RESULTS_COLUMNS_TOSTRING = 'result_columns[]';

const RESULT_COLUMNS = {
  NB_RESULTS: 'nb_results',
  ID: 'id',
  TITLE: 'title',
  CREATOR_NAME: 'creator_name',
  CREATOR_ID: 'creator_id',
  COUNTRY_NAME: 'country_name',
  WIDTH: 'width',
  HEIGHT: 'height',
  THUMBNAIL_URL: 'thumbnail_url',
  THUMBNAIL_HTML_TAG: 'thumbnail_html_tag',
  THUMBNAIL_WIDTH: 'thumbnail_width',
  THUMBNAIL_HEIGHT: 'thumbnail_height',
  THUMBNAIL_110_URL: 'thumbnail_110_url',
  THUMBNAIL_110_WIDTH: 'thumbnail_110_width',
  THUMBNAIL_110_HEIGHT: 'thumbnail_110_height',
  THUMBNAIL_160_URL: 'thumbnail_160_url',
  THUMBNAIL_160_WIDTH: 'thumbnail_160_width',
  THUMBNAIL_160_HEIGHT: 'thumbnail_160_height',
  THUMBNAIL_220_URL: 'thumbnail_220_url',
  THUMBNAIL_220_WIDTH: 'thumbnail_220_width',
  THUMBNAIL_220_HEIGHT: 'thumbnail_220_height',
  THUMBNAIL_240_URL: 'thumbnail_240_url',
  THUMBNAIL_240_WIDTH: 'thumbnail_240_width',
  THUMBNAIL_240_HEIGHT: 'thumbnail_240_height',
  THUMBNAIL_500_URL: 'thumbnail_500_url',
  THUMBNAIL_500_WIDTH: 'thumbnail_500_width',
  THUMBNAIL_500_HEIGHT: 'thumbnail_500_height',
  THUMBNAIL_1000_URL: 'thumbnail_1000_url',
  THUMBNAIL_1000_WIDTH: 'thumbnail_1000_width',
  THUMBNAIL_1000_HEIGHT: 'thumbnail_1000_height',
  MEDIA_TYPE_ID: 'media_type_id',
  CATEGORY: 'category',
  CATEGORY_HIERARCHY: 'category_hierarchy',
  NB_VIEWS: 'nb_views',
  NB_DOWNLOADS: 'nb_downloads',
  CREATION_DATE: 'creation_date',
  KEYWORDS: 'keywords',
  HAS_RELEASES: 'has_releases',
  COMP_URL: 'comp_url',
  COMP_WIDTH: 'comp_width',
  COMP_HEIGHT: 'comp_height',
  IS_LICENSED: 'is_licensed',
  VECTOR_TYPE: 'vector_type',
  CONTENT_TYPE: 'content_type',
  FRAMERATE: 'framerate',
  DURATION: 'duration',
  STOCK_ID: 'stock_id',
  COMPS: 'comps',
  DETAILS_URL: 'details_url',
  TEMPLATE_TYPE_ID: 'template_type_id',
  TEMPLATE_CATEGORY_IDS: 'template_category_ids',
  MARKETING_TEXT: 'marketing_text',
  DESCRIPTION: 'description',
  SIZE_BYTES: 'size_bytes',
  PREMIUM_LEVEL_ID: 'premium_level_id',
  VIDEO_PREVIEW_URL: 'video_preview_url',
  VIDEO_PREVIEW_WIDTH: 'video_preview_width',
  VIDEO_PREVIEW_HEIGHT: 'video_preview_height',
  VIDEO_PREVIEW_CONTENT_LENGTH: 'video_preview_content_length',
  VIDEO_PREVIEW_CONTENT_TYPE: 'video_preview_content_type',
  VIDEO_SMALL_PREVIEW_URL: 'video_small_preview_url',
  VIDEO_SMALL_PREVIEW_WIDTH: 'video_small_preview_width',
  VIDEO_SMALL_PREVIEW_HEIGHT: 'video_small_preview_height',
  VIDEO_SMALL_PREVIEW_CONTENT_LENGTH: 'video_small_preview_content_length',
  VIDEO_SMALL_PREVIEW_CONTENT_TYPE: 'video_small_preview_content_type',
};

const LICENSE_STATE_PARAMS = {
  EMPTY: {
    EMPTY_LICENSE: '',
  },
  IMAGE: {
    STANDARD: 'standard',
    STANDARD_M: 'standard_m',
    EXTENDED: 'extended',
  },
  VIDEO: {
    VIDEO_HD: 'video_hd',
    VIDEO_4K: 'video_4k',
  },
  VECTOR_ASSETS: {
    STANDARD: 'standard',
    EXTENDED: 'extended',
  },
  ASSETS_3D: {
    STANDARD: 'standard',
  },
  TEMPLATES: {
    STANDARD: 'standard',
  },
};

const PURCHASE_STATE_PARAMS = {
  NOT_PURCHASED: 'not_purchased',
  PURCHASED: 'purchased',
  CANCELLED: 'cancelled',
  NOT_POSSIBLE: 'not_possible',
  JUST_PURCHASED: 'just_purchased',
  OVERAGE: 'overage',
};

export default class Constants {
  static get QUERY_PARAMS_PROPS() {
    return Object.freeze(QUERY_PARAMS_PROPS);
  }

  static get HTTPMETHOD() {
    return Object.freeze(HTTPMETHOD);
  }

  static get ENVIRONMENT() {
    return Object.freeze(ENVIRONMENT);
  }

  static get SEARCH_PARAMS() {
    return Object.freeze(SEARCH_PARAMS);
  }

  static get SEARCH_PARAMS_ORDER() {
    return Object.freeze(SEARCH_PARAMS_ORDER);
  }

  static get SEARCH_PARAMS_AGE() {
    return Object.freeze(SEARCH_PARAMS_AGE);
  }

  static get SEARCH_PARAMS_ORIENTATION() {
    return Object.freeze(SEARCH_PARAMS_ORIENTATION);
  }

  static get SEARCH_PARAMS_TYPE() {
    return Object.freeze(SEARCH_PARAMS_TYPE);
  }

  static get SEARCH_PARAMS_HAS_RELEASES() {
    return Object.freeze(SEARCH_PARAMS_HAS_RELEASES);
  }

  static get SEARCH_PARAMS_THUMB_SIZES() {
    return Object.freeze(SEARCH_PARAMS_THUMB_SIZES);
  }

  static get SEARCH_PARAMS_3D_TYPES() {
    return Object.freeze(SEARCH_PARAMS_3D_TYPES);
  }

  static get SEARCH_PARAMS_TEMPLATE_TYPES() {
    return Object.freeze(SEARCH_PARAMS_TEMPLATE_TYPES);
  }

  static get SEARCH_PARAMS_VIDEO_DURATION() {
    return Object.freeze(SEARCH_PARAMS_VIDEO_DURATION);
  }

  static get SEARCH_PARAMS_TEMPLATE_CATEGORIES() {
    return Object.freeze(SEARCH_PARAMS_TEMPLATE_CATEGORIES);
  }

  static get RESULTS_COLUMNS_TOSTRING() {
    return RESULTS_COLUMNS_TOSTRING;
  }

  static get RESULT_COLUMNS() {
    return Object.freeze(RESULT_COLUMNS);
  }

  static get SEARCH_FILES_ITERATOR_RETURN_ERROR() {
    return -1;
  }

  static get LICENSE_STATE_PARAMS() {
    return Object.freeze(LICENSE_STATE_PARAMS);
  }

  static get PURCHASE_STATE_PARAMS() {
    return Object.freeze(PURCHASE_STATE_PARAMS);
  }
}
