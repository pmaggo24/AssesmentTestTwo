package com.example.assesmenttesttwo;

import android.app.SearchableInfo;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assesmenttesttwo.Constants.AppConstant;
import com.example.assesmenttesttwo.Models.MovieListModel;
import com.example.assesmenttesttwo.Models.Search;
import com.example.assesmenttesttwo.Models.SearchParent;
import com.example.assesmenttesttwo.Utils.FontUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyVieHolder> {
    private Context context;
    private List<SearchParent.Search> searches;

    public MovieAdapter(Context context, List<SearchParent.Search> searches) {
        this.context = context;
        this.searches = searches;
    }

    @Override
    public MovieAdapter.MyVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movielayout,parent,false);
        return new MyVieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MyVieHolder holder, int position) {
        final SearchParent.Search search = searches.get(position);

        holder.txt_movie_title.setText(""+search.getTitle());
        holder.txt_movie_year.setText(""+search.getYear());
        holder.txt_movie_imdbID.setText(""+search.getImdbID());
        holder.txt_movie_type.setText(""+search.getType());
        Glide.with(context)
                .load(""+search.getPoster())
                .into(holder.img_source);
    }

    @Override
    public int getItemCount() {
        if (searches != null){
            return searches.size();
        }
          return 0;

    }



    public class MyVieHolder extends RecyclerView.ViewHolder {
        private TextView txt_movie_title,txt_movie_year,txt_movie_imdbID,txt_movie_type;
        private ImageView img_source;
        Search searches;
        public MyVieHolder(View itemView) {
            super(itemView);
            txt_movie_title = (TextView)itemView.findViewById(R.id.txt_movie_title);
            txt_movie_year = (TextView)itemView.findViewById(R.id.txt_movie_year);
            txt_movie_imdbID = (TextView)itemView.findViewById(R.id.txt_movie_imdbID);
            txt_movie_type = (TextView)itemView.findViewById(R.id.txt_movie_type);

            img_source = (ImageView)itemView.findViewById(R.id.img_source);

            FontUtils.changeFont(context,txt_movie_title, AppConstant.FONT_SANS_SEMIBOLD);
            FontUtils.changeFont(context,txt_movie_year,AppConstant.FONT_SANS_SEMIBOLD);
            FontUtils.changeFont(context,txt_movie_imdbID,AppConstant.FONT_SANS_SEMIBOLD);
            FontUtils.changeFont(context,txt_movie_type,AppConstant.FONT_SANS_SEMIBOLD);
        }
        public void setData(final Search searches){
         this.searches = searches;
            txt_movie_title.setText(""+searches.getTitle());
            txt_movie_year.setText(""+searches.getYear());
            txt_movie_imdbID.setText(""+searches.getImdbID());
            txt_movie_type.setText(""+searches.getType());

            Glide.with(context)
                    .load(""+searches.getPoster())
                    .into(img_source);
        }
    }

    public void filterList(ArrayList<SearchParent.Search> filteredList) {
        searches = filteredList;
        notifyDataSetChanged();
    }
}
