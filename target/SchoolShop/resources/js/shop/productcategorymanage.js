$(function () {
    var urlString = getUrlString("shopId");
    getlist();

    function getlist() {
        $.ajax({
            url: '/SchoolShop/shopAdmin/getpclsit',
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    setList(data);
                }
            }
        });
    }

    function setList(data) {
        var html = '';
        data.productcategorycist.map(function (item, index) {
            html += '<div  class="row row-shop now"><div class="col-40">' +
                item.productCategoryName + '</div><div class="col-40">' +
                item.priority + '</div><div class="col-20">' +
                '<a href="#" class="button delete" data-id="' +
                item.productCategoryId + '">删除</a>' + '</div></div>';
        });
        $("#shopList").html(html);
    }

    $("#new").click(function () {
        var tempHtml = '<div class="row row-product-category temp " >'
            + '<div class="col-35"><input style="width: 143px" class="category-input category " type="text" placeholder="分类名"></div>'
            + '<div class="col-35"><input style="width: 143px" class="category-input priority " type="number" placeholder="优先级"></div>'
            + '<div id="delete_add" class="col-30"><a href="#" style="width: 52px" class="button delete ">删除</a></div>'
            + '</div>';
        $("#shopList").append(tempHtml);
    });

    $("#submit").click(function () {

        var productCategoryList = [];
        $(".temp").each(function (i) {
            tempObj = {};
            tempObj.productCategoryName = $(this).find('.category').val();
            tempObj.priority = $(this).find('.priority').val();
            if (tempObj.productCategoryName != null && tempObj.priority != null) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: '/SchoolShop/shopAdmin/addproductcategorys',
            type: 'POST',
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast("提交成功");
                    getlist();
                } else {
                    $.toast("提交失败" + data.errMsg);
                }
            }
        })
    });
    $(".shop-wrap").on('click', '.row-product-category.temp .delete ', function (e) {
        $(this).parent().parent().remove();
    });
    $(".shop-wrap").on('click', '.row-shop.now .delete ', function (e) {
        var target = e.currentTarget;
        $.confirm("确定要删除吗？", function () {
            $.ajax({
                url: '/SchoolShop/shopAdmin/deleteproductcategorys',
                type: 'POST',
                data: {
                    productCategoryId: target.dataset.id
                },
                dataType:'json',
                success:function (data) {
                    if (data.success){
                        $.toast("删除成功");
                        getlist();
                    } else {
                        $.toast("删除失败");
                    }
                }
            });
        });
    })
});