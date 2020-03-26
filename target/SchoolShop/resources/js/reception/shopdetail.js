$(function () {
    var loading = false;
    var maxItems = 20;

    var listUrl = '/SchoolShop/reception/getproductlist';

    var pageNum = 1;
    var shopId = getUrlString('shopId');
    var productCategoryId = '';
    var productName = '';

    var shopUrl = '/SchoolShop/reception/getshopbyid?shopId='
        + shopId;
    var productCategoryUrl = '/SchoolShop/reception/getproductcategorylist?shopId=' + shopId;

    var isloding = true;

    function getSearchDivData() {
        $.getJSON(
            shopUrl,
            function (data) {
                if (data.success) {
                    var shop = data.shop;
                    $('#shop-cover-pic').attr('src', shop.shopImg);
                    $('#shop-update-time').html(
                        new Date(shop.lastEditTime)
                            .Format("yyyy-MM-dd"));
                    $('#shop-name').html(shop.shopName);
                    $('#shop-desc').html(shop.shopDesc);
                    $('#shop-addr').html(shop.shopAddr);
                    $('#shop-phone').html(shop.phone);

                    var productCategoryList = data.productCategoryList;
                    var html = '';
                    productCategoryList
                        .map(function (item, index) {
                            html += '<a href="#" class="button" data-product-search-id='
                                + item.productCategoryId
                                + '>'
                                + item.productCategoryName
                                + '</a>';
                        });
                    $('#shopdetail-button-div').html(html);
                }
            });
        $.getJSON(
            productCategoryUrl,
            function (data) {
                if (data.success) {
                    var productCategoryList = data.productCategoryList;
                    var html = '';
                    productCategoryList
                        .map(function (item, index) {
                            html += '<a href="#" class="button" data-product-search-id='
                                + item.productCategoryId
                                + '>'
                                + item.productCategoryName
                                + '</a>';
                        });
                    $('#shopdetail-button-div').html(html);
                }
            });
    }

    getSearchDivData();


    function addItems(pageIndex, productCategoryId, productName) {
        // 生成新条目的HTML
        var url = listUrl + '?' + 'pageNum=' + pageIndex;
        if (productCategoryId != null && productCategoryId != "") {
            url = url + '&productCategoryId=' + productCategoryId;
        }
        if (productName != null && productName != "") {
            url = url + '&productName=' + productName;
        }
        url = url + "&shopId=" + shopId;
        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                maxItems = data.count;
                var html = '';
                data.productList.map(function (item, index) {
                    html += '' + '<div class="card" data-product-id='
                        + item.productId + '>'
                        + '<div class="card-header">' + item.productName
                        + '</div>' + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.imgAddr + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.productDesc
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
                pageNum += 1;
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
            addItems(pageNum, productCategoryId, productName);
        }
    });

    $('#shopdetail-button-div').on(
        'click',
        '.button',
        function (e) {
            productCategoryId = e.target.dataset.productSearchId;
            $('.list-div').empty();
            pageNum = 1;
            addItems(pageNum, productCategoryId, productName);
            isloding = true;
        });

    $('.list-div')
        .on(
            'click',
            '.card',
            function (e) {
                var productId = e.currentTarget.dataset.productId;
                window.location.href = '/SchoolShop/reception/productdetail?productId='
                    + productId;
            });


    $("#search").blur(function (e) {
        productName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageNum, productCategoryId, productName);
        isloding = true;
    })

    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });
    $.init();
});
