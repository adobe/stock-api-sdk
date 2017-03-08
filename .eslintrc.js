module.exports = {
    'env': {
        'browser': true,
        'es6': true,
    },
    'plugins': ['jasmine'],
    'extends': 'plugin:jasmine/recommended',
    'rules': {
        'comma-spacing': [
            'error',
            {
                'before': false,
                'after': true,
            }
        ],
        'jasmine/missing-expect': 'error',
        'jasmine/no-spec-dupes': 'off',
        'strict': [
            'error',
            'function'
        ],
        'comma-dangle': [
            'error',
            'always-multiline'
        ],
        'semi': [
            'error',
            'always',
        ],
        'no-debugger': 'error',
        'no-console': 'off',
        'require-jsdoc': 'error',
        'valid-jsdoc': [
            'error',
            {
                'requireParamDescription': false,
                'requireReturn': false
            }
        ],
        'no-empty': 'error',
        'indent': [
            'error',
            4,
            {
                'SwitchCase': 1
            }
        ],
        'quotes': [
            'error',
            'single',
            'avoid-escape'
        ],
        'no-trailing-spaces': [
            'error'
        ],
        'no-func-assign': 'error',
        'no-unused-vars': 'off',
        'no-use-before-define': 'off',
        'comma-style': [
            'error',
            'last'
        ],
        'eol-last': 'warn',
        'keyword-spacing': 'warn',
        'newline-before-return': 'off',
        'no-spaced-func': 'warn',
        'no-multiple-empty-lines': [
            'warn',
                {
                    'max': 1
                }
        ],
        'space-before-blocks': 'warn',
        'space-in-parens': ['error', 'never'],
        'semi-spacing': [
            'warn',
            {
                'before': false,
                'after': true
            }
        ],
        'no-extra-semi': 'error',
        'no-irregular-whitespace': 'warn',
        'valid-typeof': 'error',
        'no-undef-init': 'error',
        'no-alert': 'error',
        'no-multi-spaces': 'error',
        'space-infix-ops': 'error',
        'vars-on-top': 'error',
        'camelcase': ['error', {
            'properties': 'never'
        }]
    }
};
