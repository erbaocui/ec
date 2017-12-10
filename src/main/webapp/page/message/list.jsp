<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/plugins/uploadfiy/uploadify.css" />
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
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
                        <option value="0">未发送</option>
                        <option value="1">已发送</option>
                    </select>
                <td>

                <td><a id="search" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true">搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>
            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a  id="openAddDialog"  class="easyui-linkbutton" data-options="selected:true" >添加</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 520px;height:520px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id" >

        <table cellspacing="8px">
            <tr>
                <td>标题：</td>

                <td><input type="text" id="title" name="title" size="40"   class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            <tr id="st">
                <td>标题：</td>

                <td><input type="text" id="sendTime" name="sendTime" size="40"   class="easyui-validatebox" required="true"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>内容：</td>
                <td><textarea id="content" name="content" cols="40"  class="easyui-validatebox" required="true"></textarea></td>
            </tr>
            <tr>
                <td>URL：</td>

                <td><input type="text" id="url" name="url"  size="40"/>&nbsp;
                </td>
            </tr>

            <tr>
                <td>图片：</td>

                <td><input type="text" id="picture" name="picture" size="40" readonly="readonly"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="2" >   <img id="showImg" src="" style="width:300px;height:130px;padding: 10px 20px"></td>
            </tr>
            <tr  >
                <td colspan="2"  style="width:50px;height:50px"> </td>
            </tr>
            <tr id="bt">
                <td colspan="2">
                    <div id="fileQueue"></div>
                    <input type="file" name="uploadify" id="uploadify" />
                </td>
            </tr>
        </table>
    </form>
</div>





<div id="dlg-buttons">
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">发送</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>