/**
 * Created by home on 2017/7/7.
 */
var url;
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        title:"意见反馈管理",
        toolbar:"#tb",
        url:getContextPath()+"/feedback/list.do",
        method:"post",
        columns:[[
            {field:'ck',title:'',width:100,checkbox:true},
            {field:'createTime',title:'创建时间',width:100,formatter:commonFormatter.time},
            {field:'feedback',title:'意见',width:100},
            {field:'contact',title:'联系方式',width:100},
            {field: 'status', title: '状态', width: 100,align:'center',
                formatter: function (value, rec, index) {
                    if (value == 0) return '未处理';
                    if (value == 1) return '已处理';
                    return '';
                }
            },
            {field:'dealTime',title:'处理时间',width:100,formatter:commonFormatter.time},
            {field:'remark',title:'备注',width:100,align:'center',formatter:commonFormatter.brief},
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
                $(this).datagrid('appendRow', { loginName: '<div style="text-align:center;color:red">没有相关记录！</div>' });
                if($("input[type='checkbox']")[0]!=null) {
                    $("input[type='checkbox']").remove(0);
                }
                $(this) .datagrid('mergeCells', { index: 0, field: 'loginName', colspan:5 });
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

    //编辑窗口
    $('#openModifyDialog').click(function(){
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        if (row.status!=0) {
            $.messager.alert("系统提示", "只能处理未处理的数据！");
            return;
        }
        $('#fm').form('load', row);

        url = getContextPath ()+"/feedback/modify.do";
        $("#dlg").dialog({title: "编辑个人客户信息",modal:true});
        $("#dlg").dialog("open");
    });
    //保存按钮
    $('#saveDialog').click(function(){

        $("#fm").form("submit", {
            url: url,
            success: function (data) {
                var json = $.parseJSON(data);
                if (json.result == "success") {
                    $.messager.alert("系统提示", "处理成功");
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
        $("#fm").form('clear');
    });


});




