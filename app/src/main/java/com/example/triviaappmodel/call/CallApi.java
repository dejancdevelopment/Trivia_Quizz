package com.example.triviaappmodel.call;
import com.example.triviaappmodel.Model.ResponseCall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallApi {

    @GET("api.php?")
    Call<ResponseCall> getQuestions (@Query("amount") String amouthOfQuestions,
                                     @Query("category") String category,
                                     @Query("difficulty") String difficulty,
                                     @Query("multiple") String multiple);
}
