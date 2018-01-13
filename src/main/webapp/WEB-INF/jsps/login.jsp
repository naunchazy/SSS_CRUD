<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
<!-- http://localhost/sss/ -->
	<h1>登录</h1>
	<hr>
	<form action="validate" method="POST">
		用户名:<input type="text" name="username"/><br>
		密码:<input type="password" name="password"/><br>
		<input type="submit" value="登录"/>
	</form>
	
	<br>
	<a href="list">展示所有用户</a>
</body>
</html>