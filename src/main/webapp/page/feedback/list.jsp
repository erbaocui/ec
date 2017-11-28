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
                <td>&nbsp;开始时间：&nbsp;<input type="text" id="qryBeginTime" size="20" class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><td>
                <td>&nbsp;结束时间：&nbsp;<input type="text" id="qryEndTime" size="20"  class="Wdate" onfocus="WdatePicker({readOnly:true,isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><td>
                <td>&nbsp;状态：&nbsp;
                    <select id="qryStatus"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                        <option value="-1">全部</option>
                        <option value="0">未处理</option>
                        <option value="1">已处理</option>
                    </select>
                <td>

                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true">搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>
            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a  id="openModifyDialog"  class="easyui-linkbutton" data-options="selected:true">处理</a>

    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 520px;height:450px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id" >

        <table cellspacing="8px">
            <tr>
                <td>创建时间：</td>

                <td><input type="text" id="createTime" name="createTime" readonly="readonly"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>联系方式：</td>

                <td><input type="text" id="contact" name="contact" readonly="readonly"/>&nbsp;
                </td>
            </tr>

            <tr>
                <td>反馈意见：</td>
                <td><textarea id="feedback" name="feedback" readonly="readonly"></textarea></td>
            </tr>

            <tr>
                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>
        </table>
    </form>
</div>





<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">处理</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>