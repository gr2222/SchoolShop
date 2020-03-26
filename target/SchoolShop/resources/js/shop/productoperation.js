$(function () {
    var productId = getUrlString("productId");
    var isUpdate = productId == null ? false : true;
    var initUrl = '/SchoolShop/shopAdmin/getpclsit';
    var registerProductUrl = '/SchoolShop/shopAdmin/addproduct';
    var getProduct = '/SchoolShop/shopAdmin/getproductbyid?productId=';
    var updateProduct = '/SchoolShop/shopAdmin/updateproduct';
    if (isUpdate) {
        updateProductInitInfo(productId);
    } else {
        getProductInitInfo();
    }

    function updateProductInitInfo(productId) {
        getProductInitInfo();
        $.getJSON(getProduct + productId, function (data) {
            if (data.success) {
                var product = data.product;
                $("#productName").val(product.productName);
                $("#priority").val(product.priority);
                $("#normalPrice").val(product.normalPrice);
                $("#promotionPrice").val(product.promotionPrice);
                $("#productDesc").val(product.productDesc);
                $("#integral").val(product.integral);
                $("#area option[data-id='" + product.productCategory.productCategoryId + "']").attr("selected", "selected");
            }
        });
    }

    function getProductInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtnl = '';
                data.productcategorycist.map(function (item, index) {
                    tempHtnl += '<option data-id="' + item.productCategoryId + '">' +
                        item.productCategoryName + '</option>';
                });
                $("#productCategory").html(tempHtnl);
            }
        });
    }

    $("#detail-img-div").on('change', '.detail-img:last-child', function () {
        if ($(".detail-img").length < 6) {
            $("#detail-img-div").append("<input id='productImgs' type='file' class='detail-img'>");
        }
    });

    $("#submit").click(function () {
        var product = {};
        if (isUpdate) {
            product.productId = productId;
        }
        product.productName = $("#productName").val();
        product.priority = $("#priority").val();
        product.normalPrice = $("#normalPrice").val();
        product.promotionPrice = $("#promotionPrice").val();
        product.productDesc = $("#productDesc").val();
        product.productDesc = $("#productDesc").val();
        product.integral = $("#integral").val();
        product.productCategory = {
            productCategoryId: $("#productCategory").find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var formData = new FormData();
        var shopImg = $("#productImage")[0].files[0];
        $('.detail-img').map(
            function (index, item) {
                if ($('.detail-img')[index].files.length > 0) {
                    formData.append('productImgs' + index,
                        $('.detail-img')[index].files[0]);
                }
            });
        formData.append('productImg', shopImg);
        formData.append('productStr', JSON.stringify(product));
        if (shopImg==null&&formData.get("productImgs0")==null){
            formData.append('isfile',false);
        } else {
            formData.append('isfile',true);
        }
        $.ajax({
            url: (isUpdate ? updateProduct : registerProductUrl),
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
})