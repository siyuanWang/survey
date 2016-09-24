'use strict';
define(['jquery','response-common'], function ($, rc) {

    var containerIds = [];
    $(".paperModule").each(function(index) {
        containerIds.push({
            moduleId: $(this).data("moduleid"),
            moduleName: $(this).data("modulename")
        });
    });

    //迭代所有的module
    for(var i = 0, length = containerIds.length; i < length; i++) {
        var obj = containerIds[i];
        var $chooseTbody = $("#questionContainer"+obj.moduleId).find("tbody");
        $chooseTbody.click(function(e) {
            var target = e.target, $target = $(e.target);
            //移除试题
            if(target.className == "btn btn-danger") {
                $target.parents("tr").remove();
            }
            order();
        });
    }


    $(".chooseQues").click(function(e) {
        var $this = $(this);
        var $tr = $this.parents("tr");
        var $tds = $tr.find("td");
        var data = {
            title: $tds.eq(0).text().trim(),
            type: $tds.eq(1).text().trim(),
            id: $tr.data("id")
        };
        console.log(data);
        add(data, $("#questionContainer"+$this.data("moduleid")).find("tbody"));
    })


    /**
     * 生成问卷
     */
    $("#saveConfig").click(function() {
        var paperId = $("#paperId").val();
        var paper = {};
        var modules = [];
        for(var i = 0, length = containerIds.length; i < length; i++) {
            var obj = containerIds[i];
            var $container = $("#questionContainer"+obj.moduleId);
            var $chooseTbody = $container.find("tbody");
            var module = {};
            var questions = [];
            $chooseTbody.find("tr").each(function(index) {
                var $tr = $(this);
                var questionId = $tr.data("id");
                questions.push({
                    id: questionId
                })
            });
            module['questions'] = questions;
            module['paperId'] = paperId;
            module['id'] = obj.moduleId;
            modules.push(module)
        }
        paper['modules'] = modules;

        saveConfigAjax(paperId, paper, function(data) {
            if(rc.response(data)) {
                location.href = "/paper/list";
            }
        })
    });

    function saveConfigAjax(paperId, paper, callback) {
        $.ajax({
            type: "post",
            url:"/paper/config/"+paperId,
            data:{paperJson: JSON.stringify(paper)},
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }

    /**
     * 新增试题
     * @param data
     */
    function add(data, $chooseTbody) {

        $chooseTbody.append(
            '<tr data-id="'+data.id+'">' +
            '<td>&nbsp;</td>' +
            '<td>'+data.title+'</td>' +
            '<td>'+data.type+'</td>' +
            '<td><a class="btn btn-danger">移除</a></td>' +
            '</tr>'
        );
        order();
    }

    /**
     * 移除试题
     */
    function remove() {

    }

    /**
     * 重新排序
     */
    function order() {
        for(var i = 0, length = containerIds.length; i < length; i++) {
            var obj = containerIds[i];
            var $chooseTbody = $("#questionContainer"+obj.moduleId).find("tbody");
            $chooseTbody.children().each(function(index) {
                $(this).find("td").eq(0).text(index+1)
            });
        }

    }
});