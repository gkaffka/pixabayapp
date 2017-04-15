package com.kaffka.pixabayapp.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kaffka.pixabayapp.R;
import com.kaffka.pixabayapp.adapters.PixabayImageListAdapter;
import com.kaffka.pixabayapp.api_services.InternetCheck;
import com.kaffka.pixabayapp.api_services.PixabayService;
import com.kaffka.pixabayapp.listeners.InfiniteScrollListener;
import com.kaffka.pixabayapp.models.PixabayImage;
import com.kaffka.pixabayapp.models.PixabayImageList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<PixabayImage> pixabayImageList;
    private PixabayImageListAdapter pixabayImageListAdapter;
    private InfiniteScrollListener infiniteScrollListener;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView noResults;
    private MenuItem searchMenuItem;
    private String currentQuery = "fruits";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initRecyclerView();
        initToolbar();
        if (!InternetCheck.isInternetAvailable(this)) initSnackbar(R.string.no_internet);
        else loadImages(1, currentQuery);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_list);
        progressBar = (ProgressBar) findViewById(R.id.activity_main_progress);
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        noResults = (TextView) findViewById(R.id.activity_main_no_results_text);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        pixabayImageList = new ArrayList<>();
        pixabayImageListAdapter = new PixabayImageListAdapter(pixabayImageList);
        recyclerView.setAdapter(pixabayImageListAdapter);
        initInfiniteScrollListener(mLayoutManager);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initSnackbar(int messageId) {
        progressBar.setVisibility(View.GONE);
        Snackbar snackbar = Snackbar.make(recyclerView, messageId, Snackbar.LENGTH_INDEFINITE).setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetCheck.isInternetAvailable(v.getContext())) {
                    resetImageList();
                    progressBar.setVisibility(View.VISIBLE);
                    loadImages(1, currentQuery);
                } else initSnackbar(R.string.no_internet);
            }
        });
        snackbar.show();
    }

    private void initInfiniteScrollListener(LinearLayoutManager mLayoutManager) {
        infiniteScrollListener = new InfiniteScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                progressBar.setVisibility(View.VISIBLE);
                loadImages(page, currentQuery);
            }
        };
        recyclerView.addOnScrollListener(infiniteScrollListener);
    }

    private void loadImages(int page, String query) {
        PixabayService.createPixabayService().getImageResults(getString(R.string.PIXABAY_API_KEY), query, page, 20).enqueue(new Callback<PixabayImageList>() {
            @Override
            public void onResponse(Call<PixabayImageList> call, Response<PixabayImageList> response) {
                if (response.isSuccessful()) addImagesToList(response.body());
                else progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PixabayImageList> call, Throwable t) {
                initSnackbar(R.string.error);
            }
        });
    }

    private void addImagesToList(PixabayImageList response) {
        progressBar.setVisibility(View.GONE);
        int position = pixabayImageList.size();
        pixabayImageList.addAll(response.getHits());
        pixabayImageListAdapter.notifyItemRangeInserted(position, position + 20);
        if(pixabayImageList.isEmpty()) noResults.setVisibility(View.VISIBLE);
        else noResults.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(searchListener);
        return true;
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchMenuItem.collapseActionView();
            currentQuery = query;
            resetImageList();
            progressBar.setVisibility(View.VISIBLE);
            noResults.setVisibility(View.GONE);
            loadImages(1,currentQuery);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void resetImageList(){
        pixabayImageList.clear();
        infiniteScrollListener.resetCurrentPage();
        pixabayImageListAdapter.notifyDataSetChanged();
    }
}
