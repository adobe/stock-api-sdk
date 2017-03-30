module.exports = {
    "extends": "airbnb-base",
    "env": {
        "browser": 1,
    },
    "plugins": [
        "import"
    ],
    'import/ignore': [
      'node_modules',
      '.(css|scss|sass)$',
      '.json$',
    ],
    'parserOptions': {
        'sourceType': 'module',
    },
    'rules': {
    'no-console': 1,
    'import/no-unresolved': 0,
    'import/default': 1,
    'new-cap': 0,
  },
};
