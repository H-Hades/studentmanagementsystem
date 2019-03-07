<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--打开El表达式--%>
<%@ page isELIgnored="false" %>
<%--把jstl很多好用的标签都引入进来了--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Spring MVC提供了专门的表单标签库 专门用来进行数据绑定操作的--%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>增加学生信息</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="margin-top: 30px">
    <%-- <nav aria-label="breadcrumb">
         <ol class="breadcrumb">
             <label style="color: #f014e5;">我的位置：</label>
             <li class="breadcrumb-item"><a href="userlist">用户首页</a></li>
             <li class="breadcrumb-item"><a href="studentlist">学生管理</a></li>
             <li class="breadcrumb-item active" aria-current="page">修改学生</li>
         </ol>
     </nav>--%>
    <%--需要一个表单--%>
    <div class="row justify-content-center">
        <div class="col-5">

            <%--
            带有SPring MVC数据自动绑定功能的表单
            问题：pwd和clazz不能自动绑定数据
            --%>
            <fm:form method="post" action="${pageContext.request.contextPath}/add"
                     modelAttribute="stu">
                <div class="form-group">
                    <label>姓名</label>
                    <fm:input path="name" class="form-control"></fm:input>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <fm:password path="pwd" class="form-control"></fm:password>
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <fm:input path="age" class="form-control"></fm:input>
                </div>
                <%--选择性别--%>
                <div class="form-group">
                    <fm:radiobutton path="sex" value="男" />
                    <label>男</label>
                    <fm:radiobutton path="sex" value="女" />
                    <label>女</label>
                </div>
                <%--选择班级--%>
                <div class="form-group">
                    <fm:select path="clazz.id">
                        <c:forEach var="curclazz" items="${clazzList}">
                            <fm:option value="${curclazz.id}">${curclazz.name}</fm:option>
                        </c:forEach>
                    </fm:select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">添加</button>
            </fm:form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</body>
</html>
