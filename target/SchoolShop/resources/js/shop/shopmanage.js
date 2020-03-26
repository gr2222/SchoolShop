$(function () {
    var shopId = getUrlString('shopId');
    if (shopId != undefined && shopId != null) {
        var shopInfoUrl = '/SchoolShop/shopAdmin/setshopsession?shopId=' + shopId;
    }else {
        var shopInfoUrl = '/SchoolShop/shopAdmin/setshopsession';
    }

    $.getJSON(shopInfoUrl, function (data) {
        if (data.redirect) {
            window.location.href = data.url;
        } else {
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
            $("#shopmag").attr("href", "/SchoolShop//shopAdmin/shopoperation?shopId=" + shopId);
            $("#productcategory").attr("href", "/SchoolShop/shopAdmin/productcategorymanage?shopId=" + shopId);
        }
    });
});