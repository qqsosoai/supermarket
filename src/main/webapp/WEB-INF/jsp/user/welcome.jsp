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
        <p><span>下午好！</span><span style="color: #fff21b"> ${sessionScope.user.userName}</span> , 欢迎你！</p>
        <a href="login.html/out">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li ><a href="/bill/main.html">账单管理</a></li>
                <li><a href="/pro/main.html">供应商管理</a></li>
                <c:if test="${sessionScope.user.userType!=3}"><li><a href="/user/main.html">用户管理</a></li></c:if>
                <li><a href="/password.html">密码修改</a></li>
                <li><a href="/login.html/out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <img class="wColck" src="/static/img/clock.jpg" alt="小锁子" title="别闹，这只是一把小锁子"/>
        <div class="wFont">
            <h2>${sessionScope.user.userName}</h2>
            <p>欢迎来到超市账单管理系统!</p>
        </div>
    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="/static/js/time.js"></script>
<script src="/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    $(function () {
        var flag='${param.flag}';
        if (flag=="ok"){
            alert("修改密码成功");
        }
        if (flag=="false"){
            alert("修改密码失败");
        }
    })
</script>

</body>
</html>