/**
 * <p>Title: 通用的组件</p>
 * <p>Company: http://www.ymq.io </p>

 * @Describe:
 * @Author: penglei
 * @Email:<a href="admin@souyunku.com"/></a>
 * @CreateDate: 2017/9/9 21:26
 **/

/**
 * /js方法：序列化表单
 * @param form 对象
 * @returns {{}}
 */

layui.use('layer', function () {
    var layer = layui.layer;
});

function serializeForm(form) {
    var obj = {};
    $.each(form.serializeArray(), function (index) {
        if (obj[this['name']]) {
            obj[this['name']] = obj[this['name']] + ',' + $.trim(this['value']);
        } else {
            obj[this['name']] = $.trim(this['value']);
        }
    });
    return obj;
}

/**
 * Tab的切换功能，切换事件监听等，需要依赖element模块
 */
layui.use('element', function () {
    var $ = layui.jquery, element = layui.element;
});

/**
 * 复制外链
 * @param data 数据
 */
function copylink(data) {

    layer.open({
        type: 1,
        area: ['446px', '180px'],
        shadeClose: true,
        title: "您正在预览外链：" + data.imageName,
        content: $('#copyURLTool').html()
    });

    var copyHtmlContent = '<img src="' + data.url + '" alt="' + data.imageName + '" title="' + data.imageName + '"/> ';
    var copyMarkdownContent = '![' + name + '](' + data.url + ')';
    var copyMarkdownWithLinkContent = '[![' + data.imageName + '](' + data.url + ')](' + data.url + ')';
    var copyURLContent = data.url;

    $("#copyHtml").val(copyHtmlContent);
    $("#copyMarkdown").val(copyMarkdownContent);
    $("#copyMarkdownWithLink").val(copyMarkdownWithLinkContent);
    $("#copyURL").val(copyURLContent);
}

/**
 * 预览图片
 * @param data 数据
 */
function previewImages(data) {

    var default_config = {title: "图片预览"};
    var img = new Image();
    img.onload = function () {//避免图片还未加载完成无法获取到图片的大小。
        //避免图片太大，导致弹出展示超出了网页显示访问，所以图片大于浏览器时下窗口可视区域时，进行等比例缩小。
        var max_height = $(window).height() - 100;
        var max_width = $(window).width();
        //rate1，rate2，rate3 三个比例中取最小的。
        var rate1 = max_height / img.height;
        var rate2 = max_width / img.width;
        var rate3 = 1;
        var rate = Math.min(rate1, rate2, rate3);
        //等比例缩放
        default_config.height = img.height * rate; //获取图片高度
        default_config.width = img.width * rate; //获取图片宽度

        $.extend(default_config, data);
        var imgHtml = "<img src='" + data.url + "' width='" + default_config.width + "px' height='" + default_config.height + "px'/>";
        //弹出层
        layer.open({
            type: 1,
            shade: 0.8,
            offset: 'auto',
            area: [default_config.width + 'px', default_config.height + 43 + 'px'], ////宽，高
            shadeClose: true,
            scrollbar: false,
            title: "您正在预览：" + data.imageName,
            content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
            }
        });
    };
    img.src = data.url;
}



