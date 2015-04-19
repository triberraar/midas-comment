module.exports = function(grunt) {
  grunt.initConfig({
	  karma: {
          unit: {
              configFile: 'karma.conf.js'
          }
      }
  });
 
  // load the Grunt task
  grunt.loadNpmTasks('grunt-karma');
  
  grunt.registerTask('test', ['karma:unit']);
};