'use strict';
define(['jquery','response-common'], function ($, rc) {
    $(".chooseQuestion").click(function() {
        var $this = $(this);
        var $tr = $this.parents("tr");
        var $tds = $tr.find("td");
        var data = {
            title: $tds.eq(0).text().trim(),
            type: $tds.eq(1).text().trim(),
            id: $tr.data("id")
        }
        console.log(data);
        add(data);
    });

    var $chooseTbody = $("#chooseTbody");

    $chooseTbody.click(function(e) {
        var target = e.target, $target = $(e.target);

        if(target.className == "btn btn-danger") {
            $target.parents("tr").remove();
        }

        order();
    });

    /**
     * 生成问卷
     */
    $("#saveConfig").click(function() {
        var questionIds = [];
        var paperId = $("#paperId").val();
        $chooseTbody.children().each(function(index) {
            var questionId = $(this).data("id");
            questionIds.push(questionId);
        });
        saveConfigAjax(paperId, questionIds, function(data) {
            if(rc.response(data)) {
                location.href = "/paper/list";
            }
        })
    });

    function saveConfigAjax(paperId, questionIds, callback) {
        $.ajax({
            type: "post",
            url:"/paper/config/"+paperId,
            data:{questionIds: questionIds.join(",")},
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
    function add(data) {
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
        $chooseTbody.children().each(function(index) {
            $(this).find("td").eq(0).text(index+1)
        });
    }
});