<%@page import="org.springframework.core.env.Environment"%>

<script type="text/javascript" src="resources/js/jquery-1.10.2.js"></script>
<!--  Ie7 compatibility requirement -->
<script type="text/javascript" src="resources/js/json2.js"></script>
<script type="text/javascript" src="resources/js/utils.js"></script>
<script type="text/javascript" src="resources/js/listApps.js"></script>



<%

Environment env = (Environment)request.getAttribute("env");

%>

Select Weblogic Console:
<form name = "listApp" id="listAppID">
<select name="console" id="consoleID">
	<option value="<%=env.getProperty("USDEVConsole") %>">US DEV - <%=env.getProperty("USDEVConsole") %></option>
</select>
Port:<input type="text" name="port" id="portID" value="22">
user:<input type="text" name="username" id="usernameID" value="joao_paiva">
pass:<input type="password" name="password" id="passwordID">
<a href="javaScript:submitListApp()">Submit</a>
</form>
<hr>
<form name ="fileForm" id="fileFormID">
<input type="hidden" name="port">
<input type="hidden" name="username" id="usernameID" >
<input type="hidden" name="password" id="passwordID">
<input type="file" name="filePath" id="filePathID">
<a href="javaScript:submitListApp()">Submit</a>



</form>
<div id="messagesID"></div>

<div id="pageBodyID"></div>