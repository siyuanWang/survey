'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    var $option = $('<div class="control-group option"><label class="control-label">选项1</label><div class="controls"><input type="text" name="option"> &nbsp;&nbsp; <a class="btn btn-default" data-operator="plus"> <i class="icon-plus" data-operator="plus"></i> </a> &nbsp;&nbsp; <a class="btn btn-default" data-operator="reduce"> <i class="icon-minus" data-operator="reduce"></i> </a> </div> </div>');
    var $optionsDiv = $("#options");
    var $plus = $('<a class="btn btn-default" data-operator="plus"><i class="icon-plus" data-operator="plus"></i></a>');
    var $reduce = $('<a class="btn btn-default" data-operator="reduce"><i class="icon-minus" data-operator="reduce"></i></a>');
    var maxOptionNumber = 10;

    $("#questionContainer").click(function (e) {
        var target = e.target;
        var _data = $(target).data();
        switch (_data.operator) {
            case "plus":
                addOption();
                break;
            case "reduce":
                delOption();
                break;
            default:
                break;
        }
    });

    /**
     * 新增选项
     */
    function addOption(optionData) {
        var $options = $optionsDiv.find(".option");
        if ($options.length == maxOptionNumber) {
            alert("最多十个选项");
        } else {
            var _$option = $option.clone().appendTo($optionsDiv);
            if (optionData) {
                _$option.find("input").val(optionData);
            }
            clearAndOrder();
        }
    }

    /**
     * 删除选项
     */
    function delOption() {
        var $options = $optionsDiv.find(".option");
        if ($options.length <= 1) {
            alert("至少一个选项")
        } else {
            $optionsDiv.find(".option").last().remove();
            clearAndOrder();
        }
    }

    $("#saveBtn").click(function () {
        var title = $("#title").val();
        var mode = $("#mode").val();
        var options = [];
        $optionsDiv.find(".option").each(function (index) {
            var $this = $(this);
            options.push($this.find("input").val());
        });

        var questionObj = {
            title: title,
            mode: mode,
            options: JSON.stringify(options)
        };
        saveQuestionAjax(questionObj, function (data) {
            if(rc.response(data)) {
                location.href = "/question/list"
            }
        })
    });

    /**
     * 新增问题
     * @param questionObj 对象
     * @param callback 回调
     */
    function saveQuestionAjax(questionObj, callback) {
        $.ajax({
            type: "post",
            url: "/question/save",
            data: questionObj,
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }

    function clearAndOrder() {
        var $options = $optionsDiv.find(".option");
        $options.each(function (index) {
            var $this = $(this);
            $this.find(".control-label").empty().html("选项" + (index + 1));
            $this.find(".controls").find("a").remove();
        });
        $options.last().find(".controls").append($plus).append("&nbsp;&nbsp;").append($reduce);
    }

    /**
     * 题型select事件
     */
    $("#mode").change(function (e) {
        var $this = $(this);
        if ($this.val() == 3) {//如果是问答题,只需要展示题干即可
            $optionsDiv.empty();
        } else {//如果是单选题或者多选题
            if ($optionsDiv.find(".option").length == 0) {//没有选项了，新增一个
                addOption();
            }
        }
    })
});