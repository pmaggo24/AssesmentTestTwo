package com.example.assesmenttesttwo.Models;

import android.app.Application;
import android.app.SearchableInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private RepoClass repoClass;
    private LiveData<SearchParent> movieListModelLiveData;

    public MovieListViewModel(Application application) {
        super(application);
        repoClass = new RepoClass();
        this.movieListModelLiveData = repoClass.getMovieList("b9bd48a6", "Marvel","movie");
    }

    public LiveData<SearchParent> getMovieListResponseLiveData() {
        return movieListModelLiveData;
    }
}
