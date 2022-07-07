package com.example.assesmenttesttwo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchableInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assesmenttesttwo.Constants.AppConstant;
import com.example.assesmenttesttwo.Models.MovieListModel;
import com.example.assesmenttesttwo.Models.MovieListViewModel;
import com.example.assesmenttesttwo.Models.Search;
import com.example.assesmenttesttwo.Models.SearchParent;
import com.example.assesmenttesttwo.MovieAdapter;
import com.example.assesmenttesttwo.R;
import com.example.assesmenttesttwo.Retrofit.ApiClient;
import com.example.assesmenttesttwo.Retrofit.ApiService;
import com.example.assesmenttesttwo.SampleDecoration.GridSpacingItemDecoration;
import com.example.assesmenttesttwo.Utils.FontUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private TextView txt_movielist;
    private EditText edit_search;
    private MovieListViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private MovieAdapter movieAdapter;
    private RecyclerView recycler_view_movielist;
    private List<SearchParent.Search> movieListModels = new ArrayList<>();
    private MovieListModel movieListModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_movielist = (TextView)findViewById(R.id.txt_movielist);
        edit_search = (EditText)findViewById(R.id.edit_search);


        recycler_view_movielist = (RecyclerView)findViewById(R.id.recycler_view_movielist);
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        viewModel.getMovieListResponseLiveData().observe(this, new Observer<SearchParent>() {
            @Override
            public void onChanged(SearchParent searchableInfo) {
                System.out.println("getting_datalistllllllll::::::::"+ new Gson().toJson(searchableInfo));
                if (searchableInfo != null) {

                    initRecycler(searchableInfo.getSearch());

                }
            }
        });

        FontUtils.changeFont(MainActivity.this,txt_movielist, AppConstant.FONT_SANS_SEMIBOLD);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter_user(editable.toString());
            }
        });

    }
     private void initRecycler(List<SearchParent.Search> search){
         movieListModels=search;
        System.out.println("getting_datalistll::::::::"+ new Gson().toJson(movieListModels));
         movieAdapter = new MovieAdapter(getApplicationContext(),movieListModels);
   //      recycler_view_movielist.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
         GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
         recycler_view_movielist.setLayoutManager(gridLayoutManager);
         recycler_view_movielist.setAdapter(movieAdapter);
     }

    private void filter_user(String text){
        ArrayList<SearchParent.Search> filteredList = new ArrayList<>();

        for (SearchParent.Search item : movieListModels) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()) || item.getYear().toLowerCase().contains(text.toLowerCase()) || item.getImdbID().toLowerCase().contains(text.toLowerCase()) || item.getType().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        movieAdapter.filterList(filteredList);
    }
}