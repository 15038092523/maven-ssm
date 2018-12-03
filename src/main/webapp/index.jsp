<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品中心相关功能测试</title>
<!-- Javascript goes in the document HEAD -->
<script type="text/javascript">
	function altRows(id) {
		if (document.getElementsByTagName) {

			var table = document.getElementById(id);
			var rows = table.getElementsByTagName("tr");

			for (i = 0; i < rows.length; i++) {
				if (i % 2 == 0) {
					rows[i].className = "evenrowcolor";
				} else {
					rows[i].className = "oddrowcolor";
				}
			}
		}
	}

	window.onload = function() {
		altRows('alternatecolor');
	}
</script>


<!-- CSS goes in the document HEAD or added to your external stylesheet -->
<style type="text/css">
table.altrowstable {
	font-family: verdana, arial, sans-serif;
	font-size: 16px;
	color: #333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}

table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	height: 120px;
	width: 300px;
	overflow: hidden
}

.oddrowcolor {
	background-color: #68EE68;
}

.evenrowcolor {
	background-color: #68EE30;
}

.threed {
	color: #fafafa;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135
}
</style>
</head>
<body bgcolor="#68EE50">
	<h1 class="threed" align="center">商品中心相关功能测试</h1>
	<!-- <img src="ol/src/main/webapp/image/pic.png" alt="这是一张图片" /> -->
	<div align="center">

		<table class="altrowstable" id="alternatecolor">
			<tr>
				<td align="left"><form
						action="${pageContext.request.contextPath }/goods/getEntity.do"
						method="post">
						查询商品 : <input type="text" name="id" id="id" /><br /> <input
							type="submit" value="查询" align="center" />
					</form> <br></td>
				<td><form
						action="${pageContext.request.contextPath }/goods/getEntityJson.do"
						method="post">
						商品json : <input type="text" name="id" id="cid" /><br /> <input
							type="submit" value="查询" />
					</form> <br></td>
				<td><form
						action="${pageContext.request.contextPath }/type/getList.do"
						method="post">
						商品类目查询 : <input type="text" name="id" id="id" /><br /> <input
							type="submit" value="查询" />
					</form> <br></td>
			</tr>
			<tr>
				<td><form
						action="${pageContext.request.contextPath }/goods/update.do"
						method="post">
						i d: <input type="text" name="id" id="cid" /><br> name: <input
							type="text" name="name" id="name" /><br> price: <input
							type="text" name="price" id="price" /><br> color: <input
							type="text" name="color" id="color" /><br> size: <input
							type="text" name="size" id="size" /><br> <input
							type="submit" value="更新" />
					</form> <br></td>
				<td><font>分页查询商品信息</font>
					<form action="${pageContext.request.contextPath }/goods/getAll.do"
						method="post">
						page: <input type="text" name="page" id="page" /><br> rows:
						<input type="text" name="rows" id="rows" /><br> <input
							type="submit" value="查询" align="center" />
					</form> <br></td>
				<td><form
						action="${pageContext.request.contextPath }/jms/sendMessage.do"
						method="get">
						消息 : <input type="text" name="msg" id="msg" /><br /> <input
							type="submit" value="发送" />
					</form> <br></td>
			</tr>
			<tr>
				<td><form
						action="${pageContext.request.contextPath }/goods/deleteGoods.do"
						method="post">
						id: <input type="text" name="id" /><br> <input type="submit"
							value="删除" align="center" />
					</form> <br></td>
				<td><form
						action="${pageContext.request.contextPath }/goods/deleteGoodsList.do"
						method="post">
						ids(","隔开): <input type="text" name="ids" /><br> <input
							type="submit" value="删除" align="center" />
					</form> <br></td>
				<td><form 
						action="${pageContext.request.contextPath }/jms/receiveMessage.do"
						method="get">
					 <input 	type="submit" value="接收消息" />
					</form> <br></td>
			</tr>
			<tr>
				<td><font>乐观锁实现转账</font>
					<form
						action="${pageContext.request.contextPath }/account/walleroptimisticlock.do"
						method="post">
						openId: <input type="text" name="openId" id="openId" /><br>
						openType: <input type="text" name="openType" id="openType" /><br>
						amount: <input type="text" name="amount" id="amount" /><br>
						<input type="submit" value="确认" align="center" />
					</form> <br></td>
				<td><font>悲观锁实现转账</font>
					<form
						action="${pageContext.request.contextPath }/account/update.do"
						method="post">
						openId: <input type="text" name="openId" id="openId" /><br>
						openType: <input type="text" name="openType" id="openType" /><br>
						amount: <input type="text" name="amount" id="amount" /><br>
						<input type="submit" value="确认" align="center" />
					</form> <br></td>
				<td><font>分页多条件查询商品信息</font>
					<form
						action="${pageContext.request.contextPath }/goods/getAllByParam.do"
						method="post">
						page: <input type="text" name="page" /><br> rows: <input
							type="text" name="rows" /><br> brand: <input type="text"
							name="brand" /><br> name: <input type="text" name="name" /><br>
						simpleName: <input type="text" name="simpleName" /><br>
						color: <input type="text" name="color" /><br> <input
							type="submit" value="查询" align="center" />
					</form> <br></td>
			</tr>
		</table>
	</div>
</body>
</html>