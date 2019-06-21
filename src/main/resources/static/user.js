$('#updateModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
    modal.find('input[name="password"]').val(button.data('password'));
    modal.find('input[name="number"]').val(button.data('number'));
    modal.find('option').attr("selected", null);
    modal.find('option[value='+button.data('type')+']').attr("selected", "selected");
});
$("#update").click(function(){
    $.post("/update/user", $('#updateForm').serialize(), function(data) {
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

$('#banModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
});
$("#ban").click(function(){
    $.post("/ban/user", $('#banForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});

$('#unBanModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    modal.find('input[name="id"]').val(button.data('id'));
    modal.find('input[name="name"]').val(button.data('name'));
});
$("#unBan").click(function(){
    $.post("/ban/user", $('#unBanForm').serialize(), function(data) {
        //alert(data.code);
        if (data.code == 0) {
            location.reload()
        } else {
            alert(data.msg);
        }
        //alert("数据: \n" + data + "\n状态: " + status);
    });
});