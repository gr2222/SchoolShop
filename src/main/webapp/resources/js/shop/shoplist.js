$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: '/SchoolShop/shopAdmin/getshoplist',
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success){
                    handUserName(data);
                    setList(data);
                } else {
                    $.toast(data.errMsg);
                }
            }
        });
    }

    function handUserName(data) {
        $("#userName").text(data.user.name);
    }
    function setList(data) {
        var html = '';
        data.shopList.map(function (item,index) {
            html += '<div class="row row-shop"><div class="col-40">'+
                item.shopName +'</div><div class="col-40">'+
                shopStatus(item.enableStatus) +'</div><div class="col-20">'+
                goShop(item.enableStatus, item.shopId) +'</div></div>';
        });
        $("#shopList").html(html);
    }
    function shopStatus(enableStatus) {
        if (enableStatus ==0){
            return"审核中";
        } else if (enableStatus == -1){
            return "店铺非法";
        } else if (enableStatus == 1){
            return "审核通过";
        }
    }
    function goShop(enableStatus,shopId) {
        if (enableStatus == 1){
            return '<a href="/SchoolShop/shopAdmin/shopmanage?shopId='+ shopId +'">进入</a>';
        }else {
            return '';
        }
    }
});