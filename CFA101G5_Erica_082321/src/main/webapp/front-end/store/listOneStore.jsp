<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.report.model.*"%>
<%@ page import="java.util.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 將網頁寬度設為跟隨設備的螢幕款度 ，縮放倍率為1 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!-- 最佳兼容模式 -->
<title>店家會員資料</title>

<%
	StoreVO storeVO = null;
	StoreVO storeVO_session = (StoreVO) session.getAttribute("StoreVO");

	StoreService svc = new StoreService();
	System.out.println("LIST ONE JSP : id" + storeVO_session.getStoreId());
	storeVO = svc.getOneStore(storeVO_session.getStoreId());
	System.out.println("LIST ONE JSP : storeVO" + storeVO);
	/* // 確認沒有session
	if (null == storeVO) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/front-end/store/storeLogin.jsp";
		response.sendRedirect(redirectURL);
	} */

  	StoreReportService cvc = new StoreReportService();
  	List<StoreReportVO> listStoreReportVO = cvc.getStoreReportByStoreId(storeVO.getStoreId());
  	System.out.println("listStoreReportVO:" + listStoreReportVO.size());

	// 有

	// 	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>

<style>
body {
	padding: 0;
	margin: 0;
	background-color: RGB(246, 249, 252); //
	background:
		url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg");
	background-size: cover;
	background-position: center;
}

.login {
	border: 0px solid white;
	border-radius: 20px 20px 20px 20px;
	font-size: 25px;
	margin: auto;
	font-style: "微軟正黑體", cursive;
	line-height: 40px;
}

.left_font {
	text-align: center;
	font-size: 25px;
	font-style: "微軟正黑體", cursive;
	line-height: 40px;
	color: black;
	font-weight: bold;
}

i {
	font-size: 26;
	color: #444444;
}

.item_font {
	font-size: 19px;
	color: #444444;
}

.label_font {
	font-weight: bold;
}
</style>
</head>
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-store.jsp" />
	</div>
	
	<div class="container">
		<div class="row align-items-center" style="margin-top: 100px;">
