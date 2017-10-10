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
                    <li ><a href="/bill/main.html">账单管理</a></li>
                    <li><a href="/pro/main.html">供应商管理</a></li>
                    <li><a href="/user/main.html">用户管理</a></li>
                    <li id="active"><a href="/user/password.html">密码修改</a></li>
                    <li><a href="/login.html/out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form>
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <div class="">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldPassword" id="oldPassword" onblur="isOldPassword()" onfocus="isFocus(this)" required/>
                        <span>*请输入原密码</span>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newPassword" id="newPassword" onblur="isPassword()" onfocus="isFocus(this)" required/>
                        <span >*请输入新密码</span>
                    </div>
                    <div>
                        <label for="reNewPassword">确认新密码：</label>
                        <input type="password" name="reNewPassword" id="reNewPassword" onblur="isReNewPassword()" onfocus="isFocus(this)" required/>
                        <span >*请输入新确认密码，保证和新密码一致</span>
                    </div>
                    <div class="providerAddBtn">
                        <input id="submit" type="button" value="保存"/>
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
<script type="text/javascript">
    var count=3;
    $(function () {
        $("form span").html("");
    });
    function isOldPassword() {//判断旧密码输入是否正确
        if ($("#oldPassword").val()!='') {
            $.post("/validate.html",{"oldPassword":$("#oldPassword").val()},function (data) {
                if (data.flag=="true"){
                    $("#oldPassword").attr("flag","true");
                    isValidate($("#oldPassword").val(),$("#oldPassword"),"");
                }else{
                    $("#oldPassword").attr("flag","false");
                    isValidate(undefined,$("#oldPassword"),"原密码不正确请重新输入");
                    count--;
                    if (count<=0){
                        alert("抱歉您输入的密码错误三次默认退出登录，谢谢体谅");
                        window.location="/login.html/out";
                    }
                }
            },"json");
        }else{
            $("#oldPassword").attr("flag","false");
            isValidate(undefined,$("#oldPassword"),"原密码不能为空，请输入原密码");
        }
    }
    function isPassword(){//验证密码是否输入正确
        if ($("#oldPassword").attr("flag")=="true") {
            if ($("#newPassword").val()==$("#oldPassword").val()){
                isValidate(undefined, $("#newPassword"), "密码与原密码相同，不能修改");
                return false;
            }
            if(isValidate(/^[a-zA-Z|0-9]{5,12}$/, $("#newPassword"), "密码最少有5位最多12位组成")){return true;}else{return false;}
        }else{
            var flag=isValidate(/^[a-zA-Z|0-9]{5,12}$/, $("#newPassword"), "密码最少有5位最多12位组成");
            if (flag){return true;}
            return false;
        }
    }
    function isReNewPassword(){//验证二次密码输入是否正确
        var flag=isValidate($("#newPassword").val(),$("#reNewPassword"),"两次密码输入不一致，请重新输入");
        if (flag){return true;}return false;
    }
    $("#submit").click(isSubmit);
    function isSubmit() {
        var flag=$("#oldPassword").attr("flag");
        if (flag=="true"){
            if (isPassword()&&isReNewPassword()){flag=true;}else{flag=false;}
        }else{
            flag=false;
        }
        if (!flag){
            return;
        }
        $.ajax({
            url:"/submit.html",
            type:"POST",
            dataType:"html",
            data:{oldPassword:$("#oldPassword").val(),newPassword:$("#newPassword").val()},
            beforeSend:function () {
                $("#submit").unbind("click");
            },
            success:function (data) {
                if (data=="success"){
                    alert("修改密码成功，密码立即生效");
                    window.location.href="/welcome.html";
                }else if(data=="eor"){
                    alert("密码修改失败，请重试");
                }else{
                    window.location.href="/login.html/out";
                }
            },
            error:function () {
                $("#submit").bind("click",isSubmit);
            }
        });
    }
</script>
</body>
</html>