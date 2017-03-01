<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/meta.jsp" %>
<%@ include file="common/include_base_style.jsp" %>
<title>登录界面</title>
</head>
<body>
	<center>
		<c:if test="${not empty param.error}">
			<h1>用户名、密码错误，请重新输入</h1>
		</c:if>
		<div>
			<h1>Activiti测试平台</h1>
		</div>
		<form method="post" action="${ctx}/login">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username"/></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" id="passwd" name="passwd" /></td>
				</tr>
			</table>
			<input type="submit" id="sbtn1" value="提交"/>
		</form>
		<ul>
			<li>user1/123456(input)</li>
			<li>user2/123456(check)</li>
			<li>kermit/kermit</li>
		</ul>
	</center>
</body>
</html>