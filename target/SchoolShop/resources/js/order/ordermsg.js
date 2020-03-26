$(function () {
    var orderMsg = getUrlString("orderMsg");
    var url = '/SchoolShop/order/endorder?orderMsg='+orderMsg;
    $.getJSON(url, function (data) {
        if (data.success){
            $("#ordermsg").html(data.msg);
        }else {
            $("#ordermsg").html(data.errMsg);
        }
    });
});
