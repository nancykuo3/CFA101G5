<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.admin.model.*"%>
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

	StoreService storeSvc = new StoreService();
	List<StoreVO> storeVoList = storeSvc.getAll();
%>

<html>
<head>
<meta charset="UTF-8">
	<title>店家會員管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="/CFA101G5/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="/CFA101G5/back-end/assets/fonts/fontawesome-all.min.css">
    

	<style>
    	.back-end-li:hover>.back-end-li-child{	
			display: block !important;
		}
		
		.nav-item{
			list-style-type: none;
		}
		
		.fill {
			
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
                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/back-end-index.jsp"><i class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/index-admin.jsp"><i class="fas fa-table"></i>管理員管理</a></li>
 					<li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i>商品管理</a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/product/listAllType.jsp"><i class="fas fa-table"></i><span>商品類型管理</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/product/prodInfoQuery.jsp"><i class="fas fa-table"></i><span>商品管理審核</span></a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>討論區管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/forum/select_actype_page.jsp"><i class="fas fa-table"></i><span>資料查詢</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/forum/addActype.jsp"><i class="fas fa-table"></i><span>新增文章類型資料</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/forum/articleReport.jsp"><i class="fas fa-table"></i><span>討論區文章檢舉管理</span></a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link active" href="#"><i class="fas fa-table"></i><span>前台會員管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
		                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/mem/allMem.jsp"><i class="fas fa-table"></i>一般會員管理</a></li>
		                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/store/allStore.jsp"><i class="fas fa-table"></i>店家會員管理</a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>會員檢舉管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
		                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/report/memReport.jsp"><i class="fas fa-table"></i>一般會員檢舉管理</a></li>
		                    <li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/report/storeReport.jsp"><i class="fas fa-table"></i>店家會員檢舉管理</a></li>
                    	</ul>
                    </li>
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>公告管理</span></a>
                    	<ul class="back-end-li-child" style="display:none;">
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/announcement/select_page.jsp"><i class="fas fa-table"></i><span>查看活動公告</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/news/select_page.jsp"><i class="fas fa-table"></i><span>查看最新消息</span></a></li>
                    		<li class="nav-item"><a class="nav-link" href="/CFA101G5/back-end/shopEvent/select_page.jsp"><i class="fas fa-table"></i><span>查看商城活動</span></a></li>
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
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small">${administratorVO.adminName}</span><img class="border rounded-circle img-profile" src="/CFA101G5/image/logo1.png"></a>
                                    <div
                                        class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
                                            <div class="dropdown-divider"></div><a class="dropdown-item" href="/CFA101G5/logOutServlet?action=admin"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                    </div>
                    </li>
                    </ul>
            </div>
            </nav> 
		    

		<div class="container">
				<div class="row align-items-center"">
				
		<div class="col-10" style="margin:auto;">
				<div class="row">
								<div class="row left_font" style="margin-top: 25px">
				<h1 style="font-weight: bold;">
										<font face="monospace">&nbsp&nbsp 店家會員管理</font>
									</h1>
								</div>
							</div>
					
						<div class="row" style="height: 50px"></div>
						<div class="row table-responsive">
		<!-- 					<input class="form-control" id="myInput" type="text" placeholder="Search.."> -->
							<div class="input-group flex-nowrap">
						  		<span class="input-group-text" id="addon-wrapping">姓名：</span>
							  	<input id="myInput" type="text" placeholder="Search for name" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="addon-wrapping">
							</div>
						<table class="table table-striped table-hover">
							<thead>
								<tr class="">
									<th scope="col">店家編號</th>
									<th scope="col">店家名稱</th>
									<th scope="col">加入時間</th>
									<th scope="col">被檢舉記點</th>
									<th scope="col">帳號狀態</th>
									<th colspan="2">操作</th>
						
								</tr>
							</thead>
							 <tbody id="myTable">
							<%
								for (int i = 0; i < storeVoList.size(); i++) {
							%>
								<tr>
									<td scope="row" ><%=storeVoList.get(i).getStoreId()%></td>
									<td><%=storeVoList.get(i).getStoreName()%></td>
									<td><%=storeVoList.get(i).getStoreRegDate()%></td>
									<td><%=storeVoList.get(i).getStoreReportCount()%></td>
									<td>
									
									
									
										<div class="form-check">
											  <input class="form-check-input" value="0" type="radio" name="flexRadioDefault<%=i %>" id="flexRadioDefault<%=i %>_1" <%=storeVoList.get(i).getAccStatus() == 0 ? "checked" : "" %>>
											  <label class="form-check-label" for="flexRadioDefault<%=i %>_1">
											  		 帳號未啟用
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" value="1" type="radio" name="flexRadioDefault<%=i %>" id="flexRadioDefault<%=i %>_2" <%=storeVoList.get(i).getAccStatus() == 1 ? "checked" : "" %>>
											  <label class="form-check-label" for="flexRadioDefault<%=i %>_2">
											    	帳號已啟用 
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" value="2" type="radio" name="flexRadioDefault<%=i %>" id="flexRadioDefault<%=i %>_3"  <%=storeVoList.get(i).getAccStatus() == 2 ? "checked" : "" %>>
											  <label class="form-check-label" for="flexRadioDefault<%=i %>_3">
											    	帳號停權
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" value="3"  type="radio" name="flexRadioDefault<%=i %>" id="flexRadioDefault<%=i %>_4"  <%=storeVoList.get(i).getAccStatus() == 3 ? "checked" : "" %>>
											  <label class="form-check-label" for="flexRadioDefault<%=i %>_4">
											    	帳號失效
											  </label>
											</div>
										</td>
										<td>
											<button type="button" name="button_<%=i %>" data-id="<%=storeVoList.get(i).getStoreId()%>"  class="btn btn-outline-info">送出</button>
										</td>
									</tr>
									<%
										}
									%>
								 </tbody>
						</table>
					</div>
					</div>
				</div>
			</div>
									<form style="display: none;" action="<%=request.getContextPath() %>/store/StoreServlet" id="update_form" method="post">
										<input id="storeId" name="storeId" >
										<input id="status" name="status" >
										<input value="getOne_For_Update" name="action">
									</form>
		
			
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Copyright © 2021 桌‧迷藏</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="/CFA101G5/back-end/assets/js/jquery.min.js"></script>
    <script src="/CFA101G5/back-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="/CFA101G5/back-end/assets/js/theme.js"></script>
	
	<script>
		$(document).ready(function(){
			// 即時查詢
		  $("#myInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#myTable tr").filter(function() {
			  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		  });
		  
		  $('.btn').click(function(event) {
			console.log(this.name)
			var name = "flexRadioDefault"+this.name.split('_')[1];
			console.log(name);
			console.log($("input[name='"+name+"']"));
			var status;
			$("input[name='"+name+"']").each(function() {
				console.log( this.value + ":" + this.checked );
				if(this.checked){
					status = this.value;
				}
			});
			
			$('#status').val(status)
			$('#storeId').val($(this).attr("data-id"))
			$('#update_form').submit();
			// 送出更新  
			 
		  });

	});
	</script>
	
</body>

</html>