/*eslint strict: ["error", "never"]*/

/**
 * jsdom does not support local storage.
 * This helper is to implement the missing local storage functions
 *
 * @type {{getItem: Window.sessionStorage.getItem, setItem: Window.sessionStorage.setItem}}
 */
window.localStorage = window.sessionStorage = {

    /**
     * Gets a specific item from `localstorage`
     * @param {string} key
     * @returns {*} key found
     */
    getItem: function (key) {
        return this[key];
    },

    /**
     * Sets a specific item from `localstorage`
     * @param {string} key
     * @param {string} value
     */
    setItem: function (key, value) {
        this[key] = value;
    },
};
