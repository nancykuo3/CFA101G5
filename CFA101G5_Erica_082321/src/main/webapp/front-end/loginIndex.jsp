<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
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
#div1 , h2{
margin: 20px auto;
text-align: center;
}

table#table-1 h4 {
	color: red;
	width: 300px;
	height: 80px;
	display: block;
	border: 2px solid #9393FF;
	text-align: center;
	margin-bottom: 1px;

h4 {
	color: black;
	display: inline;
}
</style>
</head>
<body>
<div class="d-grid gap-2 col-6 mx-auto" id="div1">
		<a href="<%=request.getContextPath()%>/front-end/mem/login.jsp">
			<button class="btn btn-success btn-lg" type="button">我是一般會員</button>
		</a>
		<a href="<%=request.getContextPath()%>/front-end/store/storeLogin.jsp">
			<button class="btn btn-success btn-lg" type="button">我是店家會員</button>
		</a>
	</div>
	<%-- <table>
		<td>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
				style="margin-bottom: 10px;">
				<input type="submit" value="一般會員"> <input type="hidden"
					name="memId" value="${memVO.memId}"> <input type="hidden"
					name="action" value="insert">
			</FORM>
		</td>
		<td>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/mem/store.do"
				style="margin-bottom: 10px;">
				<input type="submit" value="店家會員"> <input type="hidden"
					name="storeId" value="${memVO.storeId}"> <input
					type="hidden" name="action" value="insert">
			</FORM>
		</td>
	</table> --%>

</body>
</html>