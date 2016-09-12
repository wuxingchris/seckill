var seckill = {
    //封装秒杀ajax的url
    URL : {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
            execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    handleSeckillkill: function (seckillId, node) {
        //处理秒杀逻辑
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //在回调函数中,执行交互流程
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //开始秒杀
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log('killUrl='+killUrl);
                    //绑定按钮事件
                    $('#killBtn').one('click', function () {
                        //禁用按钮
                        $(this).addClass('disabled');
                        //发送请求
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var status = killResult['status'];
                                var statusInfo = killResult['statusInfo'];
                                //显示秒杀结果
                                node.html('<span class="label label-success">' + statusInfo + '</span>')
                            }
                        });
                    });
                    node.show();
                } else {
                    //未开始秒杀
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countdown(seckillId, now, start, end);
                }

            } else {
                console('result=' + result);
            }
        });
    },
    countdown:function(seckillId, nowTime, startTime, endTime){
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('秒杀结束');
        }else if(nowTime < startTime){
            seckillBox.html('秒杀未开始');
            //计时
            var killTime = new Date(startTime + 1000);

            seckillBox.countdown(killTime, function(event){
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //时间完成后回调事件
            }).on('finish.countdown', function () {
                //获取秒杀地址, 执行秒杀
                seckill.handleSeckillkill(seckillId, seckillBox);
            });
        }else{
            //秒杀开始
            seckill.handleSeckillkill(seckillId, seckillBox);
        }
    },

    validatePhone : function(phone){
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },

    //详情页秒杀逻辑
    detail:{
        init:function(param){
            //用户手机验证和登录, 计时交互
            var killPhone = $.cookie('killPhone');
            var startTime = param['startTime'];
            var endTime = param['endTime'];
            var seckillId = param['seckillId'];
            if(!seckill.validatePhone(killPhone)){
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,   //显示弹出层
                    backdrop:'static',  //禁止位置关闭
                    keyboard:false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function(){
                   var inputPhone = $('#killPhoneKey').val();
                    if(seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<lable class="label label-danger">手机号错误</lable>').show(300);
                    }
                });
            }
            //已经登录
            $.get(seckill.URL.now(), {}, function(result){
                if(result && result['success']){
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                }else{
                    console.log('result=' + result);
                }
            });
        }
    }

}