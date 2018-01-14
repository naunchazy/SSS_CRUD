<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建用户页面</title>
</head>
<body>
	<h1>新建用户</h1>
	<!-- http://localhost/sss/insertPro -->
	<hr>
	<form action="insert" method="post" id="insertUser">
	用户名：<input type="text" name="username"/><br>
	密码：<input type="text" name="password"/><br>
	<a href="javascript:void(0)" onclick="insert()">确认新增</a>
	<input type="hidden" name="_method" value="POST" />
	</form>
	<script type="text/javascript">
		function insert(){
			var form=document.getElementById("insertUser");
			form.submit();
		}
	</script>
</body>
</html>