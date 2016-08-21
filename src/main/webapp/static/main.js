'use strict';

requirejs.config({
    baseUrl: '/static',
    paths: {
        'jquery': '/static/assets/js/jquery.min',
        'jquery-ui': '/static/assets/js/jquery.ui.custom',
        'bootstrap-js':'/static/assets/js/bootstrap.min',
        'matrix': '/static/assets/js/matrix',
        'datatable': '/static/assets/js/jquery.dataTables.min',
        'jquery-uniform': '/static/assets/js/jquery.uniform',
        'select2': '/static/assets/js/select2.min',
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
        },
        datatable:{
            deps:['jquery']
        },
        'jquery-uniform': {
            deps:['jquery']
        },
        'select2': {
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
    function() {

    }
);
