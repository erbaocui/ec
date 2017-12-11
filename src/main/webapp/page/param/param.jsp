<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>参数设置</title>
    <link href="<%=request.getContextPath()%>/css/basic_info.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/plugins/uploadfiy/uploadify.css" />
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
    <script type="text/javascript" src="param.js"></script>



</head>
<body>

<div class="container">

    <div class="content">
        <div class="easyui-tabs1" style="width:90%;">
            <div title="参数信息" data-options="closable:false" class="basic-info"  buttons="#dlg-buttons">
                <form id="fm">
                <div class="column"><span class="current">app参数设置</span></div>

                <table class="kv-table">
                    <tbody id="apptb">

                   </tbody>
                    </table>
                    <div class="column"><span class="current">share参数设置</span></div>

                    <table class="kv-table">
                        <tbody id="sharetb">

                        </tbody>
                    </table>
                    <div class="column"><span class="current">商城参数设置</span></div>

                    <table class="kv-table">
                        <tbody id="malltb">

                        </tbody>
                    </table>
                <table class="kv-table">

                    <tr>
                         <td colspan="3" width="400px"> </td>
                        <td>

                                <a id="save" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
                                <a id="reset" class="easyui-linkbutton" iconCls="icon-cancel">重置</a>

                        </td>
                    </tr>


                </table>
                </form>
            </div>

        </div>
    </div>
</div>

<div id="imgDlg" class="easyui-dialog"
     style="width: 600px;height:450px;padding: 10px 20px" closed="true"
     buttons="#imgDlg-buttons">
    <form id="imgFm" method="post">
        <table>
            <tr  >
                <td colspan="2" >   <img id="showImg" src="" style="width:400px;height:200px;padding: 10px 20px"></td>
            </tr>
            <tr  >
                <td colspan="2"  style="width:50px;height:50px"> </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="fileQueue"></div>
                    <input type="file" name="uploadify" id="uploadify" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="imgDlg-buttons" >
    <a id="imgCloseDlg" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>
