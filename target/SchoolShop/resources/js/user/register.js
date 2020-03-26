$(function () {
    var registerurl = '/SchoolShop/user/registerUser';
   $("#submit").click(function () {
       personInfo ={};
       personInfo.name=$("#userName").val();
       personInfo.email = $("#email").val();
       personInfo.gender = $("#shopCategory").find('option').not(function () {
           return !this.selected;
       }).data('id')
        localAuth ={};
       localAuth.username = $("#loginname").val();
       localAuth.password = $("#loginpassword").val();

       var headimg = $("#headImage")[0].files[0];
       var formData = new FormData();
       formData.append("personInfo" ,JSON.stringify(personInfo));
       formData.append("loaclAuth",JSON.stringify(localAuth));
       formData.append("headimg",headimg);
       $.ajax({
           url: registerurl,
           type: 'POST',
           data: formData,
           contentType: false,
           processData: false,
           async: false,
           cache: false,
           success: function (data) {
               if (data.success) {
                   $.toast("提交成功");
                   window.location.href = '/SchoolShop/user/login';
               } else {
                   $.toast("提交失败" + data.errMsg);
               }
           }
       });

   })

});