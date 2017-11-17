define(function () {
    var menu = {
        add: function (id) {
            alert(id);
        },
        update: function (id) {
            alert(id);
        },
        remove: function (id) {
            alert(id);
        },
        enable: function (id) {
            var url = '/menu/enable?id='+id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        list();
                    }
                }
            });
        },
        disable: function (id) {
            var url = '/menu/disable?id='+id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        list();
                    }
                }
            });
        },

        /**
         * 获取菜单
         */
        list: function () {
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
    }
    return {
        menu : menu
    };
});