'use strict';
define(['jquery','response-common'], function ($, rc) {
    $("#saveBtn").click(function() {
        var title = $("#title").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var describe = $("#describe").val();
        var paperObj = {
            title: title,
            describe: describe,
            startTime: startTime,
            endTime: endTime
        };

        savePaperAjax(paperObj, function(data) {
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
    function savePaperAjax(paperObj, callback) {
        $.ajax({
            type: "post",
            url:"/paper/save",
            data:paperObj,
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }
});