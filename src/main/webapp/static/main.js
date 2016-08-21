'use strict';

requirejs.config({
    baseUrl: '/static',
    paths: {
        'jquery': '/static/assets/js/jquery.min',
        'jquery-ui': '/static/assets/js/jquery.ui.custom',
        'bootstrap-js':'/static/assets/js/bootstrap.min',
        'matrix': '/static/assets/js/matrix'
    },
    shim:{
        'jquery': {
            exports:'jquery'
        },
        'bootstrap-js': {
            deps:['jquery'],
            exports: 'bootstrap-js'
        },
        'jquery-ui':{
            deps:['jquery']
        },
        'matrix':{
            deps:['jquery']
        }
    }
});

require(
	[
	 	'jquery',
        'jquery-ui',
        'bootstrap-js',
        'matrix'
    ],
    function(angular, app) {

    }
);
