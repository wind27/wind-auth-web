/**
 * Created by qianchun on 17/11/10.
 */

require.config({
    baseUrl: '/js',
    paths: {
        jquery: 'lib/jquery1.10.2.min',
        juicer: 'lib/juicer',
        header: 'header.js?v=' + Math.random(100),
        menu: 'menu.js?v=' + Math.random(100)
    }
});
var module = '';

require(['jquery', 'juicer'], function ($, tpl) {
    currentJS = $("#current-js");
    var currentModule = currentJS.attr("current-module");
    var initMethod = currentJS.attr("init-method");
    console.log('初始化 :' + currentModule + "(" + initMethod + ')');
    //header
    require(['header'], function (header) {
        header.nav();
    });

    if (currentJS && currentModule && initMethod) {
        // 页面加载完毕后再执行相关业务代码比较稳妥
        $(function () {
                require([currentModule], function (target) {
                        module = target;
                        if ('menu' == currentModule && 'list' == initMethod) {
                            module.menu.list();
                        } else if ('menu' == currentModule && 'add' == initMethod) {
                            module.menu.add(id);
                        } else if ('menu' == currentModule && 'edit' == initMethod) {
                            module.menu.edit(id);
                        } else if ('menu' == currentModule && 'detail' == initMethod) {
                            module.menu.detail(id);
                        }
                    }
                );
            }
        )
    }
});