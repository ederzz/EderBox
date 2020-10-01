package com.eder.coolweather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eder.coolweather.R;
import com.eder.coolweather.gson.SspaiNews;

import java.util.List;

public class SspaiNewsAdapter extends RecyclerView.Adapter<SspaiNewsAdapter.ViewHolder> {
    private List<SspaiNews> news;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView banner;
        private TextView title;
        private TextView summary;

        public ViewHolder(View view) {
            super(view);

            banner = view.findViewById(R.id.news_banner);
            title = view.findViewById(R.id.news_title);
            summary = view.findViewById(R.id.news_summary);
        }
    }

    public SspaiNewsAdapter(List<SspaiNews> l) {
        news = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sspai_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int idx) {
        SspaiNews item = news.get(idx);
        holder.title.setText(item.title);
        holder.summary.setText(item.summary);
    }

    public int getItemCount() {
        return news.size();
    }
}
