'use strict';
define(['jquery','response-common'], function ($, rc) {
    $("#sureBtn").click(function() {
        var origin = $("#origin").val();
        var newPass = $("#newPass").val();
        var confirmPass = $("#confirmPass").val();
        if(!origin) {
            alert("原始密码必填");
            return false;
        }

        if(newPass == "" || confirmPass == "") {
            alert("请输入新密码两次,必须相同");
            return false;
        }

        if(newPass != confirmPass) {
            alert("新密码两次输入不一致");
            return false;
        }
        $("#form")[0].submit();
    });

});