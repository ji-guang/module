package com.neil.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.neil.module.data.net.modle.SendCodeModle;
import com.neil.module_lib.net.core.BaseMap;
import com.neil.module_lib.net.core.Observer;
import com.neil.module_lib.util.util.LogUtil;
import com.neil.module_lib.xm.net.XmJsonModle;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView iv01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv01 = findViewById(R.id.iv01);

        findViewById(R.id.bt01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });
    }
    private void fun() {
        String json = "{\"token\":\"\",\"content\":{\"custMbl\":\"13676016799\",\"type\":0,\"appType\":\"android\",\"deviceId\":\"6e09d4008d4a1e83\",\"deviceType\":\"android\",\"deviceToken\":\"6e09d4008d4a1e83\",\"appEdition\":\"5.0.02\",\"downSource\":\"agent\",\"latitude\":\"22.575959\",\"longitude\":\"114.066719\",\"province\":\"广东省\",\"city\":\"深圳市\",\"gpsPosition\":\"中国广东省深圳市福田区梅康路\"}}";

//        HttpJson.request(url, json, new HttpJson.HttpJsonCallback<String>(){
//            @Override
//            public Class<String> getTClass() {
//                return String.class;
//            }
//            @Override
//            public void onback(boolean isSuccess, String aLong, String msg) {
//            }
//        });

        new SendCodeModle().request(new BaseMap<Object>() {
            @Override
            protected void initMap(Map<String, Object> map) {
                map.put(SendCodeModle.Params.custMbl,"13676016799");
                map.put(SendCodeModle.Params.type,0);
            }
        }, new Observer<String>() {
            @Override
            public void update(boolean success, String s, String msg) {
                LogUtil.e(success+"\n"+s+"\n"+msg);
            }
        });
    }

}
