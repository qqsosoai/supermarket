$(function () {
    $("form span").html("");
});
function isProCode() {
    var flag=isValidate(/^\d{3,20}$/,$("#proCode"),"供应商编号必须不少于3位,最大20位有效数字");
    if (flag){return true;}return false;
}
function isProName() {
    var flag=isValidate(/^[\s\S]+$/,$("#proName"),"供应商名称不能为空");
    if (flag){return true;}return false;
}
function isProContact() {
    var flag=isValidate(/^[\s\S]{1,4}$/,$("#proContact"),"供应商联系人不能为空");
    if (flag){return true;}return false;
}
function isProPhone() {
    var flag=isValidate(/^[0-9]{11}$/,$("#proPhone"),"供应商电话号码输入格式不正确");
    if (flag){return true;}return false;
}
function isProAddress() {
    var flag=isValidate(/^[\s\S]+$/,$("#proAddress"),"供应商地址不能为空");
    if (flag){return true;}return false;
}
function isSubmit() {
    if (isProCode()&&isProName()&&isProContact()&&isProPhone()&&isProAddress())
    {return true;}return false;
}
function isProFax() {
    var flag=isValidate(/^[\s\S]*$/,$("#profax"),"");
    if (flag){return true;}return false;
}
function isProDesc() {
    var flag=isValidate(/^[\s\S]*$/,$("#prodesc"),"");
    if (flag){return true;}return false;
}