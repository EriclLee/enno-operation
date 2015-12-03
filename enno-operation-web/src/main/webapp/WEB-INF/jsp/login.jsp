<%--
  Created by IntelliJ IDEA.
  User: EriclLee
  Date: 15/12/2
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
  <title>Enno-Operation Login Page</title>
  <link href="${pageContext.request.contextPath}/css/Console.css" rel="stylesheet" />
</head>
<body class="modal-ready">
<div class="container">
  <div class="page-home">
    <div class="wrapper">
      <div class="home-logo en"></div>
      <div class="form-wrapper" style="width:246px">
        <form class="form" action="/login" method="POST">
          <%--<c:if test="${loginFaild==true}">--%>
            <%--<span>${loginMessage}</span>--%>
          <%--</c:if>--%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <div style="">
            <fieldset><legend>Login</legend>
              <div class="item user">
                <input type="text" id="username" name="username" placeholder="User Name">
              </div>
              <div class="item password">
                <input type="password" id="password" name="password" placeholder="Password">
              </div>
              <div class="item">
                <input class="btn btn-primary" type="submit" value="Login" style="width:246px">
              </div>
            </fieldset>
          </div>
        </form>
      </div>
      <div class="home-footer"></div>
    </div>
  </div>
</div>
</body>
</html>
