$("#addTopic").click(function() {
    $.post("/add/topic", {
        title : $("#title").val(),
        content : $("#content").val(),
        boardId : $("#boardId").val()
    }, function(data, status) {
        if (data == "true") {
            alert("发表成功");
            location.reload()
        } else {
            alert("发表失败");
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});