 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
        <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="styles/common.css"/>
    </head>
    <body>
    <% 
    	String username = null;
		String password = null;
		//获取请求中全部cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				//查找名字是username password的cookie
				if(cookie.getName().equals("username")){
					username = cookie.getValue();
				}
				if(cookie.getName().equals("password")){
					password = cookie.getValue();
				}
			}
		}
		//如果不存在cookie，转到登录页面
		if(username!=null||password!=null){
			
			//如果存在cookie 则直接登录
			request.getRequestDispatcher("LoginServlet?username="+username+"&pwd="+password).forward(request, response);
		}
		%>
             <div class="page-content">
                <div class="content-nav">
                    登录
                </div>
                <form action="LoginServlet" method="post">
                    <fieldset>
                        <legend>登录信息</legend>     
                         <table class="formtable" style="width:50%">                 
                         <tr>
                                <td>提示信息:</td>
                                <td>
                                  <font color='red'> ${requestScope.msg}</font>
                                </td>
                         </tr>
                        
                       
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="username" name="username" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="pwd" name="pwd" type="password" />
                                </td>
                            </tr>
                             <tr>
                                <td>
                              
                                </td>
                                <td>
                                    <select id="timelength" name="timelength">
                                    	<option value="0" selected>每次都需要登录</option>
                                    	<option value="10">10天内</option>
                                    	<option value="30">30天内</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <input type="submit" value="登录" class="clickbutton" />
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                    <input type="button" value="注册" class="clickbutton" onclick="window.location.href='ViewDepartmentsServlet'"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
 
    </body>
</html>