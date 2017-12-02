
<meta charset="UTF-8"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%--
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/icon.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/demo/demo.css" />
link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/themes/default/easyui.css" />
--%>

<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/easyui.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/css/base.css" />
<%--<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/icon.css" />--%>


<%--<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/panel.css" />--%>


<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/datagrid.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/dialog.css" />
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/uimaker/combobox.css" />




<%----%>
<%--
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
--%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/plugins/jquery.combobox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/plugins/jquery.validatebox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-rule.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validator-method.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorUser.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCustomer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorTask.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorRole.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorCommodity.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/validator/validatorVersion.js"></script>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/datagrid.js"></script>--%>
<%--
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/uploadfiy/jquery.uploadify.js"></script>
--%>
<script type="text/javascript">
    function getContextPath() {
        // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
        var currentPath = window.document.location.href;
        // 获取主机地址之后的目录，如： /ssm/index.jsp
        var pathName = window.document.location.pathname;
        var pos = currentPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8080
        var localhostPath = currentPath.substring(0, pos);
        // 获取带"/"的项目名，如：/ssm
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        /* alert("currentPath:" + currentPath + "\n"
         + "pathName:" + pathName + "\n"
         + "localhostPath:" + localhostPath + "\n"
         + "projectName:" + projectName + "\n"
         + "contextPath:"+ localhostPath + projectName);*/
        return(localhostPath + projectName);
    }

    function localhostPath() {
        // 获取当前网址，如：http://localhost:8080/ssm/index.jsp
        var currentPath = window.document.location.href;
        // 获取主机地址之后的目录，如： /ssm/index.jsp
        var pathName = window.document.location.pathname;
        var pos = currentPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8080
        var localhostPath = currentPath.substring(0, pos);
        // 获取带"/"的项目名，如：/ssm
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        /* alert("currentPath:" + currentPath + "\n"
         + "pathName:" + pathName + "\n"
         + "localhostPath:" + localhostPath + "\n"
         + "projectName:" + projectName + "\n"
         + "contextPath:"+ localhostPath + projectName);*/
        return(localhostPath);
    }

    var commonFormatter=
    {
        dateTime: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            // return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute ;
        },
        time: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;

        },
        sedMilli: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            var h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var milliSecond=date.getMilliseconds();
            minute = minute < 10 ? ('0' + minute) : minute;
            second = second < 10 ? ('0' + second) : second;
            milliSecond=milliSecond<10 ?('00' + milliSecond) :milliSecond;
            milliSecond=(milliSecond<100&&milliSecond>=10) ?('0' + milliSecond) :milliSecond;

            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second+ ':'+ milliSecond ;

        },

        day: function (value, rec, index) {
            if (value == null) {
                return "";
            }
            var date = new Date(value);
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            m = m < 10 ? ('0' + m) : m;
            var d = date.getDate();
            d = d < 10 ? ('0' + d) : d;

            return y + '-' + m + '-' + d;

        },
     /*   status: function (value, rec, index) {
            if (value == 0) return '';//有效
            if (value == 1) return '';//无效
            return decodeURI('');

        },
*/

        brief: function (value, rec, index) {

            if (value == null) return "";
            if(value.length>5){
                var str=value.substr(3)+"."
                var content = '<span title="' + value + '" class="tip">' + str+ '</span>';
                return content;
            }
            return value;//未知
        }


    }


</script>





