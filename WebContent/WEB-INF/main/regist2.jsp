<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
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
    
    var xmlhttp;      //定义一个全局对象
    
    function validUname(){
    	var uname = document.getElementById("uname").value;    	
    	//创建ajax对象
    	xmlhttp=null;		
		if (window.XMLHttpRequest){
  			xmlhttp = new XMLHttpRequest(); // 针对 Mozilla等浏览器的代码：
  		}
		else if (window.ActiveXObject){
  			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");  // 针对 IE 的代码：
  		}
    	//发送ajax请求
    	if (xmlhttp!=null){
  			xmlhttp.onreadystatechange = state_Change22;   //注册回调函数
  			var url = "<%=basePath%>UnameValidSvl?uname=" + uname;
  			xmlhttp.open("GET",url,true);
  			xmlhttp.send(null);                          //发送异步请求
  		}else{
  			alert("您的浏览器不支持XMLHTTP")
  		}      	
    }
    //在回调函数中接收ajax回应
    function state_Change22()
    {
    	if (xmlhttp.readyState==4){    
        if (xmlhttp.status==200){
        	// 回应正确,接收返回值
        	var span = document.getElementById("unameAlert");
        	var msg = xmlhttp.responseText;
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
        }else{
        	alert("出现错误，请检查!");
        }
      }
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