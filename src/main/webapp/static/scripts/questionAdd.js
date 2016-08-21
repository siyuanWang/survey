'use strict';
define(['jquery'], function ($) {
    var $option = $(".option");
    var $optionsDiv = $("#options");
    var $plus = $('<a class="btn btn-default" data-operator="plus"><i class="icon-plus" data-operator="plus"></i></a>');
    var $reduce = $('<a class="btn btn-default" data-operator="reduce"><i class="icon-minus" data-operator="reduce"></i></a>');
    var maxOptionNumber = 10;


    $("#questionContainer").click(function (e) {
        var target = e.target;
        var _data = $(target).data();
        switch (_data.operator) {
            case "plus":
                var $options = $optionsDiv.find(".option");
                if($options.length == maxOptionNumber) {
                    alert("最多十个选项");
                    break;
                }
                $optionsDiv.append($option.clone());
                clearAndOrder();
                break;
            case "reduce":
                var $options = $optionsDiv.find(".option");
                if($options.length <= 1) {
                    alert("至少一个选项")
                } else {
                    $optionsDiv.find(".option").last().remove();
                    clearAndOrder();
                }
                break;
            default:
                break;
        }
    });

    $("#saveBtn").click(function() {
        var title = $("#title").val();
        var mode = $("#mode").val();
        var options = [];
        $optionsDiv.find(".option").each(function(index) {
            var $this = $(this);
            options.push($this.find("input").val());
        });

        var questionObj = {
            title: title,
            mode: mode,
            options: JSON.stringify(options)
        };
        saveQuestionAjax(questionObj, function(data) {
            if(data.resultCode == 1) {
                alert('保存成功')
                location.href = "/question/list"
            }
        })
    });

    function saveQuestionAjax(questionObj, callback) {
        $.ajax({
            type: "post",
            url:"/question/save",
            data:questionObj,
            dataType: "json",
            success: function(data) {
                callback(data);
            }
        })
    }

    function clearAndOrder() {
        var $options = $optionsDiv.find(".option");
        $options.each(function(index) {
            var $this = $(this);
            $this.find(".control-label").empty().html("选项"+(index+1));
            $this.find(".controls").find("a").remove();
        });
        $options.last().find(".controls").append($plus).append("&nbsp;&nbsp;").append($reduce);
    }
});