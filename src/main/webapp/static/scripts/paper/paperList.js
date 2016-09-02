'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    $("#paperTbody").click(function (e) {
        var target = e.target;
        var $target = $(target);
        switch (target.className) {
            case "icon-edit upd":
                location.href = "/paper/upd/" + $target.data("id");
                break;
            case "icon-trash del":
                if (confirm("确认删除?")) {
                    var id = $target.data("id");
                    delQuestionAjax(id, function (data) {
                        if (rc.response(data)) {
                            location.href = "/paper/list";
                        }
                    })
                }
                break;
            case "icon-upload-alt publish":
                if (confirm("确认发布?")) {
                    var id = $target.data("id");
                    publishQuestionAjax(id, function (data) {
                        if (rc.response(data)) {
                            location.href = "/paper/list";
                        }
                    })
                }
                break;
        }
    });

    /**
     * 删除问卷
     * @param questionObj 对象
     * @param callback 回调
     */
    function delQuestionAjax(id, callback) {
        $.ajax({
            type: "post",
            url: "/paper/del/" + id,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }

    /**
     * 发布
     * @param questionObj 对象
     * @param callback 回调
     */
    function publishQuestionAjax(id, callback) {
        $.ajax({
            type: "post",
            url: "/paper/publish/" + id,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }
});