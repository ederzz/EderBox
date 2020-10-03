package com.eder.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class SspaiNews {
    public String banner;
    public String title;
    public String nickname;
    public String summary;

    @SerializedName(("created_time"))
    public int createdAt;

    @SerializedName("comment_count")
    public String commentCount;

    @SerializedName("like_count")
    public String likeCount;
}
