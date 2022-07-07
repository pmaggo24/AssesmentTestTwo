package com.example.assesmenttesttwo.Retrofit;

import android.app.SearchableInfo;

import com.example.assesmenttesttwo.Models.MovieListModel;
import com.example.assesmenttesttwo.Models.Search;
import com.example.assesmenttesttwo.Models.SearchParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(".")
    Call<SearchParent> getmovielist(@Query("apikey") String apikey, @Query("s") String s, @Query("type") String type);
}
