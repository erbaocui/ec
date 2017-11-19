<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>

    <title></title>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->


</head>
<body class="easyui-layout">

<div>
    <fieldset>
        <legend>方法一：下拉选择框实现省市区（县）三级联动</legend>
        <form action="#">
            <label for="addr-show">您选择的是：
                <input type="text" value="" id="addr-show">
            </label>
            <br/>

            <!--省份选择-->
            <select id="prov" onchange="showCity(this)">
                <option>=请选择省份=</option>

            </select>

            <!--城市选择-->
            <select id="city" onchange="showCountry(this)">
                <option>=请选择城市=</option>
            </select>

            <!--县区选择-->
            <select id="country" onchange="selecCountry(this)">
                <option>=请选择县区=</option>
            </select>
            <button type="button" class="btn met1" onClick="showAddr()">确定</button>
        </form>
    </fieldset>
</div>

</body>
</html>