<!-- 			<div class="col-1"></div> -->
			<!-- 			 // 大頭測選藍 -->
			<div class="col-3" style="margin-right: 50px;">
				<!-- 			// 圓圈大頭貼 -->
				<div class="row">
					<img class="img-thumbnail" id="preview_imgId" style="border-radius: 50%;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyVljlCMw733ieGwF0O4EO7uscfdkUtn7igQ&usqp=CAU" />
				</div>
				<div class="row left_font" style="margin-top: 25px">
					<h2 style="font-weight: bold;"><%=storeVO.getStoreName()%></h2>
				</div>
				<div class="row left_font">
					<h2 style="font-weight: bold; font-size: 24px"><%=storeVO.getStoreId()%></h2>
				</div>
			<div class="row" style="margin-top: 25px">
					<div class="list-group">
						<a id="account_button" href="#" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="fas fa-user"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">Account</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a>  <a id="report_button" href="#" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="far fa-frown"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">Report list</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a> <a href="<%=request.getContextPath()+"/front-end/store/updateOneStore.jsp" %>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="fas fa-edit"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">Revise</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a> <a  href="<%=request.getContextPath()%>/login/logOutServlet?action=store" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="fas fa-sign-out-alt"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">log out</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a> <a  href="<%=request.getContextPath()+"/front-end/store/index.html" %>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="fas fa-home"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">HOME</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- 			// 更換頁面 -->
			<div class="col-8" style="text-align: center;">
				<!-- 	             		會員資訊 -->
				<div id="user_info" class="login row">
					<div class="row">
						<div class="row left_font" style="margin-top: 25px">
							<h1 style="font-weight: bold;">
								<font face="monospace">User info</font>
							</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-12">

							<%
								if (null != storeVO) {
							%>
							<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/StoreServlet"> --%>
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>店家帳號</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreAcc()%></div>
									</div>
								
								</div>
								<div class="col-6">
										<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
											<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>信箱</font></label>
											<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreEmail()%></div>
										</div>
								</div>
							</div>
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>店家名稱</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreName()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>統編</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreGui()%></div>
									</div>
								</div>
							</div>
							
							
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>負責人</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStorePic()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>電話</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreTel()%></div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>傳真</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreFax()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>登記地址</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreAdd()%></div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>聯絡人</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStorePoc()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>連絡電話</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreConPhone()%></div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-12">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>聯絡地址</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreConAdd()%></div>
									</div>
								</div>
							</div>




							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>轉帳帳號</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getBankAccount()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>總分數(商城)</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreShopRatingScore()%></div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>總分數(預約)</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreShopRatingCount()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>總評價數(預約)</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreRsvnRatingScore()%></div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>總分數(預約)</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreRsvnRatingCount()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>被檢舉計點</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getStoreReportCount()%></div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>可容納人數</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getCapacity()%></div>
									</div>
								</div>
								<div class="col-6">
									<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
										<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>公休日</font></label>
										<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getDayOff()%></div>
									</div>
								</div>
							</div>
							
							<div class="row">

							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>營業時段</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=storeVO.getOpeningHours().indexOf("1") + ":00~"
						+ (storeVO.getOpeningHours().lastIndexOf("1") + 1) + ":00"%></div>

							</div>
							</div>














							<input type="hidden" name="action" value="getOne_For_Display"> <input type="hidden" name="storeId" value="<%=storeVO.getStoreId()%>"> <input type="hidden" name="storeAcc" value="<%=storeVO.getStoreAcc()%>">

							<%
								}
							%>
							<!-- 							<div class="mb-3"> -->
							<%-- 								<a class="btn btn-primary" href="<%=request.getContextPath()%>/front-end/store/updateOneStore.jsp" role="button">修改</a> --%>
							<!-- 								<button type="submit" class="btn btn-primary">回首頁</button> -->
							<!-- 							</div> -->
						</div>
					</div>
				</div>

				<!-- 檢舉頁面 -->
				<div id="report_list" class="co row" style="display:none">
					<div class="row">
						<div class="row left_font" style="margin-top: 25px">
							<h1 style="font-weight: bold;">
								<font face="monospace">檢舉頁面 </font>
							</h1>
						</div>
					</div>
					<div class="row">
	<table  class="table table-striped table-hover">
							<tr>
								<th>檢舉單號</th>
								<th>檢舉時間</th>
								<th>檢舉文字內容</th>
								<th>處理完成時間</th>
								<th>處理狀態</th>
								<th>處理結果</th>
								<th>處理註記</th>
								

							</tr>
							<%
 								for (int i = 0; i < listStoreReportVO.size(); i++) {
 							%> 
							<tr>
								<th><%=listStoreReportVO.get(i).getReportno()%></th>
								<th><%=listStoreReportVO.get(i).getReportTime()%></th>
								<th><%=listStoreReportVO.get(i).getContent()%></th>
								<th><%=listStoreReportVO.get(i).getDoneTime()!=null ? listStoreReportVO.get(i).getDoneTime():""%></th> 
								<th><%=listStoreReportVO.get(i).getStatus() == 0 ? "未處理" : "已處理"%></th>
								<th>
<%-- 								<%=listStoreReportVO.get(i).getResult()%> --%>
								<%
								if (listStoreReportVO.get(i).getStatus() == 1) {
									%> <%=listStoreReportVO.get(i).getResult() == 1 ? "不通過" : "通過"%> <%	
							
								} else {
							%>
							<%
								}
							%>
								</th>
								<th><%=listStoreReportVO.get(i).getNote()!=null ? listStoreReportVO.get(i).getNote():""%></th>
								<th></th>
							</tr>
							<%
							}
 							%> 
						</table>
					</div>


				</div>
			</div>
			<div class="col-1"></div>
		</div>
	</div>
</body>
</html>

<script>
$('#account_button').click(function(){
    //Some code
	console.log("account_button");
	$('#report_list').css('display','none');
	$('#user_info').css('display','block');
    
});
$('#report_button').click(function(){
    //Some code
	console.log("report_button");
	$('#report_list').css('display','block');
	$('#user_info').css('display','none');
});


</script>
</html>