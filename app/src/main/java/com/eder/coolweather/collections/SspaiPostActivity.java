package com.eder.coolweather.collections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.eder.coolweather.R;
import com.eder.coolweather.util.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.noties.markwon.Markwon;
import io.noties.markwon.html.HtmlPlugin;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SspaiPostActivity extends AppCompatActivity {
    private final String POST_URL = "https://sspai.com/api/v1/article/info/get?id=";
    private final String REQ_ERROR_MSG = "请求少数派数据失败";

    private JSONObject postData;
    private ImageView imageView;
    private TextView postTitle;
    private TextView postContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sspai_post);

        imageView = findViewById(R.id.banner);
        postTitle = findViewById(R.id.post_title);
        postContent = findViewById(R.id.post_container);

        Intent intent = getIntent();
        int id = intent.getIntExtra("post_id", -1);
        getPostDetail(id);
    }

    private void getPostDetail(int id) {
        String url = POST_URL + id;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SspaiPostActivity.this, REQ_ERROR_MSG, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String respTxt = response.body().string();
                handlePostResp(respTxt);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final Markwon markwon = Markwon.builder(SspaiPostActivity.this)
                                .usePlugin(HtmlPlugin.create())
                                .build();
                        try {
                            Glide.with(SspaiPostActivity.this).load(postData.getString("banner")).into(imageView);
                            postTitle.setText(postData.getString("title"));
                            markwon.setMarkdown(postContent, postData.getString("body"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void handlePostResp(String resp) {
        try {
            JSONObject obj1 = new JSONObject(resp);
            postData = obj1.getJSONObject("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}