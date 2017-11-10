module.export={
    init : function(){
        var url = '/navigation';
        $.ajax({
            url: url,
            type: "get",
            dataType: "text",
            success: function(result){
                console.log(result);
            }
        });
    }
}

// var a = require('a.js')
// var b = require('b.js')
