<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="/static/css/public.css"/>
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> Admin</span> , 欢迎你！</p>
        <a href="/login.html/out">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="/bill/main.html">账单管理</a></li>
                <li id="active"><a href="/pro/main.html">供应商管理</a></li>
                <c:if test="${sessionScope.user.userType!=3}"><li><a href="/user/main.html">用户管理</a></li></c:if>
                <li><a href="/password.html">密码修改</a></li>
                <li><a href="/login.html/out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编码：</strong><span>${pro.proCode}</span></p>
            <p><strong>供应商名称：</strong><span>${pro.proName}</span></p>
            <p><strong>联系人：</strong><span>${pro.proContact}</span></p>
            <p><strong>联系电话：</strong><span>${pro.proPhone}</span></p>
            <p><strong>传真：</strong><span><c:choose>
                <c:when test="${pro.proFax!=null}">${pro.proFax}</c:when>
                <c:otherwise>暂无传真</c:otherwise>
            </c:choose></span></p>
            <p><strong>描述：</strong><span>${pro.proDesc}</span></p>
            <a href="/pro/main.html">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="/static/js/time.js"></script>

</body>
</html>