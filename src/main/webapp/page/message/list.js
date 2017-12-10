/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"推送消息管理",
        toolbar:"#tb",
        url:getContextPath()+"/message/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:100,checkbox:true},
            {field:'title',title:'标题',width:"10%"},
            {field:'sendTime',title:'发送时间',width:"12%",formatter:commonFormatter.time},
            {field: 'status', title:'发送状态', width: "10%",align:'center',
                formatter: function (value, rec, index) {
                    if (value == 0) return '未发送';
                    if (value == 1) return '已发送';
                    return '';
                }
            },
            {field:'content',title:'消息内容',width:"20%",formatter:commonFormatter.brief},
            {field:'picture',title:'图片',width:"20%",formatter:commonFormatter.brief},
            {field:'url',title:'URL',width:"20%",formatter:commonFormatter.brief},
            {field:'_opation',title:"操作",width:"10%",align:'center',
                formatter:function(value,row,index){
                    var str="";
                    str+='<a href="#" class="easyui-linkbutton" onclick="showInfo(\''+index+'\')">详情</a>';

                    return str;
                }

            },
            {field:'id',title:'',width:100,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "beginTime": $("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "status":$("#qryStatus").combobox('getValue')
        },
        onLoadError: function (data) {
          if(data.responseText=="loseSession"){
              window.location.href=getContextPath()+"/page/login.jsp"
          }
      },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
               if($("input[type='checkbox']")[0]!=null) {
                   $("input[type='checkbox']").remove(0);
               }
                $(this).datagrid('appendRow', { name: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this) .datagrid('mergeCells', { index: 0, field: 'name', colspan:7 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
                canExport=true;
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            }
        }
    });

    $('#dg').datagrid('getPager').pagination({
        pageSize: 10,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50],
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    //搜索按钮
    $('#search').click(function(){
        $("#dg").datagrid('load', {
            "beginTime": $("#qryBeginTime").val(),
            "endTime":$("#qryEndTime").val(),
            "status":$("#qryStatus").combobox('getValue')
        });
    });
    //重置按钮
    $('#resetBtn').click(function(){
        $("#qf").form('clear');
        $("#qryStatus").combobox("setValue",-1);
    });
    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        initUpload("picture");
        $("#st").hide();
        $("#bt").show();
        url = getContextPath ()+"/message/add.do";
        $("#dlg").dialog({title: "添加推送消息",modal:true,buttons: "#dlg-buttons"});
        $("#dlg").dialog("open");

    });

    //保存按钮
    $('#saveDialog').click(function(){
        $("#fm").form("submit", {
            url: url,
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function (data) {
                var json = $.parseJSON(data);
                if (json.result == "success") {
                    $.messager.alert("系统提示", "操作成功");
                    $("#fm").form('clear');
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("系统提示", "操作失败");
                }

            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
        $("#fm").form('clear');
    });


});

//信息详情
function showInfo(index) {

    var row = $('#dg').datagrid('getData').rows[index];
    $('#fm').form('load', row);
    //$("#loginName").val(row.loginName);
    $("#st").show();
    $("#bt").hide();
    $("#showImg").attr("src", row.picture);
    $("#sendTime").val(commonFormatter.time(row.sendTime));
    $("#dlg").dialog({title: "推送信息", modal: true,buttons:""});
    $("#dlg").dialog("open");
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




