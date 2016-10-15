/** 商品根据配置筛选价格插件**/
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

    var _params = [];
    var _args = {};
    var _goodInfo;
    var _currentColumnIndex;
    var _version = new Array();
    var _color = {};
    var timerIndex;
    var _colorMap = {
        "#000000": "icon-color-black",
        "#FFFFFF": "icon-color-white",
        "#F2F2F2": "icon-color-silver",
        "#F4FA58": "icon-color-little-yellow-people",
        "#848484": "icon-color-iron-tower",
        "#F6CEF5": "icon-color-rose",
        "#F5A9F2": "icon-color-red-rose-princess",
        "#2E2E2E": "icon-color-yahei",
        "#E6E6E6": "icon-color-classical-white",
        "#FAAC58": "icon-color-orange-grey",
        "#F6CECE": "icon-color-orange-white",
        "#A9F5F2": "icon-color-blue-white",
        "#BDBDBD": "icon-color-bo-yayin",
        "#FBFBEF": "icon-color-fresh-white",
        "#8A4B08": "icon-color-yao-shijin",
        "#190707": "icon-color-gao-guanghei",
        "#04B404": "icon-color-gao-guanglv",
        "#FF00FF": "icon-color-high-guangmei-red",
        "#58FAF4": "icon-color-hight-light-blue",
        "#6607ee": "icon-color-violet",
        "#ff9900": "icon-color-gold",
        "#424242":"icon-color-yin-hei",
        "#F5EFFB":"icon-color-yin-bai",
    };

    var _minPrice = 0, _maxPrice = 0;

    $.fn.extend({
        // 添加图片
        addPic: function () {
            var _this = this;
            var gs = _params[_currentColumnIndex]["versions"];
            $.each(gs, function (i, v) {
                if (i == 0) {
                    for (var key in v) {
                        _this.attr("src", v[key].pic);
                    }
                }
            });
        },
        // 添加栏目分类名称
        addColumn: function () {

            var _this = this;

            var html = "";
            var count = 0;

            // 列名显示活动开始时间及当前活动状态
            // $.each(_params, function (index, value) {
            //     var spuV = value["spu"];
            //     if (count == 0) {
            //         html += "<li class='active'><a href='javascript:;' goodId='" + spuV.goodId + "' column='" + index + "' name='" + spuV.name + "'>" + dateProcess(spuV.startTime, spuV.endTime, spuV.brandName) + "</a></li>";
            //
            //         _currentColumnIndex = index;
            //
            //         // 切换详情页
            //         $("#goodDetail").html(spuV["goodDetail"]);
            //     } else {
            //         html += "<li><a href='javascript:;' goodId='" + spuV.goodId + "' column='" + index + "' name='" + spuV.name + "'>" + dateProcess(spuV.startTime, spuV.endTime, spuV.brandName) + "</a></li>";
            //     }
            //     count++;
            // });

            // 列名显示商品名称
            $.each(_params, function (index, value) {
                var spuV = value["spu"];
                if (count == 0) {
                    html += "<li class='active'><a href='javascript:;' goodId='" + spuV.goodId + "' column='" + index + "' name='" + spuV.name + "'>" + spuV.name + "</a></li>";

                    _currentColumnIndex = index;

                    // 切换详情页
                    $("#goodDetail").html(spuV["goodDetail"]);
                } else {
                    html += "<li><a href='javascript:;' goodId='" + spuV.goodId + "' column='" + index + "' name='" + spuV.name + "'>" + spuV.name + "</a></li>";
                }
                count++;
            });

            _this.append(html);

            $.columnEvent(_this);

        },
        // 添加spu名称
        addName: function () {
            var _this = this;

            _goodInfo = _params[_currentColumnIndex];

            var spu = _goodInfo["spu"];
            var name = spu["name"];//+ " " + spu["screenSize"] + "英寸 " + spu["resolution"];

            return _this.append(name);
        },
        // 添加版本
        addVersion: function () {

            var _this = this;

            // 获得以version为分组的商品信息
            var versions = _params[_currentColumnIndex]["versions"];

            // 取得版本信息并去重，之后显示
            $.each(versions, function (name, value) {
                for (var key in value) {
                    if (_version.length === 0 || _version.indexOf(key) === -1) {
                        _version.push(key);
                    }
                }
            });

            // 显示版本信息
            _this.append(doWithVersion());

            // 给版本追加点击事件
            $.versionEvent(_this);

        },
        // 添加价格
        addPrice: function () {

            var _this = this;

            // 获得以version为分组的商品信息
            var versions = _params[_currentColumnIndex]["versions"];

            var count = 0;

            // 比较价格 确定最小价格和最大价格
            $.each(versions, function (index, value) {
                $.each(value, function (name, v) {
                    if (count == 0) {
                        _minPrice = v.price;
                        _maxPrice = v.price;
                    } else {
                        if (v.price <= _minPrice) {
                            _minPrice = v.price;
                        }
                        if (v.price >= _maxPrice) {
                            _maxPrice = v.price;
                        }
                    }

                    count++;
                });
            });

            _this.append(doWithPrice());
        },
        addColor: function () {

            var _this = this;

            // 获得以version为分组的商品信息
            var versions = _params[_currentColumnIndex]["versions"];

            // 遍历sku信息，取得所有颜色和其对应的十六进制表示
            $.each(versions, function (index, value) {
                $.each(value, function (name, v) {

                    var colorName = v.colorName;
                    var colorRgb = v.colorRgb;

                    // 颜色去重
                    if (!_color.hasOwnProperty(colorName)) {
                        _color[colorName] = _colorMap[colorRgb];
                    }
                });

            });

            // 显示颜色信息
            _this.append(doWithColor());

            // 给颜色追加点击事件
            $.colorEvent(_this);
        },
        addStock: function () {
            var _this = this;
            // 获得以version为分组的商品信息
            var versions = _params[_currentColumnIndex]["versions"];

            var stock = 0;
            // 遍历sku信息，将库存相加
            $.each(versions, function (index, value) {
                $.each(value, function (name, v) {
                    stock += v.stock;
                });

            });

            _this.html(stock);
        }
    });

    $.extend({
        columnEvent: function (obj) {
            // 取得所有column
            var lis = obj.find("li");
            $.each(lis.find("a"), function (index, value) {

                var $value = $(value);
                $value.on("click", function () {

                   $(".btn-cart").hide();

                    $('.amount-box input').val("1");

                    $value.parents('li').addClass('active');
                    $value.parents('li').siblings('li').removeClass('active');

                    _currentColumnIndex = $("#" + _args["column"]).find("ul li[class=active] a").attr("column");
 
                    // 切换详情页
                    $("#goodDetail").html(_params[_currentColumnIndex]["spu"]["goodDetail"]);


                    clearInterval(timerIndex);

                    htmlGen();
                });
            });
        },
        versionEvent: function (obj) {
            // 取得所有version
            var lis = obj.find("li");

            // 给每一个version都添加一个点击事件
            $.each(lis, function (index, value) {

                var $value = $(value);

                // 单个version的点击事件
                $value.on("click", function () {

                    // 如果没有class d 则添加选中样式
                    if ($value.find(".d").length === 0) {

                        var $color = $("#" + _args["color"]).find("li a");

                        // 如果点击的时候已经有选中样式了，则取消,并且将颜色全部可点
                        if ($value.hasClass("active")) {
                            $value.removeClass("active");

                            // 获得所有颜色列表，如果颜色符合当前版本的颜色 则可点，否则不可点
                            $.each($color, function (i1, v1) {
                                    var $v1 = $(v1);
                                    if ($v1.hasClass("d")) {
                                        $v1.removeClass("d").addClass("c");
                                    }
                                }
                            );
                            $("#" + _args["price"]).empty();

                            $("#" + _args["price"]).addPrice();

                            var stock = 0;

                            // 获得以version为分组的商品信息
                            var versions = _params[_currentColumnIndex]["versions"];

                            // 遍历sku信息，将库存相加
                            $.each(versions, function (index, value) {
                                $.each(value, function (name, v) {
                                    stock += v.stock;
                                });

                            });

                            $("#" + _args["stock"]).html(stock);
                        } else {
                            // 追加active样式
                            $value.addClass("active");
                            $value.siblings('li').removeClass('active');

                            // 获得version的内容
                            var content = $value.find("a").html();

                            // 获得以version为分组的商品信息
                            var versions = _params[_currentColumnIndex].versions;

                            // 清空_color
                            _color = {};

                            // 遍历sku信息，取得所有颜色和其对应的十六进制表示
                            $.each(versions, function (i, v) {
                                for (var key in v) {
                                    if (content === key) {
                                        var colorName = v[content]["colorName"];
                                        var colorRgb = v[content]["colorRgb"];

                                        // 颜色去重
                                        if (!_color.hasOwnProperty(colorName)) {
                                            _color[colorName] = _colorMap[colorRgb];
                                        }
                                    }
                                }
                            });

                            // 获得所有颜色列表，如果颜色符合当前版本的颜色 则可点，否则不可点
                            $.each($color, function (i1, v1) {
                                    var $colors = $(v1).find("span:eq(1)");
                                    var $colorName = $colors.html();

                                    if (!_color.hasOwnProperty($colorName)) {
                                        $(v1).removeClass("c").addClass("d");
                                    } else {
                                        $(v1).removeClass("d").addClass("c");
                                    }
                                }
                            );

                            // 版本选中后看颜色是否有被选中的，如果有，则将价格重置
                            // 并且取得sku的ID
                            var $checkedColor = $("#" + _args["color"]).find("li.active");

                            var $checkedColorName = $checkedColor.find(" a span:eq(1)").html();

                            var stock = 0;

                            var skuId;

                            if ($checkedColor.length == 1) {
                                $.each(versions, function (index, value) {
                                    for (var key in value) {
                                        var sss = value[key];
                                        if (key === content && sss.colorName === $checkedColorName) {
                                            _minPrice = sss.price;
                                            _maxPrice = sss.price;
                                            stock = sss.stock;
                                            skuId = sss.skuId;
                                        }
                                    }

                                });

                                $("#" + _args["price"]).empty();
                                $("#" + _args["price"]).append(doWithPrice());

                                if (stock > 0) {
                                    $("#" + _args["stock"]).html(stock);
                                }

                                // 处理skuid
                                if (skuId) {
                                    $("#skuId").val(skuId);
                                } else {
                                    $("#skuId").val("");
                                }
                            }
                        }
                    }
                });
            });
        },
        colorEvent: function (obj) {
            // 取得所有颜色
            var lis = obj.find("li");

            // 给每一种颜色添加一个点击事件
            $.each(lis, function (index, value) {

                var $value = $(value);

                // 颜色的点击事件
                $value.on("click", function () {

                    // 如果没有class d 则添加选中样式
                    if ($value.find(".d").length === 0) {

                        var $version = $("#" + _args["version"]).find("li a");

                        // 如果点击的时候已经有选中样式了，则取消,并且将版本信息全部可点
                        if ($value.hasClass("active")) {

                            $value.removeClass('active');

                            $.each($version, function (n, v) {
                                var $v = $(v);
                                if ($v.hasClass("d")) {
                                    $v.removeClass("d").addClass("c");
                                }

                                $("#" + _args["price"]).empty();

                                $("#" + _args["price"]).addPrice();

                                var stock = 0;

                                // 获得以version为分组的商品信息
                                var versions = _params[_currentColumnIndex]["versions"];

                                // 遍历sku信息，将库存相加
                                $.each(versions, function (index, value) {
                                    $.each(value, function (name, v) {
                                        stock += v.stock;
                                    });

                                });

                                $("#" + _args["stock"]).html(stock);
                            });
                            // 如果点击的时候没有选中样式，则追加，并且筛选版本信息
                        } else {
                            // 追加active样式
                            $value.addClass("active");
                            $value.siblings('li').removeClass('active');

                            // 获得color的内容
                            var content = $value.find("a span:eq(1)").html();

                            // 获得以version为分组的商品信息
                            var versions = _params[_currentColumnIndex].versions;

                            // 清空_version
                            _version.length = 0;

                            // 以选中的color为条件取得版本信息并去重
                            $.each(versions, function (name, v) {
                                for (var key in v) {
                                    if (v[key].colorName === content && _version.indexOf(key) == -1) {
                                        _version.push(key);
                                    }
                                }
                            });

                            $.each($version, function (n, v) {
                                var $versionName = $(v).html();
                                if (_version.indexOf($versionName) == -1) {
                                    $(v).removeClass("c").addClass("d");
                                } else {
                                    $(v).removeClass("d").addClass("c");
                                }
                            });

                            // 选中颜色后,判断版本信息是否被选中,如果被选中,则重置价格信息
                            // 并且取得sku的ID
                            var $checkedVersion = $("#" + _args["version"] + " li.active");

                            var $checkedVersionName = $checkedVersion.find("a").html();

                            var stock = 0;

                            var skuId;

                            $.each(versions, function (index, value) {
                                for (var key in value) {
                                    var sss = value[key];
                                    if (key === $checkedVersionName && sss.colorName === content) {
                                        _minPrice = sss.price;
                                        _maxPrice = sss.price;
                                        stock = sss.stock;
                                        skuId = sss.skuId;
                                    }
                                }
                            });

                            $("#" + _args["price"]).empty();
                            $("#" + _args["price"]).append(doWithPrice());

                            if (stock > 0) {
                                $("#" + _args["stock"]).html(stock);
                            }

                            // 处理skuid
                            if (skuId) {
                                $("#skuId").val(skuId);
                            } else {
                                $("#skuId").val("");
                            }
                        }
                    }
                });

            });
        },
        init: function (params, args) {

            if (!argsCheck(args)) {
                throw "args error!";
            }

            //$(".btn-cart").hide();

            _args = args;

            // 处理数据
            data(params);

            var $column = $("#" + _args["column"]);
            $column.find("ul").addColumn();

            _currentColumnIndex = $column.find("ul li[class=active] a").attr("column");

            // 生成html
            htmlGen();
        }

    });

    /**
     * 将原始数据进行处理
     * @param params
     */
    function data(params) {
        // 遍历商品信息，以spu.name 作为key进行分组
        $.each(params["obj"], function (name, value) {
            var spu = {};

            var tmpSku = value.goodsSkus;

            spu["screenSize"] = value.screenSize; // 屏幕尺寸
            spu["resolution"] = value.resolution; // 屏幕分辨率
            spu["ram"] = value.ram; // 运行内存
            spu["name"] = value.name; // 名称
            spu["canBuyNum"] = value.canBuyNum; // 限购数量
            spu["goodId"] = value.id; // 商品id
            spu["goodDetail"] = value.goodsDetail; // 商品详情介绍
            spu["startTime"] = value.shoppingRushStartTime; // 活动开始时间
            spu["endTime"] = value.shoppingRushEndTime; // 活动结束时间
            spu["sysTime"] = params.msg; // 系统时间(么用了吧。)
            spu["brandName"] = value.brand.name || ""; // 品牌名字

            // 以version为key分组，添加version，颜色，价格的对应关系
            var __version = new Array();

            $.each(tmpSku, function (n, v) {
                // 取得版本
                var version = v.edition + v.standard + " " + value.ram + "内存" + "+" + v.storage + "容量";

                // 取得该sku的颜色和颜色的十六进制表示
                var skuTmp = {};
                skuTmp["colorName"] = v.color.colorName;
                skuTmp["colorRgb"] = v.color.colorRgb;
                // 取得该sku的价格
                skuTmp["price"] = v.price;
                // 取得sku的图片,多个默认第一张
                $.each(v.goodsPics, function (i, vl) {
                    if (i == 0) {
                        skuTmp["pic"] = vl.picSrc;
                    }
                });
                // 取得sku的库存
                skuTmp["stock"] = v.stock;
                // 取得sku的ID
                skuTmp["skuId"] = v.id;

                var versionAndSku = {};

                versionAndSku[version] = skuTmp;

                __version.push(versionAndSku);
            });

            _params.push({"spu": spu, "versions": __version});

        });
    }

    /**
     * 生成html
     */
    function htmlGen() {

        var $pic = $("#" + _args["pic"]);
        $pic.addPic();

        var $price = $("#" + _args["price"]);
        $price.empty();
        $price.addPrice();

        var $name = $("#" + _args["name"]);
        $name.empty();
        $name.addName();

        _version.length = 0;

        var $version = $("#" + _args["version"]);
        $version.empty();
        $version.addVersion();

        _color = {};

        var $color = $("#" + _args["color"]);
        $color.empty();
        $color.addColor();

        var version = _params[_currentColumnIndex];

        // 限购
        var canByNum = version.spu.canBuyNum;

        // 添加限购数量
        $("#canByNum").html(canByNum);

        // 添加库存
        var $stock = $("#" + _args["stock"]);
        $stock.addStock();

        // 初始化倒计时
        if (_args["initTimeCountDown"] === "true") {
            timerIndex = $("#timer-count-down").sanjiTimerCountDown(new Date(version.spu.startTime.replace(/-/g, '/')).getTime(), {
                success: function () {
                    var $a = $("#" + _args["column"]).find("ul li.active a");
                    var $aV = $a.html();

                    $a.html($aV.substring(0, $aV.lastIndexOf('>') + 1) + "进行中");

                    $(".btn-cart").show();

                }
            });
        }
        scanLog();
    }

    //添加浏览量
    function  scanLog(){
    	var s = GetQueryString("s");
    	var id = $("#goodColumn ul li.active a").attr("goodid");
    	$.get("goods/scanLog.html?s="+s+",cut&id="+id);
    }

    //获取url地址栏参数
    function GetQueryString(name){
         var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
         var r = window.location.search.substr(1).match(reg);
         if(r!=null)return  unescape(r[2]); return null;
    }
    
    /**
     * 检查args是否全面
     * @param args
     * @returns {boolean}
     */
    function argsCheck(args) {

        if (args.hasOwnProperty("column") && args.hasOwnProperty("name") && args.hasOwnProperty("color")
            && args.hasOwnProperty("price") && args.hasOwnProperty("version") && args.hasOwnProperty("stock")) {
            return true;
        }

        return false;
    }

    /**
     * 添加颜色
     * @param obj
     */
    function doWithColor() {
        var html = "";
        $.each(_color, function (name, value) {
            html += "<li><a class='c' href='javascript:;'><span class='icon " + value + " radio'></span><span>" + name + "</span></a></li>";
        });
        return html;
    }

    /**
     * 添加版本
     */
    function doWithVersion() {
        var html = "";

        // 取得版本信息并去重，之后显示
        $.each(_version, function (index, v) {
            html += "<li><a class='c' href='javascript:;'>" + v + "</a></li>";
        });

        return html;
    }

    /**
     * 添加价格
     */
    function doWithPrice() {
        var html = "";

        if (_minPrice === _maxPrice) {
            html = "￥" + _minPrice;
        } else {
            html = "￥" + _minPrice + "~" + _maxPrice;
        }

        return html;
    }

    function dateProcess(start, end, brandName) {
        var today = getTodayDate();
        var beginDate = start.substring(0, 10);
        var beginTime = start.substring(11, 16);
        var res = "";

        if (today === beginDate) {

            switch (timeDeference(start, end)) {
                case -1:
                    res = "今日" + beginTime + "<br/>" + brandName + " 已结束";
                    break;
                case 0:
                    res = "今日" + beginTime + "<br/>" + brandName + " 进行中";
                    break;
                case 1:
                    res = "今日" + beginTime + "<br/>" + brandName + " 即将开抢";
                    break;
                default:
                    res = "今日" + beginTime + "<br/>" + brandName + " 已结束";
            }
        } else if (dateDeference(today, beginDate) == 1) {
            res = "明日" + beginTime + "<br/>" + brandName + " 即将开抢";
        } else {
            res = beginDate + " " + beginTime + "<br/>" + brandName + " 即将开抢";
        }

        function dateDeference(date1, date2) {
            var d1 = date1.substring(8);
            var d2 = date2.substring(8);
            return Number(d2) - Number(d1);
        }

        /**
         * -1:已结束
         * 0:进行中
         * 1:即将开抢
         * @param time1
         * @param time2
         * @returns {boolean}
         */
        function timeDeference(time1, time2) {
            var t1 = stringToDate(time1.replace(/-/g, '/')).getTime();
            var t2 = stringToDate(time2.replace(/-/g, '/')).getTime();
            var now = new Date().getTime();

            // 已结束
            if (t2 - now < 0) {
                return -1;
            }

            // 进行中
            if (t1 - now <= 0 && t2 - now >= 0) {
                return 0;
            }

            // 即将开抢
            if (t1 - now > 0) {
                return 1;
            }

        }

        return res;
    }
})(jQuery);