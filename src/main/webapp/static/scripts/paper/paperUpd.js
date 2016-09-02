'use strict';
define(['jquery','response-common'], function ($, rc) {
    $("#updBtn").click(function() {
        var title = $("#title").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var id = $("#paperId").val();
        var describe = $("#describe").val();
        var paperObj = {
            id: id,
            title: title,
            startTime: startTime,
            endTime: endTime,
            describe: describe
        };
        updPaperAjax(paperObj, function(data) {
            if(rc.response(data)) {
                location.href = "/paper/list";
            }
        })
    });

    /**
     * 新增问卷
     * @param questionObj 对象
     * @param callback 回调
     */
    function updPaperAjax(paperObj, callback) {
        $.ajax({
            type: "post",
            url:"/paper/upd",
            data:paperObj,
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }
});