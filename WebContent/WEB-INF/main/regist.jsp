<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
    function tijiao(){
    	   var uname = document.getElementById("uname").value;
    	   if(uname == ""){
    		   alert("用户名不能为空!")
    		   return false;
    	   }
    	   var pwd = document.getElementById("pwd").value;
    	   if(pwd == ""){
    		   alert("密码不能为空!")
    		   return false;
    	   }
    	   var pwd2 = document.getElementById("pwd2").value;
    	   if(pwd != pwd2){
    		   alert("密码与密码确认不一致，请重新输入....")
    		   return false;
    	   }
    	   var myform = document.getElementById("myform");
    	   myform.submit();	       	
    }
    
  
    
    function validUname(){
    	var uname = $('#uname').val();   
    	var target = "<%=basePath%>UnameValidSvl?uname=" + uname;
    	$.ajax({
    		   type: "POST",
    		   url: target,
    		   success: function(msg){
    		       //回调函数的返回处理
    			    var span = document.getElementById("unameAlert");
    	        	if(msg == "1"){
    	        		span.innerHTML="用户名冲突，重新输入！";
    	        	}else if(msg=="0"){
    	        		span.innerHTML="用户名可以使用！";
    	        	}else if(msg == "-2"){
    	        		span.innerHTML="用户名不能为空!";
    	        	}else if(msg == "-1"){
    	        		span.innerHTML="网络异常，请和管理员联系...!";
    	        	}else{
    	        		
    	        	}  	
    		   }
    	});
    }   
</script>
</head>
<body>
<table align="center" width=70%>
      <tr>
      	<td>
				<form action="<%=basePath%>RegistSvl" id="myform" method="post" >
						<table  border="0" cellpadding="0" cellspacing="0" align="center">
							<tr><td height=100></td></tr>
							<tr>
							  <td width="107" height="36">用户名：</td>
							  <td width="524"><INPUT name="uname" id="uname" type="text" onkeyup="validUname()">
								<span id="unameAlert" style="color:red;font-size:8px"></span>
							  </td>
							</tr>
							<tr>
							  <td width="107" height="36">密码：</td>
							  <td width="524"><INPUT name="pwd" id="pwd" type="password"></td>
							</tr>
							<tr>
							  <td width="107" height="36">确认密码：</td>
							  <td width="524"><INPUT name="pwd2" id="pwd2" type="password"></td>
							</tr>						
							<tr>
							<td width="107" height="36">联系电话：</td>
							<td width="524"><INPUT name="tel" type="text"></td>
						  </tr>   
							<tr>
								<td colspan=2 >
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value="提交" onclick="tijiao()" /> &nbsp; 
									<a href="<%=basePath%>MainSvl">返回</a>
								</td>
							</tr>
							<tr><td colspan=2 align="center">
							<p style="color:red;font-size:8px">${msg}</p>
							</td></tr>
						</table>
			   </form>
      	</td>
      </tr>    
    </table>

</body>
</html>