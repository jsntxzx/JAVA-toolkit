/**
 * Created by 中希 on 2016/8/16.
 */
// 使用模块化编写
var seckill = {
    URL:{
         now : function () {
             return '/seckill/time/now';
         },
         exposer : function (seckillId) {
            return '/seckill/' + seckillId + '/exposer' ;
         },
         excution : function (seckillId,md5) {
            return '/seckill/'  + seckillId + '/' + md5 + '/excution'
         }
    },
    handleExcution : function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {} , function (result) {
           if(result && result['success']){
                var exposer = result['data'] ;
               if(exposer['flag']){
                    var md5 = exposer['md5'] ;
                    var u = seckill.URL.excution(seckillId, md5);
                    console.log('execution url is ' + u);
                    $('#killBtn').one('click',function () {
                        $(this).addClass('disabled');
                        $.post(u, {} , function (msg) {
                            if(msg && msg['success']){
                                var killResult = msg['data'] ;
                                var state = killResult['state'] ;
                                var stateInfo = killResult['stateDescrition'] ;
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }else {
                                console.log('execution:' + msg);
                            }
                        });
                    });
                   node.show();
               }else{
                   var now = exposer['sysTime'] ;
                   var start = exposer['startTime'] ;
                   var end = exposer['endTime'];
                   seckill.countDown(seckillId,start,end,now);
               }
           }else{
               console.log('result:' + result);
           }
        });

    },
    validatePhone : function(phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    countDown : function (seckillId, startTime, endTime, nowTime) {
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('秒杀结束');
        }else if(nowTime < startTime){
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                console.log(format);
                seckillBox.html(format);
            }).on('finish.countdown',function () {
                seckill.handleExcution(seckillId,seckillBox);
            });
        }else {
            seckill.handleExcution(seckillId,seckillBox);
        }
    },
    detail : {
        init : function(params){
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'] ;
            var endTime = params['endTime'] ;
            var seckillId = params['seckillId'] ;
            if(!seckill.validatePhone(killPhone)){
                $('#killPhone').modal({
                    show:true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#killPhoneBtn').click(function(){
                       var inputPhone = $('#killPhoneKey').val() ;
                       if(seckill.validatePhone(inputPhone)){
                           $.cookie('killPhone', inputPhone,{expires:7,path:'/seckill'});
                           window.location.reload();
                       }
                       else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                       }

                    });
            }
            $.get(seckill.URL.now(), {} , function (result) {
                if(result && result['success']){
                        var nowTime = result['data'];
                        console.log(nowTime + ' ' + startTime + ' ' + endTime);
                        seckill.countDown(seckillId, startTime, endTime, nowTime);
                }else {
                    console.log('result:' + result);
                }
            });
        }
    }
}