package com.kaffka.pixabayapp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kaffka.pixabayapp.R;
import com.kaffka.pixabayapp.databinding.ActivityDetailsBinding;
import com.kaffka.pixabayapp.models.PixabayImage;
import com.kaffka.pixabayapp.viewmodels.PixabayImageViewModel;

/**
 * Created by kaffka on 4/15/2017.
 */

public class DetailActivity extends AppCompatActivity {
    ActivityDetailsBinding activityDetailsBinding;
    public final static String PIXABAY_IMAGE = "PIXABAY_IMAGE";
    private PixabayImage image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        initImage();
        activityDetailsBinding.setViewmodel(new PixabayImageViewModel(image));
    }

    private void initImage() {
        try {
            image = new Gson().fromJson(getIntent().getStringExtra(PIXABAY_IMAGE), PixabayImage.class);
        } catch (Exception e) {
            finish();
        }
    }
}
