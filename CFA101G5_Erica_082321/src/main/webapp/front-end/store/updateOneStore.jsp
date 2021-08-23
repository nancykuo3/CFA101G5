<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<%
StoreVO storeVO = null;
StoreVO storeVO_session = (StoreVO) session.getAttribute("StoreVO");
	StoreService svc = new StoreService();
	storeVO = svc.getOneStore(storeVO_session.getStoreId());
	System.out.println(storeVO);
	/* // 確認沒有session
	if (null == storeVO) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/front-end/store/storeLogin.jsp";
		response.sendRedirect(redirectURL);
	} */
	// 有

	// 	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<title>會員資料修改</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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

	<div class="container">
		<div class="row align-items-center">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="login">
					<div class="row">
						<div class="col-1"></div>
						<div class="col-10">

							<%
								if (null != storeVO) {
							%>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet">

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>店家編號</font></label>
									<div><%=storeVO.getStoreId()%></div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>帳號</font></label>
									<div><%=storeVO.getStoreAcc()%></div>
								</div>

								<div class="mb-3">
									<label for="validationServer02" class="form-label">
										<font color=white>密碼</font>
									</label> 
									<input class="form-control"  id = "validationServer02" type="text" placeholder="請輸入密碼" size="45" name="storePwd" value="<%=storeVO.getStorePwd()%>" required/>
								    <div class="invalid-feedback">密碼請勿空值!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer03" class="form-label">
										<font color=white>店家名稱</font>
									</label> 
									<input class="form-control" id = "validationServer03" type="TEXT" size="45" name="storeName" value="<%=storeVO.getStoreName()%>" required/>
									<div class="invalid-feedback">請填入店家名稱!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer04" class="form-label">
										<font color=white>統一編號</font>
									</label> 
									<input class="form-control" id = "validationServer04"  type="TEXT" size="45" name="storeGui" value="<%=storeVO.getStoreGui()%>" required/>
								    <div class="invalid-feedback">請填入統一編號!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer05" class="form-label">
										<font color=white>負責人</font>
									</label> 
									<input class="form-control" id = "validationServer05" type="TEXT" size="45" name="storePic" value="<%=storeVO.getStorePic()%>" required/>
									<div class="invalid-feedback">請填入負責人姓名!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer06" class="form-label">
										<font color=white>電話</font>
									</label> 
									<input class="form-control" id = "validationServer06" type="TEXT" size="45" name="storeTel" value="<%=storeVO.getStoreTel()%>" required/>
									<div class="invalid-feedback">請填入電話號碼!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer07" class="form-label">
										<font color=white>傳真</font>
									</label> 
									<input class="form-control" id = "validationServer07" type="TEXT" size="45" name="storeFax" value="<%=storeVO.getStoreFax()%>" required/>
									<div class="invalid-feedback">請填入傳真號碼!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer08" class="form-label">
										<font color=white>登記地址</font>
									</label> 
									<input class="form-control"  id = "validationServer08" type="TEXT" size="45" name="storeAdd" value="<%=storeVO.getStoreAdd()%>" required/>
									<div class="invalid-feedback">請填入登記地址!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer09" class="form-label">
										<font color=white>聯絡人</font>
									</label> 
									<input class="form-control"  id = "validationServer09" type="TEXT" size="45" name="storePoc" value="<%=storeVO.getStorePoc()%>" required/>
									<div class="invalid-feedback">請填入聯絡人!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer10" class="form-label">
										<font color=white>連絡電話</font>
									</label> 
									<input class="form-control" id = "validationServer10" type="TEXT" size="45" name="storeConPhone" value="<%=storeVO.getStoreConPhone()%>" required/>
									<div class="invalid-feedback">請填入連絡電話!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer11" class="form-label">
										<font color=white>聯絡地址</font>
									</label> 
									<input class="form-control" id = "validationServer11" type="TEXT" size="45" name="storeConAdd" value="<%=storeVO.getStoreConAdd()%>" required/>
									<div class="invalid-feedback">請填入連絡地址!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer12" class="form-label">
										<font color=white>信箱</font>
									</label> 
									<input class="form-control" id = "validationServer12" type="TEXT" size="45" name="storeEmail" value="<%=storeVO.getStoreEmail()%>" required/>
									<div class="invalid-feedback">請填入信箱!</div>
								</div>

								<div class="mb-3">
									<label for="validationServer13" class="form-label">
										<font color=white>轉帳帳號</font>
									</label> 
									<input class="form-control"  id = "validationServer13" type="TEXT" size="45" name="bankAccount" value="<%=storeVO.getBankAccount()%>" required/>
									<div class="invalid-feedback">請填入轉帳帳號!</div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>總分數(商城)</font></label>
									<div><%=storeVO.getStoreShopRatingScore()%></div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>總分數(商城)</font></label>
									<div><%=storeVO.getStoreShopRatingCount()%></div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>總評價數(預約)</font></label>
									<div><%=storeVO.getStoreRsvnRatingScore()%></div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>總分數(預約)</font></label>
									<div><%=storeVO.getStoreRsvnRatingCount()%></div>
								</div>

								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label"><font color=white>被檢舉計點</font></label>
									<div><%=storeVO.getStoreReportCount()%></div>
								</div>

								<div class="mb-3">
									<label for="validationServer14" class="form-label"><font color=white>可容納人數</font></label>
									<input class="form-control" id = "validationServer14" type="TEXT" size="45" name="capacity" value="<%=storeVO.getCapacity()%>" required/>
									<div class="invalid-feedback">請填入可容納人數!</div>
								</div>

								<div class="dropdown">
								<select name="dayOff" class="form-select" aria-label="Default select example" >
<!-- 								  <option >請選擇公休日</option> -->
								  <option value="1" <%=storeVO.getDayOff()%>>星期一</option>
								  <option value="2">星期二</option>
								  <option value="3">星期三</option>
								  <option value="4">星期四</option>
								  <option value="5">星期五</option>
								  <option value="6">星期六</option>
								  <option value="7">星期日</option>
								</select>
							</div>
							<br>
							<div class="dropdown">
								<select class="form-select" aria-label="Default select example" name=startTime>
<!-- 								  <option selected>請選擇營業開始時段</option> -->
								  <option value="1" <%=storeVO.getOpeningHours().indexOf("1") == 1 ? "selected" : "" %>>01:00</option>
								  <option value="2" <%=storeVO.getOpeningHours().indexOf("1") == 2 ? "selected" : "" %>>02:00</option>
								  <option value="3" <%=storeVO.getOpeningHours().indexOf("1") == 3 ? "selected" : "" %>>03:00</option>
								  <option value="4" <%=storeVO.getOpeningHours().indexOf("1") == 4 ? "selected" : "" %>>04:00</option>
								  <option value="5" <%=storeVO.getOpeningHours().indexOf("1") == 5 ? "selected" : "" %>>05:00</option>
								  <option value="6" <%=storeVO.getOpeningHours().indexOf("1") == 6 ? "selected" : "" %>>06:00</option>
								  <option value="7" <%=storeVO.getOpeningHours().indexOf("1") == 7 ? "selected" : "" %>>07:00</option>
								  <option value="8" <%=storeVO.getOpeningHours().indexOf("1") == 8 ? "selected" : "" %>>08:00</option>
								  <option value="9" <%=storeVO.getOpeningHours().indexOf("1") == 9 ? "selected" : "" %>>09:00</option>
								  <option value="10" <%=storeVO.getOpeningHours().indexOf("1") == 10 ? "selected" : "" %>>10:00</option>
								  <option value="11" <%=storeVO.getOpeningHours().indexOf("1") == 11 ? "selected" : "" %>>11:00</option>
								  <option value="12" <%=storeVO.getOpeningHours().indexOf("1") == 12 ? "selected" : "" %>>12:00</option>
								  <option value="13" <%=storeVO.getOpeningHours().indexOf("1") == 13 ? "selected" : "" %>>13:00</option>
								  <option value="14" <%=storeVO.getOpeningHours().indexOf("1") == 14 ? "selected" : "" %>>14:00</option>
								  <option value="15" <%=storeVO.getOpeningHours().indexOf("1") == 15 ? "selected" : "" %>>15:00</option>
								  <option value="16" <%=storeVO.getOpeningHours().indexOf("1") == 16 ? "selected" : "" %>>16:00</option>
								  <option value="17" <%=storeVO.getOpeningHours().indexOf("1") == 17 ? "selected" : "" %>>17:00</option>
								  <option value="18" <%=storeVO.getOpeningHours().indexOf("1") == 18 ? "selected" : "" %>>18:00</option>
								  <option value="19" <%=storeVO.getOpeningHours().indexOf("1") == 19 ? "selected" : "" %>>19:00</option>
								  <option value="20" <%=storeVO.getOpeningHours().indexOf("1") == 20 ? "selected" : "" %>>20:00</option>
								  <option value="21" <%=storeVO.getOpeningHours().indexOf("1") == 21 ? "selected" : "" %>>21:00</option>
								  <option value="22" <%=storeVO.getOpeningHours().indexOf("1") == 22 ? "selected" : "" %>>22:00</option>
								  <option value="23" <%=storeVO.getOpeningHours().indexOf("1") == 23 ? "selected" : "" %>>23:00</option>
								  <option value="24" <%=storeVO.getOpeningHours().indexOf("1") == 24 ? "selected" : "" %>>24:00</option>
								</select>
								</br>
								<select class="form-select" aria-label="Default select example" name="endTime">
<!-- 								  <option selected>請選擇營業結束時段</option> -->
								  <option value="1" <%=storeVO.getOpeningHours().lastIndexOf("1") == 1 ? "selected" : "" %>>01:00</option>
								  <option value="2" <%=storeVO.getOpeningHours().lastIndexOf("1") == 2 ? "selected" : "" %>>02:00</option>
								  <option value="3" <%=storeVO.getOpeningHours().lastIndexOf("1") == 3 ? "selected" : "" %>>03:00</option>
								  <option value="4" <%=storeVO.getOpeningHours().lastIndexOf("1") == 4 ? "selected" : "" %>>04:00</option>
								  <option value="5" <%=storeVO.getOpeningHours().lastIndexOf("1") == 5 ? "selected" : "" %>>05:00</option>
								  <option value="6" <%=storeVO.getOpeningHours().lastIndexOf("1") == 6 ? "selected" : "" %>>06:00</option>
								  <option value="7" <%=storeVO.getOpeningHours().lastIndexOf("1") == 7 ? "selected" : "" %>>07:00</option>
								  <option value="8" <%=storeVO.getOpeningHours().lastIndexOf("1") == 8 ? "selected" : "" %>>08:00</option>
								  <option value="9" <%=storeVO.getOpeningHours().lastIndexOf("1") == 9 ? "selected" : "" %>>09:00</option>
								  <option value="10" <%=storeVO.getOpeningHours().lastIndexOf("1") == 10 ? "selected" : "" %>>10:00</option>
								  <option value="11" <%=storeVO.getOpeningHours().lastIndexOf("1") == 11 ? "selected" : "" %>>11:00</option>
								  <option value="12" <%=storeVO.getOpeningHours().lastIndexOf("1") == 12 ? "selected" : "" %>>12:00</option>
								  <option value="13" <%=storeVO.getOpeningHours().lastIndexOf("1") == 13 ? "selected" : "" %>>13:00</option>
								  <option value="14" <%=storeVO.getOpeningHours().lastIndexOf("1") == 14 ? "selected" : "" %>>14:00</option>
								  <option value="15" <%=storeVO.getOpeningHours().lastIndexOf("1") == 15 ? "selected" : "" %>>15:00</option>
								  <option value="16" <%=storeVO.getOpeningHours().lastIndexOf("1") == 16 ? "selected" : "" %>>16:00</option>
								  <option value="17" <%=storeVO.getOpeningHours().lastIndexOf("1") == 17 ? "selected" : "" %>>17:00</option>
								  <option value="18" <%=storeVO.getOpeningHours().lastIndexOf("1") == 18 ? "selected" : "" %>>18:00</option>
								  <option value="19" <%=storeVO.getOpeningHours().lastIndexOf("1") == 19 ? "selected" : "" %>>19:00</option>
								  <option value="20" <%=storeVO.getOpeningHours().lastIndexOf("1") == 20 ? "selected" : "" %>>20:00</option>
								  <option value="21" <%=storeVO.getOpeningHours().lastIndexOf("1") == 21 ? "selected" : "" %>>21:00</option>
								  <option value="22" <%=storeVO.getOpeningHours().lastIndexOf("1") == 22 ? "selected" : "" %>>22:00</option>
								  <option value="23" <%=storeVO.getOpeningHours().lastIndexOf("1") == 23 ? "selected" : "" %>>23:00</option>
								  <option value="24" <%=storeVO.getOpeningHours().lastIndexOf("1") == 24 ? "selected" : "" %>>24:00</option>
								</select>
							</div>

<!-- 								<div class="mb-3"> -->
<!-- 									<label for="exampleFormControlInput1" class="form-label"><font color=white>營業時段</font></label> -->
<!-- 									<input class="form-control" type="TEXT" size="45" name="openingHours"  -->
<%-- 									value="<%=storeVO.getOpeningHours().indexOf("1")+":00~"+storeVO.getOpeningHours().lastIndexOf("1")+":00" %>"/> --%>
<!-- 									</div> -->
<!-- 								</div> -->

								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="storeId" value="<%=storeVO.getStoreId()%>"> 
								<input type="hidden" name="storeAcc" value="<%=storeVO.getStoreAcc()%>">


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
</script>
</html>







