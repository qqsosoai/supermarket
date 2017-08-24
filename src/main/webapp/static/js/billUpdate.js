$(function () {
    $("form span").html("");
});
function isBillcode(){//判断账单编号
    if ($("#billId").val()!=undefined){
        var flag=isValidate(/^\d{3,20}$/,$("#billId"),"商品账单编号必须不少于3位,最大20位有效数字");
        if (flag){return true;}return false;
    }else{
        return true;
    }
}
function isBillName() {//判断账单名称
    var flag=isValidate(/^[\s\S]+$/,$("#billName"),"商品名称不能为空");
    if (flag){return true;}return false;
}
function isBillCom() {//判断商品单位
    var flag=isValidate(/^[\s\S]{1,2}$/,$("#billCom"),"商品单位不能为空且不能超过两个汉字或字母");
    if (flag){return true;}return false;
}
function isBillNum() {
    var flag=isValidate(/^[1-9]{1}[0-9]*\.?[0-9]{0,2}$/,$("#billNum"),"*请输入大于0的正整数");
    if (flag){return true;}return false;
}
function isMoney() {//判断金额
    var flag=isValidate(/^[1-9]{1}[0-9]*\.?[0-9]{0,2}$/,$("#money"),"*请输入大于0的正自然数，小数点后保留2位");
    if (flag){return true;}return false;
}
function isSubmit(){
    if (isBillcode()&&isBillName()&&isBillCom()&&isBillNum()&&isMoney()){
        return true;
    }
    return false;
}