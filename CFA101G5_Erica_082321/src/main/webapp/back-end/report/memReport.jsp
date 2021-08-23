<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.report.model.*"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<%
	AdministratorVO adminVO = null;
	adminVO = (AdministratorVO) session.getAttribute("administratorVO");
	if (null == adminVO) {
		// 跳回登入
		String redirectURL = request.getContextPath() + "/back-end/admin/adminLogin.jsp";
		response.sendRedirect(redirectURL);
	} else {

	}
	MemReportService memReportService = new MemReportService();
	List<MemReportVO> memReportVOList = memReportService.getAll();
	System.out.print(memReportVOList.size());
// 	MemManagementService memSvc = new MemManagementService();
// 	List<MemVO> memVoList = memSvc.getAll();
%>

<html>
<head>
<meta charset="UTF-8">
	<title>一般會員檢舉管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome-all.min.css">
           
	
    <style>
		<!--
		.fill {
			
		}

		.table {
			text-align: center;
		}

		body {
			padding: 0;
			margin: 0;
			background-color: RGB(246, 249, 252); //
			background:
				url("http://g.udn.com.tw/upfiles/B_HS/hsusysy/PSN_PHOTO/584/f_16687584_1.jpg");
			background-size: cover;
			background-position: center;
		}

		.left_font {
			text-align: center;
			font-size: 25px;
			font-style: "微軟正黑體", cursive;
			line-height: 40px;
			color: black;
			font-weight: bold;
		}

		.min-100 {
			
		}
		-->
	
    	.back-end-btn{
			color:#7f70f5; 
			border-color:#7f70f5
		}
		
		.back-end-btn:hover{
			background-color: #7f70f5;
			color: #ffffff !important;
		}
    
    	.back-end-li:hover>.back-end-li-child{	
			display: block !important;
		}
		
		.nav-item{
			list-style-type: none;
		}
    </style>
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex p-0">
                <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>桌‧迷藏</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="nav navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-end-index.jsp"><i class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i class="fas fa-table"></i>管理員管理</a></li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i>商品管理</a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/product/listAllType.jsp"><i class="fas fa-table"></i><span>商品類型管理</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/product/prodInfoQuery.jsp"><i class="fas fa-table"></i><span>商品管理審核</span></a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>討論區管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/forum/select_actype_page.jsp"><i class="fas fa-table"></i><span>資料查詢</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/forum/addActype.jsp"><i class="fas fa-table"></i><span>新增文章類型資料</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/forum/articleReport.jsp"><i class="fas fa-table"></i><span>討論區文章檢舉管理</span></a></li>
                    	</ul>
                    </li>
					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>前台會員管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/mem/allMem.jsp"><i class="fas fa-table"></i>一般會員管理</a></li>
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/store/allStore.jsp"><i class="fas fa-table"></i>店家會員管理</a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link active" href="#"><i class="fas fa-table"></i><span>會員檢舉管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/report/memReport.jsp"><i class="fas fa-table"></i>一般會員檢舉管理</a></li>
		                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/report/storeReport.jsp"><i class="fas fa-table"></i>店家會員檢舉管理</a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>公告管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/announcement/select_page.jsp"><i class="fas fa-table"></i><span>查看活動公告</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/news/select_page.jsp"><i class="fas fa-table"></i><span>查看最新消息</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/shopEvent/select_page.jsp"><i class="fas fa-table"></i><span>查看商城活動</span></a></li>
                    	</ul>
                    </li>
                </ul>
                <div class="text-center d-none d-md-inline" style="margin:0 auto;"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <form class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group">
                                <div class="input-group-append"></div>
                            </div>
                        </form>
                        <ul class="nav navbar-nav flex-nowrap ml-auto">
                            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><i class="fas fa-search"></i></a>
                                <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in" aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto navbar-search w-100">
                                        <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                                            <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="<%=request.getContextPath() %>/customerservice/NameServlet?backaction=admin&userID=${administratorVO.adminid}" onclick="window.open(this.href, '', 'width=800,height=800'); return false;"><span class="badge badge-danger badge-counter"></span><i class="fa fa-comment"></i></a>

                                </div>
                            </li>
                            <div class="d-none d-sm-block topbar-divider"></div>
                            <li class="nav-item dropdown no-arrow">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small">${administratorVO.adminName}</span><img class="border rounded-circle img-profile" src="<%=request.getContextPath()%>/image/logo1.png"></a>
                                    <div
                                        class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
                                            <div class="dropdown-divider"></div><a class="dropdown-item" href="<%=request.getContextPath()%>/login/logOutServlet?action=admin"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                    </div>
                    </li>
                    </ul>
            </div>
            </nav>
            
            <div style="margin:20px 15px;">		
				<div class="container">
					<div class="row align-items-center" style="margin-top: 100px;">
					

			<!-- 		測選 -->
						<div class="col-4"  style="margin-right: 50px">
							<div class="row">
								<img class="img-thumbnail" id="preview_imgId" style="border-radius: 50%;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyVljlCMw733ieGwF0O4EO7uscfdkUtn7igQ&usqp=CAU" />
							</div>
							
							<div class="row left_font" style="margin-top: 25px">
								<h2 style="font-weight: bold;"><%=adminVO.getAdminName()%></h2>
							</div>
							<div class="row left_font">
								<h2 style="font-weight: bold; font-size: 24px;"><%=adminVO.getAdminid()%></h2>
								
							</div>
							<div class="row" style="margin-top: 25px">
								<div class="list-group">
									<a id="mem_button" href="<%=request.getContextPath() + "/back-end/mem/allMem.jsp"%>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="fas fa-user"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">一般會員管理</font>
												<!-- 					  			Account -->
											</div>
											<div class="col-2">
												<i class="fas fa-chevron-right"></i>
											</div>
										</div>
									</a> 
									<a id="store_button" href="<%=request.getContextPath() + "/back-end/store/allStore.jsp"%>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="fas fa-user"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">店家會員管理</font>
												<!-- 					  			Account -->
											</div>
											<div class="col-2">
												<i class="fas fa-chevron-right"></i>
											</div>
										</div>
									</a>
									<a id="collection_button" href="<%=request.getContextPath() + "/back-end/report/memReport.jsp"%>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="far fa-frown"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">一般會員檢舉管理</font>
												<!-- 					  			Account -->
											</div>
											<div class="col-2">
												<i class="fas fa-chevron-right"></i>
											</div>
										</div>
									</a>
									<a id="collection_button" href="<%=request.getContextPath()+"/back-end/report/storeReport.jsp"%>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="far fa-frown"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">店家會員檢舉管理</font>
												<!-- 					  			Account -->
											</div>
											<div class="col-2">
												<i class="fas fa-chevron-right"></i>
											</div>
										</div>
									</a>
									<a id="store_button" href="<%=request.getContextPath()+"/logOutServlet?action=admin" %>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="fas fa-sign-out-alt"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">登出</font>
												<!-- 					  			Account -->
											</div>
											<div class="col-2">
												<i class="fas fa-chevron-right"></i>
											</div>
										</div>
									</a>
									<a id="store_button" href="<%=request.getContextPath()+"/back-end/back-end-index.jsp" %>" style="background-color: #eeeeee; height: 45px;" class="list-group-item list-group-item-action">
										<div class="row">
											<div class="col-2" style="border-right: 1px solid #cccccc;">
												<i class="fas fa-home"></i>
											</div>
											<div class="col-8 item_font" style="margin-left: 0px">
												<font face="monospace">回首頁</font>
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
			<!-- 			內容 -->
						<div class="col-7">
							
								<div class="row">
									<div class="row left_font" style="margin-top: 25px">
										<h1 style="font-weight: bold;">
											<font face="monospace">&nbsp&nbsp 一般會員檢舉管理</font>
										</h1>
									</div>
								</div>
						
							<div class="row" style="height: 50px"></div>
							<div class="row table-responsive">
			<!-- 					<input class="form-control" id="myInput" type="text" placeholder="Search.."> -->
								<div class="input-group flex-nowrap">
									<span class="input-group-text" id="addon-wrapping">單號：</span>
									<input id="myInput" type="text" placeholder="Search for name" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping">
								</div>
								<table class="table table-striped table-hover">
									<thead>
										<tr class="">
											<th scope="col">檢舉單號</th>
											<th scope="col">管理員編號</th>
											<th scope="col">處理狀態</th>
											<th scope="col">處理結果</th>
											<th scope="col">處理註記</th>
											<th colspan="2">操作</th>
										</tr>
									</thead>
									<tbody id="myTable">
										<%
											for (int i = 0; i < memReportVOList.size(); i++) {
										%>
										<tr>
										<td scope="row"><%=memReportVOList.get(i).getReportno()%></td>
										<td><%=memReportVOList.get(i).getAdminid()%></td>
										<td><%=memReportVOList.get(i).getStatus() == 0 ? "未處理" : "已處理"%></td>
										<td>
										<%
											if (memReportVOList.get(i).getStatus() == 1) {
												%> <%=memReportVOList.get(i).getResult() == 1 ? "不通過" : "通過"%> <%	
										
											} else {
										%>
										<%
											}
										%>
										</td>

				<%-- 					<td><%=storeReportVOList.get(i).getNote()%></td> --%>
										<td>
												<%=memReportVOList.get(i).getNote() == null ? "" : memReportVOList.get(i).getNote() %>
										</td>		
										<td>
											<button type="button" name="button_<%=i%>" data-id="<%=memReportVOList.get(i).getMemid()%>" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#exampleModal<%=i%>">查看</button>								
											 <% if (memReportVOList.get(i).getStatus() == 0) {%>
											<button type="button" name="" data-id="<%=memReportVOList.get(i).getMemid()%>" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#updateModal<%=i%>">審核</button>
											

											
											</div> 
											<%
												}
											%>


										</td>


									</tr>
										
										
									<!-- Modal -->
									<div class="modal fade" id="exampleModal<%=i%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">檢舉單詳細資料</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">
													<!-- 									model內文 -->
													<div class="mb-3">
														<label for="exampleFormControlInput1" class="form-label"><font>[會員編號]</font></label>
														<div><%=memReportVOList.get(i).getMemid()%></div>
													</div>

													<div class="mb-3">
														<label for="exampleFormControlInput1" class="form-label"><font>[被檢舉會員編號]</font></label>
														<div><%=memReportVOList.get(i).getMemidReported()%></div>
													</div>	
													
													<div class="mb-3">
														<label for="exampleFormControlInput1" class="form-label"><font>[檢舉內容]</font></label>
														<div><%=memReportVOList.get(i).getContent()%></div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
									
									<!-- Modal_update -->
									<div class="modal fade" id="updateModal<%=i%>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">檢舉單審核</h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">
													<!-- 									model內文 -->
													<form id=""    action="<%=request.getContextPath()%>/report/memReportServlet">
														<div class="mb-3">
															<label for="exampleFormControlInput1" class="form-label"><font>檢舉單號</font></label>
															&nbsp;
															<input type="text" name="reportno" value="<%=memReportVOList.get(i).getReportno()%>">
														</div>
				
														<div class="mb-3">
															<label for="exampleFormControlInput1" class="form-label"><font>處理結果:</font></label>
															&nbsp;
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="radio" value="0" name="result" id="result1">
																<label class="form-check-label" for="result1" >通過
																</label>
															</div>
															<div class="form-check form-check-inline">
																<input class="form-check-input" type="radio" value="1" name="result" id="result2">
																<label class="form-check-label" for="result2">未通過
																</label>
															</div>
														</div>
				
														<div class="mb-3">
															<label for="exampleFormControlInput1" class="form-label"><font>處理註記</font></label>
															&nbsp;
															<input type="text" name="note" >
															
														</div>
														<input style="display:none"type="text" name="action" value="update">
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary">Save</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>


									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<form style="display: none;" action="<%=request.getContextPath()%>/mem/MemManagementServlet" id="update_form" method="post">
				<input id="memId" name="memId"> <input id="status" name="status"> <input value="getOne_For_Update" name="action">
			</form>
            </div>
            
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Copyright © 2021 桌‧迷藏</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/theme.js"></script> 
	
<script>
	$(document).ready(
			function() {
				// 即時查詢
				$("#myInput").on(
						"keyup",
						function() {
							var value = $(this).val().toLowerCase();
							$("#myTable tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});

				//   $('.btn').click(function(event) {
				//  	console.log(this.name)
				//  	var name = "flexRadioDefault"+this.name.split('_')[1];
				//  	console.log(name);
				//  	console.log($("input[name='"+name+"']"));
				//  	var status;
				// 	$("input[name='"+name+"']").each(function() {
				// 	    console.log( this.value + ":" + this.checked );
				// 	    if(this.checked){
				// 	    	status = this.value;
				// 	    }
				// 	});

				// 	$('#status').val(status)
				// 	$('#storeId').val($(this).attr("data-id"))
				// 	$('#update_form').submit();
				// 	// 送出更新  

				//   });

			});
</script>
	
	
</body>

</html>