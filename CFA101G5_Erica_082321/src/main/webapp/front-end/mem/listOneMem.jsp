
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.reservationfav.model.*"%>
<%@ page import="com.report.model.*"%>
<%@ page import="com.store.model.*"%>
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
<title>會員資料</title>

<%
	MemVO memVO = null;
	MemVO memVO_session = (MemVO) session.getAttribute("memVO");

	MemManagementService svc = new MemManagementService();
//	System.out.println("memVO_ID:" + memVO_.getMemId());
	memVO = svc.getOneMem(memVO_session.getMemId());
//	System.out.println("memVO:" + memVO);

/* 	// 確認沒有session
	if (null == memVO_) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/front-end/mem/login.jsp";
		response.sendRedirect(redirectURL);
	} */
	
	ReservationFavService Fvc = new ReservationFavService();
	List<ReservationFavVO> listReservationFavVO = Fvc.getReservationFavsByMemId(memVO_session.getMemId());
	System.out.println(listReservationFavVO.size());

	MemReportService rvc = new MemReportService();
	List<MemReportVO> listMemReportVO = rvc.getMemReportByMemId(memVO_session.getMemId());
	System.out.println("listMemReportVO:" + listMemReportVO.size());
	
	StoreService storeSvc = new StoreService();
	List<StoreVO> listStoreVO = storeSvc.getAll();
	//  MemVO memVO = (MemVO) request.getAttribute("memVO");
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
<body>
	<div id="header" style="height:70px">
		<jsp:include page="/front-end/header-mem.jsp" />
	</div>
	<div class="container">
		<div class="row align-items-center" style="margin-top: 100px;">
<!-- 			<div class="col-1"></div> -->
			<!-- 			 // 大頭測選藍 -->
			<div class="col-3" style="margin-right: 50px;">
				<!-- 			// 圓圈大頭貼 -->
				<div class="row">
					<img class="img-thumbnail" id="preview_imgId" style="border-radius: 50%;" src="<%=request.getContextPath() + memVO.getMemPicSrc()%>" />
				</div>
				<div class="row left_font" style="margin-top: 25px">
					<h2 style="font-weight: bold;"><%=memVO.getMemName()%></h2>
				</div>
				<div class="row left_font">
					<h2 style="font-weight: bold; font-size: 24px;"><%=memVO.getMemId()%></h2>
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
						</a> <a id="collection_button" href="#" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
							<div class="row">
								<div class="col-2" style="border-right: 1px solid #cccccc;">
									<i class="fas fa-heart-circle"></i>
								</div>
								<div class="col-8 item_font" style="margin-left: 0px">
									<font face="monospace">my collection</font>
									<!-- 					  			Account -->
								</div>
								<div class="col-2">
									<i class="fas fa-chevron-right"></i>
								</div>
							</div>
						</a> <a id="report_button" href="#" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
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
						</a> <a href="<%=request.getContextPath()+"/front-end/mem/updateMemInput.jsp" %>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
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
						</a> <a  href="<%=request.getContextPath()%>/login/logOutServlet?action=mem" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
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
						</a> 
						<a  href="<%=request.getContextPath()+"/index-front.jsp"%>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
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
			<!-- 			// 更換葉面 -->
			<div class="col-8">
				<!-- 					會員資訊 -->
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
								if (null != memVO) {
							%>


							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>會員帳號</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=memVO.getMemAcc()%></div>
							</div>

							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>性別</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=memVO.getMemGender().equals("1") ? "男" : "女"%></div>
							</div>

							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>信箱</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=memVO.getMemEmail()%></div>
							</div>

							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>手機</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=memVO.getMemMobile()%></div>
							</div>

							<div class="mb-3" style="border-bottom: 1px solid #cccccc;">
								<label for="exampleFormControlInput1" class="form-label label_font"><font color='#555555'>地址</font></label>
								<div style="color: #b3b3b3; font-size: 22px"><%=memVO.getMemAddr()%></div>
							</div>

							<input type="hidden" name="action" value="getOne_For_Display"> 
							<input type="hidden" name="memId" value="<%=memVO.getMemId()%>"> 
							<input type="hidden" name="memAcc" value="<%=memVO.getMemAcc()%>">

							<%
								}
							%>
						</div>
					</div>
				</div>
				<!-- 					我的收藏 -->
			
					<div id="collection_list" class="co row" style="display:none">
					
					<div class="row">
						<div class="row left_font" style="margin-top: 25px">
							<h1 style="font-weight: bold;">
								<font face="monospace">我的收藏</font>
							</h1>
						</div>
					<div class="row">
						<div class="col-2"></div>
						<div class="col-2"></div>
						<div class="col-2"></div>
						<div class="col-2"></div>
						<div class="col-2"></div>
						<div class="col-2">
						<div class="row left_font" >
							<button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#add_follow">
								<font face="monospace" >新增收藏</font>
							</button>
							<div class="modal fade" id="add_follow" tabindex="-1" aria-labelledby="add_followModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalLabel">新增收藏</h5>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
							      
							      		<form id="" action="<%=request.getContextPath()%>/reservation/ReservationFavForMemServlet">
														<input value="insert" name="action" style="display: none"> 
														<input name="memId" value="<%=memVO_session.getMemId()%>" style="display: none"> 
														<label for="validationCustom04" class="form-label"> 收藏店家名稱</label> 
														<select name="storeId" class="form-select" id="validationCustom04" required>
															<option selected disabled value="">Choose...</option>
															<%
																for (int i = 0; i < listStoreVO.size(); i++) {
															%>
															<option value="<%=listStoreVO.get(i).getStoreId()%>"><%=listStoreVO.get(i).getStoreName()%></option>

															<%
																}
															%>
														</select>
														<div class="invalid-feedback">請選擇店家</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary">送出</button>
														</div>
													</form>
												</div>

											</div>

										</div>
									</div>
		<!-- 					  刪除關注 -->
								<form id="rm_fav_form"action="<%=request.getContextPath()%>/reservation/ReservationFavForMemServlet" method="post" style="display:none">
		  							<input id="rm_fav_form_id" type="text" name="reservationFavId"> 
		  							<input type="text" name="action" value="delete"> 
								</form>
							</div>
						</div>		
					</div>
					<div class="row">

						<%
							for (int i = 0; i < listReservationFavVO.size(); i++) {
						%>
						<div class="row mb-3" style="border-bottom: 1px solid #cccccc;">
							<div class="col-3"></div>
<!-- 							<div class="col-2">圖片</div> -->
							<div class="col-5"><%=listReservationFavVO.get(i).getStoreName()%></div>
							<div class="col-3">
									<button class="btn btn-outline-warning remove_follow" data-fav-id="<%=listReservationFavVO.get(i).getReservationFavId()%>" type="button"
								data-bs-toggle="modal" data-bs-target="#exampleModal" >關注中</button>	
							</div>
							<div class="col-1"></div>
						</div>
						<%
							}
						%>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">取消追蹤</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        	是否確定要取消關注當前店家？
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					        <button id="submit_rm_fav_form" type="button" class="btn btn-primary">取消追蹤</button>
					      </div>
					    </div>
					  </div>
