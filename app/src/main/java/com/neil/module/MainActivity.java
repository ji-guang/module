package com.neil.module;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.neil.module_lib.config.ModuleConfig;
import com.neil.module_lib.net.HttpFile;
import com.neil.module_lib.net.HttpImg;
import com.neil.module_lib.util.name.UUIDUtil;
import com.neil.module_lib.xm.net.XmFile;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ImageView iv01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv01 = findViewById(R.id.iv01);
        ModuleConfig.initConfig(getApplicationContext());

        findViewById(R.id.bt01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun();
            }
        });
    }
    private void fun() {
        

    }
}
