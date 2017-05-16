<%@page import="org.springframework.core.env.Environment"%>
<html>
	<head>
		<title>Listing Apps</title>
		<script type="text/javascript" src="resources/js/jquery-3.2.1.js"></script>
		<!--  Ie7 compatibility requirement -->
		<script type="text/javascript" src="resources/js/json2.js"></script>
		<script type="text/javascript" src="resources/js/utils.js"></script>
		
		<script type="text/javascript" src="resources/js/jClass.js"></script>
		<script type="text/javascript" src="resources/js/page.js"></script>
		<script type="text/javascript" src="resources/js/listApps.js"></script>
		
		<link rel="stylesheet" href="resources/css/listApps.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="container">
			<%
			
			Environment env = (Environment)request.getAttribute("env");
			
			%>
			
			Select Weblogic Console:
				<div id="div_form">
					<form name = "listApp" id="listAppID">
							<div id=""> </div>
							<select name="console" id="consoleID">
							<option value="<%=env.getProperty("USDEVConsole") %>">US DEV - <%=env.getProperty("USDEVConsole") %></option>
						</select>
						Port:<input type="text" name="port" id="portID" value="22">
						user:<input type="text" name="username" id="usernameID" value="">
						pass:<input type="password" name="password" id="passwordID">
						<a id="a_Submit" href="javaScript:void(0)">Submit</a><br />
					
					</form>
				</div>
				<div id="messagesID"></div>
				
				<div id="pageBodyID">
					<ul>
					
						<li class="li_ListApps"><a href="javascript:void(0);" class="links_list" data-div="1">name</a>
							<div id="div_1" class="div_listApps">
								<div class="div_left">
									<a href="javascript:void(0);"></a>
								</div>
								<div class="div_right">
									<p>path</p>
									<p>targets</p>
								</div>
							</div>
						</li>
						<li class="li_ListApps"><a href="javascript:void(0);" class="links_list" data-div="2">name</a>
							<div id="div_2" class="div_listApps">
								<div class="div_left">
									<a href="javascript:void(0);"></a>
								</div>
								<div class="div_right">
									<p>path</p>
									<p>targets</p>
								</div>
							</div>
						</li>
						<li class="li_ListApps"><a href="javascript:void(0);" class="links_list" data-div="3">name</a>
							<div id="div_3" class="div_listApps">
								<div class="div_left">
									<a href="javascript:void(0);"></a>
								</div>
								<div class="div_right">
									<p>path</p>
									<p>targets</p>
								</div>
							</div>
						</li>
						<li class="li_ListApps"><a href="javascript:void(0);" class="links_list" data-div="4">name</a>
							<div id="div_4" class="div_listApps">
								<div class="div_left">
									<a href="javascript:void(0);"></a>
								</div>
								<div class="div_right">
									<p>path</p>
									<p>targets</p>
								</div>
							</div>
						</li>
						<li class="li_ListApps"><a href="javascript:void(0);" class="links_list" data-div="5">name</a>
							<div id="div_5" class="div_listApps">
								<div class="div_left">
									<a href="javascript:void(0);"></a>
								</div>
								<div class="div_right">
									<p>path</p>
									<p>targets</p>
								</div>
							</div>
						</li>
					
					
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>