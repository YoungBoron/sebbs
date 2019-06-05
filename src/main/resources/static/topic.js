$("#addReply").click(function() {
    $.post("/add/reply", {
        content : $("#content").val(),
        topicId : $("#topicId").val()
    }, function(data) {
        if (data.code == 0) {
            alert("回复成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});