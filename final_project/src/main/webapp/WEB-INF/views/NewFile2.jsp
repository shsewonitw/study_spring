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
		background-color:  #A199E2;
	}

	.section4 {
		background-color:  #CC938E;
	}

	.section5 {
		background-color:  #D2C598;
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
	
	#videobcg { 
     top: 0px;
     left: 0px;
     min-width: 100%;
     min-height: 100%;
     width: auto;
     height: auto;
     z-index: -1000;
     overflow: hidden;
}

img { 
     top: 0px;
     left: 0px;
     min-width: 100%;
     min-height: 100%;
     width: 100%;
     height: 100%;
     z-index: -1000;
     overflow: hidden;
}

	</style>	
</head>
<body>
	<div id="main" class="scroll-container">
		<section class="section1">
			
			<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
				<source src="<%=request.getContextPath() %>/resources/video/SampleVideo.mp4" type="video/mp4">
			</video>
			<!-- 
 			<iframe class="video-foreground" frameborder="0" height="100%" width="100%" style="pointer-events:none;"
src="https://www.youtube.com/embed/xRbPAVnqtcs?mute=1&loop=1&autoplay=1&rel=0&controls=0&showinfo=0&modestbranding=1" allow="autoplay; encrypted-media" allowfullscreen>
</iframe>
 -->
		</section>
		<section class="section2">
			<div>
				<span>Page #2</span>				
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
	</script>
</body>
</html>