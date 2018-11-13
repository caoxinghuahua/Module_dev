package com.hua.module.module_dev;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.JsonObject;
import com.hua.module.basemodule.HttpManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button goto_bbsBt;
    private Button goto_shopBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }
    private void initView(){
        goto_bbsBt= (Button) findViewById(R.id.goto_bbs_bt);
        goto_shopBt= (Button) findViewById(R.id.goto_shop_bt);
    }

    private void initListener(){
        goto_bbsBt.setOnClickListener(this);
        goto_shopBt.setOnClickListener(this);
    }
    private void initData(){
        HttpService httpService=HttpManger.getInstance().create(HttpService.class);
        Call<JsonObject> call=httpService.getPickListTopData();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("xxx","res:"+response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_bbs_bt:
                ARouter.getInstance().build("/bbs/main")
                        .withString("id","121")
                        .withString("name","bbs").navigation();
                break;
            case R.id.goto_shop_bt:
                ARouter.getInstance().build("/shop/main")
                        .withString("id","122")
                        .withString("name","shop").navigation();
                break;
        }
    }
}
