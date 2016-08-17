define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$routeParams', '$location','userService','CONTEXT_PATH'];
    var sysUserUpdateController = function($scope, $http, $routeParams, $location, userService) {
        $scope.fields = {};
        $scope.sexItems = [
            {name: '男', value: '0'},
            {name: '女', value: '1'}
        ];
        $scope.userId = $routeParams.userId;
        
        userService.queryById($scope.userId).then(function(data) {
        	$scope.user = data;
        }, function(error) {
        	throw error;
        });
        
    	$scope.submit = function() {
    		console.log($scope.fields);
            $http.put(CONTEXT_PATH+"/user/", $scope.fields)
                .success(function(data, status, headers, config) {
                    console.log(data)
                })
                .error(function(response, status, headers, config) {
                    console.log(response)
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.sex = $scope.fields.sex.value;
        };
    };
    sysUserUpdateController.$inject = injectParams;
    app.register.controller('sysUserUpdateController', sysUserUpdateController);
});