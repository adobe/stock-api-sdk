// Includes the path
var path = require('path'),
    Jasmine,
    jasmine;

// Creates a custom reporter
var CustomReporter = require('jasmine-spec-reporter'),
    customReporter = new CustomReporter();

var reporters = require('jasmine-reporters');
var junitReporter = new reporters.JUnitXmlReporter({
    savePath: path.resolve('tests/reports/jasmine/'),
    consolidateAll: true,
});

// Loaded in a specific order in order to act like a browser
require('jsdom-global')();
require('jasmine-core');
Jasmine = require('jasmine');
jasmine = new Jasmine();
require('jasmine-ajax');

// Load local storage reference since it is not part of jsdom
require(path.resolve('tests/jasmine/helpers/localstorage'));

// Loads up Jasmine with the current spec file - does it's own path.resolve
jasmine.loadConfigFile('tests/jasmine/config/jasmine.json');

// Loads up the reporter
jasmine.addReporter(!process.env.JENKINS ? customReporter : junitReporter);

// Runs the tests
jasmine.execute();
