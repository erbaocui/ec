/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"定时器管理",
        toolbar:"#tb",
        url:getContextPath()+"/task/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:0,checkbox:true},
            {field:'jobGroup' ,title:'job组',width:"7%"},
            {field:'jobName',title:'job名',width:"7%"},
            {field:'isConcurrent',title:'当前状态',width: "4%"},
            {field:'cronExpression' ,title:'cron表达式',width:"10%"},
            {field:'beanClass',title:'bean类名',width: "13%"},
            {field:'springId',title:'bean名称',width: "5%"},
            {field:'methodName',title:'方法名',width: "5%"},
            {field: 'jobStatus', title: '状态', width: "3%"
                ,formatter: commonFormatter.status
            },

            {field:'description',title:'备注',width: "10%"},
            {field:'createTime',title:'创建时间',width:"10%",align:'center', formatter:commonFormatter.time},
            {field:'updateTime',title:'更新时间',width:"10%",align:'center', formatter:commonFormatter.time},
            {field:'_opation',title:"操作",width:"10%",align:'center',
                formatter:function(value,row,index){
                    var str="";
                    if((row.isConcurrent==""||row.isConcurrent==null)&&row.jobStatus=="0"){
                        str+='|<a href="#" class="easyui-linkbutton" onclick="loadJob(\''+index+'\')">加载</a>';
                    }
                    if(row.isConcurrent!=""&&row.isConcurrent!=null&& row.jobStatus=="0"){
                        str+='|<a href="#" class="easyui-linkbutton" onclick="deleteJob(\''+index+'\')">卸载</a>';
                    }
                    if(row.isConcurrent!=""&&row.isConcurrent!=null&&row.isConcurrent!="5"&&row.isConcurrent!="2"&&row.jobStatus=="0"){
                        str+='|<a href="#" class="easyui-linkbutton" onclick="runJob(\''+index+'\')">立即执行</a>';
                    }
                    if(row.isConcurrent=="2"&&row.jobStatus=="0"){
                        str+='|<a href="#" class="easyui-linkbutton" onclick="resumeJob(\''+index+'\')">恢复</a>';
                    }
                    if(row.isConcurrent!=""&&row.isConcurrent!=null&&row.isConcurrent!="2"&&row.jobStatus=="0"){
                        str+='|<a href="#" class="easyui-linkbutton" onclick="pauseJob(\''+index+'\')">暂停</a>';
                    }
                    str=str.substring(1,str.length) ;
                    return str;
                }

            },
            {field:'jobId',title:'',width:0,hidden:true}
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:true,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "jobGroup": $("#qryJobGroup").val(),
            "jobName":$("#qryJobName").val(),
            "jobStatus":$("#qryJobStatus").val()
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
                $(this) .datagrid('mergeCells', { index:0, field: 'name', colspan:5 });
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
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
    $('#search').click(function(){
        $("#dg").datagrid('load', {
            "jobGroup": $("#qryJobGroup").val(),
            "jobName":$("#qryJobName").val(),
            "jobStatus":$("#qryJobStatus").val()
        });
    });
    //重置按钮
    $('#resetBtn').click(function(){
        $("#qf").form('clear');
        $("#qryJobStatus").combobox("setValue",-1);
    });


    //新增窗口
    $('#openAddDialog').click(function(){
        $("#fm").form('clear');
        $("#jobStatus").val("0");
        $('#jobGroup').attr("disabled","true");
        $('#jobGroup').validatebox('reduce');
        $('#jobName').attr("disabled","true");
        $('#jobName').validatebox('reduce');
        $('#beanClass').attr("disabled","true");
        $('#beanClass').validatebox('reduce');
        $('#springId').attr("disabled","true");
        $('#springId').validatebox('reduce');
        $('#methodName').attr("disabled","true");
        $('#methodName').validatebox('reduce');
        url = getContextPath ()+"/task/addTask.do";
        $("#dlg").dialog({title: "添加定时任务",modal:true});
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
        $("#jobStatus").val(row.jobStatus);
        $('#jobGroup').attr("disabled","false");
        $('#jobGroup').validatebox('remove');
        $('#jobName').attr("disabled","false");
        $('#jobName').validatebox('remove');
        $('#beanClass').attr("disabled","false");
        $('#beanClass').validatebox('remove');
        $('#springId').attr("disabled","false");
        $('#springId').validatebox('remove');
        $('#methodName').attr("disabled","false");
        $('#methodName').validatebox('remove');

        url = getContextPath ()+"/task/update.do";
        $("#dlg").dialog({title:"编辑定时任务",modal:true});
        $("#dlg").dialog("open");
    });
    //删除按钮
    $('#delete').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要删除的数据！");
            return;
        }
        var row = selectedRows[0];
        if(row.isConcurrent!=""&&row.isConcurrent!=null){
            $.messager.alert("系统提示", "请先卸载定时任务！");
            return;
        }
        $.messager.confirm("系统提示", "您确认要删除定时器<font color=red>"
        + row.jobGroup+"."+row.jobName+ "</font>吗？", function (r) {
            if (r) {
                $.post( getContextPath ()+"/task/deleteTask.do", {
                    'jobId': row.jobId
                }, function (data) {
                    if (data.code=='0') {
                        $.messager.alert("系统提示", "数据已成功删除！");
                        $("#dg").datagrid("reload");
                    }else{
                        $.messager.alert("系统提示", "数据删除失败！");
                    }
                }, "json");
            }
        });
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
                if (json.code == "0") {
                    $.messager.alert("系统提示", "保存成功");
                    $("#fm").form('clear');
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                }


            }
        });
    });
    //关闭按钮
    $('#closeDialog').click(function(){
        $("#dlg").dialog("close");
    });

});

function loadJob(index){
    var row = $('#dg').datagrid('getData').rows[index];
    $.post( getContextPath ()+"/task/addJob.do", {
       "jobId": row.jobId
    }, function (data) {
        if (data.code=='0') {
            $.messager.alert("系统提示", "定时任务加载成功！");
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示", "定时任务加载失败！");
        }
    }, "json");
}

function pauseJob(index){
    var row = $('#dg').datagrid('getData').rows[index];
    $.post( getContextPath ()+"/task/pauseJob.do", {
        "jobId": row.jobId
    }, function (data) {
        if (data.code=='0') {
            $.messager.alert("系统提示", "定时任务暂停成功！");
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示", "定时任务暂停失败！");
        }
    }, "json");
}

function resumeJob(index){
    var row = $('#dg').datagrid('getData').rows[index];
    $.post( getContextPath ()+"/task/resumeJob.do", {
        "jobId": row.jobId
    }, function (data) {
        if (data.code=='0') {
            $.messager.alert("系统提示", "定时任务恢复成功！");
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示", "定时任务恢复失败！");
        }
    }, "json");
}
function runJob(index){
    var row = $('#dg').datagrid('getData').rows[index];
    $.post( getContextPath ()+"/task/runJob.do", {
        "jobId": row.jobId
    }, function (data) {
        if (data.code=='0') {
            $.messager.alert("系统提示", "定时任务执行成功！");
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示", "定时任务执行失败！");
        }
    }, "json");
}

function deleteJob(index){
    var row = $('#dg').datagrid('getData').rows[index];
    $.post( getContextPath ()+"/task/deleteJob.do", {
        "jobId": row.jobId
    }, function (data) {
        if (data.code=='0') {
            $.messager.alert("系统提示", "定时任务卸载成功！");
            $("#dg").datagrid("reload");
        }else{
            $.messager.alert("系统提示", "定时任务卸载失败！");
        }
    }, "json");
}


