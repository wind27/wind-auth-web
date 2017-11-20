define(['juicer'], function () {
    var nav = function () {
        var url = '/navigation';
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    var tpl = document.getElementById('header_tpl').innerHTML;
                    var html = juicer(tpl, result.data);
                    $('.div_header_menus ul').html(html);
                    $('.div_header_user').html('<label style="line-height: 70px;">欢迎' + result.data.name + '</label>');
                    $('.footer').html('<p>Copyright 2017 ' + result.data.name + '</p>');

                }
            }
        });

    }
    return {
        nav: nav
    };
});