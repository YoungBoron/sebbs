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

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="content"]').val(button.data('content'));
});

$("#delete").click(function(){
    $.post("/delete/reply", $('#deleteForm').serialize(), function(data) {
        if (data.code == 0) {
            //alert("");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});