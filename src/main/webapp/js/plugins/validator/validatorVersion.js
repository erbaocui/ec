$.extend($.fn.validatebox.defaults.rules, {
    CheckVersion:{
        validator:function(value, param){
            var result = true;

            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/version/exist.do",
                    dataType:"json",
                    data:{"v":value,"type":$(param[0]).combobox("getValue")},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckVersion.message ="版本已存";
                            result = false;
                        }
                    }
                });
            }
           return result;


        },
        message:"{0}"
    }
});