$(function() {
    var paperId = $("#paperId").val();

    function getQuestionDataAjax(paperId, callback) {
        $.ajax({
            type: "get",
            url: "/paper/showData/" + paperId,
            data: {},
            dataType: "json",
            success: function (data) {
                callback(JSON.parse(data.result));
            }
        })
    }

    getQuestionDataAjax(paperId, function(data) {
        var quesContainer = $("#questionContainer");
        $.each(data, function(index, ques) {
            if(ques.modeType == 1) {
                addSingleOption(quesContainer, ques, index);
            } else if(ques.modeType == 2) {
                addManyOption(quesContainer, ques, index);
            } else if(ques.modeType == 3) {
                addEssayOption(quesContainer, ques, index);
            }

        })
    });
    /**
     * 插入单选题
     */
    function addSingleOption(quesContainer, ques, index) {
        var options = JSON.parse(ques.options);
        var rowFluid = $('<div class="row-fluid"></div>');
        var span12 = $(
            '<div class="span12" data-id="'+ques.id+'" data-mode="1">' +
                '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div>'
        );
        for(var i = 0, length = options.length; i < length; i++) {
            var quesTitle = options[i];
            span12.append(
                "<label class='ques'>" +
                    "<input type='radio' name='ques"+index+"' value='"+(i+1)+"'/>&nbsp;" +
                    "<span>"+quesTitle+"</span>" +
                "</label>"
            );
        }
        span12.appendTo(rowFluid);
        rowFluid.appendTo(quesContainer);
    }
    /**
    * 插入多选题
    */
    function addManyOption(quesContainer, ques, index) {
        var options = JSON.parse(ques.options);
        var rowFluid = $('<div class="row-fluid"></div>');
        var span12 = $(
            '<div class="span12" data-id="'+ques.id+'" data-mode="2">' +
                '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div>'
        );
        for(var i = 0, length = options.length; i < length; i++) {
            var quesTitle = options[i];
            span12.append(
                "<label class='ques'>" +
                    "<input type='checkbox' name='ques"+index+"' value='"+(i+1)+"'/>&nbsp;" +
                    "<span>"+quesTitle+"</span>" +
                "</label>"
            );
        }
        span12.appendTo(rowFluid);
        rowFluid.appendTo(quesContainer);
    }

    /**
     * 插入问答题
     * @param quesContainer 容器
     * @param ques 问题对象
     * @param index 序号
     */
    function addEssayOption(quesContainer, ques, index) {
        var rowFluid = $('<div class="row-fluid"></div>');
        var span12 = $(
            '<div class="span12" data-id="'+ques.id+'" data-mode="3">' +
                '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div>'
        );
        span12.append("<label class='ques'><textarea name='ques+"+index+"' style='min-width: 100%;max-width: 100%;'></textarea></label>")
        span12.appendTo(rowFluid);
        rowFluid.appendTo(quesContainer);
    }

    $("#submitPaper").click(function() {
        var $btn = $(this);

        if($btn.attr("disabled") == "disabled") {

        } else {
            var surveyStatistics = getSurveyStatistics();
            var surveyStatisticsQues = getSurveyStatisticsQues();
            submitAjax(surveyStatistics, surveyStatisticsQues, function(data) {
                if(data.resultCode == 0) {
                    alert(data.result);
                    $btn.text("提交问卷");
                    $btn.removeAttr("disabled");
                } else {
                    alert("提交成功,谢谢您的参与!");
                    location.href = "http://www.investchaoyang.gov.cn";
                }
            });
        }
        $btn.text("提交中");
        $btn.attr("disabled", true);

    });

    /**
     * 获得试题对象
     * @returns {{paperId: (*|jQuery), email: (*|jQuery)}}
     */
    function getSurveyStatistics() {
        return {
            paperId: paperId,
            email: $("#email").val()
        }
    }

    /**
     * 获得每个试题的答案对象
     * @returns {Array}
     */
    function getSurveyStatisticsQues() {
        var surveyStatisticsQues = [];
        $("#questionContainer").children().each(function(index) {
            var $this = $(this);
            var $ques = $this.find(".span12");
            var questionId = $ques.data("id");
            var options = $ques.find(".ques");
            var mode = $ques.data("mode");
            var answers = [];
            $.each(options, function(index, option) {
                var $option = $(option);
                if(mode == 1) {
                    var $radio = $option.find("input[type='radio']");
                    if($radio.prop("checked")) {
                        answers.push($radio.val());
                        return false;
                    }
                } else if(mode == 2) {
                    var $checkbox = $option.find("input[type='checkbox']");
                    if($checkbox.prop("checked")) {
                        answers.push($checkbox.val());
                    }
                } else {
                    answers.push($option.find("textarea").val());
                }
            });

            surveyStatisticsQues.push({
                paperId: paperId,
                questionId: questionId,
                answer: answers,
                questionMode: mode
            })
        });

        return surveyStatisticsQues;
    }

    function submitAjax(surveyStatistics, surveyStatisticsQues, callback) {
        $.ajax({
            type: "post",
            url: "/statistics/save/" + surveyStatistics.paperId,
            data: {
                surveyStatistics: JSON.stringify(surveyStatistics),
                surveyStatisticsQues: JSON.stringify(surveyStatisticsQues)
            },
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        })
    }
});