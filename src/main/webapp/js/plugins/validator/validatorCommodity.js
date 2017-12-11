$.extend($.fn.validatebox.defaults.rules, {
    CheckCommodity:{
        validator:function(value, param){
            var result = true;
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/commodity/exist.do",
                    dataType:"json",
                    data:{"name":value,"noid":$(param[0]).val()},
                    success: function(data){
                        if(data.result==true){
                            $.fn.validatebox.defaults.rules.CheckCommodity.message ="商品已存在";
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