$(function () {
    $("form span").html("");
});
function isAjaxUserCode(){//ajax请求判断
    if (/^[a-zA-z|\d]{5,12}$/.test($("#userCode").val())) {
        $.post("/user/validate.html",{"userCode":$("#userCode").val()},function (data) {
            if (data.flag=="true"){
                $("#userCode").parent().removeClass().addClass("error");
                $("#userCode").attr("flag","false");
                $("#userCode").next().html("用户账号已存在，请重新输入");
            }else{
                $("#userCode").parent().removeClass().addClass("ok");
                $("#userCode").attr("flag","true");
                $("#userCode").next().html("");
            }
        },"json");
    }else{
        $("#userCode").parent().removeClass().addClass("error");
        $("#userCode").attr("flag","false");
        $("#userCode").next().html("用户由字母数字组成，最少5位，最多12位");
        return false;
    }
}
function isUserCode() {
    if ($("#userCode").val()!=undefined) {
        if ($("#userCode").val()!='') {
            var flag=$("#userCode").attr("flag");
            if (flag=="true"){
                return true;
            }else{
                return false;
            }
        }else{
            $("#userCode").parent().removeClass().addClass("error");
            $("#userCode").attr("flag","false");
            $("#userCode").next().html("用户由字母数字组成，最少5位，最多12位");
            return false;
        }
    }else{return true;}
}
function isUserName(){//判断用户姓名
    var flag=isValidate(/^[\s\S]{2,4}$/,$("#userName"),"用户姓名不能为空,最少两位最多不可超过4位汉字");
    if(flag){return true;}return false;
}
function isUserpassword(){//判断用户密码
    if ($("#userpassword").val()!=undefined) {
        var flag = isValidate(/^[a-zA-Z|0-9]{5,12}$/, $("#userpassword"), "密码最少有5位最多12位组成");
        if (flag) {
            return true;
        }
        return false;
    }else{return true;}
}
function isUserRemi(){//判断用户第二次密码
    if ($("#userRemi").val()!=undefined) {
        var flag = isValidate($("#userpassword").val(), $("#userRemi"), "两次密码输入不一致");
        if (flag) {
            return true;
        }
        return false;
    }else{return true;}
}
function isBirthday(){//判断用户出生日期
    var flag=isValidate(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,
        $("#birthday"),"输入的日期不正确请重新输入");
    if (flag){return true;}return false;
}
function isUserPhone(){//判断用户电话
    var flag=isValidate(/^[0-9]{11}$/,$("#userphone"),"用户电话必须11位数字");
    if(flag){return true;}return false;
}
function isUserAddress(){//判断用户地址
    var flag=isValidate(/^[\s\S]{1,30}$/,$("#userAddress"),"用户地址不能为空,且不能超过30位字符");
    if(flag){return true;}return false;
}
function isSubmit(){
    if (isUserCode()&&isUserName()&&isUserpassword()&&isUserRemi()&&isBirthday()&&isUserPhone()&&isUserAddress()){
        return true;}return false;
}