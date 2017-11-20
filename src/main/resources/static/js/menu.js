define(function () {
    var menu = {
        /**
         * 新增
         * @param id
         */
        add: function (id) {
            var tpl = document.getElementById('tpl_menu_add').innerHTML;
            $('.container').html(tpl);
        },

        /**
         * 编辑
         * @param id
         */
        edit: function (id) {
            var url = '/menu/edit/' + id;
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

        save: function () {
            var url = '/menu/add/';
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        alert("操作成功!!!");
                    } else {
                        alert("操作失败");
                    }
                }
            });
        },
        /**
         * 更新
         * @param id
         */
        update: function (id) {
            var name = $(':input[name=name]').val();
            var url = $(':input[name=url]')//唯一性校验
            var appId = $('').val();
            var parentId = $(':input[name=parentId]').val();
            var status = $('').val();

            var data = {
                'name' : name,
                'url' : url,
                'appId': appId,
                'parentId' : parentId,
                'status' : status
            }

            var url = '/menu/update/';
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data:{

                },
                success: function (result) {
                    if (result.code == 0) {
                        alert("操作成功!!!");
                    } else {
                        alert("操作失败");
                    }
                }
            });
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