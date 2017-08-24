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
    <section class="publicMian">
        <div class="left">
            <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
            <nav>
                <ul class="list">
                    <li><a href="/bill/main.html">账单管理</a></li>
                    <li><a href="/pro/main.html">供应商管理</a></li>
                    <c:if test="${sessionScope.user.userType!=3}"><li id="active"><a href="/user/main.html">用户管理</a></li></c:if>
                    <li><a href="/password.html">密码修改</a></li>
                    <li><a href="/login.html/out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
                <span>用户名：</span>
                <input type="text" id="name" placeholder="请输入用户名"/>
                <input type="button"  value="查询" onclick="find('ajax.html',success)"/>
                <a href="/user/add.html">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="30%">操作</th>
                </tr>
                <tbody id="tbody">
                    <c:forEach items="${users}" var="user" varStatus="s">
                        <tr>
                            <td>${user.userCode}</td>
                            <td>${user.userName}</td>
                            <td><c:choose>
                                <c:when test="${user.sex==1}">女</c:when>
                                <c:otherwise>男</c:otherwise>
                            </c:choose></td>
                            <td>${user.age}</td>
                            <td>${user.phone}</td>
                            <td><c:choose>
                                <c:when test="${user.userType==1}">系统管理员</c:when>
                                <c:when test="${user.userType==2}">经理</c:when>
                                <c:otherwise>员工</c:otherwise>
                            </c:choose></td>
                            <td>
                                <a href="/user/view.html/${user.userId}"><img src="/static/img/read.png" alt="查看" title="查看"/></a>
                                <a href="/user/update.html/${user.userId}"><img src="/static/img/xiugai.png" alt="修改" title="修改"/></a>
                                <a href="javascript:delbtn(${user.userId});" class="removeUser"><img src="/static/img/schu.png" alt="删除" title="删除"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="hidden" id="id">
            <input type="hidden" id="pageIndex" value="${page.pageIndex}">
            <input type="hidden" id="pageCount" value="${page.pageCount}">
            <input type="hidden" id="sqlCount" value="${page.sqlCount}">
            <a href="javascript:pageFirst('ajax.html',success);" id="pageFirst">首页</a>
            <a href="javascript:pagePrev('ajax.html',success);" id="pagePrev">上一页</a>
            <a href="javascript:pageNext('ajax.html',success);" id="pageNext">下一页</a>
            <a href="javascript:pageLast('ajax.html',success);" id="pageLast">末页</a>
            <span id="page">当前页面${page.pageIndex}/${page.pageCount}  共${page.sqlCount}条记录</span>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="javascript:del();" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
        版权归北大青鸟
    </footer>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/js.js"></script>
<script src="/static/js/time.js"></script>
<script src="/static/js/jquery-1.8.3.min.js"></script>
<script src="/static/js/public.js"></script>
<script type="text/javascript">
    $(function () {
        var flag='${param.flag}';
        if (flag=="per"){
            alert("您没有权限修改该用户");
        }
        if (flag=="false"){
            alert("修改失败");
        }
    });
    function isUserTypr(type) {
        if (type==1){
            return "系统管理员";
        }
        if (type==2){
            return "经理";
        }
        if (type==3){
            return "员工";
        }
    }
    function del(){//确定删除    //未完成
        $.post("/user/delete.html",{"id":$("#id").val()},function (data) {
            if (data.flag=="true"){
                window.location="/user/main.html";
            }else{
                alert("没有权限删除该用户");
            }
        },"json");
        $("#removeUse").hide();
        $("[class='zhezhao']").hide();
    }
    function delbtn(id) {//点击删除按钮
        $("#id").val(id);
        $("[class='zhezhao']").show();
        $("#removeUse").show();
    }
    function success(data) {
        $("#tbody").html("");
        $(data).each(function () {
            if (this.userId) {
                $("#tbody").append("<tr>"
               +"    <td>"+this.userCode+"</td>"
               +"    <td>"+this.userName+"</td>"
               +"    <td>"+(this.sex==1?"女":"男")+"</td>"
               +"<td>"+this.age+"</td>"
               +"<td>"+this.phone+"</td>"
               +"<td>"+isUserTypr(this.userType)+"</td>"
               +"<td>"
               +"<a href='/user/view.html/"+this.userId+"'><img src='/static/img/read.png' alt='查看' title='查看'/></a>"
               +"    <a href='/user/update.html/"+this.userId+"'><img src='/static/img/xiugai.png' alt='修改' title='修改'/></a>"
               +"    <a href='javascript:delbtn("+this.userId+");' class='removeUser'><img src='/static/img/schu.png' alt='删除' title='删除'/></a>"
               +"    </td>"
               +"    </tr>");
            }else if(this.pageIndex){
                $("#pageIndex").val(this.pageIndex);
                $("#pageCount").val(this.pageCount);
                $("#sqlCount").val(this.sqlCount);
                $("#page").html("当前页面"+this.pageIndex+"/"+this.pageCount+"  共"+this.sqlCount+"条记录");
            }else{
                $("#tbody").append("<tr>" +
                    "<td colspan='7'>暂无搜索到符合条件的数据</td>" +
                    "</tr>");
                $("#page").html("当前页面1/1  共0条记录");
            }
        });
    }
</script>
</body>
</html>