package com.eder.coolweather;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.eder.coolweather.adapter.SspaiNewsAdapter;
import com.eder.coolweather.dummy.DummyContent;
import com.eder.coolweather.gson.SspaiNews;
import com.eder.coolweather.util.HttpUtil;
import com.eder.coolweather.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    // sspai最热
    private final String newsUrl = "https://sspai.com/api/v1/article/tag/page/get?limit=10&offset=0&created_at=1600487659&tag=%E7%83%AD%E9%97%A8%E6%96%87%E7%AB%A0&released=false";
    private Button sendBtn;
    private List<SspaiNews> news = new ArrayList<>();
    private RecyclerView recyclerView;
    private SspaiNewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.sspai_news_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new SspaiNewsAdapter(news);
        recyclerView.setAdapter(adapter);
        loadNews();
        return view;
    }

    private void loadNews() {
        HttpUtil.sendOkHttpRequest(newsUrl, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "请求少数派数据失败！", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String respTxt = response.body().string();
                List<SspaiNews> data = Utility.handleSspaiNewsResp(respTxt);
                for (int i = 0; i < data.size(); i++) {
                    news.add( data.get(i) );
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}