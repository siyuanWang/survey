'use strict';
define(['jquery'], function ($) {
    $("#questionTbody").click(function(e) {
        var target = e.target;
        var $target = $(target);
        switch(target.className) {
            case "upd":
                location.href = "/question/upd/"+ $target.data("id");
                break;
            case "del":
                alert("删除")
                break;
        }
    })
});