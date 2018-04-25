package com.neil.module.data.net.modle;

import com.neil.module_lib.xm.net.XmJsonModle;

/**
 * Created by chen on 2018/4/25.
 * <p>
 * 短信验证码接口定义!!
 */
public class SendCodeModle extends XmJsonModle<String> {
    @Override
    protected String url() {
        return "http://test.creditmate.cn/cm-app-consume/line/cust/sendValidCode";
    }

    //    public SendCodeModle(String token){
//        super(token);
//    }
    public interface Params {
        String custMbl = "custMbl"; //手机号
        String type = "type";       // 0:登录验证码；1：修改交易密码验证码
    }


    @Override
    protected Class<String> getOclass() {
        return String.class;
    }
}
