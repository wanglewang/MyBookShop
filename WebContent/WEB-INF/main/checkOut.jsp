<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
    function tijiao(){
    	var r = confirm("是否确认付款？")
    	if (r==true){
    	    window.location.href = "<%=basePath%>user/PaySvl";
    	}
    }
</script>
</head>
<body>
	<table align="center" width=90%>
      <jsp:include page="mhead.jsp"></jsp:include>
      <tr>
      	<td>
      		<table border="1" width=100%> 
      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td>折扣</td><td width="12%">数量</td></tr>		       
       			   <c:forEach var="bk" items="${books}">     				
       					<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td><td>${bk.discount}</td><td >${bk.num}本</tr>    
					</c:forEach>
				 
       			
      			    <tr><td colspan=5 align=center>账户余额：￥${user.account}  &nbsp;&nbsp;&nbsp;&nbsp; 
      			            商品总价：￥<fmt:formatNumber type="number" pattern=".##">${allMoney}</fmt:formatNumber>   
      			    </td></tr>
    		</table>
      	</td>
      </tr>
      <tr>
      	
      		<td align="center">
      		<c:if test="${user.account-allMoney>=0}">
      			<input type="button" value="付款确认" onclick="tijiao()"> &nbsp; 
      		</c:if>
      		<c:if test="${user.account-allMoney<0}">
      			<p style="color:red;font-size:8px">余额不足，请充值！</p>
      		</c:if>
      		<a href="<%=basePath%>MainSvl">返回</a></td>
        	
      </tr>
    
    </table>
	
</body>
</html>