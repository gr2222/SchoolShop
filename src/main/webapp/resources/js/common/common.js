function getUrlString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return null;
}

Date.prototype.Format = function(fmt) {
    var o = {
        "M+" : this.getMonth() + 1, // 月份
        "d+" : this.getDate(), // 日
        "h+" : this.getHours(), // 小时
        "m+" : this.getMinutes(), // 分
        "s+" : this.getSeconds(), // 秒
        "q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
        "S" : this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for ( var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function getUserState() {
var url = '/SchoolShop/reception/getloginstate';
    $.getJSON(url, function (data) {

        var html = '';
        if (data.loginState) {
            if (data.userType==1){
                html="<p><img src='"+data.userImg+"' </p><p>"+"积分："+data.integral+"</p><p>"+"呢称："+data.userName+"</p><p><a class='external' href='/SchoolShop/order/orderpage'>订单页面</a></p>";
            }
            if (data.userType==2) {
                html="<p><img src='"+data.userImg+"' </p><p>"+"积分："+data.integral+"</p><p><p>"+"呢称："+data.userName+"</p><p><a class='external' href='/SchoolShop/shopAdmin/shoplist'>管理商店</a></p><p><a class='external' href='/SchoolShop/order/orderpage'>订单页面</a></p>";
            }
        }else {
                html = "<a class='external' href='/SchoolShop/user/login'>登录</a><p><a class='external' href='/SchoolShop/user/register'>注册</a></p>"
        }
        $('#cebianlan').html(html);
    });}