/**
 * <p>Title: 瀑布流 </p>
 * <p>Company: http://www.ymq.io </p>

 * @Describe:
 * @Author: penglei
 * @Email:<a href="admin@souyunku.com"/></a>
 * @CreateDate: 2017/9/9 21:28
 **/

/**
 * 绑定  瀑布流 form 搜索按钮
 */

$('#submitPForm').click(function () {
    $("li[name='pinterest']").remove();
    $(".layui-flow-more").remove();
    var params = serializeForm($("#pForm"))
    pinterest(params);
});


/**
 * 初始化瀑布流
 */
layui.use('flow', function () {
    $('#submitPForm').click();
});

/**
 * 瀑布流入口方法
 * @param params
 */
function pinterest(params) {

    $("#sc").removeClass("active");
    $("#lb").addClass("active");

    $("#pinterest").css("height", $(window).height() + "px");

    var flow = layui.flow;
    flow.load({
        elem: '#pinterest' //流加载容器
        , scrollElem: '#pinterest'
        , isAuto: true
        , isLazyimg: true
        , done: function (pageNum, next) { //执行下一页的回调

            //数据插入
            $.ajax({
                url: "/image/imagesList",
                data: {
                    params: JSON.stringify(params),
                    pageNum: pageNum,
                    pageSize: 8
                },
                type: 'post',
                success: function (data) {
                    //console.log(data);
                    if (data.code == "200") {

                        prependImages(params, pageNum, next, data.data);

                    } else {
                        layer.msg('查询失败：' + data.msg, {time: 4000,});
                    }
                }, error: function (e) {
                    layer.msg('查询失败：' + e, {time: 4000,});
                }
            });
        }
    });

}

/**
 * 添加标签
 * @param params
 * @param pageNum
 * @param next
 * @param data
 */
function prependImages(params, pageNum, next, data) {

    var listData = [];

    layui.each(data.list, function (index, item) {

        var contentHtml = $('#pinterestTool').html();

        contentHtml = contentHtml.replace(/pinterestImagesId/g, item.imageId);
        contentHtml = contentHtml.replace(/url/g, item.url);
        contentHtml = contentHtml.replace(/imageName/g, item.imageName);
        contentHtml = contentHtml.replace(/resData/g, JSON.stringify(item));

        listData.push(contentHtml);
    });

    var pageCount = data.count / 8;

    if (pageCount <= 1) {
        pageCount = 1
    }
    if (params != null) {

        $("#pinterest").prepend(listData.join(''));

        next(null, pageNum < pageCount);
    } else {
        next(listData.join(''), pageNum < pageCount);
    }

}

/**
 * 根据类型调用方法
 * @param obj
 * @param type 1:复制外链，2.预览图片
 */
function methodType(obj, type) {

    var data = JSON.parse($('#' + obj.value).text());
    //console.log(data);

    if (type == 1) {

        copylink(data); //复制外链

    } else if (type == 2) {

        previewImages(data);  //预览图片
    }

}
