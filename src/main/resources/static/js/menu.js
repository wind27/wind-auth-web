define(function () {
    var menu = {
        /**
         * 新增
         * @param id
         */
        add: function (id) {
            alert(id);
        },

        /**
         * 编辑
         * @param id
         */
        edit: function (id) {
            var url = '/menu/detail/' + id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        var tpl = document.getElementById('tpl_menu_edit').innerHTML;
                        var html = juicer(tpl, result.data);
                        $('.container').html(html);
                    }
                }
            });
        },

        /**
         * 更新
         * @param id
         */
        update: function (id) {
            alert(id);
        },

        /**
         * 详情
         * @param id
         */
        detail: function (id) {
            var url = '/menu/detail/' + id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        var tpl = document.getElementById('tpl_menu_detail').innerHTML;
                        var html = juicer(tpl, result.data);
                        $('.container').html(html);
                    }
                }
            });
        },

        /**
         * 删除
         * @param id
         */
        remove: function (id) {
            alert(id);
        },

        /**
         * 启用
         * @param id
         */
        enable: function (id) {
            var url = '/menu/enable/' + id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        menu.list();
                    }
                }
            });
        },

        /**
         * 停用
         * @param id
         */
        disable: function (id) {
            var url = '/menu/disable/' + id;
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        menu.list();
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
                        var tpl = document.getElementById('tpl_menu_list').innerHTML;
                        var html = juicer(tpl, result.data);
                        $('.container').html(html);
                    }
                }
            });
        }
    }
    return {
        menu: menu
    };
});