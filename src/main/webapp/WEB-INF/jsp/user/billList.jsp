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
    <section class="publicMian">
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
                <span>账单管理页面</span>
            </div>
            <div class="search">
                <span>商品名称：</span>
                <input type="text" id="billName" placeholder="请输入商品的名称"/>
                
                <span>供应商：</span>
                <select id="proId" >
                    <option value="0">--全部--</option>
                    <c:forEach items="${providers}" var="pro">
                        <option value="${pro.id}">${pro.proName}</option>
                    </c:forEach>
                </select>
                <span>是否付款：</span>
                <select id="isPay">
                    <option value="2">--全部--</option>
                    <option value="1">已付款</option>
                    <option value="0">未付款</option>
                </select>
                <input type="button" onclick="find()" value="查询"/>
                <a href="/bill/add.html">添加订单</a>
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                <th width="10%">账单编码</th>
                <th width="20%">商品名称</th>
                <th width="10%">供应商</th>
                <th width="10%">账单金额</th>
                <th width="10%">是否付款</th>
                <th width="10%">创建时间</th>
                <th width="30%">操作</th>
            </tr>
                <tbody id="tbody">
                    <c:forEach items="${bills}" var="bill" varStatus="s">
                        <tr>
                            <td>${bill.billCode}</td>
                            <td>${bill.productName}</td>
                            <td>${bill.proId.proName}</td>
                            <td>${bill.totalPrice}</td>
                            <td><c:choose>
                                <c:when test="${bill.isPayment==0}">未支付</c:when>
                                <c:otherwise>已支付</c:otherwise>
                            </c:choose></td>
                            <td><fm:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <a href="view.html/${bill.id}"><img src="/static/img/read.png" alt="查看" title="查看"/></a>
                                <a href="update.html/${bill.id}"><img src="/static/img/xiugai.png" alt="修改" title="修改"/></a>
                                <a href="javascript:delbtn(${bill.id});" class="removeBill"><img src="/static/img/schu.png" alt="删除" title="删除"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="hidden" id="id">
            <a href="javacript:" onclick="pageFirst()">首页</a>
            <a href="javacript:" onclick="pagePrev()">上一页</a>
            <a href="javacript:" onclick="pageNext()">下一页</a>
            <a href="javacript:" onclick="pageLast()">末页</a>
            <span id="page">当前页面${page.pageIndex}/${page.pageCount}  共${page.sqlCount}条记录</span>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
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
<script src="/static/js/public.js"></script>
<script src="/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    var pageIndex=${page.pageIndex};//当前页码
    var pageCount=${page.pageCount};//总页数
    var sqlCount=${page.sqlCount};//数据库总记录数
    $(function () {
        var add='${param.add}';
        var update='${param.update}';
        var del='${param.del}';
        if (add=='false'){
            alert('添加失败');
        }
        if (update=='false'){
            alert("修改失败");
        }
        if (del=='false'){
            alert("删除失败");
        }
    });
    function del(){//确定删除
        window.location='/bill/delete.html/'+$("#id").val();
    }
    function delbtn(id) {//点击删除按钮
        $("#id").val(id);
        $("[class='removerChid']").show();
        $("#removeBi").show();
    }

    function ajax(Index,billName,proId,isPay) {
        $.post("/bill/ajax.html",{"pageIndex":Index,
            "billName":billName,
            "proId":proId,
            "isPay":isPay},
            function (data) {
            $("#tbody").html("");
            $(data).each(function () {
                if (this.id){
                    $("#tbody").append("<tr>"
                    +"       <td>"+this.billCode+"</td>"
                    +"       <td>"+this.productName+"</td>"
                    +"       <td>"+this.proId.proName+"</td>"
                    +"       <td>"+this.totalPrice+"</td>"
                    +"       <td>"+(this.isPayment==0?"未付款":"已付款")+"</td>"
                        +"  <td>"+format(this.creationDate)+"</td>"
                        +"   <td>"
                        +"   <a href='view.html/"+this.id+"'><img src='/static/img/read.png' alt='查看' title='查看'/></a>"
                        +"       <a href='update.html/"+this.id+"'><img src='/static/img/xiugai.png' alt='修改' title='修改'/></a>"
                        +"       <a href='javascript:delbtn("+this.id+");' class='removeBill'><img src='/static/img/schu.png' alt='删除' title='删除'/></a>"
                        +"   </td>"
                        +"      </tr>");
                }else if(this.pageIndex){
                    pageIndex=this.pageIndex;
                    pageCount=this.pageCount;
                    sqlCount=this.sqlCount;
                    $("#page").html("当前页面"+pageIndex+"/"+pageCount+"  共"+sqlCount+"条记录");
                }else{

                }
            });
        },"json");
    }
    function pageNext(){//下一页
        if (pageIndex+1<=pageCount) {
            ajax(pageIndex + 1, $("#billName").val(), $("#proId").val(), $("#isPay").val());
        }else{
            alert("没有下一页");
        }
    }
    function pagePrev(){//上一页
        if (pageIndex-1>0){
            ajax(pageIndex-1,$("#billName").val(),$("#proId").val(),$("#isPay").val());
        }else{
            alert("没有上一页");
        }

    }
    function pageFirst(){//首页
        ajax(1,$("#billName").val(),$("#proId").val(),$("#isPay").val());
    }
    function pageLast(){//末页
        ajax(pageCount,$("#billName").val(),$("#proId").val(),$("#isPay").val());
    }
    function find() {
        var $billName=$("#billName").val();
        var $proId=$("#proId").val();
        var $isPay=$("#isPay").val();
        ajax(1,$billName,$proId,$isPay);
    }
</script>
</body>
</html>