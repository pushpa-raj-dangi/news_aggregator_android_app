package com.pushpa.news_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pushpa.news_app.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pushpa.news_app.models.News;

import java.util.ArrayList;

public class RecentNewsAdapter extends RecyclerView.Adapter<RecentNewsAdapter.MyViewHolder> {
    private Context context;
    ArrayList<News> news;
    public RecentNewsAdapter(Context context,ArrayList<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View conv = LayoutInflater.from(context).inflate(R.layout.custom_list,null);
        MyViewHolder myViewHolder  = new MyViewHolder(conv);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView tv =  holder.itemView.findViewById(R.id.cTitle);

        tv.setText(news.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    protected static  class  MyViewHolder extends  RecyclerView.ViewHolder{



        public  MyViewHolder(View convertView){
            super(convertView);
        }
    }
}
