'use strict';
define(['jquery'], function ($) {
    var response = function(data) {
        var flag = false;
        if(data.resultCode == 1) {
            alert(data.result);
            flag = true;
        } else {
            alert(data.result);
        }
        return flag;
    };

    return {
        response: response
    }
});