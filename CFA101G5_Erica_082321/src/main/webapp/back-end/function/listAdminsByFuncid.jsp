<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admin.model.*"%>

<jsp:useBean id="listAdminsByFuncid" scope="request"
	type="java.util.Set<AdministratorVO>" /><!-- 可省略 -->
<jsp:useBean id="funcSvc" scope="page"
	class="com.function.model.FunctionService" />

<html>
<head>
<meta charset="UTF-8">
<title>權限管理員列表</title>
<style>
	.back-end-btn{
		color:#7f70f5; 
		border-color:#7f70f5
	}
		
	.back-end-btn:hover{
		background-color: #7f70f5;
		color: #ffffff !important;
	}
</style>
</head>
<body>
	<div style="padding:20px 15px;">
		<h2>權限管理員列表：</h2>
	
		<div>
			<table class="table table-striped table-sm table-hover">
				<tr>
					<th scope="col">編號</th>
					<th scope="col">帳號</th>
					<th scope="col">密碼</th>
					<th scope="col">名稱</th>
					<th scope="col">帳號啟用狀態</th>
					<th scope="col">修改</th>
					<th scope="col">刪除</th>
				</tr>
				<c:forEach var="adminVO" items="${listAdminsByFuncid}">
					<tr ${(adminVO.adminid==param.adminid) ? 'bgcolor=blue':''}>
						<td>${adminVO.adminid}</td>
						<td>${adminVO.adminacc}</td>
						<td>${adminVO.adminpwd}</td>
						<td>${adminVO.adminName}</td>
						<td>
							<c:if test="${adminVO.adminStatus==1}">
								有效
							</c:if> 
							<c:if test="${adminVO.adminStatus==0}">
								失效
							</c:if>
						</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/admin/UpdateAdminServlet">
								<input type="hidden" name="adminid" value="${adminVO.adminid}">
								<input type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<button type="submit" name="action" value="updateOneAdmin" class="btn back-end-btn">修改</button>
							</form>
						</td>
						<td>
							<form method="post"
								action="<%=request.getContextPath()%>/admin/DeleteAdminServlet">
								<input type="hidden" name="adminid" value="${adminVO.adminid}">
								<input type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<button type="submit" name="action" value="deleteOneAdmin" class="btn back-end-btn">刪除</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<c:if test="${not empty errorMsgs}">
				<b>請修正以下錯誤：</b>
				<ul>
					<c:forEach var="msg" items="${errorMsgs}">
						<li>${msg}</li>
					</c:forEach>
				</ul>
	
			</c:if>
		</div>
	</div>
</body>
</html>