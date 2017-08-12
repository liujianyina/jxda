/**
 * 设置弹出窗口的位置
 */
window.onload = function () {
    var width = $(window).width();
    $('#upload-window').css("left", width / 2 - 200);
}


/**
 * 点击
 */
$('#choise-file-button').click(function () {
    return $('#file-input').click();
});

/**
 * 将文件路径显示到filenamedisplay框
 */
$('#file-input').change(function () {
    $('#filename-display').attr("value", $(this).val());
});

/**
 * 如果点击上传文件按钮
 */
$('#uploadNewFileButton').click(function () {

    var roleBox = $('#role-box');

    $.ajax({
        url: "/role/get_all_role",
        type: "get",
        cache: "true",
        dataType: 'json',
        success: function (result) {
            var data = result['data'];
            for (var i = 0; i < data.length; i++) {
                var label = $("<label class='rolename'>" + "<input type='checkbox' name='roleSids' value=" + data[i].sid + ">" + "<i>\u2713</i>" + data[i].cname + "</label>").appendTo(roleBox);
            }
        },
        error: function () {
            alert("加载失败")
        }
    });

    $('#upload-window').show();
});

/**
 * 关闭上传文件弹出框
 */
$('#upload-window-close').click(function () {
    $('#upload-window').hide();
});

/**
 * 如果点击全选按钮
 */
$('#selectAllButton').click(function () {
    if ($(this).attr("select") == "false") {
        $(this).attr("select", "true");
        $(this).attr("src", "/static/img/selected.png")

        $('.singleSelect').attr("select", "true");
        $('.singleSelect').attr("src", "/static/img/selected.png")

    } else {
        $(this).attr("select", "false");
        $(this).attr("src", "/static/img/select.png")

        $('.singleSelect').attr("select", "false");
        $('.singleSelect').attr("src", "/static/img/select.png")
    }
});

/**
 * 点击每一行前面的单选
 */
$('.singleSelect').click(function () {
    if ($(this).attr("select") == "false") {
        $(this).attr("select", "true");
        $(this).attr("src", "/static/img/selected.png")
    } else {
        $(this).attr("select", "false");
        $(this).attr("src", "/static/img/select.png")
    }
});

/**
 * 获取当前被选中的行的id
 */
function getSelected() {
    var ids = new Array();
    var elems = $('.singleSelect');
    var j = 0;
    for (var i = 0; i < elems.length; i++) {
        if ($(elems[i]).attr("select") == "true") {
            ids[j++] = $(elems[i]).attr("id");
        }
    }
    return ids;
}

/**
 * 点击下载按钮
 */
$('#downloadButton').click(function () {
    var sids = getSelected();
    if (sids.length == 0) {
        //当前未选中文件，不能进行此操作
        alert("\u5f53\u524d\u672a\u9009\u4e2d\u6587\u4ef6\uff0c\u4e0d\u80fd\u8fdb\u884c\u6b64\u64cd\u4f5c");
        return;
    }
    var downloadBox = $("#download-box");
    for (var i = 0; i < sids.length; i++) {
        $("<a href='/resource/download?taken=" + sids[i] + "'><span id='dowmload-btn'></span></a>").appendTo(downloadBox);
        $('#dowmload-btn').click();
        downloadBox.removeClass();
    }
});

/**
 * 点击删除按钮
 */
$('#deleteButton').click(function () {
    var sids = getSelected();
    if (sids.length == 0) {
        //当前未选中文件，不能进行此操作
        alert("\u5f53\u524d\u672a\u9009\u4e2d\u6587\u4ef6\uff0c\u4e0d\u80fd\u8fdb\u884c\u6b64\u64cd\u4f5c");
        return;
    }
    alert(sids);
});

/**
 * 点击重命名按钮
 */
$('#renameButton').click(function () {
    var sids = getSelected();
    if (sids.length == 1) {
        alert(sids);
    } else {
        //只能选择一个文件进行此操作
        alert("\u53ea\u80fd\u9009\u62e9\u4e00\u4e2a\u6587\u4ef6\u8fdb\u884c\u6b64\u64cd\u4f5c");
        return;
    }
});

