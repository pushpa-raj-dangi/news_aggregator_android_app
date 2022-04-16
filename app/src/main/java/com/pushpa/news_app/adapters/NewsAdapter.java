package com.pushpa.news_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.like.IconType;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.pushpa.news_app.MyWebViewActivity;
import com.pushpa.news_app.R;
import com.pushpa.news_app.Utils.DbHelper;
import com.pushpa.news_app.models.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    DbHelper dbHelper;
    ArrayList<News> news;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,null,false);

        return new  ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyWebViewActivity.class);
                intent.putExtra("url",news.get(position).getLink());
                Log.e("id", "onClick: "+news.get(position).getLink() );
                context.startActivity(intent);


            }
        });

        holder.pTime.setText(news.get(position).getPublishedAt());
        holder.pTitle.setText(news.get(position).getTitle());
        holder.pAuthor.setText(news.get(position).getAuthor());
        Log.e("TAG", "fav status: "+news.get(position).getFavoriteStatus() );
        if(news.get(position).getFavoriteStatus()==null){
            holder.favorite.setLiked(false);
        }else{
            holder.favorite.setLiked(true);

        }

        News newNews = new
                    News(
                    news.get(position).getAuthor(),
                    news.get(position).getTitle(),
                    news.get(position).getPublishedAt(),
                    news.get(position).getDescription(),
                    news.get(position).getLink(),
                    news.get(position).getImage(),
                    news.get(position).getFavoriteStatus()
                    );

        holder.favorite.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                dbHelper.addToFav(newNews);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                dbHelper.addToFav(newNews);

            }
        });


        if(news.get(position).getImage()==null){
            Glide.with(context).load(R.drawable.blogfeed_imagefour).into(holder.imageView);
        }

        Glide.with(context).load(news.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pTitle,pAuthor,pTime;
        ImageView imageView;
        CardView cardView;
        LikeButton favorite;
        SwipeRefreshLayout refreshLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pTime = itemView.findViewById(R.id.pTime);
            imageView = itemView.findViewById(R.id.pImage);
            pAuthor = itemView.findViewById(R.id.pAuthor);
            pTitle = itemView.findViewById(R.id.pTitle);
            cardView = itemView.findViewById(R.id.pCard);
            favorite = itemView.findViewById(R.id.favorite);


            dbHelper = new DbHelper(context);



        }


    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateRecycler(ArrayList<News> newList) {
        news.clear();
        news.addAll(newList);
        this.notifyDataSetChanged();
    }
}
