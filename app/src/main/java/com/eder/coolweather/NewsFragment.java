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

import com.eder.coolweather.dummy.DummyContent;
import com.eder.coolweather.util.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    // sspai最热
    private final String newsUrl = "https://sspai.com/api/v1/article/tag/page/get?limit=10&offset=0&created_at=1600487659&tag=%E7%83%AD%E9%97%A8%E6%96%87%E7%AB%A0&released=false";
    private Button sendBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        sendBtn = view.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNews();
            }
        });

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
                Log.d("sfsdlfsd", "请示数据发送李福克斯");
                final String respTxt = response.body().string();
                Log.d("少数派数据", respTxt);
            }
        });
    }
}