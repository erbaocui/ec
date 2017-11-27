/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"商品管理",
        toolbar:"#tb",
        url:getContextPath()+"/commodity/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:100,checkbox:true},
            {field:'name',title:'商品名称',width:100},
            {field:'brief',title:'简介',width:100},
            {field:'price',title:'价格',width:100},
            {field:'seq',title:'排序',width:100},
            {field: 'status', title: '状态', width: 100,align:'center',
                formatter: function (value, rec, index) {
                    if (value == 0) return '有效';
                    if (value == 1) return '无效';
                    return decodeURI('');
                }
            },

            {field:'remark',title:'备注',width:100,align:'center'},
            {field:'_opation',title:"操作",width:"10%",align:'center',
                formatter:function(value,row,index){
                    var str="";
                        str+='<a href="#" class="easyui-linkbutton" onclick=" openPics(\''+index+'\')">图片</a>';
                    return str;
                }

            },
            {field:'id',title:'',width:0,hidden:true},
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "name": $("#qryName").val(),
            "status":$("#qryStatus").combobox('getValue')
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this).datagrid('appendRow', { loginName: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this) .datagrid('mergeCells', { index:0, field: 'loginName', colspan:5 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else {
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
    $('#searchUser').click(function(){
        $("#dg").datagrid('load', {
            "name": $("#qryName").val(),
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
        initUpload();
        $("#statusDisplay").hide();
        $('#name').attr("disabled","disabled");
        $('#name').validatebox('reduce');
        url = getContextPath ()+"/commodity/add.do";
        $("#dlg").dialog({title: "添加商品信息",modal:true});
        $("#dlg").dialog("open");

    });
    //编辑窗口
    $('#openModifyDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        $('#fm').form('load', row);
        $("#status").combobox('select', row.status);
        $("#statusDisplay").show();
        $('#name').attr("disabled","false");
        $('#name').validatebox('remove');
        url = getContextPath ()+"/commodity/modify.do";
         $("#dlg").dialog({title: "编辑商品信息",modal:true});
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
                    $.messager.alert("系统提示", "保存成功");
                    $("#fm").form('clear');
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("系统提示", "保存失败");
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




//open图片设置
function openPics(index){
    var row = $('#dg').datagrid('getData').rows[index];
   // window.open(getContextPath ()+"/picture/picture.do?type=1&relationName="+row.name+"&relationId="+row.id);
    var open=getContextPath ()+"/picture/picture.do?type=1&relationName="+row.name+"&relationId="+row.id;
    parent.$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
        title:"图片管理",
        content: '<iframe class="page-iframe" src="'+open +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
        closable: true
    });
}

function initUpload(){
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

            $("#showImg").attr("src",data);
            $("#url").val(data);
        }
    });
}









