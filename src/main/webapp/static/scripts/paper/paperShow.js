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
        var span12 = $(
            '<div class="row-fluid"><div class="span12" data-id="'+ques.id+'">' +
            '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div></div>'
        );
        for(var i = 0, length = options.length; i < length; i++) {
            var quesTitle = options[i];
            span12.append("<label><input type='radio' name='ques"+index+"'/>&nbsp;"+quesTitle+"</label>");
        }
        span12.appendTo(quesContainer);
    }
    /**
    * 插入多选题
    */
    function addManyOption(quesContainer, ques, index) {
        var options = JSON.parse(ques.options);
        var span12 = $(
            '<div class="row-fluid"><div class="span12" data-id="'+ques.id+'">' +
            '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div></div>'
        );
        for(var i = 0, length = options.length; i < length; i++) {
            var quesTitle = options[i];
            span12.append(
                "<label>" +
                    "<input type='checkbox' name='ques"+index+"'/>&nbsp;" +
                    "<span style='line-height: 14px;'>"+quesTitle+"</span>" +
                "</label>"
            );
        }
        span12.appendTo(quesContainer);
    }

    /**
     * 插入问答题
     * @param quesContainer 容器
     * @param ques 问题对象
     * @param index 序号
     */
    function addEssayOption(quesContainer, ques, index) {
        var span12 = $(
            '<div class="row-fluid"><div class="span12" data-id="'+ques.id+'">' +
            '<h5>'+(index+1)+'、'+ques.title+'</h5>' +
            '</div></div>'
        );
        span12.append("<textarea name='ques+"+index+"' style='min-width: 100%;max-width: 100%;'></textarea>")
        span12.appendTo(quesContainer);
    }
});