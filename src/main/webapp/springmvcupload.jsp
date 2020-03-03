<%--
  Created by IntelliJ IDEA.
  User: pankx
  Date: 2020/3/3
  Time: 4:37 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用SpringMVC上传文件</title>
</head>
<body>
    <h1>使用SpringMVC上传文件</h1>
    <%--    必须设置form的enctype的属性为multipart/form-data否则文件上传失败--%>
    <form action="upload/mvcupload" method="post" enctype="multipart/form-data">
        <input type="file" name="mvcuploadfile">
        <hr/>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>
