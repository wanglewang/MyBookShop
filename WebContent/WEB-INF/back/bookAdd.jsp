<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
 <table align="center" width=90%>
    	 <tr>
      	<td align=right>
      	    
      	  		管理员: admin &nbsp;	  		       	         	    	         	          	  	
      		
      			<a href="#">退出</a>
      		      	
      	</td>       	
      </tr>
      <tr>
      	<td align=center>
      	    <a href="#">新书上架</a> &nbsp;  <a href="#">书增加库存</a>  &nbsp;  <a href="#">书下架</a> &nbsp;  <a href="#">用户管理</a>
      	    &nbsp;  <a href="#">修改售价</a> &nbsp; <a href="#">用户购买记录</a>
      	</td>
      	</tr>    	
      <tr><td align="left"><h2>新书上架</h2></td></tr>
      <tr>
      	<td>
      	<form action="#" method="post" enctype="multipart/form-data" onsubmit="return validName()">
      		<table border="0" width=60% align="center">  		
      		
      			<tr><td>书号ISBN</td><td><input type="text" name="isbn"/></td></tr>
       			<tr><td>书名</td><td><input type="text" name="bkname"/><span id="NameNull"></span></td></tr>
       			<tr><td>作者</td><td><input type="text" name="author"/></td></tr>
       			<tr><td>出版社</td><td><input type="text" name="press"/></td></tr>
       			<tr><td>出版日期</td><td><input type="text" name="pubdate"/></td></tr>
       			<tr><td>价格</td><td><input type="text" name="price"/></td></tr>
       			<tr><td>图片上传</td><td><input type="file" name="pic"/></td></tr>
       			<tr><td colspan=2 align=center><input type=submit value=提交 /></td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>

</body>
</html>