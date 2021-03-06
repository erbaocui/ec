<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html >
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>uimaker信息管理系统</title> 
<link href="<%=request.getContextPath()%>/css/base.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/platform.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/uimaker/easyui.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/uimaker/jquery.easyui.min.js"></script>

</head> 
<body>
<input type="hidden" id="userName" value="${user.displayName}">
    <div class="container">
        <div id="pf-hd">
            <div class="pf-logo">
                <img src="<%=request.getContextPath()%>/images/main/main_logo.png" alt="logo">
            </div>
            
            <div class="pf-nav-wrap" id="topbtns">
              <!--<div class="pf-nav-ww">-->
              <div class="pf-nav-ww">
               
                <ul class="pf-nav">
                  
                </ul>
              </div>
              <!-- </div> -->
              

              <a href="javascript:;" class="pf-nav-prev disabled iconfont">&#xe606;</a>
              <a href="javascript:;" class="pf-nav-next iconfont">&#xe607;</a>
            </div> 
            <div class="pf-user">
                <div class="pf-user-photo">
                    <img src="<%=request.getContextPath()%>/images/main/user.png" alt="">
                </div>
                <h4 class="pf-user-name ellipsis">${user.displayName}</h4>
                <i class="iconfont xiala">&#xe607;</i>

                <div class="pf-user-panel">
                    <ul class="pf-user-opt">
                        <li class="pf-logout">
                            <a href="javascript:;">
                                <i class="iconfont">&#xe60e;</i>
                                <span class="pf-opt-name">退出</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </div>

        <div id="pf-bd">
            <div class="pf-sider-wrap">
              <!-- <div id="pf-sider">
                  <h2 class="pf-model-name">
                      <span class="iconfont">&#xe633;</span>
                      <span class="pf-name">组织管理</span>
                      <span class="toggle-icon"></span>
                  </h2>

                  <ul class="sider-nav">
                       <li class="current">
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">供应商组织</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                          <ul class="sider-nav-s">
                             <li class="active"><a href="#">供应商组织1</a></li>
                             <li><a href="#">供应商组织2</a></li>
                             <li><a href="#">供应商组织3</a></li>
                             <li><a href="#">供应商组织4</a></li>
                          </ul>
                       </li>
                       <li>
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">采购组织</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                          <ul class="sider-nav-s">
                             <li class="active"><a href="#">供应商组织1</a></li>
                             <li><a href="#">供应商组织2</a></li>
                             <li><a href="#">供应商组织3</a></li>
                             <li><a href="#">供应商组织4</a></li>
                          </ul>
                       </li>
                       <li>
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">专家库</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                          <ul class="sider-nav-s">
                             <li class="active"><a href="#">供应商组织1</a></li>
                             <li><a href="#">供应商组织2</a></li>
                             <li><a href="#">供应商组织3</a></li>
                             <li><a href="#">供应商组织4</a></li>
                          </ul>
                       </li>
                       <li>
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">注册供应商</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                          <ul class="sider-nav-s">
                             <li class="active"><a href="#">供应商组织1</a></li>
                             <li><a href="#">供应商组织2</a></li>
                             <li><a href="#">供应商组织3</a></li>
                             <li><a href="#">供应商组织4</a></li>
                          </ul>
                       </li>
                       <li>
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">RFI动态信息</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                       </li>
                       <li>
                          <a href="javascript:;">
                              <span class="iconfont sider-nav-icon">&#xe633;</span>
                              <span class="sider-nav-title">资质过期</span>
                              <i class="iconfont">&#xe642;</i>
                          </a>
                       </li>
                   </ul> 
              </div> -->
            </div>
            

            <div id="pf-page">
                <!-- <div class="easyui-tabs1" style="width:100%;height:100%;">
                  <div title="首页" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="workbench.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                  </div>
                  <div title="采购组织" style="padding:10px 5px 5px 10px;" data-options="closable:true">
                    <iframe class="page-iframe" src="index.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                    </div>
                  <div title="基本信息" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="basic_info.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                  </div>
                  <div title="业务流程" data-options="closable:true" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="process.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                  </div>
                </div> -->
            </div>
        </div>

        <div id="pf-ft">
            <div class="system-name">
              <i class="iconfont">&#xe6fe;</i>
              <span>信息管理系统&nbsp;v1.0</span>
            </div>
            <div class="copyright-name">
              <span>CopyRight&nbsp;2016&nbsp;&nbsp;uimaker.com&nbsp;版权所有</span>
              <i class="iconfont" >&#xe6ff;</i>
            </div>
        </div>
    </div>

    <div id="mm" class="easyui-menu tabs-menu" style="width:120px;display:none;">
         <div id="mm-tabclose">关闭</div>
         <div id="mm-tabcloseall">关闭所有</div>
         <div id="mm-tabcloseother">关闭其他</div> 
    </div>
    <div id="dlg" class="easyui-dialog" title="业务日志查看" data-options="closed:true,modal:true" style="width:720px;height:490px;padding:10px;display:none;">
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/process.css">
      <div class="time-line">
        <div class="time-item date">
          <div class="content-left first">
            <span>2016-04-25</span>
            <label><span class="dot"></span><i class="line"></i></label>
          </div>
        </div>
        <div class="time-item time">
          <div class="content-left">
            <span>15:58:34</span>
            <label><i class="line"></i><span class="dot"></span></label>
          </div>
          <div class="content-right">
            <span class="left-arrow"></span>
            <div class="detail-outer">
              <div class="detail">
                <div>
                  <span class="name">占立中</span>
                  <label>[买方]</label>
                  <span class="status">发布</span>
                </div>
                <div>
                  <span class="name">占立中</span>
                  <label>[买方]</label>
                  <span class="status">发布</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="time-item time important">
          <div class="content-left">
            <span>17:00:21</span>
            <label><i class="line"></i><span class="dot"></span></label>
          </div>
          <div class="content-right">
            <span class="left-arrow"></span>
            <div class="detail-outer">
              <div class="detail">
                <div>
                  <span class="name">纪相东</span>
                  <label>[供方]</label>
                  <span class="status">石家庄华能电力有限公司。报价已发布，报价单号：<span class="order">121568215782</span></span>
                </div>
                <div>
                  <span class="name">纪相东</span>
                  <label>[供方]</label>
                  <span class="status">石家庄华能电力有限公司。报价已发布，报价单号：<span class="order">121568215782</span></span>
                </div>
                <div>
                  <span class="name">纪相东</span>
                  <label>[供方]</label>
                  <span class="status">石家庄华能电力有限公司。报价已发布，报价单号：<span class="order">121568215782</span></span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="time-item date">
          <div class="content-left">
            <span>2016-04-26</span>
            <label><span class="dot"></span><i class="line"></i></label>
          </div>
        </div>
        <div class="time-item time">
          <div class="content-left">
            <span>09:21:14</span>
            <label><i class="line"></i><span class="dot"></span></label>
          </div>
          <div class="content-right">
            <span class="left-arrow"></span>
            <div class="detail-outer">
              <div class="detail">
                <div>
                  <span class="name">占立中</span>
                  <label>[买方]</label>
                  <span class="status">发布</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="time-item last">
          <div class="content-left">
            <label><i class="line"></i><span class="dot"></span></label>
          </div>
        </div>
      </div>
    </div>

    <%--<script type="text/javascript" src="js/menu.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/page/main.js"></script>
    <!--[if IE 7]>
      <script type="text/javascript">
        $(window).resize(function(){
          $('#pf-bd').height($(window).height()-76);
        }).resize();
        
      </script>
    <![endif]-->  

    
    <script type="text/javascript">
   
    $(window).resize(function(){
          $('.tabs-panels').height($("#pf-page").height()-46);
          $('.panel-body').not('.messager-body').height($(".easyui-dialog").height)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
      $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


      if($(this).hasClass('disabled')) return;
      if($(this).hasClass('pf-nav-next')){
        page++;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == pages){
          $(this).addClass('disabled');
          $('.pf-nav-prev').removeClass('disabled');
        }else{
          $('.pf-nav-prev').removeClass('disabled');
        }
        
      }else{
        page--;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == 0){
          $(this).addClass('disabled');
          $('.pf-nav-next').removeClass('disabled');
        }else{
          $('.pf-nav-next').removeClass('disabled');
        }
        
      }
    })   
    </script>
</body> 
</html>
