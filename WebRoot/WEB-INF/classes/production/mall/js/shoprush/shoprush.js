$(function () {
    if (!Array.indexOf) {
        Array.prototype.indexOf = function (obj) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                    return i;
                }
            }
            return -1;
        }
    }
    $.ajax({
        url: "goods/shoprush.html",
        type: "get",
        beforeSend: function (request) {
            request.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        },
        success: function (data) {
            if (null != data) {
                loadData($.parseJSON(data));
                //scanLog();
            } else {
                alert("貌似没有商品!");
            }
        },
        error: function (data) {
            alert("系统出错了!去打死那群程序员吧!");
        }
    });

    $("#pay_success").click(function () {
        location.href = "http://www.3j1688.com/myCenter/index.html";
    });
    $("#pay_error").click(function () {
        location.href = "http://www.3j1688.com/myCenter/index.html";
    });
});

function isNull(str) {
    return (null === str || "null" === str || undefined === str || "undefined" === str);
}

function loadData(goodsInfo) {
    var args = {
        "version": "goodVersion",
        "column": "goodColumn",
        "name": "goodName",
        "color": "goodColor",
        "price": "goodPrice",
        "pic": "goodPic",
        "stock": "goodStock",
        "startTime": "shoppingRushStartTime",
        "endTime": "shoppingRushEndTime",
        "initTimeCountDown": "false"
    };

    $.init(goodsInfo, args);

    $(".btn-cart").on("click", function () {
        var $color = $("#goodColor li.active a span:eq(1)").html();
        var $version = $("#goodVersion li.active a").html();

        if (isNull($color) || isNull($version)) {
            alert("颜色或者版本为空,请选择!");
            return;
        }

        var $stock = $("#goodStock").html();

        if (Number($stock) <= 0) {
            alert("您下手晚啦!此版本加颜色组合的商品已经卖完啦!");
            return;
        }

        var params = {
            "column": $("#goodColumn ul li.active a").attr("name"),
            "name": $("#goodVersion li.active a").html(),
            "color": $color,
            "count": Number($('.amount-box input').val()),
            "canByNum": $("#canByNum").html(),
            "price": $("#goodPrice").html(),
            "version": $version,
            "goodId": $("#goodColumn ul li.active a").attr("goodId"),
            "skuId": $("#skuId").val(),
        };

        $.addToShoppingCart(params);
    });
}


