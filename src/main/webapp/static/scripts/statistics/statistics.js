'use strict';
define(['jquery', 'response-common'], function ($, rc) {
    var paperId = $("#paperId").val();
    var answerArray = ['A','B','C','D','E','F','G'];

    function getStatisticsData(paperId, callback) {
        $.ajax({
            type: "get",
            url: "/statistics/"+paperId,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }


    getStatisticsData(paperId, function(data) {
        var map = data.map;
        console.log(map)
        for(var prop in map) {
            createQuestion(map[prop]);
        }
    });


    /**
     * 创建试题
     */
    function createQuestion(prop) {
        $("#questionStatisticsContainer").append(
            "<tr>" +
                "<td>"+prop[0]['id']+"</td>" +
                "<td style='max-width: 200px;'>"+prop[0]['title']+"</td>" +
                "<td>"+getAnswerStatistics(prop)+"</td>" +
            "</tr>"
        )
    }

    function getAnswerStatistics(questions) {
        var str = "";
        $.each(questions, function(index, ques) {
            var array = [];
            if(ques.modeType == 1) {
                array.push(answerArray[JSON.parse(ques.answer)][0]);
            } else if(ques.modeType == 2) {
                var options = JSON.parse(ques.answer);
                var a = "";
                $.each(options, function(index, o) {
                    a += answerArray[o-1] + ",";
                });
                array.push(a.substring(0, a.length - 1));
            } else {
                array.push(JSON.parse(ques.answer)[0])
            }
            str += (index+1)+":" +array.join(",") + "<br/>";
        });

        return str;
    }
});