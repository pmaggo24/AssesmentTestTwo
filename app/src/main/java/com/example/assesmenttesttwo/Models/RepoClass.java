package com.example.assesmenttesttwo.Models;

import android.app.Application;
import android.app.SearchableInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assesmenttesttwo.Retrofit.ApiClient;
import com.example.assesmenttesttwo.Retrofit.ApiService;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoClass {

    private ApiService apiService;

    public RepoClass() {
        apiService = ApiClient.getApiService();
    }

   public LiveData<SearchParent> getMovieList(String apiKey, String s, String type){
       final MutableLiveData<SearchParent> listModelMutableLiveData = new MutableLiveData<>();
       apiService.getmovielist(apiKey,s,type).enqueue(new Callback<SearchParent>() {
           @Override
           public void onResponse(Call<SearchParent> call, Response<SearchParent> response) {
               if (response.body() != null){
                   System.out.println("getting_responsebodydataif " + new Gson().toJson(response));
                   listModelMutableLiveData.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<SearchParent> call, Throwable t) {
               System.out.println("getting_responsebodydataelse " + call.toString());
               listModelMutableLiveData.setValue(null);
           }
       });
       return listModelMutableLiveData;
   }




}
