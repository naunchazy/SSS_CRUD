<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改用户信息页面</title>
</head>
<body>
	<h1>更改用户信息</h1>
	<!-- http://localhost/sss/updateShow/2 -->
	<hr>
	<form action="update/${user.id}" method="POST" id="updateUser">
		<!-- 修改用户需要的控件名，但因被定义主键id为数据库自增策略。不能修改，所以隐藏 -->
		<input type="hidden" name="id" value="${user.id}"/>
		用户名:<input type="text" name="username" value="${user.username}"/><br>
		密码:<input type="text" name="password" value="${user.password}"/><br>
		<a href="javascript:void(0)" onclick="update()">确认更改</a>
		<input type="hidden" name="_method" value="PUT" />
	</form>
	<script type="text/javascript">
		function update(){
			var form=document.getElementById("updateUser");
			form.submit();
		}
		function usernameChange(){
			this.value=this.change;
		}
	</script>
	
</body>
</html>
<!-- <input type="hidden" name="_method" value="DELETE" /> -->