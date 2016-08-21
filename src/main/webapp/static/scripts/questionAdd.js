'use strict';
define(['jquery'], function ($) {
    var $option = $(".option");
    var $optionsDiv = $("#options");
    var $plus = $('<a class="btn btn-default" data-operator="plus"><i class="icon-plus" data-operator="plus"></i></a>');
    var $reduce = $('<a class="btn btn-default" data-operator="reduce"><i class="icon-minus" data-operator="reduce"></i></a>');



    $("#questionContainer").click(function (e) {
        var target = e.target;
        var _data = $(target).data();
        switch (_data.operator) {
            case "plus":
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