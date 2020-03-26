$(function () {
    $.init();
    var loading = false;
    var maxItems = 999;
    var listUrl = '/SchoolShop/reception/getshoplistlimit';
    var shopCategoryUrl = '/SchoolShop/reception/getshopcategory';
    var areaUrl = '/SchoolShop/reception/getarealist';
    var pageNum = 1;
    var parentId = getUrlString('shopCategoryId');
    var areaId = '';
    var shopName = '';
    var isloding = true;

    function getSearchDivData() {
        if (parentId == null) {
            var url = shopCategoryUrl;
        } else {
            var url = shopCategoryUrl + '?' + 'shopCategoryId=' + parentId;
        }
        $.getJSON(
            url,
            function (data) {
                if (data.success) {
                    var shopCategoryList = data.shopCategoryList;
                    var html = '';
                    html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
                    shopCategoryList
                        .map(function (item, index) {
                            html += '<a href="#" class="button" data-category-id='
                                + item.shopCategoryId
                                + '>'
                                + item.shopCategoryName
                                + '</a>';
                        });
                    $('#shoplist-search-div').html(html);
                }
            });
        $.getJSON(areaUrl, function (data) {
            if (data.success) {
                var selectOptions = '<option value="">全部区域</option>';
                var areaList = data.areaList;
                areaList.map(function (item, index) {
                    selectOptions += '<option value="'
                        + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#area-search').html(selectOptions);
            }
        })
    }

    getSearchDivData();

    function addItems(pagenum, shopCategoryId, shopName, areaId) {
        // 生成新条目的HTML
        var url = listUrl + '?' + 'pageNum=' + pagenum;
        if (shopCategoryId != null && shopCategoryId != "") {
            url = url + '&shopCategoryId=' + shopCategoryId;
        }
        if (areaId != null && areaId!="") {
            url = url + '&areaId=' + areaId;
        }
        if (shopName != null && shopName!="") {
            url = url + '&shopName=' + shopName;
        }
        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                maxItems = data.count;
                var html = '';
                data.shopList.map(function (item, index) {
                    html += '' + '<div class="card" data-shop-id="'
                        + item.shopId + '">' + '<div class="card-header">'
                        + item.shopName + '</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.shopImg + '" width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class="item-subtitle">' + item.shopDesc
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

    addItems(1, parentId);

    getUserState();

    $(document).on('infinite', '.infinite-scroll-bottom', function () {
        if (loading)
            return;
        if (isloding) {
            addItems(pageNum, parentId, shopName, areaId);
        }
    });

    $('.shop-list').on('click', '.card', function (e) {
        var shopId = e.currentTarget.dataset.shopId;
        window.location.href = '/SchoolShop/reception/shopdetail?shopId=' + shopId;
    });

    $('#shoplist-search-div').on('click', '.button', function (e) {
        parentId = e.target.dataset.categoryId;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageNum, parentId, shopName, areaId);
        isloding = true;
    });

    $("#search").blur(function (e) {
        shopName = e.target.value;
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageNum, parentId, shopName, areaId);
        isloding = true;
    })

    $('#area-search').on('change', function () {
        areaId = $('#area-search').val();
        $('.list-div').empty();
        pageNum = 1;
        addItems(pageNum, parentId, shopName, areaId);
        isloding = true;
    });

    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $.init();
});
