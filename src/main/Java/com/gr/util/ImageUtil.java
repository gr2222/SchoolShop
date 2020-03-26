package com.gr.util;

import com.gr.dto.ImageHolder;
import com.gr.entity.Product;
import com.gr.entity.ProductImg;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 11:43
 */
public class ImageUtil {
    private static String basepath = Thread.currentThread()
            .getContextClassLoader().getResource("").getPath();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);


    /**
     * 处理缩略图
     *
     * @param imageHolder    原始图片
     * @param targetAddr 想要存储的相对路径
     * @return 返回处理后的图片全路径
     */
    public static String makingThumbnails(ImageHolder imageHolder, String targetAddr) throws RuntimeException{
        String willName = getNewName();
        String fileSuffix = getfileSuffix(imageHolder.getImageName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + willName + fileSuffix;
        logger.debug("relativeAddr is :" + relativeAddr);
        File thumbnail = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("complete Addr is :" + relativeAddr);
        try {
            Thumbnails.of(imageHolder.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basepath+"/shuiying.png")), 1f)
                    .outputQuality(0.8f).toFile(thumbnail);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("图片处理失败");
        }
        return relativeAddr;
    }

    /**
     * 创建文件夹
     *
     * @param targetAddr 相对路径
     */
    private static void makeDirPath(String targetAddr) {
        String realPath = PathUtil.getImgBasePath() + targetAddr;
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 获取后缀名
     *
     * @param imgFile 图片文件
     * @return 返回后缀名
     */
    private static String getfileSuffix(String imgFile) {
        return imgFile.substring(imgFile.lastIndexOf('.'));
    }

    /**
     * 根据当前时间和5位随机数组成名字
     *
     * @return 随机数
     */
    public static String getNewName() {
        Random random = new Random();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String prefix = simpleDateFormat.format(new Date());
        int suffix = random.nextInt(89999) + 10000;
        prefix = prefix + suffix;
        return prefix;
    }

    /**
     * 删除图片路径下目录或文件
     * @param route
     */
    public static void deleteFileOrPath(String route){
        File file = new File(PathUtil.getImgBasePath()+route);
        if (file.exists()){
            if (file.isDirectory()){
                File file1[] = file.listFiles();
                for (int i = 0; i < file1.length; i++) {
                    file1[i].delete();
                }
            }
            file.delete();
        }
    }

    public static String makeProductDetailImg(ImageHolder img, String imgPath) {
        String newName = getNewName();
        String fileSuffix = getfileSuffix(img.getImageName());
        makeDirPath(imgPath);
        String pathStr = imgPath+newName+fileSuffix;
        File thumbnail = new File(PathUtil.getImgBasePath() + pathStr);
        try {
            System.out.println(basepath + "/shuiying.png");
            Thumbnails.of(img.getImage()).size(337, 640)
                    .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basepath+"/shuiying.png")), 1f)
                    .outputQuality(0.9f).toFile(thumbnail);
        } catch (IOException e) {
            throw new RuntimeException("图片处理失败");
        }
        return pathStr;
    }
}
