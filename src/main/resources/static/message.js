$("#addMessage").click(function() {
    $.post("/add/message", $('#addMessageForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("私信发送成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});