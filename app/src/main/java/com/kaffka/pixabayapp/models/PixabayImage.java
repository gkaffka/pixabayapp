package com.kaffka.pixabayapp.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.kaffka.pixabayapp.constants.PixabayImageType;

/**
 * Created by kaffka on 4/14/2017.
 */

public class PixabayImage {
    private int previewHeight;
    private int likes;
    private int favorites;
    private String tags;
    private int webformatHeight;
    private long views;
    private int webformatWidth;
    private int previewWidth;
    private int comments;
    private long downloads;
    private String pageURL;
    private String previewURL;
    private String webformatURL;
    private int imageWidth;
    @SerializedName("user_id")
    private long userId;
    private String user;
    private PixabayImageType type;
    private long id;
    private String userImageURL;
    private int imageHeight;

    public PixabayImage(int previewHeight, int likes, int favorites, String tags,
                        int webformatHeight, long views, int webformatWidth, int previewWidth,
                        int comments, long downloads, String pageURL, String previewURL,
                        String webformatURL, int imageWidth, long userId, String user,
                        PixabayImageType type, long id, String userImageURL, int imageHeight) {

        this.previewHeight = previewHeight;
        this.likes = likes;
        this.favorites = favorites;
        this.tags = tags;
        this.webformatHeight = webformatHeight;
        this.views = views;
        this.webformatWidth = webformatWidth;
        this.previewWidth = previewWidth;
        this.comments = comments;
        this.downloads = downloads;
        this.pageURL = pageURL;
        this.previewURL = previewURL;
        this.webformatURL = webformatURL;
        this.imageWidth = imageWidth;
        this.userId = userId;
        this.user = user;
        this.type = type;
        this.id = id;
        this.userImageURL = userImageURL;
        this.imageHeight = imageHeight;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public String getLikes() {
        return String.valueOf(likes);
    }

    public String getFavorites() {
        return String.valueOf(favorites);
    }

    public String getTags() {
        if (tags == null) return "";
        if (tags.contains(", ")) {
            String splitTags[] = tags.toUpperCase().split(", ");
            return TextUtils.join(" - ", splitTags);
        } else return tags;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public long getViews() {
        return views;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public String getComments() {
        return String.valueOf(comments);
    }

    public long getDownloads() {
        return downloads;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public long getUserId() {
        return userId;
    }

    public String getUser() {
        return "By: " + user;
    }

    public PixabayImageType getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}