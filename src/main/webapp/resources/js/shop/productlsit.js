$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: '/SchoolShop/shopAdmin/getproductlist',
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    setList(data);
                } else {
                    $.toast(data.errMsg);
                }
            }
        });
    }

    function setList(data) {
        var html = '';
        data.productlist.map(function (item, index) {
            if (item.enableStatus == 1) {
                textOp = '下架';
            } else if (item.enableStatus == 0) {
                textOp = '上架';
            }
            html = html + '' + '<div class="row row-product">'
                + '<div class="col-40">'
                + item.productName
                + '</div>'
                + '<div class="col-30">'
                + item.priority
                + '</div>'
                + '<div class="col-30">'
                + '<a href="#" class="edit" style="margin-left:70px " data-id="'
                + item.productId
                + '" data-status="'
                + item.enableStatus
                + '">编辑</a>'
                + '<a href="#" class="status" data-id="'
                + item.productId
                + '" data-status="'
                + item.enableStatus
                + '">'
                + textOp
                + '</a>'
                + '<a href="#" class="preview" data-id="'
                + item.productId
                + '" data-status="'
                + item.enableStatus
                + '">预览</a>'
                + '</div>'
                + '</div>';
        });
        $("#producList").html(html);
    }

    $('.product-wrap')
        .on(
            'click',
            'a',
            function (e) {
                var target = $(e.currentTarget);
                if (target.hasClass('edit')) {
                    window.location.href = '/SchoolShop/shopAdmin/productoperation?productId='
                        + e.currentTarget.dataset.id;
                } else if (target.hasClass('status')) {
                    change(e.currentTarget.dataset.id,
                        e.currentTarget.dataset.status);
                } else if (target.hasClass('preview')) {
                    window.location.href = '/SchoolShop/reception/productdetail?productId='
                        + e.currentTarget.dataset.id;
                }
            });
    function change(productId,enableStatus){
        var product ={};
        product.productId = productId;
        if (enableStatus==1){
            enableStatus=0;
        } else if (enableStatus == 0) {
            enableStatus=1;
        }
        product.enableStatus =enableStatus;
        $.confirm("确定？",function () {
            $.ajax({
                url: '/SchoolShop/shopAdmin/updateproduct',
                type: 'POST',
                data: {
                    productStr:JSON.stringify(product),
                    ischange:true
                },
                dataType:'json',
                success: function (data) {
                    if (data.success) {
                        $.toast("提交成功");
                        getlist();
                    } else {
                        $.toast("提交失败" + data.errMsg);
                    }
                }
            });
        });
    }
});