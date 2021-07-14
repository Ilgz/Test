package com.example.player2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.player2.R;
import com.example.player2.main_windows.ProfileActivity;
import com.example.player2.ui.MovieDetailActivity;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;

public class SliderPageAdapter extends PagerAdapter {
    private final Context mcontext;
    private final List<com.example.player2.adapters.Slide> mList;
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
        RequestOptions option=new RequestOptions();
        option.optionalCenterCrop();
Glide.with(mcontext).load(mList.get(position).getImage()).apply(option).into(slideImage);
slideText.setText(mList.get(position).getTitle());

        SlideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream is;
                if(position == 0){
                    mcontext.startActivity(new Intent(mcontext,ProfileActivity.class));
                }
































                else if(position ==1 ){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Локи");
                    intent.putExtra("imgURL", "https://gidonline.io//img/dc642ecf5_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/loki/");

                    mcontext.startActivity(intent);

                }
                else if(position==2){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Война будущего");
                    intent.putExtra("imgURL", "https://gidonline.io//img/323b57788_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/vojna-budushhego/");

                    mcontext.startActivity(intent);
                }
                else if(position==3){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Дота:Кровь Дракона");
                    intent.putExtra("imgURL", "https://gidonline.io//img/818ec550c_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/dota-krov-drakona/");

                    mcontext.startActivity(intent);
                } else if(position==4){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Армия мертвецов");
                    intent.putExtra("imgURL", "https://gidonline.io//img/82e5dd33b_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/armiya-mertvecov/");

                    mcontext.startActivity(intent);
                } else if(position==5){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Волшебный дракон");
                    intent.putExtra("imgURL", "https://gidonline.io//img/3b5d6de47_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/volshebnyj-drakon/");

                    mcontext.startActivity(intent);
                } else if(position==6){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Бесконечность");
                    intent.putExtra("imgURL", "https://gidonline.io//img/fbc8e230b_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/beskonechnost-2/");

                    mcontext.startActivity(intent);
                } else if(position==7){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Звездные войны:Бракованная партия");
                    intent.putExtra("imgURL", "https://gidonline.io//img/c6aca03f8_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/zvyozdnye-vojny-brakovannaya-partiya/");

                    mcontext.startActivity(intent);
                } else if(position==9){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "МОДОК");
                    intent.putExtra("imgURL", "https://gidonline.io//img/71c92ef7d_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/modok/");

                    mcontext.startActivity(intent);
                } else if(position==8){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);

                    intent.putExtra("title", "Гнев человеческий");
                    intent.putExtra("imgURL", "https://gidonline.io//img/9ce8be3e7_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/gnev-chelovecheskij/");

                    mcontext.startActivity(intent);

                    mcontext.startActivity(intent);
                } else if(position==10){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "По наклонной");
                    intent.putExtra("imgURL", "https://gidonline.io//img/b7ea5cc4f_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/po-naklonnoj/");

                    mcontext.startActivity(intent);
                } else if(position==11){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Сокол и Зимныи Солдат");
                    intent.putExtra("imgURL", "https://gidonline.io//img/bcdec37a8_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/sokol-i-zimnij-soldat/");

                    mcontext.startActivity(intent);
                } else if(position==12){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Лука");
                    intent.putExtra("imgURL", "https://gidonline.io//img/a1438d26f_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/luka/");

                    mcontext.startActivity(intent);
                } else if(position==13){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Митчеллы против машин");
                    intent.putExtra("imgURL", "https://gidonline.io//img/27d5b5114_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/mitchelly-protiv-mashin/");

                    mcontext.startActivity(intent);
                } else if(position==14){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Мортал Комбат");
                    intent.putExtra("imgURL", "https://gidonline.io//img/9e212d8cd_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/mortal-kombat/");

                    mcontext.startActivity(intent);
                } else if(position==15){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Райя и последний дракон");
                    intent.putExtra("imgURL", "https://gidonline.io//img/11a3a1ea5_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/rajya-i-poslednij-drakon/");

                    mcontext.startActivity(intent);
                } else if(position==16){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Годзилла против Конга");
                    intent.putExtra("imgURL", "https://gidonline.io//img/3d8c43b09_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/godzilla-protiv-konga/");

                    mcontext.startActivity(intent);
                } else if(position==17){
                    Intent intent=new Intent(mcontext, MovieDetailActivity.class);
                    intent.putExtra("title", "Лига справедливости Зака Снайдера");
                    intent.putExtra("imgURL", "https://gidonline.io//img/72fdd15d9_200x300.jpg");
                    intent.putExtra("siteLink", "https://gidonline.io/film/liga-spravedlivosti-zaka-snajdera/");

                    mcontext.startActivity(intent);
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
    }
    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

}
