package com.hua.module.shoplibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/shop/main")
public class ShopActivity extends Activity implements View.OnClickListener{
    private Button gotoBBSBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initListener();
        initData();
    }
    private void initView(){
        gotoBBSBt= (Button) findViewById(R.id.shopToBBS_bt);
    }
    private void initListener(){
        gotoBBSBt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.shopToBBS_bt) {
            ARouter.getInstance().build("/bbs/main").navigation();
        }
    }
    private void initData(){
        String id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");
        Toast.makeText(this,"id:"+id+"name:"+name,Toast.LENGTH_SHORT).show();
    }
}