<!-- 					  刪除關注 -->
						<form id="rm_fav_form"action="<%=request.getContextPath()%>/reservation/ReservationFavForMemServlet" method="post" style="display:none">
  							<input id="rm_fav_form_id" type="text" name="reservationFavId"> 
  							<input type="text" name="action" value="delete"> 
						</form>
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
								<th>操作</th>

							</tr>
							<%
								for (int i = 0; i < listMemReportVO.size(); i++) {
							%>
							<tr>
								<th><%=listMemReportVO.get(i).getReportno()%></th>
								<th><%=listMemReportVO.get(i).getReportTime()%></th>
								<th><%=listMemReportVO.get(i).getContent()%></th>
								<th><%=listMemReportVO.get(i).getDoneTime() != null ? listMemReportVO.get(i).getDoneTime() : ""%></th>
								<th><%=listMemReportVO.get(i).getStatus() == 0 ? "未處理" : "已處理"%></th>
								<th>
<%-- 								<%=listMemReportVO.get(i).getResult() == 1 ? "不通過" : "通過"%> --%>
								<%
								if (listMemReportVO.get(i).getStatus() == 1) {
									%> <%=listMemReportVO.get(i).getResult() == 1 ? "不通過" : "通過"%> <%	
							
								} else {
							%>
							<%
								}
							%>
								</th>
								<th><%=listMemReportVO.get(i).getNote() != null ? listMemReportVO.get(i).getNote() : ""%></th>
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

</body>
</head>
<script>
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
		var max_size = 65536;
		$(this).change(function(evt) {

			var finput = $(this);
			var files = evt.target.files; // 獲得檔案物件   
			var output = [];
			for (var i = 0, f; f = files[i]; i++) { //檢查檔案大小   
				encodeImgtoBase64(this); // 沒超過64KB才顯示照片
			}
		});
			
		});
	$('#account_button').click(function(){
	    //Some code
		console.log("account_button");
	    
		$('#collection_list').css('display','none');
	    $('#report_list').css('display','none');
		$('#user_info').css('display','block');  
	});
	
	$('#collection_button').click(function(){
	    //Some code
		console.log("collection_button");
		$('#report_list').css('display','none');
		$('#collection_list').css('display','block');
		$('#user_info').css('display','none');
	});
	
	$('#report_button').click(function(){
	    //Some code
		console.log("report_button");
		$('#user_info').css('display','none');
		$('#collection_list').css('display','none');
		$('#report_list').css('display','block');
		
	});
	
	$('.remove_follow').click(function(){
		console.log("remove_follow:" + this);
		console.log($(this).data("fav-id"));
		$("#rm_fav_form_id").val($(this).data("fav-id"));
// 		$("rm_fav_form").submit();
		
	});
	
	$('#submit_rm_fav_form').click(function(){
		$("#rm_fav_form").submit();
	});


</script>

</html>