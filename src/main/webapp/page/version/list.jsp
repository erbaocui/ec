<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="list.js"></script>
   

</head>
<body style="margin:1px;">

<table id="dg">
</table>
<div id="tb" style="padding:0 30px;">
    <div>
        <form id="qf">
        <tabel>

            <tr>

                 <td>&nbsp;类型：&nbsp;
                    <select id="qryType"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="-1">全部</option>
                        <option value="0">Android</option>
                        <option value="1">IOS</option>
                    </select>
                <td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="-1">全部</option>
                        <option value="0">有效</option>
                        <option value="1">无效</option>
                    </select>
                <td>
                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true">搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>
            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a  id="openAddDialog"   class="easyui-linkbutton" data-options="selected:true">添加</a>
        <a  id="openModifyDialog"  class="easyui-linkbutton">修改</a>
        <a  id="effect"  class="easyui-linkbutton">生效</a>
        <a  id="delete"  class="easyui-linkbutton">删除</a>
      <%--  <a id="export" class="easyui-linkbutton" iconCls="icon-print" plain="true">导出Excel</a>--%>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 420px;height:350px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id" >

        <table cellspacing="8px">
            <tr>
                <td>类型：</td>
                <td>
                    <select id="type"  name="type" class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="0" selected="selected">Android</option>
                        <option value="1">IOS</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>版本：</td>
                <td><input type="text" id="v" name="v" class="easyui-validatebox" data-options="required: true,validType:['CheckVersion[\'#type\',\'#id\']']" />&nbsp;
                </td>
            </tr>
            <tr>
                <td>下载地址：</td>
                <td><input type="text" id="url" name="url" class="easyui-validatebox" data-options="required: true,validType:['CheckVersion[\'#type\']']" />&nbsp;
                </td>
            </tr>
            <tr>
                <td>名称：</td>
                <td><input type="text" id="name" name="name"
                           class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            </tr>
            <tr id="statusDisplay">
                <td>状&nbsp;&nbsp;&nbsp;态：</td>
                <td>
                    <select id="status" name="status" class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="0"  selected="selected" >有效</option>
                        <option value="1">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>