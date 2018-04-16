package com.neil.module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.neil.module_lib.net.HttpImg;

public class MainActivity extends AppCompatActivity {

    ImageView iv01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv01 = findViewById(R.id.iv01);
        fun();
    }

    private void fun() {
        String url = "https://upload-images.jianshu.io/upload_images/3067059-22f6fe754c8e3267.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/437";
        HttpImg.display(iv01,url);
    }
}
