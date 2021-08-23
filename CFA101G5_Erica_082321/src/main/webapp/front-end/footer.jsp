<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>前台 footer</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Clean.css">
    <link rel="stylesheet" href="assets/css/Footer-Dark.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    <link rel="stylesheet" href="assets/css/Simple-Slider.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <style>
     ul.footerli {
     	list-style: none;
     }
     a.aDec {
         text-decoration: none;
    	 color: black; !important
     }
     div#footer {
     	height: 300px;
     }
    </style>
</head>
<body>
	<div id="footer">
	 <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-4 col-md-3 item">
                    <h3>服務項目</h3>
                    <ul>
                        <li class="footerli"><a class="aDec" href="#">桌遊商城</a></li>
                        <li class="footerli"><a class="aDec" href="#">店家預約</a></li>
                        <li class="footerli"><a class="aDec" href="#">揪團專區</a></li>
                        <li class="footerli"><a class="aDec" href="#">討論區</a></li>
                    </ul>
                </div>
                <div class="col-sm-4 col-md-3 item">
                    <h3>關於我們</h3>
                    <ul>
                        <li class="footerli"><a class="aDec" href="#">關於我們</a></li>
                        <li class="footerli"><a class="aDec" href="#">Q&amp;A</a></li>
                        <li class="footerli"><a class="aDec" href="<%=request.getContextPath()%>/customerservice/NameServletFe?frontaction=mem">線上客服</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <p class="copyright" style="text-align: center;">Company Name © 2021</p>
	</div>
	
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.4.8/swiper-bundle.min.js"></script>
    <script src="assets/js/Simple-Slider.js"></script>
</body>
</html>