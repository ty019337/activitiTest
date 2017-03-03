<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<%@ include file="common/meta.jsp" %>
<%@ include file="common/include_base_style.jsp" %>
</head>
<body>
	<p>input form</p>
	<p><%=request.getParameter("biz_no") %></p>
	<form action="${ctx }/inputForm/save" method="POST" >
		<table>
			<tr>
				<td>taskId</td>
				<td><input type="text" id="taskId" name="taskId" value="<%=request.getParameter("taskId") %>" /></td>
			</tr>
			<tr>
				<td>业务编号</td>
				<td><input type="text" id="biz_no" name="biz_no" value="<%=request.getParameter("biz_no") %>" /></td>
			</tr>
			<tr>
				<td>账号</td>
				<td><input type="text" id="acct" name="acct"/></td>
			</tr>
			<tr>
				<td>笔数</td>
				<td><input type="text" id="sheets" name="sheets"/></td>
			</tr>
			<tr>
				<td>金额</td>
				<td><input type="text" id="amt" name="amt"/></td>
			</tr>
			<input type="submit" id="sbtn1" value="提交"/>
		</table>
	</form>
</body>
</html>