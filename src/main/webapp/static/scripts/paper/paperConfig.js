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
    })

    function add(data) {
        $("#chooseTbody").append(
            '<tr data-id="'+data.id+'">' +
            '<td>'+data.title+'</td>' +
            '<td>'+data.type+'</td>' +
            '<td><a class="btn btn-danger">移除</a></td>' +
            '</tr>'
        )
    }
});