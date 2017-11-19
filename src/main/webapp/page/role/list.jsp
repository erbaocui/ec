<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="list.js"></script>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/page/role/basic_info.css" />
</head>
<body style="margin:1px;">

<table id="dg">
</table>
<div id="tb">
    <div>
        <form id="qf">
        <tabel>

            <tr>
                <td>&nbsp;角色标识：&nbsp;<input type="text" class="easyui-textbox" id="qryKv" size="20"/><td>
                <td>&nbsp;角色名称：&nbsp;<input type="text" class="easyui-textbox" id="qryName" size="20"/><td>
                <td><a id="searchRole" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true" >搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>
            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" data-options="selected:true" >添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" >修改</a>
        <a  id="deleteRole" class="easyui-linkbutton"  >删除</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 700px;height:600px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id" value="">
        <div class="container">
            <div class="left-tree" style="height:470px;width:160px ">
                <ul id="tt" class="easyui-tree">
                </ul>
            </div>

            <div class="content">
                <div class="easyui-tabs1" style="width:100%;">
                    <div class="easyui-tabs1" style="width:100%;">
                        <div title="基本信息" data-options="closable:false" class="basic-info">
                            <div class="column"><span class="current">权限信息</span></div>
                            <table class="kv-table">
                                <tbody>

                                <tr>
                                    <td  class="kv-label">角色标识：</td>
                                    <td  class="kv-content"><input type="text" id="kv" name="kv" class="easyui-validatebox" data-options="required: true,validType:['CheckRole']"/><td>
                                </tr>
                                <tr>
                                    <td class="kv-label">角色名称：</td>
                                    <td class="kv-content"><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/><td>
                                </tr>

                                <tr>
                                    <td  colspan="2"class="kv-label">备&nbsp;&nbsp;&nbsp;注：</td>

                                </tr>
                                <tr>
                                    <td  colspan="2" class="kv-content"> <textarea id="remark" name="remark"></textarea></td>
                                </tr>
                                </tbody>
                            </table>


            </div>
           </div>
                </div>
            </div>
        </div>
    </form>
</div>


<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="showdlg" class="easyui-dialog"
     style="width: 400px;height:470px;padding: 0px 0px" closed="true">
        <div  class="easyui-panel" style="padding:0px;width:398px;height:428px">
            <ul id="showtree" class="easyui-tree">
            </ul>
        </div>

</div>
</body>
</html>