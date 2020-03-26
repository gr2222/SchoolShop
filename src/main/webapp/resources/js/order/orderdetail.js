$(function () {
    var url = 'http://qr.liantu.com/api.php?text=http://47.100.95.69:80/SchoolShop/order/ordermsg?orderMsg=';
    var uuidUrl = '/SchoolShop/order/setorderuuid?orderId=';
    var orderId = getUrlString("orderId");
    var orderuuid = null;
    $.getJSON(uuidUrl+orderId, function (data) {
        if (data.success){
            orderuuid = data.orderuuid;
            url = url+orderId+"｜"+orderuuid;
            $("#erweima").attr('src', url);
        }else {
            $.toast("获取二维码失败请重试");
        }
    });
});
