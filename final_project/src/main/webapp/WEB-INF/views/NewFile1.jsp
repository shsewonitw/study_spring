<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Full page scroll</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/full-page-scroll.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/full-page-scroll.min.css">
	<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<style type="text/css">
.section1 {
	background-color: #7DBCD4;
}

.section2 {
	background-color: #98C19F;
}

.section3 {
	background-color: #A199E2;
}

.section4 {
	background-color: #CC938E;
}

.section5 {
	background-color: #D2C598;
}

section div {
	font-family: 'Open Sans';
	font-style: normal;
	text-align: center;
	position: relative;
	top: 50%;
	transform: translateY(-50%);
}

span {
	font-size: 4em;
	font-style: normal;
	color: #fff;
}

video{
	top: 0;
	left: 0;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	z-index: -1;
}

 .box {
        width: 100%;
        height: 100%;
        background-color: #cccccc;
        float: left;
        margin-right: 10px;}
      
      .hi {
        overflow: hidden;
    
</style>	
</head>
<body>
<div style="z-index:100;position:absolute;">
<h1>~~~~!!!!!!!~~메뉴로 쓸 부분~!!!</h1>
</div>
	<div id="main" class="scroll-container">
	
		<section class="section1">
			<div class="box hi">
				<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
				<source src="<%=request.getContextPath() %>/resources/video/test2.mp4" type="video/mp4">
			</video>
			</div>
		</section>
		
		
		<section class="section2">
			<div>
			</div>
		</section>
		
		
		<section class="section3">
			<div>
				<span>Page #3</span>				
			</div>
		</section>
		
		
		<section class="section4">
			<div>
				<span>Page #4</span>				
			</div>
		</section>
		<section class="section5">
			<div>
				<span>Page #5</span>				
			</div>
		</section>
	</div>
	
	
	<script src="<%=request.getContextPath() %>/resources/js/full-page-scroll.js"></script>
	<script src="<%=request.getContextPath() %>/resources/js/full-page-scroll.min.js"></script>
	<script type="text/javascript">
		new fullScroll({
			displayDots: true,
			dotsPosition: 'left',
			animateTime: 0.7,
			animateFunction: 'ease'
		});
		
		var elem = document.getElementById("myvideo");

		function openFullscreen() {

		  if (elem.requestFullscreen) {

		    elem.requestFullscreen();

		  } else if (elem.mozRequestFullScreen) { /* Firefox */

		    elem.mozRequestFullScreen();

		  } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari & Opera */

		    elem.webkitRequestFullscreen();

		  } else if (elem.msRequestFullscreen) { /* IE/Edge */

		    elem.msRequestFullscreen();

		  }

		}



	</script>
</body>
</html>