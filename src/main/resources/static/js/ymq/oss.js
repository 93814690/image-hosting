/**
 * <p>Title: 云存储配置 </p>
 * <p>Company: http://www.ymq.io </p>

 * @Describe:
 * @Author: penglei
 * @Email:<a href="admin@souyunku.com"/></a>
 * @CreateDate: 2017/9/12 10:07
 **/


/**
 * 云存储配置
 */
layui.use(['form'], function () {

    var form = layui.form, layer = layui.layer;

    form.on('submit(saveBtnCloudStorageType)', function (data) {
        defaultCloudStorageType(data.field.storageType);
        return false;
    });

    form.on('submit(saveBtnCloudStorage)', function (data) {

        saveCloudStorageConfig();
        return false;
    });

    selectCloudStorageConfig(form);

    form.render();
});

/**
 * 保存云存储配置
 */
function saveCloudStorageConfig() {

    var cloudStorage = JSON.stringify(serializeForm($("#formCloudStorage")));

    $.ajax({
        url: "/ymq/cloudStorage/saveCloudStorageConfig",
        data: {params: cloudStorage},
        type: 'post',
        success: function (data) {
            if (data.retCode == "000000") {
                layer.msg('保存成功', {time: 1000});
            } else {
                layer.msg('保存失败：' + data.retMsg, {time: 2000});
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(textStatus, {time: 2000,});
        }
    });
}

/**
 * 设置默认云存储类型
 */
function defaultCloudStorageType(data) {

    $.ajax({
        url: "/ymq/cloudStorage/defaultCloudStorageType",
        data: {storageType: data},
        type: 'post',
        success: function (data) {
            if (data.retCode == "000000") {
                layer.msg('设置成功', {time: 1000,});
            } else {
                layer.msg('设置失败：' + data.retMsg, {time: 2000});
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(textStatus, {time: 2000,});
        }
    });
}


function selectCloudStorageConfig(form) {

    $.ajax({
        url: "/ymq/cloudStorage/selectCloudStorageConfig",
        type: 'post',
        success: function (data) {

            if (data.retCode == "000000") {

                var config = data.result;

                $("input[name=aliyunDomain]").val(config.aliyunDomain);
                $("input[name=aliyunPrefix]").val(config.aliyunPrefix);
                $("input[name=aliyunEndPoint]").val(config.aliyunEndPoint);
                $("input[name=aliyunAccessKeyId]").val(config.aliyunAccessKeyId);
                $("input[name=aliyunAccessKeySecret]").val(config.aliyunAccessKeySecret);
                $("input[name=aliyunBucketName]").val(config.aliyunBucketName);
                $("input[name=qiniuDomain]").val(config.qiniuDomain);
                $("input[name=qiniuPrefix]").val(config.qiniuPrefix);
                $("input[name=qiniuAccessKey]").val(config.qiniuAccessKey);
                $("input[name=qiniuSecretKey]").val(config.qiniuSecretKey);
                $("input[name=qiniuBucketName]").val(config.qiniuBucketName);

                $("input[name=storageType][value= '" + config.storageType + "']").attr("checked", "checked");

                form.render();
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.msg(textStatus, {time: 2000});
        }
    });
}

