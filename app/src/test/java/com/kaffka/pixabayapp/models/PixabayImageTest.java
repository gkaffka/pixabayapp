package com.kaffka.pixabayapp.models;

import com.kaffka.pixabayapp.constants.PixabayImageType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kaffka on 4/15/2017.
 */
public class PixabayImageTest {
    private PixabayImage pixabayImage;

    @Before
    public void init() {
        pixabayImage = new PixabayImage(180, 100, 50, "tag, tag2", 90, 1234, 360, 180, 150, 987,
                "http://page.com", "http://preview.com", "http://web.ccom", 1080, 123456,
                "user", PixabayImageType.illustration, 987654, "http://url.com", 1024);
    }

    @Test
    public void getPreviewHeight() throws Exception {
        assertEquals(pixabayImage.getPreviewHeight(), 180);
    }

    @Test
    public void getLikes() throws Exception {
        assertEquals(pixabayImage.getLikes(), "100");
    }

    @Test
    public void getFavorites() throws Exception {
        assertEquals(pixabayImage.getFavorites(), "50");
    }

    @Test
    public void getTags() throws Exception {
        //TO DO mock TextUtils - expected value "TAG - TAG2"
        assertEquals(pixabayImage.getTags(), null);
    }

    @Test
    public void getWebformatHeight() throws Exception {
        assertEquals(pixabayImage.getWebformatHeight(), 90);
    }

    @Test
    public void getViews() throws Exception {
        assertEquals(pixabayImage.getViews(), 1234);
    }

    @Test
    public void getWebformatWidth() throws Exception {
        assertEquals(pixabayImage.getWebformatWidth(), 360);
    }

    @Test
    public void getPreviewWidth() throws Exception {
        assertEquals(pixabayImage.getPreviewWidth(), 180);
    }

    @Test
    public void getComments() throws Exception {
        assertEquals(pixabayImage.getComments(), "150");
    }

    @Test
    public void getDownloads() throws Exception {
        assertEquals(pixabayImage.getDownloads(), 987);
    }

    @Test
    public void getPageURL() throws Exception {
        assertEquals(pixabayImage.getPageURL(), "http://page.com");
    }

    @Test
    public void getPreviewURL() throws Exception {
        assertEquals(pixabayImage.getPreviewURL(), "http://preview.com");
    }

    @Test
    public void getWebformatURL() throws Exception {
        assertEquals(pixabayImage.getWebformatURL(), "http://web.ccom");
    }

    @Test
    public void getImageWidth() throws Exception {
        assertEquals(pixabayImage.getImageWidth(), 1080);
    }

    @Test
    public void getUserId() throws Exception {
        assertEquals(pixabayImage.getUserId(), 123456);
    }

    @Test
    public void getUser() throws Exception {
        assertEquals(pixabayImage.getUser(), "By: user");
    }

    @Test
    public void getType() throws Exception {
        assertEquals(pixabayImage.getType(), PixabayImageType.illustration);
    }

    @Test
    public void getId() throws Exception {
        assertEquals(pixabayImage.getId(), 987654);
    }

    @Test
    public void getUserImageURL() throws Exception {
        assertEquals(pixabayImage.getUserImageURL(), "http://url.com");
    }

    @Test
    public void getImageHeight() throws Exception {
        assertEquals(pixabayImage.getImageHeight(), 1024);
    }

}