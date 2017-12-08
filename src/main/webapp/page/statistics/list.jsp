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
                    <td>&nbsp;登录名：&nbsp;<input type="text" id="qryLoginName"  class="easyui-textbox"  size="20"/><td>
                    <td>&nbsp;状态：&nbsp;
                        <select id="qryType"  class="easyui-combobox"  data-options="multiple: false,editable: false,width :100,panelHeight :'auto'">
                            <option value="0" selected="selected">日</option>
                            <option value="1">周</option>
                            <option value="2">月</option>
                        </select>
                    <td>
                    <td><a id="search" class="easyui-linkbutton" iconCls="icon-search"  data-options="selected:true">查询</a> </td>
                </tr>
            </tabel>
        </form>
    </div>
</div>




</body>
</html>