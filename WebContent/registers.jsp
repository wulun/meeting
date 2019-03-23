 <%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
<script type="text/javascript">
var xmlHttp;

        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function validate() {
            createXMLHttpRequest(); 
            var username = document.getElementById("username");
            var url = "ValidateUsernameServlet?username=" + escape(username.value);           
            xmlHttp.open("GET", url, true);
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }

        function callback() {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var message = xmlHttp.responseXML.getElementsByTagName("message")[0].firstChild.data;
                    var passed = xmlHttp.responseXML.getElementsByTagName("passed")[0].firstChild.data;
                    setMessage(message, passed);
                }
            }
        }
        
        function setMessage(message, passed) {            
            var validateMessage = document.getElementById("validateMessage");
            var fontColor = "red";
            if (passed == "true") {
                fontColor = "green";                
            }
            validateMessage.innerHTML = "<font color=" + fontColor + ">" + message + " </font>";
        }
        
	function check() {
		if (form1.firstpassword.value!=form1.secondpassword.value) {
			confirminfo.innerHTML = "<font color=red>两次输入的密码不相符</font>";
		}else{
			confirminfo.innerHTML="<font color=green>两次输入的密码相符</font>";
		}
	} 
	
	//employeename失去焦点 empnameblur()" onfocus="empnamefocus()
	var flg=0;
	function empnameblur(){
		var employeename=document.getElementById("employeename").value;
		var employeenamemsg=document.getElementById("employeenamemsg");
		if (employeename == "") {
			employeenamemsg.innerHTML = "<font color='red'>" + "姓名 不能为空"+"</font>";
			flag=1;
		}
		
		else if (employeename.length>6) {
			employeenamemsg.innerHTML = "<font color='red'>" + "姓名长度不能大于6 " + "</font>";
			flag = 1;
		}
	}
	//employeename获得焦点
	function empnamefocus(){
		var employeename=document.getElementById("employeename").value;
		var employeenamemsg=document.getElementById("employeenamemsg");
		employeenamemsg.innerHTML ="";
	}
	//username 失去焦点  onblur="usernameblur()" onfocus=usernamefocus()
	function usernameblur(){
		
		<% %>
		var username=document.getElementById("username").value;
		var usernamemsg=document.getElementById("usernamemsg");
		var reg = /^[A-Z a-z 0-9]+$/;

		if (username == "") {
			usernamemsg.innerHTML = "<font color='red'>" + "用户名 不能为空"+"</font>";
			flag=1;
		}
		else if (username.length>6) {
			usernamemsg.innerHTML = "<font color='red'>" + " 用户名长度不能大于6 " + "</font>";
			flag = 1;
		}else if(!reg.test(username)){
			usernamemsg.innerHTML = "<font color='red'>" + "用户名 不能为中文" + "</font>";
			return false;
		}
		
	}	
	//username获得 焦点
	function  usernamefocus(){
		var username=document.getElementById("username").value;
		var usernamemsg=document.getElementById("usernamemsg");
		usernamemsg.innerHTML = "";
	}
	//password onblur="firstpwdblur()" onfocus="firstpwdfocus()
	function firstpwdblur(){
		var firstpassword=document.getElementById("firstpassword").value;
		var firstpasswordmsg=document.getElementById("firstpasswordmsg");
		if (firstpassword == "") {
			firstpasswordmsg.innerHTML = "<font color='red'>" + "密码不能为空"+"</font>";
			flag=1;
		}
		else if (firstpassword.length<6 ||firstpassword.length>10) {
			firstpasswordmsg.innerHTML = "<font color='red'>" + "密码 长度6-10"  + "</font>";
			flag = 1;
		}
		
	}
	//password
	function  firstpwdfocus(){
		var firstpassword=document.getElementById("firstpassword").value;
		var firstpasswordmsg=document.getElementById("firstpasswordmsg");
		firstpasswordmsg.innerHTML = "";
	}
	//secpwdblur()" onfocus="secpwdfocus()
	function secpwdblur(){
		var secondpassword=document.getElementById("secondpassword").value;
		var secondpasswordmsg=document.getElementById("secondpasswordmsg");
		if (secondpassword == "") {
			secondpasswordmsg.innerHTML = "<font color='red'>" + "密码不能为空"+"</font>";
			flag=1;
		}
		else if (secondpassword.length<6 ||secondpassword.length>10) {
			secondpasswordmsg.innerHTML = "<font color='red'>" + "密码 长度6-10"  + "</font>";
			flag = 1;
		}
		
	}
	//password
	function  secpwdfocus(){
		var password=document.getElementById("secondpassword").value;
		var secondpasswordmsg=document.getElementById("secondpasswordmsg");
		secondpasswordmsg.innerHTML = "";
	}
	//phone onblur="phoneblur()" onfocus= phonefocus()
	function phoneblur(){
		var phone=document.getElementById("phone").value;
		var phonemsg=document.getElementById("phonemsg");
		var reg = /^[0-9]+$/;
		if (phone == "") {
			phonemsg.innerHTML = "<font color='red'>" + "电话不能为空"+"</font>";
			flag=1;
		}
		else if (phone.length!=11) {
			phonemsg.innerHTML = "<font color='red'>" + "电话号长度不符合规范 (11位)"  + "</font>";
			flag = 1;
		}else if(!reg.test(phone)){
			phonemsg.innerHTML = "<font color='red'>" + "电话 不能为中文 和 英文" + "</font>";
			return false;
		}
		
	}
	function phonefocus(){
		var phone=document.getElementById("phone").value;
		var phonemsg=document.getElementById("phonemsg");
		phonemsg.innerHTML = "";
	}
	//email
	function emailblur(){
		var email=document.getElementById("email").value;
		var emails= /^([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+@([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var emailmsg=document.getElementById("emailmsg");
		if (email == "") {
			emailmsg.innerHTML = "<font color='red'>" + "邮箱 不能为空"+"</font>";
			flag=1;
		}else if(!emails.test(email))
			{
			emailmsg.innerHTML="<font color='red'>" + "邮箱格式不对 "  + "</font>";
				flag = 1;

		}
	}
	function emailfocus(){
		var email=document.getElementById("email").value;
		var emailmsg=document.getElementById("emailmsg");
		emailmsg.innerHTML = "";
	}
	function addEmp(){
		//emplouyeename
		var employeename=document.getElementById("employeename").value;
		var employeenamemsg=document.getElementById("employeenamemsg");
		if (employeename == "") {
			employeenamemsg.innerHTML = "<font color='red'>" + "姓名 不能为空"+"</font>";
			flag=1;
		}
		else if (employeename.length>6) {
			employeenamemsg.innerHTML = "<font color='red'>" + "姓名长度不能大于6 " + "</font>";
			flag = 1;
		}else{
			flag = 0;
		}
		//username
		var username=document.getElementById("username").value;
		var usernamemsg=document.getElementById("usernamemsg");
		if (username == "") {
			usernamemsg.innerHTML = "<font color='red'>" + "用户名 不能为空"+"</font>";
			flag=1;
		}
		else if (username.length>6) {
			usernamemsg.innerHTML = "<font color='red'>" + " 用户名长度不能大于6 " + "</font>";
			flag = 1;
		}else{
			flag = 0;
		}
		//firstpassword
		var firstpassword=document.getElementById("firstpassword").value;
		var firstpasswordmsg=document.getElementById("firstpasswordmsg");
		if (firstpassword == "") {
			firstpasswordmsg.innerHTML = "<font color='red'>" + "密码不能为空"+"</font>";
			flag=1;
		}
		else if (firstpassword.length<6 ||firstpassword.length>10) {
			passwordmsg.innerHTML = "<font color='red'>" + "密码 长度6-10"  + "</font>";
			flag = 1;
		}else{
			flag = 0;
		}
		//secpasswird
		var secondpassword=document.getElementById("secondpassword").value;
		var secondpasswordmsg=document.getElementById("secondpasswordmsg");
		if (secondpassword == "") {
			secondpasswordmsg.innerHTML = "<font color='red'>" + "密码不能为空"+"</font>";
			flag=1;
		}
		else if (secondpassword.length<6 ||secondpassword.length>10) {
			secondpasswordmsg.innerHTML = "<font color='red'>" + "密码 长度6-10"  + "</font>";
			flag = 1;
		}else{
			flag = 0;
		}
		//phone
		var phone=document.getElementById("phone").value;
		var phonemsg=document.getElementById("phonemsg");
		if (phone == "") {
			phonemsg.innerHTML = "<font color='red'>" + "电话不能为空"+"</font>";
			flag=1;
		}
		else if (phone.length!=11) {
			phonemsg.innerHTML = "<font color='red'>" + "电话号长度不符合规范(11位) "  + "</font>";
			flag = 1;
		}else{
			flag = 0;
		}
		var email=document.getElementById("email").value;
		var emailmsg=document.getElementById("emailmsg");
		var emails= /^([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+@([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(email=="")
		{
			emailmsg.innerHTML="<font color='red'>" + "邮箱不能为空 "  + "</font>";
			flag = 1;
		}
		else if(!emails.test(email))
		{
			emailmsg.innerHTML="<font color='red'>" + "邮箱格式不对 "  + "</font>";
			flag = 1;
		}
		else
		{
			flag = 0;
		}
		var form1=document.getElementById("form1");
		if(flag==0){
			form1.action="RegistServlet";
			form1.submit();
		}
	}
	
	/* 
	var email=document.getElementById("email").value;
	var emailmsg=document.getElementById("emailmsg");
	var emails= /^([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+@([_a-zA-Z0-9]+[_|\_|\.]?)*[_a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(email=="")
	{
		emailmsg.innerHTML="<font color='red'>" + "邮箱不能为空 "  + "</font>";
		flag = 1;
	}
	else if(!emails.test(email))
	{
		emailmsg.innerHTML="<font color='red'>" + "邮箱格式不对 "  + "</font>";
		flag = 1;
	}
	else
	{
		flag = 0;
	}
 */
</script>

</head>
<body>
	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问Cool-Meeting会议管理系统</div>
	</div>
	<div class="page-content">
		<div class="content-nav">员工注册</div>
		<form id="form1" name="form1" action="RegistServlet" method="post">
			<fieldset>
				<legend>员工信息</legend>
				<font style="color:red">${requestScope.msg }</font>
				<%-- <tr>
					<td>提示信息:</td>
					<td><font color='red'>${requestScope.msg}</font></td>
				</tr> --%>
  
				<table class="formtable" style="width:50%">
					<tr>
						<td>姓名：</td>

						<td><input type="text" id="employeename" name="employeename"
							maxlength="20" value="" onblur="empnameblur()" onfocus="empnamefocus()">
						<span id="employeenamemsg"></span>	
						</td>
					</tr>
					<tr>
						<td>账户名：</td>

						<td><input type="text" id="username" name="username"
							maxlength="20" value="" onchange="validate()" onblur="usernameblur()" onfocus="usernamefocus()">
						<span id="usernamemsg"></span>
							<div id="validateMessage"></div>
						</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" id="firstpassword" name="password"
							maxlength="10" placeholder="请输入6-10位以上的密码" onblur="firstpwdblur()" onfocus="firstpwdfocus()">
							<span id="firstpasswordmsg"></span>
						</td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" id="secondpassword"
							name="password" maxlength="10" onchange="check()" onblur="secpwdblur()" onfocus="secpwdfocus()"/>
							<span id="secondpasswordmsg"></span>
							<div id="confirminfo"></div></td>
					</tr>
					<tr>
						<td>联系电话：</td>

						<td>
							<input type="text" id="phone" name="phone" maxlength="20" onblur="phoneblur()" onfocus="phonefocus()">
							<span id="phonemsg"></span>
						</td>
					</tr>
					<tr>
						<td>电子邮件：</td>
						
						<td><input type="text" id="email" name="email" maxlength="20"
							value="" onblur="emailblur()" onfocus="emailfocus()">
							<span id="emailmsg"></span>
						</td>
					</tr>
					
					<tr>
					<td>所在部门：</td>
					<td>
					<select name="deptid">
						<c:forEach var="department" items="${requestScope.departmentsList}">
							<option value="${department.departmentid}" selected="selected">${department.departmentname}</option>
						</c:forEach>
														
					</select>
					</td>
					</tr>
					<tr>
						<td colspan="6" class="command">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="clickbutton" value="注册" onclick="addEmp()" /> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset"	class="clickbutton" value="重置" />
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>