<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	Success 商品详情：
	<br> id：${currentUser.id }
	<br> name：${currentUser.name }
	<br> price：${currentUser.price }
	<br> color：${currentUser.color }
	<br> size：${currentUser.size }
	<br> brand：${currentUser.item.brand }
	<br> simpleName：${currentUser.item.simpleName }
	<br>
</body>
</html>