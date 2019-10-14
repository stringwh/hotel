<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="main.jsp">首页</a></li>
    <li><a href="#">用户留言管理</a></li>
  </ul>
</div>
<table class="tablelist">
    <thead>
      <tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>留言编号</th>
        <th>用户ID</th>
        <th>留言内容</th>
        <th>时间</th>
        <th>操作</th>
      </tr>
    </thead>
       <tbody>
       <c:forEach items="${page.list}" var="p">
      <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>${p.lid}</td>
        <td>${p.uid}</td>
        <td>${p.message}</td>
         <td>${p.ldate}</td>
        <td><a href="deleteMessage.s?lid=${p.lid}" class="tablelink"> 删除</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    
   总共${page.totalpage}页,当前第${page.pageno}页,每页${page.pagesize}条                              <a href="getMessagePageCon.s?pageno=${page.pageno-1}">上一页</a>  <a href="getMessagePageCon.s?pageno=${page.pageno+1}">下一页</a>
    
    
   
</body>
</html>
