package com.kaffka.pixabayapp.models;

import java.util.List;

/**
 * Created by kaffka on 4/14/2017.
 */

public class PixabayImageList {

    private int total;
    private int totalHits;
    private List<PixabayImage> hits;

    public PixabayImageList(int total, int totalHits, List<PixabayImage> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public List<PixabayImage> getHits() {
        return hits;
    }

    public int getTotalOfPages() {
        return (int) Math.ceil(total / 20.0);
    }
}