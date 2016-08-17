define(['app'], function(app) {
    var injectParams = ['$scope', '$http', '$location','CONTEXT_PATH'];
    var sysUserAddController = function($scope, $http, $location, CONTEXT_PATH) {
        $scope.fields = {};
        $scope.sexItems = [
            {name: '男', value: '0'},
            {name: '女', value: '1'}
        ];
    	$scope.submit = function() {
    		console.log($scope.fields);
            $http.post(CONTEXT_PATH+"/user/", $scope.fields)
                .success(function(data, status, headers, config) {
                    $location.path("/user");
                })
                .error(function(response, status, headers, config) {
                    $location.path("/error");
                });
    	};

        $scope.validateBeforSubmit = function(fields) {
            $scope.fields.sex = $scope.fields.sex.value;
        };
    };
    sysUserAddController.$inject = injectParams;
    app.register.controller('sysUserAddController', sysUserAddController);
});