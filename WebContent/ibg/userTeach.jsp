<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	try {
		boolean authed = (Boolean) session.getAttribute("authed");
		boolean permision = (Boolean) session.getAttribute("alphaPerm");

		if (!authed) {
			throw new NullPointerException();
		}
		if (!permision) {
			request.setAttribute("msg", "对不起~这个服务暂时只有内测资格的账户才能使用");
			request.getRequestDispatcher("../account/AlphaRegister.jsp").forward(request, response);
		}
	} catch (NullPointerException e) {
		response.sendRedirect("../account/login.jsp");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>IBG - 教G器人说话 (。_。)</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="./userteach_files/style.css">

<style>
textarea {
	width: 100%;
	padding: 10px;
	border: 3px solid #999;
	margin-bottom: 50px;
}

#teach_input,#teach_response {
	-webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
	-moz-box-sizing: border-box; /* Firefox, other Gecko */
	box-sizing: border-box; /* IE 8+ */
}
</style>

<style type="text/css" style="display: none !important;">
* {
	margin: 0;
	padding: 0;
}

body {
	overflow-x: hidden;
}

.bsa_it_ad {
	padding: 8px 4px 8px 12px !important;
	position: relative;
	border: 0 !important;
	background: #D6D5D5 !important;
	border-top: 0 !important;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
	font: 11px "Lucida Grande", Sans-Serif !important;
}

.bsa_it_ad:before,.bsa_it_ad:after {
	content: "";
	position: absolute;
	top: 0;
	left: 6px;
	width: 100%;
	height: 100%;
	background: #989898;
	border-bottom: 6px solid #989898;
	z-index: -1;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
}

.bsa_it_ad:before {
	top: 0;
	left: 12px;
	z-index: -2;
	background: #6C6666;
	border-bottom: 12px solid #6C6666;
}

.bsa_it_ad a {
	margin: 0 !important;
	padding: 0 !important;
}

.bsa_it_ad a img {
	border: 0 !important;
	position: static !important;
}

.bsa_it_ad a:hover img {
	margin: 0 !important;
}

.bsa_it_ad a:hover {
	background: none !important;
}

.bsa_it_i {
	margin: 0 15px 0 0 !important;
}

.bsa_it_t {
	font-size: 14px !important;
	margin: 12px 0 0 0 !important;
}

.bsa_it_d {
	padding-right: 10px;
}

.bsa_it_p {
	display: none !important;
}

#demo-bar-ad {
	width: 416px;
	position: absolute;
	right: 0;
	top: -20px;
	font: 11px "Lucida Grande", Sans-Serif !important;
}

#bsap_aplink {
	position: absolute;
	color: #999;
	text-decoration: none;
	bottom: 8px !important;
	right: 8px !important;
	padding: 0 !important;
}

.bsa_it_p a:hover {
	background: none !important;
}

#demo-top-bar {
	text-align: left;
	background: #e18728;
	position: relative;
	zoom: 1;
	width: 100% !important;
	z-index: 6000;
	box-shadow: 0 0 10px black;
	padding: 20px 0 15px;
}

#demo-bar-inside {
	width: 960px;
	margin: 0 auto;
	position: relative;
	font-size: 36px;
	color: #FFF;
	font-weight: bold;
	font-family: "Arial Black", Gadget, sans-serif;
}

#demo-top-bar:before,#demo-top-bar:after {
	content: "";
	position: absolute;
	top: 0;
	left: 6px;
	width: 100%;
	height: 100%;
	background: #e18728;
	border-bottom: 6px solid #8F5314;
	z-index: -1;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
}

#demo-top-bar:before {
	top: 0;
	left: 12px;
	background: #6C6666;
	border-bottom: 12px solid #62390E;
}

#demo-bar-buttons {
	display: inline-block;
	width: 236px;
	text-align: center;
	vertical-align: top;
	font-size: 0;
}

#demo-bar-buttons a {
	font-size: 12px;
	color: white;
	display: block;
	margin: 2px 0;
	text-decoration: none;
	font: 14px "Lucida Grande", Sans-Serif !important;
}

#demo-bar-buttons a:hover,#demo-bar-buttons a:focus {
	color: #333;
}

#demo-bar-badge {
	display: inline-block;
	width: 302px;
	padding: 0 !important;
	margin: 0 !important;
	background-color: transparent !important;
}

#demo-bar-badge a {
	display: block;
	width: 100%;
	height: 38px;
	border-radius: 0;
	bottom: auto;
	margin: 0;
	background: url(/images/examples-logo.png) no-repeat;
	background-size: 100%;
	overflow: hidden;
	text-indent: -9999px;
}

#demo-bar-badge:before,#demo-bar-badge:after {
	display: none !important;
}
</style>

<script src="/js/loadxmldoc.js"></script>
<script>
	function sendLesson(url) {
		xmlFunction = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				xmlDoc = xhttp.responseText;
				console.log(xmlDoc);
				document.getElementById("msg").innerHTML = xmlDoc;
			}
		}
		loadXMLDoc(encodeURI(url), xmlFunction);
	}
</script>

</head>


<body>

	<div id="demo-top-bar">

		<div id="demo-bar-inside">G器人训练系统</div>

	</div>
	<div id="page-wrap">

		<br /> <br /> <font id="msg" color="red"></font> <br /> <label>当你说:</label>
		<textarea id="teach_input" name="teach_input" rows="10" cols="10"
			placeholder="为什么人们喜欢在蚊子咬的地方画上十字？( °◇ °)" tabindex="1"></textarea>
		<br /> <br /> <br /> <label>G器人要回答:</label>
		<textarea id="teach_response" name="teach_response" rows="10"
			cols="10" placeholder="因为吸血鬼怕十字架……＜（￣︶￣）／" tabindex="2"></textarea>
		<br /> <input type="submit" name="submit" id="submit" value="教给G器人!!"
			tabindex="3"
			onmouseup="sendLesson('TeachIBG?teach_input=' + teach_input.value + '&teach_response=' + teach_response.value)" />

	</div>


</body>
</html>