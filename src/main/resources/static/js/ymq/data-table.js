/**
 * <p>Title: 数据表格 </p>
 * <p>Company: http://www.ymq.io </p>

 * @Describe:
 * @Author: penglei
 * @Email:<a href="admin@souyunku.com"/></a>
 * @CreateDate: 2017/9/9 21:27
 **/


/**
 * 绑定 数据表格 form 搜索按钮
 */

$('#submitDForm').click(function () {

    initTableDate(serializeForm($("#dForm")));
});


/**
 * 初始化 table 事件
 */
layui.use('table', function () {

    initTableDate(null);

    initFilterTool(); //表格操作事件

});

/**
 * 初始化表格
 * @param data
 */
function initTableDate(data) {

    var table = layui.table;

    table.render({

        elem: '#data_table',
        url: '/ymq/cloudStorage/getImagesList',
        where: {params: JSON.stringify(data)},
        width: 1140,
        cols: [
            [
                {checkbox: true, LAY_CHECKED: false},
                {field: 'imagesId', title: 'ID', width: 85, sort: true},
                {field: 'name', title: '名称', width: 270, sort: true},
                {field: 'remark', title: '描述', width: 280, sort: true},
                {field: 'screateTime', title: '上传日期', width: 160, sort: true},
                {field: 'right', title: '操作', width: 290, toolbar: '#barTool'}
            ]
        ],
        skin: 'row',//表格风格
        even: true,
        page: true,//是否显示分页
        request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        response: {
            statusCode: 000000 //成功的状态码，默认：0
        },

        limits: [5, 7, 10, 20],
        limit: 10 //每页默认显示的数量
    });

}

/**
 * 表格操作事件
 *
 * @param obj
 */
function initFilterTool() {

    var table = layui.table;


    table.on('tool(filterTool)', function (obj) {
        var data = obj.data;

        if (obj.event === 'd_btn_url_link') {

            copylink(obj.data);//复制外链

        } else if (obj.event === 'd_btn_download') {

            document.getElementById("downloadImages").href = obj.data.url;
            document.getElementById("downloadImages").click();

        } else if (obj.event === 'd_btn_preview') {

            previewImages(obj.data, 2); //预览图片

        } else if (obj.event === 'd_btn_delete') {

            deleteTable(obj);  //删除图片

        } else if (obj.event === 'd_btn_edit') {
            updateForm(obj);
        }

    });

}

/**
 * 删除图片
 * @param obj
 */
function deleteTable(obj) {
    //询问框
    layer.confirm('真的删除 ：' + obj.data.name + '吗?', {
        btn: ['删除', '取消'] //按钮
    }, function (index) {
        $.ajax({
            url: "/ymq/cloudStorage/deleteById",
            data: {imagesId: +obj.data.imagesId},
            type: 'get',
            success: function (data) {
                if (data.retCode == "000000") {
                    obj.del();
                    layer.close(index);
                    layer.msg('删除成功', {time: 1000,});
                } else {
                    layer.msg('删除失败：' + data.retMsg, {time: 2000,});
                    layer.close(index);
                }
            }, error: function (e) {
                layer.msg('删除失败：' + e, {time: 2000,});
            }
        });
    });

}

/**
 * 修改，图片名称，描述
 * @param obj
 */
function updateForm(obj) {
    layer.open({
        type: 1,
        closeBtn: 0,
        area: ['400px', '215px'],//宽高
        content: $('#updateFormTool').html()
        , btn: ['保存', '取消']
        , yes: function (index, layero) {
            var params = JSON.stringify(serializeForm($("#updateForm")));
            $.ajax({
                url: "/ymq/cloudStorage/update",
                data: {params: params},
                type: 'post',
                success: function (data) {
                    if (data.retCode == "000000") {
                        var p = $.parseJSON(params);
                        obj.update({
                            name: p.name, remark: p.remark
                        });
                        layer.close(index);
                        layer.msg('保存成功', {time: 1000,});
                    } else {
                        layer.msg('保存失败：' + data.retMsg, {time: 2000,});
                        layer.close(index);
                    }
                }, error: function (e) {
                    layer.msg('保存失败：' + e, {time: 2000,});
                }
            });
        }
        , btn2: function (index, layero) {
            layer.close(index);
        }
    });

    $("#imagesId").val(obj.data.imagesId);
    $("#name").val(obj.data.name);
    $("#remark").val(obj.data.remark);

}
