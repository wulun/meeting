<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common03.css"/>
        <script type="text/javascript">
       		var flag = 0;
       		//失去焦点
       			/* var reg = /^[A-Z a-z 0-9]+$/; */
       		function rnblur(){
       			var reg = /^[0-9]+$/;
       				var roomnumber=document.getElementById("roomnumber").value;
       				var s1=document.getElementById("s1");
       				if (roomnumber == "") {
       					s1.innerHTML = "<font color='red'>" + "会议室号 不能为空"+"</font>";
       					flag=1;
       				}
       				else if (roomnumber.length>6) {
       					s1.innerHTML = "<font color='red'>" + "会议室号不能大于6位  " + "</font>";
       					flag = 1;
       				}else if(!reg.test(roomnumber)){
       					s1.innerHTML = "<font color='red'>" + "会议室号不能为中文或字母 " + "</font>";
           				
       				}
       			
       		}
       		//获取焦点
       		function rnfocus(){
       			var roomnumber=document.getElementById("roomnumber").value;
       			var s1=document.getElementById("s1");
       			s1.innerHTML ="";
       		}
       		//失去焦点
       		function rmblur(){
       			var roomname=document.getElementById("roomname").value;
       			var s2=document.getElementById("s2");
       			if (roomname == "") {
       				s2.innerHTML = "<font color='red'>" + "会议室名 不能为空"+"</font>";
       				flag=1;
       			}
       			else if (roomname.length>6) {
       				s2.innerHTML = "<font color='red'>" + " 会议室名长度不能大于6" + "</font>";
       				flag = 1;
       			}
       		}
       		function rmfocus(){
       			var roomname=document.getElementById("roomname").value;
       			var s2=document.getElementById("s2");
       			s2.innerHTML = "";
       		}
       		
       		
       		//失去焦点
       		function roomcapacityblur(){
       			var roomcapacity=document.getElementById("roomcapacity").value;
       			var s3=document.getElementById("s3");
       			var reg = /^[0-9]+$/;

       			if (roomcapacity == "") {
       				s3.innerHTML = "<font color='red'>" + "人数 不能为空"+"</font>";
       				flag=1;
       			}
       			else if (roomcapacity.length>3) {
       				s3.innerHTML = "<font color='red'>" + " 人数不能超过人三位数"+ "</font>";
       				flag = 1;
       			}else if(!reg.test(roomcapacity)){
       				s3.innerHTML = "<font color='red'>" + "人数不能为中文或字母" + "</font>";
       				return false;
       			}
       		}
       		//获得焦点
       		function roomcapacityfocus(){
       			var roomcapacity=document.getElementById("roomcapacity").value;
       			var s3=document.getElementById("s3");
       			s3.innerHTML = "";
       		}
       		//失去焦点
       		
       		function descriptionblur(){
       			var description=document.getElementById("description").value;
       			var s4=document.getElementById("s4");
       			if (description == "") {
       				s4.innerHTML = "<font color='red'>" + "描述 不能为空"+"</font>";
       				flag=1;
       			}
       			else if (description.length>40) {
       				s4.innerHTML = "<font color='red'>" + " 描述不能大于40字" + "</font>";
       				flag = 1;
       			}
       		}
       	//获得焦点
       		function descriptionfocus(){
       			var roomcapacity=document.getElementById("roomcapacity").value;
       			var s4=document.getElementById("s4");
       			s4.innerHTML = "";
       		}
       		function yz(){
        		
        		var rn = document.getElementById("roomnumber").value;
        		var rname = document.getElementById("roomname").value;
        		var rc = document.getElementById("roomcapacity").value;
        		var d = document.getElementById("description").value;
        		if(rn ==null|| rn==""){
        			document.getElementById("s1").innerHTML="会议室号不能为空";
        			document.getElementById("s1").style.color = "red";
        			flag = 1;
        		}
        		if(rn.length > 6){
        			document.getElementById("s1").innerHTML="会议号不能超过6位";
        			document.getElementById("s1").style.color = "red";
        			flag = 1;
        		}
        		if(rname ==null|| rname==""){
        			document.getElementById("s2").innerHTML="会议室名字不能为空";
        			document.getElementById("s2").style.color = "red"
        				flag = 1;
        		}
        		if(rname.length > 6){
        			document.getElementById("s2").innerHTML="会议名字不能超过6位";
        			document.getElementById("s2").style.color = "red";
        			flag = 1;
        		}
        		if(rc ==null|| rc==""){
        			document.getElementById("s3").innerHTML="人数不能为空";
        			document.getElementById("s3").style.color = "red";
        				flag = 1;
        		}
        		if(rc.length > 6){
        			document.getElementById("s3").innerHTML="数字不能超过3位";
        			document.getElementById("s3").style.color = "red";
        			flag = 1;
        		}
        		if(d ==null|| d==""){
        			document.getElementById("s4").innerHTML="内容不能为空";
        			document.getElementById("s4").style.color = "red";
        			flag = 1;
        		}
        		if(d.length>40){
        			document.getElementById("s4").innerHTML="内容不能超过40个字";
        			document.getElementById("s4").style.color = "red";
        			flag = 1;
        		}
        		
        		if(flag == 0){
        			form1.action="AddMeetingRoomServlet";
          			form1.submit();
        		}
        	}
        		
        
        </script>
        
        
    </head>
    <body>
        
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 添加会议室
                </div>
                <form method="post" action="" name="form1">
                    <fieldset>
                        <legend>会议室信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>门牌号:</td>
                                <td>
                                    <input id="roomnumber" name="roomnum" type="text" placeholder="例如：201" maxlength="10" onblur="rnblur()" onfocus="rnfocus()"/>
                                    <span id="s1"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>会议室名称:</td>
                                <td>
                                    <input id="roomname"  name="roomname" type="text" placeholder="例如：第一会议室" maxlength="20" onblur="rmblur()" onfocus="rmfocus()"/>
                                    <span id="s2"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>最多容纳人数：</td>
                                <td>
                                    <input id="roomcapacity" name="capacity" type="text" placeholder="填写一个正整数" onblur="roomcapacityblur()" onfocus="roomcapacityfocus()"/>
                                    <span id="s3"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>当前状态：</td>
                                <td>
                                    <input type="radio" id="status" name="status" checked="checked" value="0"/><label for="status">可用</label>
                                    <input type="radio" id="status" name="status" value="1"/><label for="status" >不可用</label>
                                   
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>
                                    <textarea id="description" name="description" maxlength="200" rows="5" cols="60" placeholder="40字以内的文字描述" onblur="descriptionblur()" onfocus="descriptionfocus()"></textarea>
                                	<span id="s4"></span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="button" value="添加" class="clickbutton" onclick="yz()"/>
                                    <input type="reset" value="重置" class="clickbutton"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
    
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
    </body>
</html>