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
    <title>修改学生信息</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="margin-top: 30px">

    <%--需要一个表单--%>
    <div class="row justify-content-center">
        <div class="col-5">

            <%--
            带有SPring MVC数据自动绑定功能的表单
            问题：pwd和clazz不能自动绑定数据
            --%>
            <fm:form method="post" action="${pageContext.request.contextPath}/modify"
                     modelAttribute="stu">
                <fm:hidden path="id"></fm:hidden>
                <fm:hidden path="img"></fm:hidden>

                <%--添加修改头像的功能--%>
                <div class="form-group">
                    <label>个人头像</label>
                    <c:if test="${not empty stu.img}">
                        <img id="selfimg" src="${pageContext.request.contextPath}/assets/img/${stu.img}" height="30" width="30" style="border-radius: 50%;" />
                    </c:if>
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <fm:input path="name" class="form-control"></fm:input>
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <fm:input path="pwd" class="form-control"></fm:input>
                </div>
                <div class="form-group">
                    <label>年龄</label>
                    <fm:input path="age" class="form-control"></fm:input>
                </div>
                <%--选择性别--%>
                <div class="form-group">
                    <label>性别</label>
                    <fm:radiobutton path="sex" value="男" />
                    <label>男</label>
                    <fm:radiobutton path="sex" value="女" />
                    <label>女</label>
                </div>
                <%--选择班级--%>
                <div class="form-group">
                    <label>班级</label>
                    <fm:select path="clazz.id" >
                        <c:forEach var="curclazz" items="${clazzList}">
                            <fm:option value="${curclazz.id}">${curclazz.name}</fm:option>
                        </c:forEach>
                    </fm:select>
                </div>
                <button type="submit" class="btn btn-primary btn-block">确定修改</button>
            </fm:form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery-1.12.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</body>
</html>
