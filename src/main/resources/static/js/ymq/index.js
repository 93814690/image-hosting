$("#file").fileinput({
    uploadUrl: '/image/upload',
    allowedFileExtensions: ['jpeg', 'jpg', 'png', 'gif', 'bmp', 'webp'],
    overwriteInitial: false,
    maxFileSize: 10240,
    maxFileCount: 10,
    browseClass: "btn btn-primary", //按钮样式
    uploadAsync: true,  //异步上传
    language: 'zh'
});

$('#file').on('fileuploaded', function (event, data, previewId, index) {
    var form = data.form,
        files = data.files,
        extra = data.extra,
        response = data.response,
        filenames = data.filenames,
        reader = data.reader;
    //console.log(response);
    if (response.code == '200') {
        if ($("showurl").css("display")) {
            $('#html').append("&lt;img src=\"" + response.data.url + "\" alt=\"" + files[index].name + "\" title=\"" + files[index].name + "\" /&gt;" + "\n");
            $('#markdown').append("![" + files[index].name + "](" + response.data.url + ")" + "\n");
            $('#markdownlink').append("[![" + files[index].name + "](" + response.data.url + ")]" + "(" + response.data.url + ")" + "\n");
            $('#url').append(response.data.url + "\n");

        } else if (response.data.url) {
            $("#showurl").show();
            $('#html').append("&lt;img src=\"" + response.data.url + "\" alt=\"" + files[index].name + "\" title=\"" + files[index].name + "\" /&gt;" + "\n");
            $('#markdown').append("![" + files[index].name + "](" + response.data.url + ")" + "\n");
            $('#markdownlink').append("[![" + files[index].name + "](" + response.data.url + ")]" + "(" + response.data.url + ")" + "\n");
            $('#url').append(response.data.url + "\n");

        }
    } else {
        alert(response.msg);
    }
});