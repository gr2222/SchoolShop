$(function () {
    var productId = getUrlString('productId');
    var productUrl = '/SchoolShop/reception/getproductandimg?productId='
        + productId;
    var orderUrl = '/SchoolShop/order/addorder?productId=' + productId;

    $.getJSON(
        productUrl,
        function (data) {
            if (data.success) {
                var product = data.product;
                $('#product-img').attr('src', product.imgAddr);
                $('#product-time').text(
                    new Date(product.lastEditTime)
                        .Format("yyyy-MM-dd"));
                $('#product-name').text(product.productName);
                $('#product-normalPrice').text('¥' + product.normalPrice);
                $('#product-promotionPrice').text('¥' + product.promotionPrice);
                $('#product-desc').text(product.productDesc);
                var imgListHtml = '';
                product.productImgList.map(function (item, index) {
                    imgListHtml += '<div> <img src="'
                        + item.imgAddr + '"/></div>';
                });

                $('#imgList').html(imgListHtml);
            }
        });

    getUserState();

    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $("#buybutton").click(function () {
        $.confirm("确定购买？", function () {
            $.getJSON(
                orderUrl,
                function (data) {
                    if (data.success) {
                        $.toast("下单成功请到订单页查找");
                    } else {
                        $.toast("提交失败");
                    }

                });
        });
    });
    $.init();
});
