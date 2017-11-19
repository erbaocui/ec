$.extend($.fn.validatebox.defaults.rules, {
    CheckTaskExist:{
        validator:function(value, param){
            var result = true;
            var jobGroup=$(param[0]).val();
            var jobName=$(param[1]).val();
            if((jobGroup==null||jobGroup=="")||(jobName==null||jobName=="")){
               return  result;
            }


            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/task/exist.do",
                    dataType:"json",
                    data:{"jobName":$(param[1]).val(),"jobGroup":$(param[0]).val()},
                    success: function(data){
                        if(data.code==1){
                            $.fn.validatebox.defaults.rules.CheckTaskExist.message  =data.message;
                            result = false;
                        }
                    }
                });
            }
           return result;


        },
        message:"{0}"
    },
    CheckTaskCron:{
        validator:function(value, param){
            var result = true;

            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/task/checkCron.do",
                    dataType:"json",
                    data:{"cronExpression":value},
                    success: function(data){
                        if(data.code==1){
                            $.fn.validatebox.defaults.rules.CheckTaskCron.message  =data.message;
                            result = false;
                        }
                    }
                });
            }
            return result;


        },
        message:"{0}"
    },
    CheckBeanClass:{
        validator:function(value, param){
            var result = true;
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/task/checkBeanClass.do",
                    dataType:"json",
                    data:{"beanClass":value},
                    success: function(data){
                        if(data.code==1){
                            $.fn.validatebox.defaults.rules.CheckBeanClass.message  =data.message;
                            result = false;
                        }
                    }
                });
            }
            return result;


        },
        message:"{0}"
    },
    CheckBeanName:{
        validator:function(value, param){
            var result = true;


            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/task/checkBeanName.do",
                    dataType:"json",
                    data:{"beanName":value},
                    success: function(data){
                        if(data.code==1){
                            $.fn.validatebox.defaults.rules.CheckBeanName.message  =data.message;
                            result = false;
                        }
                    }
                });
            }
            return result;


        },
        message:"{0}"
    },
    CheckMethod:{
        validator:function(value, param){
            var result = true;
            var beanClass=$(param[0]).val();
            var beanName=$(param[1]).val();
            if(beanClass==""&&beanName==""){
                $.fn.validatebox.defaults.rules.CheckMethod.message  ="请先选择类名或bean名";
                result = false;
                return result;
            }
            if(value.length!=0){
                $.ajax({
                    type: "POST",
                    async: false,
                    url:getContextPath ()+"/task/checkMethod.do",
                    dataType:"json",
                    data:{"methodName":value,"beanClass":beanClass,"beanName":beanName},
                    success: function(data){
                        if(data.code==1){
                            $.fn.validatebox.defaults.rules.CheckMethod.message=data.message;
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