package com.example.triviaappmodel.call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL="https://opentdb.com/";

    private CallApi callApi;

    private static RetrofitClient INSTANCE;

    public static RetrofitClient getInstance(){

        if(INSTANCE==null){
            INSTANCE=new RetrofitClient();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            CallApi callApi=retrofit.create(CallApi.class);
            INSTANCE.setCallApi(callApi);

        }
        return INSTANCE;
    }

    public CallApi getCallApi() {
        return callApi;
    }

    public void setCallApi(CallApi callApi) {
        this.callApi = callApi;
    }
}
