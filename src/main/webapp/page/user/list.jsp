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
                <td>&nbsp;登录名：&nbsp;<input class="easyui-textbox" type="text" id="qryLoginName" size="20"/><td>
                <td>&nbsp;姓&nbsp;&nbsp;名：&nbsp;<input class="easyui-textbox"type="text" id="qryDisplayName" size="20"/><td>
                <td><a id="searchUser" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true" >搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>

            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a id="openAddDialog" href="#" class="easyui-linkbutton" data-options="selected:true">添加</a>
        <a id="openModifyDialog" href="#" class="easyui-linkbutton"  >修改</a>
        <a id="openResetPwDialog" href="#" class="easyui-linkbutton" >密码重置</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 400px;height:350px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id">
        <table cellspacing="8px">
            <tr>
                <td>登录名：</td>
                <td><input type="text" id="loginName" name="loginName"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckUser']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>显示名：</td>
                <td><input type="text" id="displayName"  name="displayName"
                           class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            <tr id="passwordDisplay">
                <td>密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="password"  name="password"
                           class="easyui-validatebox" data-options="required:true" />&nbsp;

                </td>
            </tr>
            <tr id="checkPasswordDisplay">
                <td>确认密码：</td>
                <td><input type="password" id="checkPassword"
                           class="easyui-validatebox" required="true" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配" />&nbsp;

                </td>
            </tr>

                <tr id="statusDisplay">
                    <td>状&nbsp;&nbsp;&nbsp;态：</td>
                    <td>
                        <select id="status" name="status"  class="easyui-combobox" style="width:100px;">
                          <option value="0"  selected="selected" >有效</option>
                          <option value="1">无效</option>
                      </select>
                    </td>
                </tr>

            <tr>

                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>

            <tr >
                <td>角&nbsp;&nbsp;&nbsp;色：</td>
                <td><input id="roleId" class="easyui-combobox" name="roleId"  /></td>
            </tr>
        </table>
    </form>
</div>


<div id="resetPwDialog" class="easyui-dialog"
     style="width: 350px;height:210px;padding: 10px 20px" closed="true"
     buttons="#pwDlg-buttons">
    <form id="pwfm" method="post">
        <input type="hidden" id="pwId" name="pwId">
        <table cellspacing="8px">


            <tr >
                <td>密&nbsp;&nbsp;&nbsp;码：</td>
                <td><input type="password" id="resetPassword"  name="resetPassword"
                           class="easyui-validatebox" data-options="required:true" />&nbsp;

                </td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td><input type="password" id="resetCheckPassword"
                           class="easyui-validatebox" required="true" validType="equalTo['#resetPassword']" invalidMessage="两次输入密码不匹配" />&nbsp;

                </td>
            </tr>
        </table>
    </form>
</div>


<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="pwDlg-buttons">
    <a id="pwSaveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="pwCloseDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>