<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <span>供应商管理页面</span>
        </div>
        <div class="search">
            <span>供应商名称：</span>
            <input type="text" id="name" placeholder="请输入供应商的名称"/>
            <input type="button" value="查询" onclick="find('ajax.html',success)"/>
            <a href="/pro/add.html">添加供应商</a>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">供应商编码</th>
                <th width="20%">供应商名称</th>
                <th width="10%">联系人</th>
                <th width="10%">联系电话</th>
                <th width="10%">传真</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
            <tbody id="tbody">
                <c:forEach items="${providers}" var="pro" varStatus="s">
                    <tr>
                        <td>${pro.proCode}</td>
                        <td>${pro.proName}</td>
                        <td>${pro.proContact}</td>
                        <td>${pro.proPhone}</td>
                        <td><c:choose>
                            <c:when test="${pro.proFax!=null}">${pro.proFax}</c:when>
                            <c:otherwise>暂无传真</c:otherwise>
                        </c:choose></td>
                        <td><fm:formatDate value="${pro.creationDate}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <a href="view.html/${pro.id}"><img src="/static/img/read.png" alt="查看" title="查看"/></a>
                            <a href="update.html/${pro.id}"><img src="/static/img/xiugai.png" alt="修改" title="修改"/></a>
                            <a href="javascript:delbtn(${pro.id});" class="removeProvider"><img src="/static/img/schu.png" alt="删除" title="删除"/></a>
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
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="javascript:del()" id="yes">确定</a>
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
        var add='${param.add}';
        var update='${param.update}';
        if (add=='false'){
            alert('添加失败');
        }
        if (update=='false'){
            alert("修改失败");
        }
    });
    function success(data) {//回掉函数
        $("#tbody").html("");
        $(data).each(function () {
            if (this.id) {
                $("#tbody").append("<tr>"
                    +"     <td>"+this.proCode+"</td>"
                    +"     <td>"+this.proName+"</td>"
                    +"     <td>"+this.proContact+"</td>"
                    +"     <td>"+this.proPhone+"</td>"
                    +"     <td></td>"
                    +" <td>"+(this.proFax==null?"暂无传真":this.proFax)+"</td>"
                    +" <td>"
                    +" <a href='view.html/"+this.id+"'><img src='/static/img/read.png' alt='查看' title='查看'/></a>"
                    +"     <a href='update.html/"+this.id+"'><img src='/static/img/xiugai.png' alt='修改' title='修改'/></a>"
                    +"     <a href='javascript:delbtn("+this.id+");' class='removeProvider'><img src='/static/img/schu.png' alt='删除' title='删除'/></a>"
                    +"     </td>"
                    +"     </tr>");
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
        })
    }
    function del(){//确定删除
        $.post("/pro/delete.html/"+$("#id").val(),{},function (data) {
            if (data.flag=="true"){
                window.location="/pro/main.html";
            }else{
                alert("该供应商有帐单请先删除帐单在做该操作");
            }
        },"json");
        $("#removeProv").hide();
        $("[class='zhezhao']").hide();
    }
    function delbtn(id) {//点击删除按钮
        $("#id").val(id);
        $("[class='zhezhao']").show();
        $("#removeProv").show();
    }
</script>
</body>
</html>