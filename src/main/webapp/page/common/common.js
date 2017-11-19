
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
       /* status: function (value, rec, index) {
            if (value == 0) return '<spring:message code="sys.status.valid"/>';//有效
            if (value == 1) return '<spring:message code="sys.status.invalid"/>';//无效
            return decodeURI('');

        },*/


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

