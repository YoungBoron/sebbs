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
$(document).ready(function () {
    var page = $("#page").val();
    $(".pagination").find("a[href$='="+page+"']").parent().attr("class", "active");
    $(".pagination").find("a[aria-label]").parent().attr("class", "");
});