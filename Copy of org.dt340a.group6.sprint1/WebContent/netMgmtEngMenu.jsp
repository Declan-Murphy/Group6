<html>
 <head>
        <title>Dt340a - Group 6</title>
       
        <link rel="icon" type="image/ico" href="http://www.ericsson.com/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/mystyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
		<%
			Cookie[] cookies = request.getCookies();
			String userName = null;
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("user")) {
					userName = cookie.getValue();
				}
			}
			if (userName==null) {
				response.getWriter().print("<script>;window.location.replace(\"index.html\");</script>");
			}
		%>
</head>


    <body>
        <div id="container">
        
            <div id="heading-container">
            
                <div id="eir-image"> 
                    <img alt="Ericsson" src="http://www.ericsson.com/shared/eipa/images/elogo.png">   
                </div> 
            
                
                <div id="dit-image"> 
                     <img alt="DIT" src="http://www.dit.ie/media/styleimages/dit_crest.gif" width="90px" height="90px">  
                </div> 
               
                
                <h1>Call Investigation Assistant</h1> 
                <h2>Group 6</h2>
                <div class="wrapper">
					<a href="index.html"><button class='button centre'>Log out</button></a>
				</div>           
            </div>
            
            <div id="home-container" >    
                  <h3>Please select one of the following: </h3>
              
                <a href="networkManagementEngineerUS09.html"  ><button class='button'>Average call failure times in a given period</button></a>
                <a href="model.html"  ><button class='button'>User Story 10 - </button></a>
                <a href="UserStory12.html"  ><button class='button'>User Story 12 - </button></a>
                
            </div>
        </div>
        
        <div id="eric-multi">
            <img src="images/ebottomgrad.jpg" >
        </div>
    
    </body>

</html>
