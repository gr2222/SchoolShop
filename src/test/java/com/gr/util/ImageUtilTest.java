package com.gr.util;

import com.gr.dto.ImageHolder;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 16:43
 */
public class ImageUtilTest {

    @Test
    public void makingThumbnails() throws FileNotFoundException {
        File file = new File("/Users/gaorui/Downloads/image/nvren.jpeg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), inputStream);
        String s = ImageUtil.makingThumbnails(imageHolder, "/schoolShop/");
        System.out.println(s);
    }
}