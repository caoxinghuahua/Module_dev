package com.hua.module.bbs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path="/bbs/main")
public class BBSActivity extends Activity implements View.OnClickListener{
    private Button gotoShopBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);
        initView();
        initListener();
        initData();
    }
    private void initView(){
        gotoShopBt= (Button) findViewById(R.id.bbsToShop_bt);
    }

    private void initListener(){
        gotoShopBt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.bbsToShop_bt){
            ARouter.getInstance().build("/shop/main").navigation();
        }
    }
    private void initData(){
        String id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");
        Toast.makeText(this,"id:"+id+"name:"+name,Toast.LENGTH_SHORT).show();
    }
}
