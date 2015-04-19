module.exports = function(config) {
    config.set({
 
        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '',
 
 
        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['jasmine'],
 
 
        // list of files / patterns to load in the browser
        files: [
            'src/main/resources/static/library/angular/angular.js',
            'src/main/resources/static/library/angular-resource/angular-resource.js',
            'src/main/resources/static/library/angular-route/angular-route.js',
            'src/main/resources/static/library/lodash/lodash.js',
            'src/main/resources/static/library/angular-mocks/angular-mocks.js',
            'src/main/resources/static/js/**/*.js',
            'src/test/resources/static/js/**/*.js',
        ],
 
 
        // list of files to exclude
        exclude: [
        ],
 
 
        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {
 
        },
 
 
        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress'],
 
 
        // web server port
        port: 9876,
 
 
        // enable / disable colors in the output (reporters and logs)
        colors: true,
 
 
        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,
 
 
        //A list of log appenders to be used. See the documentation for log4js for more information.
        loggers: [{type: 'console'}],
 
        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,
 
 
        // start these browsers
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['Chrome'],
 
 
        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: true
    });
};