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
        String url = "http://img4.imgtn.bdimg.com/it/u=992580493,23796831&fm=27&gp=0.jpg";
        HttpImg.display(iv01,url,HttpImg.Options.TRANSFORM_CIRCLE|HttpImg.Options.TRANSFORM_BLUR);
    }
}
