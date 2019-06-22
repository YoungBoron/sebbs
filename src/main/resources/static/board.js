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

$('#unBestModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#unBest").click(function(){
    $.post("/best/topic", $('#unBestForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已撤精");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#upModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#up").click(function(){
    $.post("/up/topic", $('#upForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已置顶");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#unUpModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#unUp").click(function(){
    $.post("/up/topic", $('#unUpForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已撤顶");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#announcementModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#announcement").click(function(){
    $.post("/announcement/topic", $('#announcementForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已公告");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});

$('#unAnnouncementModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var modal = $(this);
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="title"]').val(button.data('title'));
});

$("#unAnnouncement").click(function(){
    $.post("/announcement/topic", $('#unAnnouncementForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("已取消公告");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});