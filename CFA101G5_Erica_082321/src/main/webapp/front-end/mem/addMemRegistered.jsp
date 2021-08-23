<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<title>會員資料新增</title>

<style>
body{
	padding: 0;
	margin: 0;
/*  	background: url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg");  */
	background-size: cover;
	background-attachment: fixed;
	background-position: center; 
}
.login {
 	background-color: rgba(13, 11, 12, 0.5);
 	border: 0px solid white;
 	text-align: center;
 	border-radius: 20px 20px 20px 20px;
 	font-size: 25px;
 	margin: auto;
 	font-style: "微軟正黑體", cursive;
 	line-height: 40px;
 }
</style>
<body>

</head>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<div class="container">
  <div class="row align-items-center">
    <div class="col-2">
    </div>
    <div class="col-8">
   	<div class="login">
	    <div class="row">
	    	<div class="col-1">
	    	</div>
	    	<div class="col-10">
				<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/mem/MemManagementServlet">
						<h1><font color=white>會員資料新增</font></h1>
						</br>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>帳號</font></label>
							<input class="form-control" type="text" placeholder="請輸入帳號" name="memAcc" size="45" value="${param.memAcc}" />
						</div>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>密碼</font></label>
							<input class="form-control" type="password" placeholder="請輸入密碼" size="45" name="memPwd" value="${param.memPwd}" />
						</div>
						<font color=white>姓名</font>
						<input class="form-control" type="text" placeholder="請輸入姓名" name="memName" size="45" value="${param.memName}" />
						
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label" style="display: flow-root;"><font color=white >性別</font></label>
							<div class="form-check form-check-inline">
		  						<input class="form-check-input" type="radio" name="memGender" id="memGender1">
		  						<label class="form-check-label" for="memGender1"  color=white>
		    						<font color=white>男</font>
		  						</label>
							</div>
							<div class="form-check form-check-inline">
		  					<input class="form-check-input" type="radio" name="memGender" id="memGender2" checked>
		  					<label class="form-check-label" for="memGender2">
		   							<font color=white>女</font>
		  					</label>
							</div>
						</div>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>信箱</font></label>
							<input class="form-control" type="EMAIL" placeholder="請輸入信箱" name="memEmail" size="45" value="${param.memEmail}" />
						</div>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>手機</font></label>
							<input class="form-control" type="TEXT" placeholder="請輸入手機" size="45" name="memMobile" value="${param.memMobile}" />
						</div>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>地址</font></label>
							<input class="form-control" type="text" placeholder="請輸入地址" name="memAddr" size="45" value="${param.memAddr}" />
						</div>
<!-- 						// 圖片 -->
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label"><font color=white>會員照片</font></label>
<%-- 							<input class="form-control" type="text" placeholder="請放入會員照片" name="memPic" size="45" value="${param.memPic}" /> --%>
							<input class="form-control" id="imgId" name="pic" type="file" accept="image/gif, image/jpeg, image/png" value="">
						</div>
						<div class="mb-3">						
							<img style="display:none" class="img-thumbnail" id="preview_imgId" src="" />
						</div>	
						<input type="hidden" name="action" value="insert"> 
						<input type="hidden" name="memPic" id="pic_base64"> 
<!-- 						// 圖片 -->
						<div class="mb-3">
							<button type="submit"  class="btn btn-primary">送出</button>					
						</div>
				</FORM>	
	    	</div>
	    	<div class="col-1">
	    	</div>
	    	</div>
	    </div>
    </div>
    <div class="col-2">
    </div>
  </div>
</div>

</body>
</html>


		

</body>
	


<script>

	function encodeImgtoBase64(element) {

		var img = element.files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			$('#pic_base64').val(reader.result);
			$("#preview_imgId").attr('src', reader.result);
			$("#preview_imgId").css('display','block')
		}
		reader.readAsDataURL(img);
	}

	$('input[type=file]').each(function() {
		var max_size = 4194304;
		$(this).change(function(evt) {

			var finput = $(this);
			var files = evt.target.files; // 獲得檔案物件   
			var output = [];
			for (var i = 0, f; f = files[i]; i++) { //檢查檔案大小   

				if (f.size > max_size) {

					alert("上傳的圖片不能超過64KB!");
					$(this).val('');

				} 
				else {
					encodeImgtoBase64(this); // 沒超過64KB才顯示照片
				}

			}
		});
	});

</script>

</html>