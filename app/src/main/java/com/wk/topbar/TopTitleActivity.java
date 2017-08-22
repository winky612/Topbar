package com.wk.topbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by wk on 2017/1/13.
 */

public class TopTitleActivity extends AppCompatActivity {

    private Topbar topbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toptitle);
//        setContentView();
        //首先，定义atts文件，配置所需要的属性
        //然后，重写控件
        //最后，使用控件

        topbar = (Topbar) findViewById(R.id.tb_custom);
        //把具体的时实现 通过接口的方式传递了进去
        topbar.setOnTopbarClickLisener(new Topbar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopTitleActivity.this, "You click BACK", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void rightClick() {
                Toast.makeText(TopTitleActivity.this, "You click More", Toast.LENGTH_SHORT).show();


            }
        });
//        topbar.setLeftisVisible(false);

    }
}
