function format(time){//做时间转换
    var datetime=new Date(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth()+1;//js从0开始取
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minutes = datetime.getMinutes();
    var second = datetime.getSeconds();
    if(month<10){
        month = "0" + month;
    }
    if(date<10){
        date = "0" + date;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minutes <10){
        minutes = "0" + minutes;
    }
    if(second <10){
        second = "0" + second ;
    }
    year = year.toString();
    year = year.substring(2);
    var time1 = year+"年"+month+"月"+date+"日";
    var time2 = hour+"时"+minutes+"分";//09年06月12日 17时18分
    var time={time1:time1,time2:time2}    //json格式
// alert(time);
    return time1;
}
function isValidate(regex,jqeury,errorText) {//验证方法
    if (regex instanceof Object) {
        if (regex.test(jqeury.val())) {
        jqeury.parent().removeClass().addClass("ok");
        jqeury.next().html("");
        return true;
    } else {
        jqeury.parent().removeClass().addClass("error");
        jqeury.next().html(errorText);
        return false;
    }
    }else{
        if (regex==jqeury.val()){
            jqeury.parent().removeClass().addClass("ok");
            jqeury.next().html("");
            return true;
        }else {
            jqeury.parent().removeClass().addClass("error");
            jqeury.next().html(errorText);
            return false;
        }
    }
}
function isFocus(ob){
    $(ob).parent().removeClass();
    $(ob).next().html("");
}

function pageNext(url,functions){//下一页
    var pageIndex=parseInt($("#pageIndex").val());
    var pageCount=parseInt($("#pageCount").val());
    var name=$("#name").val();
    var proId=$("#proId").val();
    var isPay=$("#isPay").val();
    if (proId==undefined){
        proId=null
    }
    if (isPay==undefined){
        isPay=null;
    }
    if (pageIndex+1<=pageCount) {
        $.post(url,{"pageIndex":pageIndex+1,
                "name":name,
                "proId":proId,
                "isPay":isPay},functions,"json");
    }else{
        alert("没有下一页");
    }
}
function pagePrev(url,functions){//上一页
    var pageIndex=parseInt($("#pageIndex").val());
    var name=$("#name").val();
    var proId=$("#proId").val();
    var isPay=$("#isPay").val();
    if (proId==undefined){
        proId=null
    }
    if (isPay==undefined){
        isPay=null;
    }
    if (pageIndex-1>0){
        $.post(url,{"pageIndex":pageIndex-1,
            "name":name,
            "proId":proId,
            "isPay":isPay},functions,"json");
    }else{
        alert("没有上一页");
    }
}
function pageFirst(url,functions){//首页
    var name=$("#name").val();
    var proId=$("#proId").val();
    var isPay=$("#isPay").val();
    if (proId==undefined){
        proId=null
    }
    if (isPay==undefined){
        isPay=null;
    }
    $.post(url,{"pageIndex":1,
        "name":name,
        "proId":proId,
        "isPay":isPay},functions,"json");
}
function pageLast(url,functions){//末页
    var pageCount=parseInt($("#pageCount").val());
    var name=$("#name").val();
    var proId=$("#proId").val();
    var isPay=$("#isPay").val();
    if (proId==undefined){
        proId=null
    }
    if (isPay==undefined){
        isPay=null;
    }
    $.post(url,{"pageIndex":pageCount,
        "name":name,
        "proId":proId,
        "isPay":isPay},functions,"json");
}
function find(url,functions) {//查询按钮
    var name=$("#name").val();
    var proId=$("#proId").val();
    var isPay=$("#isPay").val();
    if (proId==undefined){
        proId=null
    }
    if (isPay==undefined){
        isPay=null;
    }
    $.post(url,{"pageIndex":1,
        "name":name,
        "proId":proId,
        "isPay":isPay},functions,"json");
}