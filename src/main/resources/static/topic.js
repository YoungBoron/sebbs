$("#addReply").click(function() {
    $.post("/add/reply", {
        content : $("#content").val(),
        topicId : $("#topicId").val()
    }, function(data, status) {
        if (data == "true") {
            alert("回复成功");
            location.reload()
        } else {
            alert("回复失败");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});