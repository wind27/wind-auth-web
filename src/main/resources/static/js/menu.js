define(['juicer'], function () {

    var init = function () {
        return getMens();
    };

    /**
     * 获取菜单
     */
    var getMens = function () {
        var url = '/menu/list';
        $.ajax({
            url: url,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {
                    var tpl = document.getElementById('tpl_list_menu').innerHTML;
                    var html = juicer(tpl, result.data);
                    $('.container').html(html);
                }
            }
        });
    }
    return {
        init: init
    };
});