<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.productType.model.*"%>

<html>
<head>
<meta charset="UTF-8">
<title>新增類型</title>
<!-- BootStrap 5.0.2 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- jQuery 1.12.4 -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>
<style>

</style>
</head>
</head>
<body>
	<%-- <h4>
		<a
			href="<%=request.getContextPath()%>/back-end/product/listAllType.jsp">取消新增，回上頁</a>
	</h4> --%>
	<!-- 錯誤訊息顯示 -->
	<c:if test="${not empty errorMsgs}">
		<b>請修正以下錯誤：</b>
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li style="color: red">${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div>
		<form method="post"
			action="<%=request.getContextPath()%>/productType/AddTypeServlet">
			<table>
				<tr>
					<td>請輸入名稱：<b style="color: red">*</b></td>
					<td><input type="text" name="typeName"
						maxlength="20" placeholder="如：兒童/中文遊戲/10人+" required /></td>
				</tr>
				<jsp:useBean id="typeSvc" scope="page"
					class="com.productType.model.TypeService" />
				<tr>
					<td>類型：</td>
					<td><select size="1" name="typeClass" required>
							<c:forEach var="typeClassVO" items="${typeSvc.classList}">
								<option value="${typeClassVO.typeClass}">${typeClassVO.typeClass}
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<br />
			<button class="btn back-end-btn" type="submit" name="action" value="insert" style="color:#7f70f5; border-color:#7f70f5">送出新增</button>
		</form>
	</div>
</body>
</html>