$(function () {
    alert("有默认值，直接点击登录即可");
    var registerurl = '/SchoolShop/user/loginUser';
   $("#submit").click(function () {
       var username = $("#loginname").val();
       var password = $("#loginpassword").val();
       var fromData = new FormData();
       fromData.append("username",username);
       fromData.append("password",password);
       $.ajax({
           url: registerurl,
           type: 'POST',
           data: fromData,
           contentType: false,
           processData: false,
           async: false,
           cache: false,
           success: function (data) {
               if (data.success) {
                   window.location.href = '/SchoolShop/reception/index';
               } else {
                   $.toast(data.errMsg);
               }
           }
       });
   })
});