$(".setScore").click(function() {
    $.post("/set/score", new FormData($(this).parent()), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("修改成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});