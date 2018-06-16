<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
<script type="text/javascript">
	/*
	 * 1. 在文档加载完成后
	 * 获取所有省，添加到<select id="province">中
	 * 给<select id="province">这个元素添加onchange事件
	事件内容：
	1. 获取当前选择的省id
	2. 使用省id访问servlet，得到该省下所有市
	3. 把每个市添加到<select id="city">中
	
	 */
	 function createXMLHttpRequest(){
	 	try{
	 		return new XMLHttpRequest();
	 	}catch(e){
	 		try{
	 			return new ActiveXObject("Msxml2.XMLHTTP");
	 		}catch(e){
	 			return new ActiveXObject("Micorsoft.XMLHTTP");
	 		}
	 	}
	 }
	 window.onload = function(){
	 
	/*
	发送异步请求，得到所有省，然后使用每个省生成一个<option>元素添加到<select id="province">中
	 */
	// 得到核心对象
	var xmlHttp = createXMLHttpRequest();
	//打开链接
	xmlHttp.open("GET", "<c:url value='/servlet/ProvinceServlet'/>", true);
	//发送
	xmlHttp.send(null);
	//添加监听
	xmlHttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4){
			if(xmlHttp.status == 200){
				// 执行服务器发送过来的json字符串，得到js的对象
				var proArray = eval("(" + xmlHttp.responseText + ")");
				for(var i = 0; i < proArray.length; i++){
					var pro = proArray[i];//得到每个pro对象
					//创建<option>元素
					var optionEle = document.createElement("option");
					// 给<option>元素的value属性赋值
					optionEle.value = pro.pid;//给<option>的实际值赋为pid，而不是name
					var textNode = document.createTextNode(pro.name);//使用省名称来创建textNode
					optionEle.appendChild(textNode);//把textNode添加到option元素中
					
					//使用省名称来创建textNode
					document.getElementById("province").appendChild(optionEle);
				}
			}
		}
	};
	
	/*
	2. 给<select id="pronvine">添加onchange监听
	*/
	document.getElementById("province").onchange = function() {
		/*
		异步请求服务器，得到选择的省下所有市
		*/
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.open("POST", "<c:url value='/servlet/CityServlet'/>", true);
		// 设置请求头
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		// 发送
		xmlHttp.send("pid=" + this.value);//用户选择的省
		
		// 添加监听
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				if(xmlHttp.status == 200) {
					/*
					0. 先要清空原来的<option>元素
					1. 得到服务器发送过来的所有市
					2. 使用每个市生成<option>元素添加到<select id="city">中
					*/
					/*
					清空<select id="city">中的选项
					*/
					var citySelect = document.getElementById("city");
					// 获取select中的所有子元素
					var cityOptionArray = citySelect.getElementsByTagName("option");
					while(cityOptionArray.length > 1) {//这里只控制循环的次数
						citySelect.removeChild(cityOptionArray[1]);//只删除1下标，不会删除0
					}
					
					/*
					得到服务器发送过来的所有市
					*/
					var cityArray = eval("(" + xmlHttp.responseText + ")");
					// 循环遍历每个city对象，用来生成<option>元素添加到<select id="city">中
					for(var i = 0; i < cityArray.length; i++) {
						var city = cityArray[i];//得到每个city对象
						// 创建<option>元素
						var optionEle = document.createElement("option");
						// 给<option>元素的value属性赋值
						optionEle.value = city.cid;//给<option>的实际值赋为cid，而不是name
						var textNode = document.createTextNode(city.name);//使用省名称来创建textNode
						optionEle.appendChild(textNode);//把textNode添加到option元素中
						
						// 把option元素添加到select元素中
						citySelect.appendChild(optionEle);
					}
				}
			}
		};
	};
	};
</script>
</head>

<body>
	<h1>省市联动</h1>
	省：
	<select name="province" id="province">
		<option value="">===请选择===</option>
	</select> 市：
	<select name="city" id="city">
		<option value="">===请选择===</option>
	</select>
</body>
</html>
