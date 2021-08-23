<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*" %>
<%
StoreVO storeVO = (StoreVO) session.getAttribute("StoreVO");
System.out.println(storeVO.getStoreId());
// String temp = (String) storeVO.getStoreId();
Integer storeid = new Integer(storeVO.getStoreId());
%>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>新增商品</title>
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
			<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front-end/assets/fonts/fontawesome-all.min.css">
			<script src="${pageContext.request.contextPath}/front-end/assets/jquery-1.12.4.min.js"></script>
			<style>
				#formStyle {
					width: 30%;
					margin: auto auto;
				}

				#successHint {
					color: red;
					font-weight: bold;
				}
			</style>
		</head>

		<body>
<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>
			<div id="formStyle">
				<form method="post" action="<%=request.getContextPath()%>/productInfo/AddProdInfoServlet">
					<div class="col">
						<h4>請輸入ISBN：</h4>
					</div>
					<div class="mb-3 row">
						<label for="isbn" class="col-sm-2 col-form-label">ISBN</label>

						<div class="col-sm-10">
							<input type="text" class="form-control" name="isbn" id="isbn" maxlength="20" size="20"
								pattern="^[1-9]{1}\d{1,}$" placeholder="請輸入數字如：4712946397453" required />
						</div>
					</div>
					<span id="successHint">資料庫中有符合商品！</span>
					<span id="failHint">資料庫中無符合商品，請點擊新增</span>
					<div class="col">
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<button class="btn btn-outline-success" id="sendAdd" type="submit" name="action"
							value="testISBN">送出</button>
						<a href="<%=request.getContextPath()%>/front-end/product/addProdInfo.jsp">
							<button class="btn btn-outline-success" id="addProdInfo" type="button">新增</button>
						</a>
					</div>
				</form>
			</div>

			<script>
				$(document).ready(function () {
					$("#successHint").hide();
					$("#failHint").hide();
					$("#sendAdd").hide();
					$("#addProdInfo").hide();

					$("#isbn").on("change", function () {
						let isbn = $.trim($("#isbn").val());
						console.log("Input: " + isbn);

						if (isbn.length == 13) {
							$.ajax({
								url: "/CFA101G5/product/TestISBNServlet",
								type: "POST",
								data: {
									"inputISBN": isbn // 將輸入的值送到後方控制器做比對
								},
								dataType: "JSON",
								success: function (data) {

									console.log("Result = " + data.result);
									let result = data.result;

									if (result == "fail") {
										$("#failHint").show();
										$("#addProdInfo").show();
									}
									if (result == "success") {
										$("#successHint").show();
										setTimeout(window.location.assign("/CFA101G5/front-end/product/addProduct.jsp"), 5000);
									}
								}
							})
						}
					});
				})
			</script>
		</body>

		</html>