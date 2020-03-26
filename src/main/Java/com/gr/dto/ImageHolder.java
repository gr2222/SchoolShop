package com.gr.dto;

import java.io.InputStream;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:51
 */
public class ImageHolder {
    private String imageName;
    private InputStream Image;

    public ImageHolder(String imageName, InputStream image) {
        this.imageName = imageName;
        Image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public InputStream getImage() {
        return Image;
    }
}
