<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/common.css"/>
</head>
<body>
<form action="UpdatePassServlet" method="post">
<fieldset>
<%-- <%=request.getAttribute("msg") %> --%>
<font style="color:red">${requestScope.msg }</font>
<br>
姓名 ： ${requestScope.employeename}<br>
<input type="hidden" value="${requestScope.employeename}" name="employeename">
新密码：<input type="password" name="pwd"><br>
<input type="submit" value="确认">
<input type="reset" value="取消">
</fieldset>
</form>
</body>
</html>