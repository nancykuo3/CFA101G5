<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productType.model.*"%>
<%@ page import="com.productInfo.model.*"%>

<html>
<head>
<meta charset="UTF-8">
	<title>新增商品詳細資料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome-all.min.css">
    

	<style>
    	.back-end-li:hover>.back-end-li-child{	
			display: block !important;
		}
		
		.nav-item{
			list-style-type: none;
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
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/back-end-index.jsp"><i class="fas fa-tachometer-alt"></i><span>首頁</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/back-end/index-admin.jsp"><i class="fas fa-table"></i>管理員管理</a></li>
 					<li class="nav-item back-end-li"><a class="nav-link active" href="#"><i class="fas fa-table"></i>商品管理</a>
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
                                        class="dropdown-menu shadow dropdown-menu-right animated--grow-in"><a class="dropdown-item" href="#"><i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a><a class="dropdown-item" href="#"><i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Settings</a>
                                            <div class="dropdown-divider"></div><a class="dropdown-item" href="<%=request.getContextPath()%>/login/logOutServlet?action=admin"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
                    </div>
                    </li>
                    </ul>
            </div>
            </nav> 
		    
		    <div style="padding:20px 15px;">
				<div id="formStyle">
				<form method="post"
					action="<%=request.getContextPath()%>/productInfo/AddProdInfoServlet">
					<div class="col">
						<h4>請於下方輸入欲新增的商品詳細資料：</h4>
					</div>
					<div class="mb-3 row">
						<label for="isbn" class="col-sm-2 col-form-label">ISBN</label>
	
						<div class="col-sm-10">
							<input type="text" class="form-control" name="isbn" id="isbn"
								maxlength="20" size="20" pattern="^[1-9]{1}\d{1,}$"
								placeholder="請輸入數字如：4712946397453" required />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="prodName" class="col-sm-2 col-form-label">名稱</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="prodName"
								id="prodName" maxlength="50" size="50" placeholder="如：狼人殺 口袋版"
								required />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="prodLang" class="col-sm-2 col-form-label">語言</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="prodLang"
								id="prodLang" maxlength="20" size="20" placeholder="如：繁體中文、英文...等" />
						</div>
					</div>
					<div class="mb-3 row">
						<label for="prodVer" class="col-sm-2 col-form-label">版本</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="prodVer" id="prodVer"
								maxlength="20" size="20"
								pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{0,20}"
								placeholder="如：年度版、2020年" />
						</div>
					</div>
					<jsp:useBean id="typeSvc" scope="page"
						class="com.productType.model.TypeService" />
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">標籤</label>
						<div class="col-sm-10">
							<c:forEach var="typeVO" items="${typeSvc.all}">
								<input type="checkbox" name="typeid" value="${typeVO.typeid}">${typeVO.typeName}
							</c:forEach>
						</div>
					</div>
					<div>
						<!--送出本網頁的路徑給Controller-->
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<button class="btn back-end-btn" type="submit" name="action" style="color:#7f70f5; border-color:#7f70f5"
							value="insert">送出</button>
					</div>
				</form>
			</div>
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
</body>

</html>