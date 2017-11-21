define(function () {

    var loginModel = {
        login : function () {
            var request_url = '/login';
            $.ajax({
                url: request_url,
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        alert("登录成功!!!");
                        window.location.href="/";
                    }
                }
            });
        },

        logout : function () {
            var request_url = '/logout';
            $.ajax({
                url: request_url,
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        alert("退出登录成功!!!");
                        window.location.href="/login";
                    }
                }
            });
        }
    }
    return {
        login: loginModel
    };
});