<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<tr>
	<td align=right>
		<c:if test="${user !=null}">
			welcome you ${user.userName} &nbsp;
			<a href="<%=basePath%>user/shop_car">购物车</a>&nbsp;
			<a href="<%=basePath%>user/logout">退出</a>    
			<c:if test="${user.role==1}">
				<a href="<%=basePath%>back/book_add">后台</a>   
			</c:if>   	 
		</c:if>
		<c:if test="${user==null}">
			<a href="<%=basePath%>login">登录</a>
		</c:if>       	  	
	</td>
</tr>