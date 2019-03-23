<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>CoolMeeting会议管理系统</title>
          <script type="text/javascript">
        	function validate(){
        		var flag = 1;
        		var departmentname= document.getElementById("departmentname").value;       		
        		if(departmentname=="" || departmentname == null){
        			alert("部门名称不能为空！");
        			flag = 2;
        		}
        		if(flag==1){
        			f.action="AddDepartmentServlet";
        			f.submit();
        		}
        	}  
        </script>
        <link rel="stylesheet" href="styles/common03.css"/>
    </head>
    <body>
           <div class="page-content">
                <div class="content-nav">
                    人员管理 > 部门管理
                </div>
                <form action="" method="post" id="f">
                    <fieldset>
                        <legend>添加部门</legend>
                        部门名称:
                        <input type="text" id="departmentname" name="departmentname" maxlength="6" placeholder="长度不能超过6个字符"/>
                        <input type="hidden" name="code" value="add" >
                        <input type="button" class="clickbutton" value="添加" onclick="validate()"/>
                    </fieldset>
                </form>
                <c:if test="${requestScope.departmentsList!=null}">
      
                <table class="listtable">
                    <caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
	
					<c:forEach var="dept" items="${requestScope.departmentsList}">
                    <tr>
                        <td>${dept.departmentid}</td>
                        <td>${dept.departmentname}</td>
                        <td>
                           <a class="clickbutton" href="AddDeleteDepartmentServlet?departmentid=${dept.departmentid}">删除</a>
                        </td>
                    </tr>
               	</c:forEach>
                </table>
                </c:if>
            </div>
    </body>
</html>