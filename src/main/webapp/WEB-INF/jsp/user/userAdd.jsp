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
                <li ><a href="/pro/main.html">供应商管理</a></li>
                <c:if test="${sessionScope.user.userType!=3}"><li id="active"><a href="/user/main.html">用户管理</a></li></c:if>
                <li><a href="/password.html">密码修改</a></li>
                <li><a href="/login.html/out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/user/add.html" method="post" onsubmit="return isSubmit()">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userCode">用户账号：</label>
                    <input type="text" name="userCode" flag="false" id="userCode" onblur="isAjaxUserCode()"/>
                    <span >*请输入用户名称</span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" onblur="isUserName()"/>
                    <span >*请输入用户名称</span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="text" name="userPassword" id="userpassword" onblur="isUserpassword()"/>
                    <span>*密码长度必须大于6位小于20位</span>
                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="text" name="userRemi" id="userRemi" onblur="isUserRemi()"/>
                    <span>*请输入确认密码</span>
                </div>
                <div>
                    <label >用户性别：</label>

                    <select name="sex">
                        <option value="2">男</option>
                        <option value="1">女</option>
                    </select>
                    <span></span>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="date" id="birthday" name="birthday" min="1990-01-01" onblur="isBirthday()"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="phone" id="userphone" onblur="isUserPhone()"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="userAddress" onblur="isUserAddress()"/>
                    <span >*</span>
                </div>
                <div>
                    <label >用户类别：</label>
                    <c:if test="${sessionScope.user.userType==1}"><input type="radio" name="userType" value="2"/>经理</c:if>
                    <input type="radio" name="userType" value="3" checked/>普通用户
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
    版权归北大青鸟
</footer>
<script src="/static/js/time.js"></script>
<script src="/static/js/jquery-1.8.3.min.js"></script>
<script src="/static/js/public.js"></script>
<script src="/static/js/userUpdate.js"></script>
</body>
</html>