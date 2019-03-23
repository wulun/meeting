<%@ page language="java"
	import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>CoolMeeting会议管理系统</title>
<link rel="stylesheet" href="styles/common03.css" />
<style type="text/css">
#divfrom {
	float: left;
	width: 150px;
}

#divto {
	float: left;
	width: 150px;
}

#divoperator {
	float: left;
	width: 50px;
	padding: 60px 5px;
}

#divoperator input[type="button"] {
	margin: 10px 0;
}

#selDepartments {
	display: block;
	width: 100%;
}

#selEmployees {
	display: block;
	width: 100%;
	height: 200px;
}

#selSelectedEmployees {
	display: block;
	width: 100%;
	height: 225px;
}
</style>
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
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

        function showEmployees() {
            createXMLHttpRequest();
       		var deptid=document.getElementById("selDepartments").value;   	
       		var url = "SelectEmployeesOfDeptServlet?departmentid=" + escape(deptid);           
            xmlHttp.open("GET", url, true);     
            xmlHttp.onreadystatechange = callback;
            xmlHttp.send(null);
        }

        function callback() {
           clearEmployees();
           var selEmployees=document.getElementById("selEmployees");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    selEmployees.options.add(new Option(text,value));
                    }       
                }
            }
              
        }
        
        function clearEmployees(){
         	document.getElementById("selEmployees").options.length=0;
        }
        
         function selectEmployees(){
         		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        var opt=new Option(selEmployees.options[i].text,selEmployees.options[i].value);
                        opt.selected=true;
                        selSelectedEmployees.options.add(opt);
                        selEmployees.options.remove(i);
                    }
                }
            }
        
        function deSelectEmployees(){
        		var selEmployees=document.getElementById("selEmployees");
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    if (selSelectedEmployees.options[i].selected){
                        selEmployees.options.add(new Option(selSelectedEmployees.options[i].text,selSelectedEmployees.options[i].value));
                        selSelectedEmployees.options.remove(i);
                    }
                }
                setSelected();
            }     
             
        function setSelected(){
         		var selSelectedEmployees=document.getElementById("selSelectedEmployees");     
                for(var i=0;i<selSelectedEmployees.options.length;i++){
                    selSelectedEmployees.options[i].selected=true;
                }
        }
        
        function refreshRooms(){
            createXMLHttpRequest();   
	        var starttime=document.getElementById("starttime").value;   	
	        var endtime=document.getElementById("endtime").value;  
	        
	       	var url = "RefreshRoomsServlet?starttime=" + escape(starttime)+"&endtime="+escape(endtime);           
	        xmlHttp.open("GET", url, true);     
	        xmlHttp.onreadystatechange = refresh;
	        xmlHttp.send(null);
        }
        
         function refresh() {
  		  /* clearMeetingRooms(); */ 
           var roomid=document.getElementById("roomid");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                	
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    roomid.options.add(new Option(text,value),i+1);
                    }       
                }
            }
        
        }
        
        function clearMeetingRooms(){
         	document.getElementById("roomid").options.length=1;
        }
        
        //判断jsp
        var flag=0;
        function meetingnameblur(){
    		
    		<% %>
    		var meetingname=document.getElementById("meetingname").value;
    		var meetingnamemsg=document.getElementById("meetingnamemsg");
    		

    		if (meetingname == "") {
    			meetingnamemsg.innerHTML = "<font color='red'>" + "会议名 不能为空"+"</font>";
    			flag=1;
    		}
    		else if (meetingname.length>4) {
    			meetingnamemsg.innerHTML = "<font color='red'>" + " 会议长度不能大于4 " + "</font>";
    			flag = 1;
    		}
    		
    	}	
    	//meetingname获得 焦点
    	function  meetingnamefocus(){
    		var meetingname=document.getElementById("meetingname").value;
    		var meetingnamemsg=document.getElementById("meetingnamemsg");
    		meetingnamemsg.innerHTML = "";
    	}
    	
    	//numofattendents   numofparticipants
		function numofattendentsblur(){
    		
    		<% %>
    		var numofattendents=document.getElementById("numofattendents").value;
    		var numofattendentsmsg=document.getElementById("numofattendentsmsg");
    		var reg = /^[0-9]+$/;

    		if (numofattendents == "") {
    			numofattendentsmsg.innerHTML = "<font color='red'>" + "人数不能为空"+"</font>";
    			flag=1;
    		}
    		else if (numofattendents.length>3) {
    			numofattendentsmsg.innerHTML = "<font color='red'>" + " 人数不能大于3位 " + "</font>";
    			flag = 1;
    		}else if(!reg.test(numofattendents)){
    			numofattendentsmsg.innerHTML = "<font color='red'>" + "人数必须为数字" + "</font>";
    			return false;
    		}
    		
    	}	
    	//numofattendents获得 焦点
    	function  numofattendentsfocus(){
    		var numofattendents=document.getElementById("numofattendents").value;
    		var numofattendents=document.getElementById("numofattendentsmsg");
    		numofattendentsmsg.innerHTML = "";
    	}
    	//开始时间
    	function starttimeblur(){
    		
    		<% %>
    		var starttime=document.getElementById("starttime").value;
    		var starttimemsg=document.getElementById("starttimemsg");
    		var reg = /^[0-9]+$/;

    		if (starttime == "" || starttime==null) {
    			starttimemsg.innerHTML = "<font color='red'>" + "时间不能为空"+"</font>";
    			flag=1;
    		}else {
    			starttimemsg.innerHTML = "";
    		}
    		
    		
    		
    		
    	}	
    	//starttime获得 焦点
    	function  starttimefocus(){
    		var starttime=document.getElementById("starttime").value;
    		var starttimemsg=document.getElementById("starttimemsg");
    		starttimemsg.innerHTML = "";
    	}
    	//结束时间
    	function endtimeblur(){
    		
    		<% %>
    		var endtime=document.getElementById("endtime").value;
    		var endtimemsg=document.getElementById("endtimemsg");
    		/* var reg = /^[0-9]+$/; */

    		if (endtime == "" |endtime==null) {
    			endtimemsg.innerHTML = "<font color='red'>" + "时间不能为空"+"</font>";
    			flag=1;
    		}else {
    			endtimemsg.innerHTML = "";
    		}
    		
    	}	
    	//endtime获得 焦点
    	function  starttimefocus(){
    		var endtime=document.getElementById("endtime").value;
    		var endtimemsg=document.getElementById("endtimemsg");
    		endtimemsg.innerHTML = "";
    	}
    	//会议说明  description
    	function descriptionblur(){
    		
    		<% %>
    		var description=document.getElementById("description").value;
    		var descriptionmsg=document.getElementById("descriptionmsg");
    		

    		if (description == "") {
    			descriptionmsg.innerHTML = "<font color='red'>" + "说明不能为空"+"</font>";
    			flag=1;
    		}
    		else if (descriptionmsg.length>40) {
    			endtimemsg.innerHTML = "<font color='red'>" + "说明不能大于40位 " + "</font>";
    			flag = 1;
    		}
    		
    	}	
    	//description获得 焦点
    	function  descriptionfocus(){
    		var description=document.getElementById("description").value;
    		var descriptionmsg=document.getElementById("descriptionmsg");
    		descriptionmsg.innerHTML = "";
    	}
        //预定会议
        function yuding(){
        	//meetingname
    		var meetingname=document.getElementById("meetingname").value;
    		var meetingnamemsg=document.getElementById("meetingnamemsg");
    		if (meetingname == "") {
    			meetingnamemsg.innerHTML = "<font color='red'>" + "会议名不能为空"+"</font>";
    			flag=1;
    		}
    		else if (meetingname.length>4) {
    			employeenamemsg.innerHTML = "<font color='red'>" + "会议名长度不能大于4  " + "</font>";
    			flag = 1;
    		}else{
    			flag = 0;
    		}
    		//numofattendents   参加人数
    		var numofattendents=document.getElementById("numofattendents").value;
    		var numofattendentsmsg=document.getElementById("numofattendentsmsg");
    		if (numofattendents == "") {
    			numofattendentsmsg.innerHTML = "<font color='red'>" + "参加人数 不能为空 "+"</font>";
    			flag=1;
    		}
    		else if (numofattendents.length>3) {
    			usernamemsg.innerHTML = "<font color='red'>" + " 参加人数不能超过3位 " + "</font>";
    			flag = 1;
    		}else{
    			flag = 0;
    		}
    		//starttime  开始时间
    		var starttime=document.getElementById("starttime").value;
    		var starttimemsg=document.getElementById("starttimemsg");
    		if (starttime == "") {
    			starttimemsg.innerHTML = "<font color='red'>" + "开始时间不能为空"+"</font>";
    			flag=1;
    		}else{
    			flag = 0;
    		}
    		//结束时间  endtime
    		var endtime=document.getElementById("endtime").value;
    		var endtimemsg=document.getElementById("endtimemsg");
    		if (endtime == "") {
    			endtimemsg.innerHTML = "<font color='red'>" + "结束时间不能为空"+"</font>";
    			flag=1;
    		}else{
    			flag = 0;
    		}
    	/* 	//选择会议室roomid
    		var roomid=document.getElementById("roomid").value;
    		var roomidmsg=document.getElementById("roomidmsg");
    		if (roomid == "") {
    			roomidmsg.innerHTML = "<font color='red'>" + "选择会议室不能为空"+"</font>";
    			flag=1;
    		}else{
    			flag = 1;
    		} */
    		
    		//description  会议说明
    		var description=document.getElementById("description").value;
    		var descriptionmsg=document.getElementById("descriptionmsg");
    		
    		if(description=="")
    		{
    			descriptionmsg.innerHTML="<font color='red'>" + "会议说明不能为空 "  + "</font>";
    			flag = 1;
    		}else if (description.length>40) {
    			descriptionmsg.innerHTML = "<font color='red'>" + " 会议说明不能超过40字" + "</font>";
    			flag = 1;
    		}
    		else
    		{
    			flag = 0;
    		}
    		
    		/* //选择参会人员selDepartments
    		var selDepartments=document.getElementById("selDepartments").value;
    		var selDepartmentsmsg=document.getElementById("selDepartmentsmsg");
    		
    		if(selDepartments=="")
    		{
    			selDepartmentsmsg.innerHTML="<font color='red'>" + "参会人员不能为空 "  + "</font>";
    			flag = 1;
    		}else
    		{
    			flag = 1;
    		} */
    		var form1=document.getElementById("form1");
    		if(flag==0){
    			form1.action="BookMeetingServlet";
    			form1.submit();
    		}

        }
        function  chongzhi() {
        	form1.action="BookMeetingServlet?code=prepare";
			form1.submit();
        	
        }
