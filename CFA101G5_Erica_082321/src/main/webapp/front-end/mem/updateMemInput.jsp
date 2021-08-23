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
<title>會員資料修改</title>

<%
MemVO memVO = null;
MemVO memVO_session = (MemVO) session.getAttribute("memVO");

MemManagementService svc = new MemManagementService();
//System.out.println("memVO_ID:" + memVO_.getMemId());
memVO = svc.getOneMem(memVO_session.getMemId());
//System.out.println("memVO:" + memVO);

	/* // 確認沒有session
	if (null == memVO_) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/front-end/mem/login.jsp";
		response.sendRedirect(redirectURL);
	}
 */
	// MemVO memVO = (MemVO) request.getAttribute("memVO"); // MemManagementServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
%>

<style>
body {
	padding: 0;
	margin: 0;
	background:
		url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg");
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
</head>
<body>
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
			<div class="col-2"></div>
			<div class="col-8">
				<div class="login">
					<div class="row">
						<div class="col-1"></div>
						<div class="col-10">

							<%
								if (null != memVO) {
							%>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/MemManagementServlet">
									
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label"><font color=white>會員編號</font></label>
										<div><%=memVO.getMemId()%></div> 
									</div>

									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label"><font color=white>帳號</font></label> 
										<div><%=memVO.getMemAcc()%></div>
									</div>
									
									<div class="mb-3">
									<label for="validationServer02" class="form-label">
										<font color=white>密碼</font>
									</label> 
									<input class="form-control"  id = "validationServer02" type="text"  name="memPwd" value="<%=memVO.getMemPwd()%>" required />
									<div class="invalid-feedback">密碼請勿空值!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer03" class="form-label">
										<font color=white>姓名</font>
									</label> 
									<input class="form-control"  id ="validationServer03"  type="TEXT" size="45" name="memName" value="<%=memVO.getMemName()%>" required>
									<div class="invalid-feedback">請填入姓名!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer04" class="form-label" style="display: flow-root;">
										<font color=white>性別</font>
									</label>
									<div class="form-check form-check-inline">
										<input class="form-check-input" id ="validationServer04" type="radio" value="1" name="memGender" id="memGender1" <%=memVO.getAccStatus() == 1 ? "checked" : ""%> required> 
										<label class="form-check-label" for="memGender1" color=white> 
											<font color=white>男</font>
										</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" id ="validationServer04" type="radio" value="2" name="memGender" id="memGender2" <%=memVO.getAccStatus() == 2 ? "checked" : ""%>required> 
										<label class="form-check-label" for="memGender2"> 
											<font color=white>女</font>
										</label>
										<div class="invalid-feedback">請填入性別!</div>
									</div>
								</div>
								<div class="mb-3">
									<label for="validationServer05" class="form-label">
										<font color=white>信箱</font>
									</label> 
									<input class="form-control" id = "validationServer05" type="TEXT" size="45" name="memEmail" value="<%=memVO.getMemEmail()%>" required/>
									<div class="invalid-feedback">請填入信箱!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer06" class="form-label">
										<font color=white>手機</font>
									</label> 
									<input class="form-control" id = "validationServer06" type="TEXT" size="45" name="memMobile" value="<%=memVO.getMemMobile()%>" required/>
								    <div class="invalid-feedback">請填入手機號碼!</div>
								</div>
								<div class="mb-3">
									<label for="validationServer07" class="form-label">
										<font color=white>地址</font>
									</label> 
									<input class="form-control" id = "validationServer07" type="TEXT" size="45" name="memAddr" value="<%=memVO.getMemAddr()%>" required/>
									<div class="invalid-feedback">請填入地址!</div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>會員照片</font></label>
									<%-- 							<input class="form-control" type="text" placeholder="請放入會員照片" name="memPic" size="45" value="${param.memPic}" /> --%>
									<input class="form-control" id="imgId" name="pic" type="file" accept="image/gif, image/jpeg, image/png" value="">
								</div>
								<div class="mb-3">
									<img class="img-thumbnail" id="preview_imgId" src="<%=request.getContextPath() + "/" + memVO.getMemPicSrc()%>" />
								</div>
								<input type="hidden" name="action" value="update"> <input type="hidden" name="memPic" id="pic_base64"> <input type="hidden" name="memId" value="<%=memVO.getMemId()%>"> <input type="hidden" name="memAcc" value="<%=memVO.getMemAcc()%>">



								<div class="mb-3">
									<button type="submit" class="btn btn-primary">送出</button>
								</div>
							</FORM>
						</div>
						<div class="col-1"></div>
					</div>
				</div>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>

</body>
</head>
<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

	function encodeImgtoBase64(element) {

		var img = element.files[0];

		var reader = new FileReader();

		reader.onloadend = function() {

			$('#pic_base64').val(reader.result);
			$("#preview_imgId").attr('src', reader.result);
			$("#preview_imgId").css('display', 'block')
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

					alert("上傳的圖片不能超過4G!");
					$(this).val('');

				} else {
					encodeImgtoBase64(this); // 沒超過64KB才顯示照片
				}

			}
		});
	});
</script>

</html>