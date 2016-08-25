'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    $("#questionTbody").click(function (e) {
        var target = e.target;
        var $target = $(target);
        switch (target.className) {
            case "upd":
                location.href = "/question/upd/" + $target.data("id");
                break;
            case "del":
                if (confirm("确认删除?")) {
                    var id = $target.data("id");
                    delQuestionAjax(id, function (data) {
                        if (rc.response(data)) {
                            location.href = "/question/list";
                        }
                    })
                }
                break;
        }
    });

    /**
     * 删除问题
     * @param questionObj 对象
     * @param callback 回调
     */
    function delQuestionAjax(id, callback) {
        $.ajax({
            type: "post",
            url: "/question/del/" + id,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }
});