package com.hua.module.module_dev;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author caoxinghua on 2018/7/18
 * @email caoxinghua@gomeplus.com
 */
public interface HttpService {
    @GET("/api/mm/pickListTop")
    Call<JsonObject> getPickListTopData();
}
