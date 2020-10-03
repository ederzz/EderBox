package com.eder.coolweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eder.coolweather.R;
import com.eder.coolweather.gson.SspaiNews;
import com.eder.coolweather.util.CommonUtil;

import java.util.List;

public class SspaiNewsAdapter extends RecyclerView.Adapter<SspaiNewsAdapter.ViewHolder> {
    private List<SspaiNews> news;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner;
        private TextView title;
        private TextView summary;
        private TextView date;
        private TextView comment;
        private TextView like;

        public ViewHolder(View view) {
            super(view);

            banner = view.findViewById(R.id.news_banner);
            title = view.findViewById(R.id.news_title);
            summary = view.findViewById(R.id.news_summary);
            date = view.findViewById(R.id.news_date);
            comment = view.findViewById(R.id.news_comment);
            like = view.findViewById(R.id.news_like);
        }
    }

    public SspaiNewsAdapter(List<SspaiNews> l) {
        news = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sspai_news, parent, false);
        mContext = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int idx) {
        SspaiNews item = news.get(idx);
        holder.title.setText(item.title);
        holder.summary.setText(item.summary);
        holder.date.setText(CommonUtil.timeFormat(item.createdAt));
        holder.comment.setText(item.commentCount);
        holder.like.setText(item.likeCount);
        Glide.with(mContext).load(bannerWrapper(item.banner)).into(holder.banner);
    }

    public String bannerWrapper(String banner) {
        String bannerUrl = "https://cdn.sspai.com/" + banner + "?imageMogr2/auto-orient/quality/95/thumbnail/!800x400r/gravity/Center/crop/800x400/interlace/1";
        return bannerUrl;
    }

    public int getItemCount() {
        return news.size();
    }
}