</script>
</head>
<body >
	<div class="page-content">
		<div class="content-nav">会议预定 > 预定会议</div>
		<form method="post" action="" id="form1">
			<fieldset>
				<legend>会议信息</legend>
				<table class="formtable">
					<tr>
						<td>会议名称：</td>
						<td><input type="text" id="meetingname"  name="meetingname" maxlength="20"  onblur="meetingnameblur()" onfocus="meetingnamefocus()" />
						<span id="meetingnamemsg"></span>	
						</td>
					</tr>
					<tr>
						<td>预计参加人数：</td>   
						<td><input type="text" id="numofattendents" name="numofparticipants" onblur="numofattendentsblur()" onfocus="numofattendentsfocus()" />
						<span id="numofattendentsmsg"></span>	
						</td>
					</tr>
					<tr>
						<td>预计开始时间：</td>
						<td><input class="Wdate" type="text" id="starttime" name="starttime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="starttimeblur()" onfocus="starttimefocus()" >
							<span id="starttimemsg"></span>	
							</td>
					</tr>
					<tr>
						<td>预计结束时间：</td>
						<td><input class="Wdate" type="text" id="endtime" name="endtime"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  onblur="endtimeblur()" onfocus="endtimefocus()" >
							<span id="endtimemsg"></span>	
						</td>
					</tr>

					<tr>
						<td>选择会议室：</td>
						
						<td>
						<span id="roomidmsg"></span>	
						<select id="roomid" name="roomid" onfocus="refreshRooms()">
						<option>请选择会议室</option>
								<c:forEach var="room" items="${requestScope.roomsList}">
									<option value="${room.roomid}">${room.roomname}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td>会议说明：</td>
						<td><textarea id="description" name="description" rows="5" onblur="descriptionblur()" onfocus="descriptionfocus()" ></textarea>
						
							<span id="descriptionmsg"></span>	
						</td>
					</tr>
					<tr>
						<td>选择参会人员：</td>
						<td>
							<div id="divfrom">
							<span id="selDepartmentsmsg"></span>	
								<select id="selDepartments" onchange="showEmployees()">
								    <option>请选择部门</option>
									<c:forEach var="dept" items="${requestScope.deptsList}">
										<option value="${dept.departmentid}">${dept.departmentname}</option>
									</c:forEach>
								</select> <select id="selEmployees"  multiple="multiple">

								</select>
							</div>
							<div id="divoperator">
								<input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()" /> 
								<input type="button"
									class="clickbutton" value="&lt;" onclick="deSelectEmployees()" />
							</div>
							<div id="divto">
								<select id="selSelectedEmployees"  name="selSelectedEmployees" multiple="multiple" >
								</select>
							</div></td>
					</tr>
					<tr>
						<td class="command" colspan="2">
						<input type="hidden" name="code" value="book">
						<input type="button" class="clickbutton" value="预定会议" onclick="yuding()"/> 
						<input type="button"	class="clickbutton" value="重置"  onclick="chongzhi()" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>

	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>