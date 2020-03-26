$(function () {
    $.init();
    var loading = false;
    var maxItems = 999;
    var listUrl = '/SchoolShop/order/getorderbybuyid';
    var pageNum = 1;
    var isloding = true;


    function addItems(pagenum) {
        // 生成新条目的HTML
        var url = listUrl + '?' + 'pageNum=' + pagenum;
        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                maxItems = data.count;
                if (maxItems <= 0){
                    $('.infinite-scroll-preloader').hide();
                    $.toast("数据为空");
                }
                    var html = '';
                data.orderList.map(function (item, index) {
                    html += '' + '<div class="card" data-order-id="'
                        + item.orderId + '">' + '<div class="card-header">'
                        + item.product.productName + "      "
                        + getstate(item.enableStatus) + '</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.product.imgAddr + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.shop.shopName
                        + '</div>' + '</div>' + '</li>' + '</ul>'
                        + '</div>' + '</div>' + '<div class="card-footer">'
                        + new Date(item.lastEditTime).Format("yyyy-MM-dd")
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        + '</div>';
                });
                $('.list-div').append(html);
                var total = $('.list-div .card').length;
                if (total >= maxItems) {
                    isloding = false;
                    // 隐藏加载提示符
                    $('.infinite-scroll-preloader').hide();
                } else {
                    $('.infinite-scroll-preloader').show();
                }
                pageNum = pageNum + 1;
                loading = false;
                $.refreshScroller();
            }
        });
    }

    addItems(1);

    getUserState();

    $(document).on('infinite', '.infinite-scroll-bottom', function () {
        if (loading)
            return;
        if (isloding) {
            addItems(pageNum);
        }
    });

    $('.shop-list').on('click', '.card', function (e) {
        var orderId = e.currentTarget.dataset.orderId;
        window.location.href = '/SchoolShop/order/orderdetail?orderId=' + orderId;
    });

    function getstate(enable_status) {
        if (enable_status == 1) {
            return '未兑换';
        }
        if (enable_status == 2) {
            return '已兑换';
        }
    }

    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $.init();
});
