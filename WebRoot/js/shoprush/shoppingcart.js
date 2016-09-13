/**
 * Created by barton on 16-3-3.
 */
var _params = {};
var _column = new Array();
(function ($) {
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

    $.extend({
        addToShoppingCart: function (params) {
            checked();

            // 切换背景小图标
            var $childrenImg = $("#contents").children("img");

            if ($childrenImg.length !== 0) {
                $childrenImg.siblings("p").remove();
                $childrenImg.remove();
            }

            _params = params;

            var $kind = $(".kind");

            var choice = false;

            $.each($kind, function (index, value) {
                var $existColumnHtml = $(value).find(".J_checked_sku").next().html();

                if (_params.column === $existColumnHtml) {
                    choice = true;
                    return;
                }
            });

            if (_column.indexOf(_params.column) === -1 || !choice) {
                _column.push(_params.column);

                var html = "<div class='kind'>";
                html += "<div class='kind-name kind-ellipsis'>"
                html += "<label>";
                html += "<input class='J_checked_sku' type='checkbox' /><span sc_goodid='" + _params.goodId + "'>" + _params.column + "</span>";
                html += "</label>";
                html += "<span class='max-num '>(今日限购<span class='J_num'>" + _params['canByNum'] + "</span>台)</span>";
                html += "</div>";
                html += "<ul class='kind-ul' name='" + _params.column + "'>";

                html += addSku();

                html += "</ul>";
                html += "</div>";

                $("#contents").append(html);
            } else {

                // 如果单品已经存在,则只修改数量和价格
                var name = _params.name + " " + _params.color;

                var $names = $(".check-lab span");

                var names = new Array();

                $.each($names, function (index, value) {
                    names.push($(value).html());
                });

                if (names.indexOf(name) === -1) {
                    $("[name='" + _params.column + "']").append(addSku());
                } else {
                    $.each($names, function (index, value) {
                        var $value = $(value);
                        if (name === $value.html()) {
                            var $priceDiv = $value.parent().siblings("div.num-price");
                            var $phoneNum = $priceDiv.find("span.phone-num");
                            var phoneNum = Number($phoneNum.html());
                            var $stock = $value.parents("ul.kind-ul").siblings("div.kind-name").find("span.max-num span").html();

                            var nums = phoneNum + _params["count"];
                            if (nums <= $stock) {
                                $phoneNum.html(nums);
                            } else {
                                $phoneNum.html($stock);
                            }

                            var $price = $priceDiv.find("span.total");
                            var $p = $priceDiv.find("span.price");
                            var p = Number($p.html().substring($p.html().indexOf("￥") + 1));

                            $price.html("￥" + p * Number($phoneNum.html()));
                        }
                    });
                }

            }

            something();
        }

    });

    function addSku() {
        var html = "";
        html += "<li>";
        html += "<a>";
        html += "<span class='pull-right'><i class='icon icon-close lin-hover'></i></span>";
        html += "<label class='check-lab kind-ellipsis'>";
        html += "<input type='checkbox'/><span sc_skuid='" + _params.skuId + "' title='" + _params.name + " " + _params.color + "'>" + _params.name + " " + _params.color + "</span></label>";
        html += "<div class='num-price'>";
        html += "<span class='icon lin-hover' disabled=''></span>";
        html += "<span class='phone-num'>" + _params.count + "</span>";
        html += "<span class='icon lin-hover'></span>";
        html += "<span class='price'>" + _params.price + "</span>";
        html += "<span class='pull-right subtotal'><span class='total'>" + "￥" + (Number(_params.count) * Number(_params.price.substring(_params.price.lastIndexOf("￥") + 1))) + "</span></span>";
        html += "</div>";
        html += "</a>";
        html += "</li>";
        return html;
    }
})(jQuery);