<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市账单管理系统</title>
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市账单管理系统</h1>
        </header>
        <section class="loginCont">
            <form class="loginForm" action="login.html" method="post" onsubmit="return isSubmit()">
                <div class="inputbox">
                    <label for="user">用户名：</label>
                    <input id="user" type="text" name="userCode" value="${user.userCode}" onblur="username()" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label for="mima">密码：</label>
                    <input id="mima" type="password" name="userPassword" value="${user.userPassword}" onblur="password()" placeholder="请输入密码" required/>
                    <div id="message" style="text-align: center;color:red;"></div>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录" />
                    <input type="reset" value="重置"/>
                </div>
            </form>
        </section>
    </section>
<script src="/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    $(function () {
        var message='${message}';
        var flag='${param.flag}';
        if (message=='name'){
            $("#message").html('用户名不正确');
        }else if(message=='pass'){
            $("#message").html('密码不正确');
        }
        if (flag=='login'){
            $("#message").html('请先登录');
        }
    });
    function validation (rep,value){//验证方法yan
        if (rep.test(value)){
            return true;
        }else{
            return false;
        }
    }
    function username() {//验证用户名方法
        if (validation(/^[a-zA-z|\d]{5,12}$/,$("#user").val())){
            return true;
        }else{
            alert('用户由字母数字组成，最少5位，最多12位');
            return false
        }
    }
    function password(){
        if (validation(/^[a-zA-z|\d]{5,12}$/,$("#mima").val())){
            return true;
        }else{
            alert('用户名不正确');
            return false;
        }
    }
    function isSubmit() {
        if (username() & password()){
            return true
        }else{
            return false;
        }
    }

</script>
</body>
</html>