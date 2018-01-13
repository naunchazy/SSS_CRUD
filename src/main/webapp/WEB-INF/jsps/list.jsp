<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列出所有用户</title>
</head>
<body>
	<h1>所有用户信息</h1>
	<!-- http://localhost/sss/list -->
	<hr>
	<table align="center" cellpadding="0px" cellspacing="0px" border="1px" width="400px">
		<tr align="center">
			<td>编号</td>
			<td>用户名</td>
			<td>操作</td>
		</tr>
		<c:if test="${empty userList }">
			<tr><td colspan="3">目前没有用户！</td></tr>
		</c:if>
		<c:if test="${!empty userList }">
			<c:forEach items="${userList }" var="user">
				<tr align="center">
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>
						<a href="javascript:void(0)" onclick="updateById(${user.id})">修改</a>
						<!-- 因为form提交触发action时，每个user的form控件的id都一样为updateUser
							form提交时会默认提交第一个user的id。所以使用id名加上用户id来区别每个用户form控件 -->
						<form action="updateShow/${user.id}" method="post" id="updateUser${user.id}">
							<input type="hidden" name="_method" value="PUT"/>
						</form>
						&nbsp;&nbsp;
						<a href="javascript:void(0)" onclick="deleteById(${user.id})">删除</a>
						<form action="delete/${user.id}" method="post" id="deleteUser${user.id}">
							<input type="hidden" name="_method" value="DELETE" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="3" align="right">
			<!-- ${pageContext.request.contextPath }/ -->
				<a href="insertPro">新增</a>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
		function deleteById(id){
			var form=document.getElementById("deleteUser"+id);
			form.submit();
		}
		function updateById(id){
			var form=document.getElementById("updateUser"+id);
			form.submit();
		}
	</script>
</body>
</html>