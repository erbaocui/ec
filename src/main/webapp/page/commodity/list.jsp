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
                <td>&nbsp;商品名：&nbsp;<input class="easyui-textbox" type="text" id="qryName" size="20"/><td>
                <select id="qryStatus" name="qryStatus"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                    <option value="-1">全部</option>
                    <option value="0">有效</option>
                    <option value="1">无效</option>
                </select>
                <td><a id="searchUser" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true" >搜索</a> </td>
                <td><a id="resetBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a> </td>

            </tr>
        </tabel>
        </form>
    </div>
    <div>
        <a id="openAddDialog" href="#" class="easyui-linkbutton" data-options="selected:true">添加</a>
        <a id="openModifyDialog" href="#" class="easyui-linkbutton"  >修改</a>
    </div>

</div>

<div id="dlg" class="easyui-dialog"
     style="width: 400px;height:350px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id">
        <table cellspacing="8px">
            <tr>
                <td>商品名：</td>
                <td><input type="text" id="name" name="name"
                           class="easyui-validatebox" data-options="required: true,validType:['CheckCommodity']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>价格：</td>
                <td><input type="text" id="price" name="price"
                           class="easyui-validatebox" data-options="required: true,validType:['money']"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td>排序：</td>
                <td><input type="text" id="seq" name="seq"
                           class="easyui-validatebox" data-options="required: true,validType:['integ']"/>&nbsp;
                </td>
            </tr>
             <tr id="statusDisplay">
                    <td>状&nbsp;&nbsp;&nbsp;态：</td>
                    <td>
                        <select id="status" name="status"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">

                            <option value="0">有效</option>
                            <option value="1">无效</option>
                        </select>
                    </td>
                </tr>
            <tr>

                <td>简&nbsp;&nbsp;&nbsp;介：</td>
                <td><textarea id="brief" name="brief"   class="easyui-validatebox" data-options="required: true"></textarea>&nbsp;</td>
            </tr>

            <tr>

                <td>备&nbsp;&nbsp;&nbsp;注：</td>
                <td><textarea id="remark" name="remark"></textarea>&nbsp;</td>
            </tr>


        </table>
    </form>
</div>


<div id="mapDialog" class="easyui-dialog"
     style="width:1200px;height:900px;padding: 10px 20px" closed="true"
     buttons="#mapDlg-buttons">

    <iframe id="mapfrm" src="map.jsp" width="100%" height="95%"></iframe>
    <input type="text" id="lat" name="lat" size="20" />
    <input type="text" id="lon" name="lon" size="20"   />
    <input type="text" id="address" name="address" size="20" />

</div>


<div id="dlg-buttons" >
    <a id="saveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="closeDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<div id="mapDlg-buttons">
    <a id="mapSaveDialog" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a id="mapCloseDialog" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>