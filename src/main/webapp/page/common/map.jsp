<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html lang="zh-CN">

<head>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PoiPicker/examples/index.html -->
    <base href="//webapi.amap.com/ui/1.0/ui/misc/PoiPicker/examples/" />
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>输入框选择POI点</title>
    <style>
        html,
        body,
        #container {
            width: 100%;
            height: 100%;
            margin: 0px;
            font-size: 13px;
        }

        #pickerBox {
            position: absolute;
            z-index: 9999;
            top: 50px;
            right: 30px;
            width: 300px;
        }

        #pickerInput {
            width: 200px;
            padding: 5px 5px;
        }

        #poiInfo {
            background: #fff;
        }

        .amap_lib_placeSearch .poibox.highlight {
            background-color: #CAE1FF;
        }

        .amap_lib_placeSearch .poi-more {
            display: none!important;
        }
    </style>
    <%@include file="/page/common/header.jsp"%><!--静态包含-->
</head>

<body>
<div id="container" class="map" tabindex="0"></div>
<div id="pickerBox">
    <input id="pickerInput" placeholder="输入关键字选取地点" />
    <div id="poiInfo"></div>
</div>
<input type="text" name="lon" size="20" />
<input type="text" name="lat" size="20" />
<input type="text" name="resourceAddress" size="20" />
<script language="javascript" src="http://webapi.amap.com/maps?v=1.4.1&key=c8d499635271ab4f9d449d35911e2cf1"></script>
<!-- UI组件库 1.0 -->
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
<script type="text/javascript">
    var lastMarker=null;
    var map = new AMap.Map('container', {
        zoom: 10
    });

    map.on('click', function(e) {
        $("input[name=lon]").val(e.lnglat.lng);
        $("input[name=lat]").val(e.lnglat.lat);
        // 填写地址
       // writeAddress([e.lnglat.lng,e.lnglat.lat]);
        myMapViewLocation();
    });

    AMapUI.loadUI(['misc/PoiPicker'], function(PoiPicker) {

        var poiPicker = new PoiPicker({
            //city:'北京',
            input: 'pickerInput'
        });

        //初始化poiPicker
        poiPickerReady(poiPicker);
    });

    function poiPickerReady(poiPicker) {

        window.poiPicker = poiPicker;

        var marker = new AMap.Marker();

        var infoWindow = new AMap.InfoWindow({
            offset: new AMap.Pixel(0, -20)
        });

        //选取了某个POI
        poiPicker.on('poiPicked', function(poiResult) {

            var source = poiResult.source,
                    poi = poiResult.item,
                    info = {
                        source: source,
                        id: poi.id,
                        name: poi.name,
                        location: poi.location.toString(),
                        address: poi.address
                    };

            marker.setMap(map);
            infoWindow.setMap(map);

            marker.setPosition(poi.location);
            infoWindow.setPosition(poi.location);
            lastMarker= marker;



            //infoWindow.setContent('POI信息: <pre>' + JSON.stringify(info, null, 2) + '</pre>');
            //infoWindow.open(map, marker.getPosition());
            $("input[name=resourceAddress]").val(poi.address);

            $("input[name=lon]").val( poi.location.lng);
            $("input[name=lat]").val(poi.location.lat);

            map.setCenter(marker.getPosition());
        });

        poiPicker.onCityReady(function() {
            poiPicker.suggest('美食');
        });
    }


    // 填写地址
    function writeAddress(lnglatXY){
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
    }
    // 地址回调
    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress; //返回地址描述
        $("input[name=resourceAddress]").val(address);
    }
    // 实例化点标记
    function addMarker(lnglatXY) {
     //console.log(lnglatXY);
        if( lastMarker!=null){
            lastMarker.hide();
        }

        marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: lnglatXY
        });


        //
        marker.setMap(map);
        map.setFitView();// 执行定位
        lastMarker= marker;
    }
    // 回显
    function myMapViewLocation(){
        //console.log("回显坐标");
        var mlon = $("input[name=lon]").val();
        var mlat = $("input[name=lat]").val();
        $("input[name=resourceAddress]").val("");
        var lnglatXY = [mlon,mlat];
        if(mlon&&mlat){
            addMarker(lnglatXY);
        }
    }


</script>

</body>

</html>