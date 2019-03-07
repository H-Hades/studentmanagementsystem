<%--
  Created by IntelliJ IDEA.
  User: shilei
  Date: 2018/11/24
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>404页面</title>

    <%--http://www.tulun.com/360buy/man/closer     aeests--%>
    <%--http://www.tulun.com/360buy/assets/css/bootstrap.min.css" --%>
    <%--

    http://www.tulun.com/360buy/man/user/login/list
    http://www.tulun.com/user/login/list

    http://www.tulun.com/360buy/user/login/list

    action = "${pageContext.request.contextPath}/user/login/list"
    --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="margin-top: 100px;">
    <div class="row justify-content-center">
        <div class="col-8">
            <h1>${errmsg}</h1>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.0.min.js"></script>
</body>
</html>