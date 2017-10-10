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
                <li id="active"><a href="/pro/main.html">供应商管理</a></li>
                <c:if test="${sessionScope.user.userType!=3}"><li><a href="/user/main.html">用户管理</a></li></c:if>
                <li><a href="/user/password.html">密码修改</a></li>
                <li><a href="/login.html/out">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/pro/add.html" method="post" onsubmit="return isSubmit()">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="proCode">供应商编码：</label>
                    <input type="text" name="proCode" id="proCode" onblur="isProCode()" onfocus="isFocus(this)"/>
                    <span>*请输入供应商编码</span>
                </div>
                <div>
                    <label for="proName">供应商名称：</label>
                    <input type="text" name="proName" id="proName" onblur="isProName()" onfocus="isFocus(this)"/>
                    <span >*请输入供应商名称</span>
                </div>
                <div>
                    <label for="proContact">联系人：</label>
                    <input type="text" name="proContact" id="proContact" onblur="isProContact()" onfocus="isFocus(this)"/>
                    <span>*请输入联系人</span>

                </div>
                <div>
                    <label for="proPhone">联系电话：</label>
                    <input type="text" name="proPhone" id="proPhone" onblur="isProPhone()" onfocus="isFocus(this)"/>
                    <span>*请输入联系电话</span>
                </div>
                <div>
                    <label for="proAddress">联系地址：</label>
                    <input type="text" name="proAddress" id="proAddress" onblur="isProAddress()" onfocus="isFocus(this)"/>
                    <span></span>
                </div>
                <div>
                    <label for="proFax">传真：</label>
                    <input type="text" name="proFax" id="proFax" onblur="isProFax()" onfocus="isFocus(this)"/>
                    <span></span>
                </div>
                <div flag="abc">
                    <label for="proDesc">描述：</label>
                    <input type="text" name="proDesc" id="proDesc" onblur="isProDesc()" onfocus="isFocus(this)"/>
                    <span></span>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="providerList.html">返回</a>-->
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
<script src="/static/js/proUpdate.js"></script>
</body>
</html>