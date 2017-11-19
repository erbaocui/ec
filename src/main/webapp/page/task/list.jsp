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
<div id="tb">
    <div>
        <form id="qf">
        <tabel>

            <tr>
                <td>&nbsp;job组：&nbsp;&nbsp;&nbsp;<input type="text" id="qryJobGroup"name="qryJobGroup" class="easyui-textbox" size="20"/><td>
                <td>&nbsp;job名称：&nbsp;<input type="text" id="qryJobName"  class="easyui-textbox" size="20"/><td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryJobStatus" name="qryStatus"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="-1">全部</option>
                        <option value="0">有效</option>
                        <option value="1">无效</option>
                    </select>
                <td>
                <td><a id="search"  class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true" >搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>
            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" data-options="selected:true">添加</a>
        <a  id="openModifyDialog" class="easyui-linkbutton" >修改</a>
        <a  id="delete" class="easyui-linkbutton">删除</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 450px;height:420px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="jobId" name="jobId">
        <table cellspacing="8px">
            <tr>
                <td>&nbsp;job组：&nbsp;&nbsp;&nbsp;</td>
                <td><input type="text" id="jobGroup"  name="jobGroup"
                           class="easyui-validatebox"  data-options="required: true,validType:['CheckTaskExist[\'#jobGroup\',\'#jobName\']']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>&nbsp;job名：&nbsp;&nbsp;&nbsp;</td>

                <td><input type="text" id="jobName" name="jobName"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckTaskExist[\'#jobGroup\',\'#jobName\']']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>&nbsp;cron表达式：&nbsp;&nbsp;&nbsp;</td>
                <td><input type="text" id="cronExpression"  name="cronExpression"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckTaskCron']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>&nbsp;bean类名：&nbsp;&nbsp;&nbsp;</td>
                <td><input type="text" id="beanClass"  name="beanClass"
                           class="easyui-validatebox" data-options="required: false,validType:['CheckBeanClass']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>&nbsp;bean名称：&nbsp;&nbsp;&nbsp;</td>
                <td><input type="text" id="springId"  name="springId"
                           class="easyui-validatebox" data-options="required: false,validType:['CheckBeanName']"/>&nbsp
                </td>
            </tr>
            <tr>
                <td>&nbsp;方法名：&nbsp;&nbsp;&nbsp;</td>
                <td><input type="text" id="methodName"  name="methodName"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckMethod[\'#beanClass\',\'#beanName\']']"/>&nbsp;
                </td>
            </tr>

            <tr>
                    <td>状&nbsp;&nbsp;&nbsp;态：</td>
                    <td>
                        <select id="jobStatus" name="jobStatus">
                          <option value="0"  selected="selected" >有效</option>
                          <option value="1">无效</option>
                      </select>
                    </td>
                </tr>

            <tr>

                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="description" name="description"  rows="3" cols="23"></textarea>&nbsp;</td>
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