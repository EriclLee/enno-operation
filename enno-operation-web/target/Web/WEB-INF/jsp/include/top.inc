<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title>Enno-Operation/></title>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.validity.js"  type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/Console.css" rel="stylesheet" />
</head>

<body class="enno-body">
<div class="container">
	<div class="viewport" id="console">
		<div class="nav-global">
			<div>
				<div class="global-logo">
					<p class="site-title">Enno-Operation Platform</p>
				</div>
				<div class="items-inner">
					<section id="login">

					</section>
				</div>
			</div>
		</div>
		<div class="nav-zone">
			<div class="nav-zone-inner">
				<%@ include file="leftmenu.inc"%>
			</div>
		</div>
		<div class="content" id="content">


