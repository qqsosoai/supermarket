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
                <li id="active"><a href="/bill/main.html">账单管理</a></li>
                <li><a href="/pro/main.html">供应商管理</a></li>
                <c:if test="${sessionScope.user.userType!=3}"><li><a href="/user/main.html">用户管理</a></li></c:if>
                <li><a href="/user/password.html">密码修改</a></li>
                <li><a href="/login.html/out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 订单添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/bill/update.html" method="post" onsubmit="return isSubmit()">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <input type="hidden" name="id" value="${bill.id}"/>
                <div>
                    <label for="billName">商品名称：</label>
                    <input type="text" name="productName" id="billName" value="${bill.productName}" onblur="isBillName()" onfocus="isFocus(this)" placeholder="123"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="billCom">商品单位：</label>
                    <input type="text" name="productUnit" id="billCom" value="${bill.productUnit}" onblur="isBillCom()" onfocus="isFocus(this)" placeholder="北极"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="billNum">商品数量：</label>
                    <input type="text" name="productCount" id="billNum" value="${bill.productCount}" onblur="isBillNum()" onfocus="isFocus(this)" placeholder="22"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="money">总金额：</label>
                    <input type="text" name="totalPrice" id="money" value="${bill.totalPrice}" onblur="isMoney()" onfocus="isFocus(this)" placeholder="200"/>
                    <span>*</span>
                </div>
                <div>
                    <label>供应商：</label>
                    <select id="proId" name="proId.id">
                        <c:forEach items="${providers}" var="pro" varStatus="s">
                            <option value="${pro.id}" <c:if test="${bill.proId.id==pro.id}">selected</c:if>>${pro.proName}</option>
                        </c:forEach>
                    </select>
                    <span>*</span>
                </div>
                <div>
                    <label >是否付款：</label>
                    <input type="radio" name="isPayment"<c:if test="${bill.isPayment==0}">checked</c:if> value="0"/>未付款
                    <input type="radio" name="isPayment"<c:if test="${bill.isPayment==1}">checked</c:if> value="1"/>已付款
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
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
<script src="/static/js/billUpdate.js"></script>
</body>
</html>