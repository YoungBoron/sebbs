$('#updateModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="exBoardName"]').val(button.data('name'));
});
$("#update").click(function(){
    $.post("/update/board", {
        id: $(this).parent().siblings(".modal-body").find('input[name="id"]').val(),
        name: $(this).parent().siblings(".modal-body").find('input[name="name"]').val()
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