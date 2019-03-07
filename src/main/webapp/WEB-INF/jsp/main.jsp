<%--
  Created by IntelliJ IDEA.
  User: shilei
  Date: 2018/11/24
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%--对当前jsp页面内容进行一个规定，text/html规定了页面的内容格式
charset=UTF-8规定了页面的内容编解码格式  language="java"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--把jstl很多好用的标签都引入进来了--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>学生列表2</title>

    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-12">
            当前登录用户：${student.name}
        </div>
    </div>

    <div class="row">
        <div class="col-2">
            <a href="add" class="btn btn-primary btn-block">增加学生</a>
        </div>
    </div>

    <div class="row">
        <div class="col-2">
            <form method="post" action="${pageContext.request.contextPath}/uploadfile" enctype="multipart/form-data">
                <%--添加multiple一次可以选择上传多个文件；或者添加多个input<type="file">的表单控件--%>
                <input type="file" name="files" multiple="multiple">
                <input type="submit" class="btn btn-primary btn-block" value="上传共享文件">
            </form>
        </div>
    </div>

    <%--显示所有上传的文件，供用户下载，放在assets/file文件夹下面--%>
    <div class="row">
        <c:forEach var="file" items="${ fileMap }" varStatus="status">
            <div class="col-4">
                <a href="${pageContext.request.contextPath}/download?fileName=${file.value}">${file.key}</a>
            </div>
        </c:forEach>
    </div>

    <div class="row">
        <table class="table table-striped">
            <tr style="background-color: black">
                <th>编号</th>
                <th>头像</th>
                <th>姓名</th>
                <th>密码</th>
                <th>年龄</th>
                <th>性别</th>
                <th>班级</th>
                <th>操作</th>
            </tr>
            <tr>

                <c:forEach var="stu" items="${stuList}" varStatus="status">
            <tr>
                <td>${ status.index + 1} </td>
                <td>
                    <c:choose>
                        <c:when test="${stu.img == null}">
                            <%--上传文件的表单enctype必须设置成multipart/form-data--%>
                            <form method="post" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
                                <input type="hidden" name="uid" value="${stu.id}">
                                <input type="file" name="file">
                                <input type="submit" value="上传头像">
                            </form>
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/assets/img/${stu.img}" height="30" width="30" style="border-radius: 50%;" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${stu.name}</td>
                <td>${stu.pwd}</td>
                <td>${stu.age}</td>
                <td>${stu.sex}</td>
                <td>
                    <a href="javascript:" onclick="showClazzInfo('${stu.clazz.name}', ${stu.clazz.number})">班级信息</a>
                </td>
                <td>
                    <a href="modify/${stu.id}">修改</a>
                    <a href="delete/uid/${stu.id}">删除</a>
                </td>
            </tr>
            </c:forEach>
            </tr>
        </table>
    </div>


    <%--一个bs的模态框的显示--%>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">班级信息</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    班级名称：<span id="clazzname"></span>
                    <br/>
                    班级人数：<span id="clazznumber"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="assets/js/jquery-1.12.0.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script>
    function showClazzInfo(name, number){
        $("#clazzname").text(name);
        $("#clazznumber").text(number);
        $("#exampleModalCenter").modal('show'); // 'hide'
    }
</script>
</body>
</html>
