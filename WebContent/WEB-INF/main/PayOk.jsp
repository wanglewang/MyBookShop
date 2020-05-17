<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String allMoney = request.getParameter("allMoney");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PaySuccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table align="center" width=60%>    
      <tr>
      	<td height="180"></td>    
      	<td style="color:black;font-size:18px">      	
      	      	   
      	           付款成功！ 付款人:${user.uname} <br>
      	           付款金额： <fmt:formatNumber value="<%=allMoney%>" pattern="#.00" type="number"/> 
      	        账户余额 ： <fmt:formatNumber value="${user.account}" pattern="#.00" type="number"/>             
      	   <p style="color:red;font-size:30px">
      	          我们会尽快为您进行配送
      	   </p>      	             	  
      		
      	</td>
      </tr>  
      <tr><td height="80"></td></tr>    
      <tr>      	
      		<td colspan="2" align="center"> <a href="<%=basePath%>MainSvl">返回主页</a></td>        	
      </tr>    
    </table>
  </body>
</html>
