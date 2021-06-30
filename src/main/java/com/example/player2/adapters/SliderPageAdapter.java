package com.example.player2.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.player2.R;
import com.example.player2.models.Movie;
import com.example.player2.ui.GlavStranitsa;
import com.example.player2.ui.MovieDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SliderPageAdapter extends PagerAdapter {
    private Context mcontext;
    private CallBack callBack;
    private List<com.example.player2.adapters.Slide> mList;
    public SliderPageAdapter(Context mcontext, List<com.example.player2.adapters.Slide> mList) {
        this.mcontext = mcontext;
        this.mList = mList;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
View SlideLayout=inflater.inflate(R.layout.slide_item,null);
ImageView slideImage= SlideLayout.findViewById(R.id.slide_img);
TextView  slideText=SlideLayout.findViewById(R.id.slide_title);
slideImage.setImageResource(mList.get(position).getImage());
slideText.setText(mList.get(position).getTitle());
SlideLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(position == 0){
            Toast.makeText(mcontext, "Slide 1 clicked", Toast.LENGTH_SHORT).show();

        }
        else if(position ==1 ){
            Toast.makeText(mcontext, "Slide 2 clicked", Toast.LENGTH_SHORT).show();
        }
    }
});
container.addView(SlideLayout);
return SlideLayout;






    }
    public interface CallBack {
        void OnClick(int position);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View)object);

    }
    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }
}
