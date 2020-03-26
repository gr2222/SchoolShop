$(function () {
    var shopId = getUrlString("shopId");
    var isUpdate = shopId == null ? false : true;
    var initUrl = '/SchoolShop/shopAdmin/getshopinitinfo';
    var registerShopUrl = '/SchoolShop/shopAdmin/register';
    var getShop = '/SchoolShop/shopAdmin/getshopbyid?shopId=';
    var updateShop = '/SchoolShop/shopAdmin/updateshop';
    if (isUpdate) {
        updateShopInitInfo(shopId);
    } else {
        getShopInitInfo();
    }

    function updateShopInitInfo(shopId) {
        $.getJSON(getShop + shopId, function (data) {
            if (data.success) {
                var shop = data.shop;
                $("#shopName").val(shop.shopName);
                $("#shopPhone").val(shop.phone);
                $("#shopAddr").val(shop.shopAddr);
                $("#shopDesc").val(shop.shopDesc);
                var shopCategory = '<option data-id="' + shop.shopCategory.shopCategoryId + '">' +
                    shop.shopCategory.shopCategoryName + '</option>';
                var shopArea = '';
                data.areaList.map(function (item, index) {
                    shopArea += '<option data-id="' + item.areaId + '">' +
                        item.areaName + '</option>';
                });
                $("#shopCategory").html(shopCategory);
                $("#shopCategory").attr("disabled", 'disabled');
                $("#area").html(shopArea);
                $("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
            }
        });
    }

    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtnl = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtnl += '<option data-id="' + item.shopCategoryId + '">' +
                        item.shopCategoryName + '</option>';
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' +
                        item.areaName + '</option>';
                });
                $("#shopCategory").html(tempHtnl);
                $("#area").html(tempAreaHtml);
            }
        });
    }

    $("#submit").click(function () {
        var shop = {};
        if (isUpdate) {
            shop.shopId=shopId;
        }
        shop.shopName = $("#shopName").val();
        shop.phone = $("#shopPhone").val();
        shop.shopAddr = $("#shopAddr").val();
        shop.shopDesc = $("#shopDesc").val();
        shop.shopCategory = {
            shopCategoryId: $("#shopCategory").find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId: $("#area").find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var shopImg = $("#shopImage")[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        if (shopImg==null){
            formData.append('isfile',false);
        } else {
            formData.append('isfile',true);
        }
        $.ajax({
            url: (isUpdate ? updateShop : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            async: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast("提交成功");
                } else {
                    $.toast("提交失败" + data.errMsg);
                }
            }
        });
    })
});