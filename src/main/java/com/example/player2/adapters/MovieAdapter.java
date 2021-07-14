package com.example.player2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.player2.R;
import com.example.player2.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {
    final Context context;
    final List<Movie> mData;
    List<Movie> mDataFilter;
    final MovieItemClickList movieItemClickList;

    public MovieAdapter(Context context, List<Movie> mData, MovieItemClickList listener) {
        this.context = context;
        this.mData = mData;
        movieItemClickList = listener;
        this.mDataFilter=mData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        return new MyViewHolder(view);
    }
    

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvTitle.setText(mDataFilter.get(position).getTitle());
        RequestOptions option=new RequestOptions();
        option.centerCrop();
        Glide.with(context).load(mDataFilter.get(position).getThumbnail()).transform(new GranularRoundedCorners(30,30,30,30)).into(holder.ImgMovie);
    }

    @Override
    public int getItemCount() {
        return mDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charcater=constraint.toString();
                if(charcater.isEmpty()){
                    mDataFilter=mData;
                }
                else {
                    List <Movie> filterList=new ArrayList<>();
                    for (Movie movie:mData){
                        if(movie.getTitle().toLowerCase().contains(charcater.toLowerCase())){

                            filterList.add(movie);

                        }
                    }
                    mDataFilter=filterList;
                }
                FilterResults  filterResults = new FilterResults();
                filterResults.values=mDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
mDataFilter =(ArrayList<Movie>) results.values;
notifyDataSetChanged();
            }
        };
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView TvTitle;
        private final ImageView ImgMovie;

        public MyViewHolder(View itemview) {

            super(itemview);
            TvTitle = itemview.findViewById(R.id.item_movie_title);
            ImgMovie = itemview.findViewById(R.id.item_movie_img);

            itemview.setOnClickListener(v -> movieItemClickList.OnMovieClick(mDataFilter.get(getAdapterPosition()), ImgMovie));

        }
    }
}
