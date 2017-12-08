/**
 * Created by home on 2017/7/7.
 */
$(document).ready(function(){
    //表格格式化
    $('#dg').datagrid({
        fitColumns: true,
        title:"客户使用记录统计",
        toolbar:"#tb",
        url:getContextPath()+"/statistics/list.do",
        method:"post",
        columns: [[
         /*   {field: 'useTime', title: '使用时间', width: "50%"},
            {field: 'durationTime', title: '使用时长', width: "50%"}*/
        ]],
        singleSelect: true,//单选
        checkOnSelect:true,
        pagination:false,//分页工具条
        rownumbers: false,//序号
        stripe:true,	//设置为true将交替显示行背景。
        fitColumns:true, //设置为true将自动使列适应表格宽度以防止出现水平滚动
        queryParams: {
            "loginName": $("#qryLoginName").val(),
            "type":$("#qryType").combobox('getValue')
        },
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                $(this).datagrid('appendRow', { statistics: '<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'statistics', colspan: 3 });
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
        pageSize: 100,
        pageNumber: 1,
        pageList: [10, 20, 30, 40, 50],
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    //搜索按钮
    $('#search').click(function(){
       if( $("#qryLoginName").val()==null||$("#qryLoginName").val()==""){
           $.messager.alert("系统提示","请输用户登录名");
           return;
       }

        if( $("#qryType").combobox('getValue')==0) {
            $('#dg').datagrid({
                columns: [[
                    {field: 'useTime', title: '使用时间', width: "50%",formatter:commonFormatter.time},
                    {field: 'durationTime', title: '使用时长', width: "50%"}
                ]]
            });
        }
        if( $("#qryType").combobox('getValue')==1) {
            $('#dg').datagrid({
                columns: [[
                    {field: 'useNo', title: '星期', width: "50%", formatter: function (value, rec, index) {
                        if (value == '1') return '星期一';
                        if (value == '2') return '星期二';
                        if (value == '3') return '星期三';
                        if (value == '4') return '星期四';
                        if (value == '5') return '星期五';
                        if (value == '6') return '星期六';
                        if (value == '0') return '星期日';
                        return '';}},
                    {field: 'durationTime', title: '使用时长', width: "50%"}
                ]]
            });
        }
        if(  $("#qryType").combobox('getValue')==2) {
            $('#dg').datagrid({
                columns: [[
                    {field: 'useNo', title: '周', width: "50%", formatter: function (value, rec, index) {
                        if (value == '1') return '第一周';
                        if (value == '2') return '第二周';
                        if (value == '3') return '第三周';
                        if (value == '4') return '第四周';
                        if (value == '5') return '第五周';
                        return '';}},
                    {field: 'durationTime', title: '使用时长', width: "50%"},
                ]]
            });
        }
        $("#dg").datagrid('load', {
            "loginName": $("#qryLoginName").val(),
            "type":$("#qryType").combobox('getValue')

        });
    });





});




