$(function () {
    var url = '/SchoolShop/order/ordersummarychart';
    var myChart = echarts.init(document.getElementById('second'));
    $.getJSON(url, function (data) {
        if (data.success) {
            var s = [];
            var a = ['product', '前天', '昨天'];
            s[0] = a;
            data.orderSummary.map(function (item, index) {
                var as=[];
                as[0] = item.productName;
                as[1] = item.yeterdayOrderNum;
                as[2] = item.terdayOrderNum;
               s[index+1]=as;
            });
            var option = {
                legend: {},
                tooltip: {},
                dataset: {
                    source: s
                },
                xAxis: {type: 'category'},
                yAxis: {},
                series: [
                    {type: 'bar'},
                    {type: 'bar'}
                ]
            };
            myChart.setOption(option);
        }
    });
});