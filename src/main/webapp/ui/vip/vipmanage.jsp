<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	

		$(".tiptop a").click(function() {
			$(".tip").fadeOut(200);
		});

		$(".sure").click(function() {
			$(".tip").fadeOut(100);
		});

		$(".cancel").click(function() {
			$(".tip").fadeOut(100);
		});
		
		$("#update").click(function(){
			var $checkboxs=$(".ids:checked");
			
			//alert($checkboxs.val());
			var temp=$checkboxs.length;
			if(temp==1){
				var id=$(".ids:checked").val();
				//alert(id);
				location.href="ShowVipOne.s?vipid="+id;
				}
			else{
				alert("请选择一个选项");
			}
					
		});

	});
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.jsp">首页</a></li>
			<li><a href="#">入住管理</a></li>
			<li><a href="#">入住信息查询</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><span><img src="images/t01.png" /> </span><a
					href="ui/vip/addvip.jsp">添加</a></li>
					
				<li class="click" id="update"
					><span ><img
						src="images/t02.png" /></span>修改</li>
						
				<li onclick="document.getElementById('form1').submit();"><span><img
						src="images/t03.png" /> </span>删除</li>
			</ul>
			<div class="toolbar1">
				<table>
					<form method="post" name="serch" action="conditionAllVip.s">
						<tr>
							<td class="zi"><span>选择分类：</span></td>
							<td><select name="con">


									<option value="vipid">会员卡号</option>



									<option value="name">会员姓名</option>


									<option value="phone">手机号码</option>


									<option value="idcard">身份证号码</option>

							</select></td>
							<td class="zi"><span>关键字：</span></td>
							<td><input type="text" name="key" placeholder="与分类关联"
								value="" />
							</td>
							<td><input type="submit" value="查询" class="button" /></td>
						</tr>
					</form>
				</table>
			</div>
		</div>
		<form method="post" id="form1" action="delVipMore.s">
			<table class="tablelist">
				<thead>
					<tr>
						<th><input name="checkbox" type="checkbox"  value="" /></th>
						<!--  <th>编号</th>-->
						<th>会员卡号</th>
						<th>会员姓名</th>
						<th>性别</th>
						<th>身份证号码</th>
						<th>手机号码</th>
						<th>会员等级</th>
						<th>累计消费</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${page.list}" var="v">
						<tr>
							<td><input name="ids" type="checkbox" class="ids" value="${v.vipid}" />
							</td>
							<!-- <td>1</td> -->
							<td>${v.vipid }</td>
							<td>${v.name }</td>
							<td>${v.sex }</td>
							<td>${v.idcard }</td>
							<td>${v.phone }</td>
							<td>${v.levelstr }</td>
							<td>${v.addmonetary }</td>
							<td><a href="delVip.s?vipid=${v.vipid }" class="tablelink">
									删除</a></td>
						</tr>
					</c:forEach>


					<c:forEach items="${list}" var="v">
						<tr>
							<td><input name="ids" type="checkbox" value="${v.vipid}" />
							</td>
							<!-- <td>1</td> -->
							<td>${v.vipid }</td>
							<td>${v.name }</td>
							<td>${v.sex }</td>
							<td>${v.idcard }</td>
							<td>${v.phone }</td>
							<td><a href="delVip.s?vipid=${v.vipid }" class="tablelink">
									删除</a></td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</form>
		<div class="pagin">
			<div class="message">
				当前是第<i class="blue">${page.pageno }</i>页，每页&nbsp;<i class="blue">${page.pagesize
					}&nbsp;</i>条，共&nbsp;<i class="blue">${page.totalpage }&nbsp;</i>页
			</div>
			<ul class="paginList">

				<c:if test="${page.pageno-1>1}">
						<li class="paginItem"><a href="ShowPage11.s?pageno=${page.pageno-1 }"><span
								class="pagepre"></span> </a></li>
					</c:if>
						
						<c:if test="${page.pageno-1<=0}">
						<li class="paginItem"><a href="ShowPage11.s?pageno=1"><span
								class="pagepre"></span> </a></li>
					</c:if>

				<c:forEach begin="1" end="${page.totalpage}" var="i">
					<li class="paginItem"><a href="ShowPage11.s?pageno=${i}">${i}</a>
					</li>
				</c:forEach>
				<!--<li class="paginItem more"><a href="javascript:;">...</a></li>  -->

				<!-- <li class="paginItem"><a
					href="ShowPage11.s?pageno=${page.pageno+1 }"><span
						class="pagenxt"></span> </a> </li> -->
						
									 <c:if test="${page.pageno+1<page.totalpage}">
    	<li class="paginItem"><a href="ShowPage11.s?pageno=${page.pageno+1}"><span class="pagenxt"></span></a><br /></li>
    </c:if>
						
						 <c:if test="${page.pageno+1>page.totalpage}">
    	<li class="paginItem"><a href="ShowPage11.s?pageno=${page.pageno }"><span class="pagenxt"></span></a><br /></li>
    </c:if>

			</ul>


			<!--   当前是第${page.pageno }页,每页${page.pagesize }条,共${page.totalpage }页<br/>
		
		
			<c:forEach begin="1" end="${page.totalpage}" var="i">
     <a href="ShowPage.s?pageno=${i }">${i }</a> 
    </c:forEach>-->
		</div>
		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>
			<div class="tipinfo">
				<span><img src="images/ticon.png" /> </span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" /> &nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
