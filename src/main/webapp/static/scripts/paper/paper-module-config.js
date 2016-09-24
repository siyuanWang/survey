'use strict';
define(['jquery','response-common'], function ($, rc) {
    $("#saveModule").click(function() {
        var paperId = $("#paperId").val();
        var name = $("#moduleName").val();
        var moduleIndex = $("#moduleIndex").val();
        if(name == "") {
            alert("模块名称不能为空");
            return false;
        }
        saveModule({
            paperId: paperId,
            name: name,
            moduleIndex: moduleIndex
        }, function(data) {
            if(rc.response(data)) {
                location.href = "/paper/config/module/"+ paperId;
            }
        })
    });

    /**
     * 新增模块
     * @param moduleObj 对象
     * @param callback 回调
     */
    function saveModule(moduleObj, callback) {
        $.ajax({
            type: "post",
            url:"/paper/module/save",
            data:moduleObj,
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }

    /**
     * 删除模块
     * @param moduelId 对象
     * @param callback 回调
     */
    function delModule(moduelId, callback) {
        $.ajax({
            type: "post",
            url:"/paper/module/del/"+moduelId,
            data:{},
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }

    /**
     * 删除模块
     */
    $(".delModule").click(function() {
        var moduleId = $(this).parents("tr").data("id");
        var paperId = $("#paperId").val();
        if(moduleId) {
            if(confirm("确认删除？")) {
                delModule(moduleId, function(data) {
                    if(rc.response(data)) {
                        location.href = "/paper/config/module/"+ paperId;
                    }
                })
            }
        }

    })
});