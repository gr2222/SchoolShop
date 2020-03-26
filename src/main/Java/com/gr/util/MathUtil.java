package com.gr.util;

import java.util.Random;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-23 19:58
 */
public class MathUtil {
    public static Long getUuid() {
        Random random = new Random();
        int a = random.nextInt(89999) + 10000;
        return Long.valueOf(a);
    }
}
