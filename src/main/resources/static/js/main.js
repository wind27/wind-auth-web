/**
 * Created by qianchun on 17/11/10.
 */

require.config({
    baseUrl : '/js',
    paths: {
        jquery: 'lib/jquery1.10.2.min',
        juicer: 'lib/juicer',
        header: 'header.js?v='+Math.random(100),
        menu:'menu.js?v='+Math.random(100)
    }
});

require(['jquery', 'header', 'menu'], function (jquery, header, menu) {
    header.init();
    menu.init();
});