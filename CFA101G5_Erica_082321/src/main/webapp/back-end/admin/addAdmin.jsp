<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta charset="utf-8">
<title>新增管理員</title>


    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome-all.min.css">
    
	<!-- BootStrap 5.0.2 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<!-- jQuery 1.12.4 -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"
		integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    
    <style>
    	#addAdmin {
			width: 40%;
			margin: auto auto;
		}

		.title {
			text-align: center;
		}

		.hint {
			color: red;
		}

		#addAdmin .adminLabel {
			width: 100px;
		}
    	.back-end-li:hover>.back-end-li-child{	
			display: block !important;
		}
		
		.nav-item{
			list-style-type: none;
		}
		
		.back-end-btn{
			color:#7f70f5; 
			border-color:#7f70f5;
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
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-end-index.html"><i class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
                    <li class="nav-item"><a class="nav-link active" href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i class="fas fa-table"></i>管理員管理</a></li>
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
                    <li class="nav-item back-end-li"><a class="nav-link" href="#"><i class="fas fa-table"></i><span>會員檢舉管理</span></a>
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
            
			<div id="addAdmin">
				<form class="addAdmin" name="addAdmin">
					<div class="col title">
						<h4>新增管理員</h4>
					</div>
						<span class="hint" id="successAdmin">可用的管理員編號</span>
						<span class="hint" id="failAdmin">此編號不可用！</span>
					<div class="mb-3 row">
						<label for="adminid" class="col-sm-2 col-form-label adminLabel">編號：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="adminid" id="adminid" maxlength="4" size="4"
								pattern="^[1-9]{1}\d{1,}$" placeholder="請輸入數字如：1001、2001" required />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="adminacc" class="col-sm-2 col-form-label adminLabel">帳號：</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="adminacc" id="adminacc" maxlength="20" size="20"
								placeholder="請輸入電子郵件帳號" required />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="adminpwd" class="col-sm-2 col-form-label adminLabel">密碼：</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="adminpwd" id="adminpwd" maxlength="20" size="20"
								pattern="^[1-9A-Za-z]{1}[1-9A-Za-z]{1,}$" required />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="adminName" class="col-sm-2 col-form-label adminLabel">名稱：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="adminName" id="adminName" maxlength="20" size="20"
								placeholder="中、英文皆可" required />
						</div>
					</div>
					<input type="hidden" id="adminStatus" name="adminStatus" value="1">
					<br>
					<button class="btn back-end-btn" type="submit" id="sendAddAdmin" name="action"
						value="insert">送出</button>
				</form>
			</div>
            
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Copyright © 2021 桌‧迷藏</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="<%=request.getContextPath()%>/back-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/theme.js"></script>
	
	<script>

			
		$(function () {
			function getRootPath(){
				let curPageUrl = window.document.location.href;
				let rootPath = "/" + curPageUrl.split("//")[1].split("/")[1];
				return rootPath;
			};
			$("#successAdmin").hide();
			$("#failAdmin").hide();
			$("#sendAddAdmin").hide();
			let result;
	
			$("#adminid").on("change", function () {
				let inputAdminid = $("#adminid").val();
				let action;
				console.log(inputAdminid);
	
				if (inputAdminid.length == 4) {
					action = "testAdmin";
					$.ajax({
						url: getRootPath() + "/admin/AddAdminServlet",
						type: "POST",
						data: {
							"action": action,
							"inputAdminid": inputAdminid
						},
						dataType: "JSON",
						success: function (data) {
							result = data.result;
							console.log("Result = " + result);
							if (result == "success") {
								$("#successAdmin").show();
								$("#failAdmin").hide();
								$("#sendAddAdmin").show();
							}
							if (result == "fail") {
								$("#successAdmin").hide();
								$("#failAdmin").show();
								$("#sendAddAdmin").hide();
							}
						}
					})
				}
			})
	
			// 將資料送至後端處理
			$("#sendAddAdmin").on("click", function () {
				$.ajax({
					url: getRootPath() + "/admin/AddAdminServlet",
					type: "POST",
					data: {
						"action": $("#sendAddAdmin").val(),
						"inputAdminid": $("#adminid").val(),
						"inputAdminAcc": $.trim($("#adminacc").val()),
						"inputAdminPwd": $.trim($("#adminpwd").val()),
						"inputAdminStatus": 1,
						"inputAdminName": $.trim($("#adminName").val())
					},
					dataType: "JSON",
					success: function (data) {
						alert("要轉送的管理員編號：" + data.adminid);
						console.log("新增成功");
						window.location.assign(getRootPath() + "/back-end/admin/listOneAdmin.jsp?adminid=" + data.adminid);
					}
				});
			});
		})
	</script>
	
</body>

</html>