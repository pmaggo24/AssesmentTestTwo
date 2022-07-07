package com.example.assesmenttesttwo.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieListModel implements Serializable {

    public MovieListModel() {
    }

    public List<Search> search;
    public String totalResults;
    public String response;

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
