'use strict';
define(['jquery','datatable','select2','jquery-uniform'], function ($) {
    $('.data-table').dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "sDom": '<""l>t<"F"fp>'
    });

    $('input[type=checkbox],input[type=radio],input[type=file]').uniform();

    $('select').select2();

    $("span.icon input:checkbox, th input:checkbox").click(function() {
        var checkedStatus = this.checked;
        var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');
        checkbox.each(function() {
            this.checked = checkedStatus;
            if (checkedStatus == this.checked) {
                $(this).closest('.checker > span').removeClass('checked');
            }
            if (this.checked) {
                $(this).closest('.checker > span').addClass('checked');
            }
        });
    });

    $("#questionTbody").click(function(e) {
        var target = e.target;
        var $target = $(target);
        switch(target.className) {
            case "upd":
                location.href = "/question/upd/"+ $target.data("id");
                break;
            case "del":
                alert("删除")
                break;
        }
    })
});