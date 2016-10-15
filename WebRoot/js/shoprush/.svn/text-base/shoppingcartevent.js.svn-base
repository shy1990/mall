/**
 * Created by barton on 16-3-4.
 */
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

    //sup小组选则
    $('#contents').on("click", ".J_checked_sku", function () {
        var $this = $(this);

        var $checkbox = $this.parents("div.kind-name").siblings("ul.kind-ul").find(":checkbox");

        if (this.checked) {
            $checkbox.prop("checked", true);
        } else {
            $checkbox.prop("checked", false);
        }
    });

    //小计计算
    $('#contents').on("click", ".icon-jian", function () {
        var phoneNum = Number($(this).siblings('span.phone-num').html());

        var $price = $(this).siblings('span.price').html();
        var price = Number($price.substring($price.indexOf("￥") + 1));

        if (phoneNum > 1) {
            if (phoneNum === 2) {
                $(this).removeClass('active');
            }
            $(this).siblings('span.phone-num').html(--phoneNum);
            $(this).siblings('span.subtotal').find('.total').html((Number(phoneNum) * Number(price)).toFixed(2));
        }
        checked();
    });

    $('#contents').on("click", ".icon-add", function () {
        var $price = $(this).siblings('span.price').html();
        var price = Number($price.substring($price.indexOf("￥") + 1));
        var phoneNum = Number($(this).siblings('span.phone-num').html());
        $(this).siblings('span.phone-num').html(++phoneNum);
        $(this).siblings('span.subtotal').find('.total').html((Number(phoneNum) * Number(price)).toFixed(2));
        $(this).siblings('span.icon-jian').addClass('active');
        checked();
    });

    $("#contents").on("click", ".icon-close", function () {
        var $this = $(this);
        var siblings = $this.parents("li").siblings();
        if (siblings.length === 0) {
            $this.parents("div.kind").remove();
        } else {
            $this.parents("li").remove();
        }

        checked();

        // 如果一个商品也没有了,则显示背景
        if ($("#contents").children().length === 0) {
            nothing();
        }
    });

    //对所有的checkbox点击绑定事件
    $('#contents').on("click", ":checkbox", function () {
        checked();
    });

    //全选
    $('#checkAll').on("click", function () {
        if (this.checked) {
            $(":checkbox").prop("checked", true)
        } else {
            $(":checkbox").prop("checked", false)
        }

        checked();
    });

    //加减号数量
    $('.amount-box .J_minus').on("click", function () {
        var amount = Number($('.amount-box input').val());
        if (amount > 1) {
            if (amount == 2) {
                $('.icon-minus-on').removeClass("icon-minus-on").addClass('icon-minus-out');
            }
            $('.amount-box input').val(amount - 1);
        }
        checked();
    });

    $('.amount-box .J_plus').on("click", function () {
        var amount = Number($('.amount-box input').val());
        var $stock = $("#goodStock").html();
        var $canByNum = $("#canByNum").html();
        var totalCount = (parseInt($stock) >= parseInt($canByNum)) ? $canByNum : $stock;

        if (amount < totalCount) {
            $('.amount-box input').val(amount + 1);
            $('.icon-minus-out').removeClass("icon-minus-out").addClass('icon-minus-on');
        }

        checked();
    });

    //购物车浮动动画
    $('.go-cart ,.go-balance ').on("click", function () {
        $(this).toggleClass('active');
        if ($(this).hasClass('active')) {
            $('.cart-show').animate({right: "300px"});
        } else {
            $('.cart-show').animate({right: "0px"});
        }
    });

    // 结算
    $(".tag-goright").parent().on("click", function () {

        if (!$(this).hasClass("disabled")) {
            var checkbox;
            $.each($(":checkbox"), function (index, value) {
                if ($(value).prop("checked") && $(value).attr("id") !== "checkAll") {
                    checkbox = true;
                }
            });

            if (!checkbox) {
                alert("还没有选择商品哦~");
                return;
            }

            toPay();
        }
    });
});

