/**
 * Created by home on 2017/7/7.
 */

$(document).ready(function(){

    $.ajax({
        url:getContextPath()+'/param/list.do',
        type:'POST', //GET
        async:false,    //或false,是否异步
        data:{
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend:function(xhr){

        },
        success:function(data,textStatus,jqXHR){
            if(data.rows!=null){
                $("#tb").empty();
                var str="";
                for (var i=0; i<data.total; i++)
                {
                   var row= data.rows[i];
                    if((i%2)==0){  str+="<tr>";}
                    if( row.type==0) {
                        str += "<td class='kv-label'>" + row.name + "</td> <td class='kv-content'><input type='text' name='values' id='"  +row.kv + "'   class='easyui-validatebox' required='true' value='" +  row.value + "'/><input type='hidden' name='kvs' value='"  +row.kv + "'/></td>";
                    }
                    if( row.type==1) {
                        str += "<td class='kv-label'>" + row.name  + "</td> <td class='kv-content'><input type='text' name='values' id='"  +row.kv + "' class='easyui-validatebox' data-options='required: true,validType:[\"integ\"]' value='" +  row.value + "'/><input type='hidden' name='kvs' value='" +row.kv + "'/></td>";
                    }
                    if( row.type==2) {
                        str += "<td class='kv-label'>" +  row.name  + "</td> <td class='kv-content'><input type='text' name='values'  id='"  +row.kv + "' class='easyui-validatebox' data-options='required: true,validType:[\"number\"]' value='" + row.value + "'/><input type='hidden' name='kvs' value='" +row.kv + "'/></td>";
                    }
                    if( row.type==3) {
                        str += "<td class='kv-label'>" +  row.name  + "</td> <td class='kv-content'><input type='text' name='values'   id='"  +row.kv + "' class='easyui-validatebox' required='true'  value='" + row.value + "' readonly='readonly'/>&nbsp<a href='javascript:openImg(\""+row.kv+"\");' class='easyui-linkbutton' >图片上传</a><input type='hidden' name='kvs' value='" +row.kv + "'/></td>";
                    }
                    if((i%2)==0&&(i+1)==data.total){
                        str+="<td class='kv-label'></td> <td class='kv-content'></td></tr>";
                    }
                    if((i%2)==1){str+="</tr>";}

                }
               // str+="<tr><td colspan='3' class='kv-content'></td><td colspan='1' ><a href='#' class='easyui-linkbutton' data-options='iconCls:\'icon-list\'' onclick='openDialog()'>业务日志</a> <a href='#' class='easyui-linkbutton' data-options='iconCls:\'icon-back\''>返回</a></td></tr>";
                $("#tb").append(str);

                $.parser.parse($('#tb').parent());

            }
        },
        error:function(xhr,textStatus){

        },
        complete:function(){

        }
    });

    //图片关闭按钮
    $('#imgCloseDlg').click(function(){
        $("#imgDlg").dialog("close");
        $("#imgFm").form('clear');
    });

//保存按钮
    $('#save').click(function(){
        $("#fm").form("submit", {
            url: getContextPath()+'/param/modify.do',
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                var json = $.parseJSON(data);
                if (json.result == "success") {
                    $.messager.alert("系统提示", "保存成功");
                }else{
                    $.messager.alert("系统提示", "保存失败");
                }

            }
        });
    });
    //关闭按钮
    $('#reset').click(function(){
        location.reload();
    });
});


//简介图片
function openImg(id){

    initUpload(id);
    $("#imgFm").form('clear');
    $("#showImg").attr("src", $("#"+id).val());
    $("#imgDlg").dialog({title: "图片管理",modal:true});
    $("#imgDlg").dialog("open");

}


function initUpload(id){
    $("#uploadify").uploadify({
        'swf' : getContextPath()+'/js/plugins/uploadfiy/uploadify.swf',

        'uploader' :getContextPath()+'/file/upload.do',//flash文件的相对路径
        'fileDataName':"Filedata", 						//设置上传文件名称,默认为Filedata
        //'cancelImg': 'images/cancel.png', 			//每一个文件上的关闭按钮图标
        'queueID': 'fileQueue', 					//文件队列的ID，该ID与存放文件队列的div的ID一致
        'queueSizeLimit': 1, 							//当允许多文件生成时，设置选择文件的个数，默认值：999
        'fileDesc': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 	//用来设置选择文件对话框中的提示文本
        'fileExt': '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', 		//设置可以选择的文件的类型
        'auto': true, 								//设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
        'multi': false, 								//设置为true时可以上传多个文件
        'simUploadLimit': 1, 						//允许同时上传的个数 默认值：1
        'sizeLimit': 2048000,						//上传文件的大小限制
        'buttonText': '上传图片',						//浏览按钮的文本，默认值：BROWSE
        'displayData': 'percentage',     			//上传队列显示的数据类型，percentage是百分比，speed是上传速度
        //回调函数
        'onComplete': function (evt, queueID, fileObj, response, data) {

        },
        'onError': function (event, queueID, fileObj, errorObj) {
            if (errorObj.type === "File Size") {
                alert("文件最大为3M");
                $("#uploadify").uploadifyClearQueue();
            }
        },
        'onQueueFull': function (event, queueSizeLimit) {
            alert("最多上传" + queueSizeLimit + "张图片");
            return false;
        },
        'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
            $("#"+id).val(data);
            $("#"+id).validatebox();
            $("#showImg").attr("src",data);

        }
    });
}





