

<!-- 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="UTF-8">
	<title>Full page scroll</title>
	<link rel="stylesheet" type="text/css" href="/SRC2/fsone/full-page-scroll.css">
	<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
	<style type="text/css">

    * { box-sizing: border-box; }

    .video-background {

        background: #000;

        position: fixed;

        top: 0; right: 0; bottom: 0; left: 0;

        z-index: -99;

    }

    .video-foreground,

    .video-background iframe {

        position: absolute;

        top: 0;

        left: 0;

        width: 100%;

        height: 100%;

        pointer-events: none;

    }

    @media (min-aspect-ratio: 16/9) {

    .video-foreground { height: 300%; top: -100%; }

    }

    @media (max-aspect-ratio: 16/9) {

    .video-foreground { width: 300%; left: -100%; }

    }

    h1{ color:white;}

    </style>
	
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


	</style>	
</head>
<body>
	<div id="main" class="scroll-container">
		<section class="section1">
			<div>
				<span><div class="video-background">
    <div class="video-foreground">
      <iframe src="https://www.youtube.com/embed/xRbPAVnqtcs?controls=0&showinfo=0&rel=0&autoplay=1&loop=1&playlist=xRbPAVnqtcs" frameborder="0" allowfullscreen></iframe>
    </div>
</div>

<h1>악동뮤지션</h1></span>					
			</div>
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
	<script src="/SRC2/fsone/full-page-scroll.js"></script>
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
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Full page scroll</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/full-page-scroll.css">
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


	</style>	
</head>
<body>
	<div id="main" class="scroll-container">
		<section class="section1">
			<div>
				<span>Page #1</span>					
			</div>
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
	<script src="<%=request.getContextPath()%>/js/full-page-scroll.js"></script>
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
