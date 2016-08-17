'use strict';
define(['app'], function(app) {
    app.factory('indexService', ['$http','$q','CONTEXT_PATH', function($http, $q, contextPath) {

        function queryMenus() {
            var defer = $q.defer();
            $http.get(contextPath+'/menu/query')
                .success(function(data, status, headers, config) {
                    defer.resolve(data);
                })
                .error(function(response, status, headers, config) {
                    defer.reject(response);
                });
            return defer.promise;
        }

        return {
            queryMenus: queryMenus
        }
    }]);
});