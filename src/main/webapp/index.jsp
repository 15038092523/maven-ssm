<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<div align="left">
		<form action="${pageContext.request.contextPath }/goods/getEntity.do"
			method="post">
			查询商品 : <input type="text" name="id" id="id" /><br /> <input
				type="submit" value="查询" align="center" />
		</form>
		<br>
		<form
			action="${pageContext.request.contextPath }/goods/getEntityJson.do"
			method="post">
			商品json : <input type="text" name="id" id="cid" /><br /> <input
				type="submit" value="查询" />
		</form>
		
		<br>
		<form
			action="${pageContext.request.contextPath }/goods/update.do"
			method="post">
			i  d: <input type="text" name="id" id="cid" /><br> 
			name: <input type="text" name="name" id="name" /><br> 
			price: <input type="text" name="price" id="price" /><br> 
			color: <input type="text" name="color" id="color" /><br> 
			size:  <input type="text" name="size" id="size" /><br> 
			<input
				type="submit" value="更新" />
		</form>
		<br>
		<font>分页查询商品信息</font>
		<form action="${pageContext.request.contextPath }/goods/getAll.do"
			method="post">
			page:  <input type="text" name="page" id="page" /><br> 
			rows:  <input type="text" name="rows" id="rows" /><br> 
			<input type="submit" value="查询" align="center" />
		</form>
		<br>
		<form action="${pageContext.request.contextPath }/goods/deleteGoods.do"
			method="post">
			id:  <input type="text" name="id" /><br> 
			<input type="submit" value="删除" align="center" />
		</form>
		<br>
		<form action="${pageContext.request.contextPath }/goods/deleteGoodsList.do"
			method="post">
			ids(","隔开):  <input type="text" name="ids" /><br> 
			<input type="submit" value="删除" align="center" />
		</form>
		
	</div>
</body>
</html>