$("#addTopic").click(function() {
    $.post("/add/topic", {
        title : $("#title").val(),
        content : $("#content").val(),
        boardId : $("#boardId").val()
    }, function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("发表成功");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});

$('#bestModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#best").click(function(){
    $.post("/best/topic", $('#bestForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已加精");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#delete").click(function(){
    $.post("/delete/topic", $('#deleteForm').serialize(), function(data) {
        if (data.code == 0) {
            //alert("");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});
