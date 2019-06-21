$(".setScore").click(function() {
    $.post("/set/score", {
        id: $(this).siblings("input[name='id']").val(),
        score: $(this).siblings("input[name='score']").val()
    }, function(data) {
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

$('#joinModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
});
$("#join").click(function(){
    $.post("/join/course", $('#joinForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            alert("已加入");
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});

$('#addModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
});
$("#add").click(function(){
    $.post("/add/course", $('#addForm').serialize(), function(data) {
        if (data.code == 0) {
            alert("增加成功");
            location.reload()
        } else {
            alert(data.msg);
        }
    });
});