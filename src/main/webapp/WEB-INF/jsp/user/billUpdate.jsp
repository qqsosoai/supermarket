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
                <li><a href="providerList.html">供应商管理</a></li>
                <li><a href="userList.html">用户管理</a></li>
                <li><a href="password.html">密码修改</a></li>
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
            <form action="/bill/update.html" method="post" onsubmit="">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="providerName">商品名称：</label>
                    <input type="text" name="productName" value="${bill.productName}" id="providerName" placeholder="123"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="people">商品单位：</label>
                    <input type="text" name="productUnit" id="people" value="${bill.productUnit}" placeholder="北极"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="phone">商品数量：</label>
                    <input type="text" name="productCount" id="phone" value="${bill.productCount}" placeholder="22"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="address">总金额：</label>
                    <input type="text" name="totalPrice" id="address" value="${bill.totalPrice}" placeholder="200"/>
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
                    <input type="radio" name="isPayment"<c:if test="${bill.isPayment==0}">checked</c:if>/>未付款
                    <input type="radio" name="isPayment"<c:if test="${bill.isPayment==1}">checked</c:if>/>已付款
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

</body>
</html>