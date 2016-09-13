/**
 * Created by barton on 16-3-14.
 */
(function ($) {
    $.fn.extend({
        sanjiTimerCountDown: function (endDate, options) {
            var nowDate;
            $.ajax({
                url: "goods/systime.html",
                type: "get",
                async: false,
                beforeSend: function (request) {
                    request.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
                }
                ,
                success: function (data) {
                    data = $.parseJSON(data);
                    nowDate = data["obj"];
                }
                ,
                error: function () {
                }
            });

            $('#day_show').html("0");
            $('#hour_show').html('<s id="h"></s>00');
            $('#minute_show').html('<s></s>00');
            $('#second_show').html('<s></s>00');

            var secDiff = (endDate - nowDate) / 1000;
            var ops = options || {};
            var callback = ops.success || function () {
                };

            var self = window.setInterval(function () {
                var day = 0,
                    hour = 0,
                    minute = 0,
                    second = 0;//时间默认值
                if (secDiff > 0) {
                    day = Math.floor(secDiff / (60 * 60 * 24));
                    hour = Math.floor(secDiff / (60 * 60)) - (day * 24);
                    minute = Math.floor(secDiff / 60) - (day * 24 * 60) - (hour * 60);
                    second = Math.floor(secDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
                } else if (secDiff <= 0) {
                    if (callback) {
                        callback();
                    }
                    clearInterval(self);
                    return;
                }

                if (minute <= 9) minute = '0' + minute;
                if (second <= 9) second = '0' + second;
                $('#day_show').html(day);
                $('#hour_show').html('<s id="h"></s>' + hour);
                $('#minute_show').html('<s></s>' + minute);
                $('#second_show').html('<s></s>' + second);
                secDiff--;
            }, 1000);

            return self;
        }
    });
})
(jQuery);