/*
 * checked()
 * 全局遍历goods = [];
 * 计算页面中被选中的checkbox总价格和总数量
 * @athor ChenGuop
 * @date  15/12/11
 *
 */
var goods = [];
function checked() {
    goods = [];
    $('.kind-ul :checked').each(function () {/*将所有选中checkbox遍历*/
        var $subtotal = $(this).parents('.check-lab').siblings('.num-price').find('.subtotal span');//小计
        var subtotal = $subtotal.html().substring($subtotal.html().indexOf("￥") + 1);
        var $num = $(this).parents('.check-lab').siblings('.num-price').find('.phone-num').html();//小计
        goods.push({
            subtotal: subtotal,
            num: $num
        })
    });
    var _totalNum = 0;
    /*商品选中总数量*/
    var _totalPrice = 0;
    /*商品选中总价格*/
    //下面使用each进行遍历
    $.each(goods, function (n, good) {
        _totalPrice = parseFloat(_totalPrice) + parseFloat(good.subtotal);
        _totalNum = parseInt(_totalNum) + parseInt(good.num);
    });
    $('#totalPrice').html(Number(_totalPrice).toFixed(2));
    $('#totalNum').html(_totalNum);
}


/**
 * 结算按钮样式+contents内容图片
 */
function nothing() {
    $('.J_Settlement').addClass('disabled');
    var nothingHtml = '<p class="nothing-text">呜呜，来都来了，不买点东西吗？</p>' +
        '<img class="nothing-img" src="images/shoprush/nothing.png" />';
    $('#contents').html(nothingHtml);
}
function something() {
    $('.J_Settlement').removeClass('disabled');
}

/**
 * 去结算
 */
function toPay() {
    //开始弹窗
    $("#background, #dialog").show();
    conPosition();

    var payD = payData();

    $.ajax({
        url: "order/hd/checkCanBuyNum.html",
        type: "get",
        data: {"hdSkus": JSON.stringify(payD)},
        dataType: "json",
        async: false,
        success: function (data) {
            if (data) {
                if (data.success) {
                    $("#hdSkus").val(JSON.stringify(payD));
                    $("#payForm").submit();
                } else {
                    if (data["obj"]) {
                        var d = data.obj;
                        if (confirm("您购买的" + d.goodsName + ",今日限购总数为" + (Number(d.repayCanNum) + Number(d.yetBuyNum))
                                + "台,已经购买" + d.yetBuyNum + "台,还可以购买" + d.repayCanNum + "台,请点击确定按钮返回购物车.")) {
                            $("#background, #dialog").hide();
                        } else {
                            $("#background, #dialog").hide();
                        }
                    } else {
                        alert(data.msg);
                        $("#background", "#dialog").hide();
                    }
                }
            }
        },
        error: function () {
        }

    });
}

function payData() {

    var data = [];

    var $kind = $(".kind");

    $.each($kind, function (index, value) {

        var $kindName = $(value);

        var $brand = $kindName.find("ul.kind-ul li a label :checkbox");

        var count = 0;

        // 无奈啊 这么复杂的数据结构
        $.each($brand, function (brandIndex, brandValue) {
            if ($(brandValue).prop("checked")) {
                count++;
            }
        });

        if (count !== 0) {
            var goodId = $kindName.find("div.kind-name label span").attr("sc_goodid");

            var obj = {};
            obj["goodsId"] = goodId;

            var skus = [];

            var $kindUL = $kindName.find("ul.kind-ul li");

            $.each($kindUL, function (liIndex, liValue) {
                // 判断其是否在选中状态
                var $ifChecked = $(liValue).find("a label :checkbox").prop("checked");
                if ($ifChecked) {
                    var sku = {};
                    sku["skuId"] = $(liValue).find("a label span").attr("sc_skuid");
                    sku["num"] = $(liValue).find("a div.num-price span.phone-num").html();
                    skus.push(sku);
                }
            });

            obj["skus"] = skus;

            data.push(obj);
        }

    });

    return data;
}