/**
 * <p>Title: 复制文本 </p>
 * <p>Company: http://www.ymq.io </p>

 * @Describe:
 * @Author: penglei
 * @Email:<a href="admin@souyunku.com"/></a>
 * @CreateDate: 2017/9/11 21:07
 **/

function copySuccess(e) {
    //layer.msg('', {time: 1000,});
    layer.tips('已复制', '#'+e.trigger.id );
}

function copyError() {
    layer.msg('copy外链失败，请手动复制外链', {time: 2000,});
}


var btnCopyHtml = new Clipboard('#btnCopyHtml');

btnCopyHtml.on('success', function (e) {

    copySuccess(e);

});

btnCopyHtml.on('error', function (e) {

    copyError();
});


var btnCopyMarkdown = new Clipboard('#btnCopyMarkdown');

btnCopyMarkdown.on('success', function (e) {

    copySuccess(e);
});

btnCopyMarkdown.on('error', function (e) {

    copyError();
});


var btnCopyMarkdownWithLink = new Clipboard('#btnCopyMarkdownWithLink');

btnCopyMarkdownWithLink.on('success', function (e) {

    copySuccess(e);
});

btnCopyMarkdownWithLink.on('error', function (e) {

    copyError();
});

var btnCopyURL = new Clipboard('#btnCopyURL');

btnCopyURL.on('success', function (e) {

    copySuccess(e);
});

btnCopyURL.on('error', function (e) {

    copyError();
});