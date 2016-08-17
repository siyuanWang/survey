'use strict';
define(['app'], function(app) {
	app.directive('equals', function() {
		return {
			restrict: 'A',
			require: 'ngModel',
			link: function(scope, elem, attrs, ngModel) {
				if(!ngModel) return;
				scope.$watch(attrs.ngModel, function() {
					validate();
				});
				attrs.$observe('equals', function (val) {
					validate();
				});

				var validate = function() {
					var val1 = ngModel.$viewValue;
					var val2 = attrs.equals;

					ngModel.$setValidity('equals', ! val1 || ! val2 || val1 === val2);
				};
			}
		}
	});

	app.directive('password', function() {
		var regExp = /^[0-9a-zA-Z]{6,16}$/;
		return {
			restrict: 'A',
			require: 'ngModel',
			link: function(scope, elem, attrs, ngModel) {
				ngModel.$validators.password = function(modelValue, viewValue) {
					if (ngModel.$isEmpty(modelValue))
						return true;

					if (regExp.test(viewValue))
						return true;

					return false;
				};
			}
		}
	});


    app.directive('menuDirective', ['CONTEXT_PATH',function (contextPath) {
        return {
            restrict: 'A',
            scope: false,
            replace: true,
            templateUrl: contextPath+"/views/template/menu.html",
            link: function (scope, element) {
            	/**
            	 * 递归创建menu
            	 * $mainMenu
            	 * menuNodes
            	 */
            	function createNode($mainMenu, menuNodes) {
            		for(var i = 0, length = menuNodes.length; i < length; i++) {
            			var menuNode = menuNodes[i];
            			var $li = $("<li></li>");
                		if(menuNode.active) {
                			$li.addClass("active opened active");
                		}
                		var $a = $("<a href='"+menuNode.href+"'></a>").appendTo($li);
                		if(menuNode.icon) {
                			$a.append("<i class='"+menuNode.icon+"'></i>");
                		}
                		$a.append("<span>"+menuNode.text+"</span>")
                		if(menuNode.hasChildren) {
                			var $ul = $("<ul></ul>");
                			$li.append($ul);
                			createNode($ul, menuNode.children);
                		}
                		$mainMenu.append($li);
            		}
            		
            	}
            	
            	scope.$on("menuDataPrepared", function() {
            		console.log(scope.menus);
            		createNode($("#main-menu"), scope.menus);
                	//初始化
                	require(['neon-custom'], function() {
                		
                	})
            	});
            }
        };
    }]);
    /**
     * server-side table：server pagination/search/order and so on.
     */
    app.directive('serverSideDataTable', ['CONTEXT_PATH','$compile',function (contextPath, $compile) {
        return {
            restrict: 'A',
            scope: false,
            link: function (scope, element, attrs) {
                //monitor the broadCast 'paginationData'
            	var dataTable = $(element).DataTable({
                    serverSide: true,
                    processing: true,
                    ajax: {
                    	url:contextPath+attrs.tableUrl,
                    	data: function(data) {
                    		var jsonData = {dataTableParamsJson: JSON.stringify(data)};
                    		return jsonData;
                    	}
                    },
                    columns: getColumns(attrs.tableColumns),
                    createdRow: function ( row, data, index ) {
                    	if(scope.dataTableRowFormat)scope.dataTableRowFormat(row, data, index);
                    },
                    fnDrawCallback: function( oSettings ) {
                        $compile(element.find("tbody"))(scope);
                    }
                });
            	
            	function getColumns(tableColumns) {
            		var array = [];
            		var columns = tableColumns.split(",");
            		for(var i = 0, length = columns.length; i < length; i++) {
            			var obj = {};
            			obj["data"] = columns[i];
            			array.push(obj);
            		}
            		console.log(array)
            		return array;
            	}
            	
            	dataTable.columns().every(function() {
                    var that = this;
                    $('input', this.footer()).on('keypress', function (e) {
                        if (that.search() !== this.value) {
                        	if(e.keyCode == 13) {
            	                // Call the API search function
                        		that.search( this.value ).draw();
            	            }
            	            return;
                        }
                    } );
                });
            	
            	$('.dataTables_filter input').unbind().bind("keypress", function(e) { // Bind our desired behavior
    	            // If the length is 3 or more characters, or the user pressed ENTER, search
    	            if(e.keyCode == 13) {
    	                // Call the API search function
    	            	dataTable.search(this.value).draw();
    	            }
    	            return;
    	        })
        	    	
            }
        };
    }]);
    /**
     * customer-side auto pagination\search\order
     */
    app.directive('customerSideDataTable', ['$http','$compile', function ($http, $compile) {
        return {
            restrict: 'A',
            scope: false,
            link: function (scope, element, attrs) {
            	scope.$on("tableDataPrepared", function() {
                	$(element).DataTable();
            	})

            }
        };
    }]);
    /**
     * the ng-repeat complete event.when over, broadcase to draw dataTable.
     */
    app.directive('onRepeatOver', ['$timeout',function ($timeout) {
    	return {
    		restrict: 'A',
    		scope: false,
    		link: function (scope, element, attrs) {
                if (scope.$last === true) {
                    $timeout(function () {
                    	scope.$parent.$broadcast("tableDataPrepared");
                    });
                }
    		}
    	};
    }]);
});