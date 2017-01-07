package com.jdann.services;

import com.jdann.util.Utils;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static boolean createPreview(File file){

        File preview = new File(Utils.removeExtension(file.getAbsolutePath()) + "_preview.png");
        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage newImage = image;

            if (image.getWidth() > 1000 || image.getHeight() > 1000){
                newImage = updateImage(image, BufferedImage.TYPE_INT_ARGB, image.getWidth() / 4, image.getHeight() / 4);
            }
            ImageIO.write(newImage, "PNG", preview);
            System.out.println("Preview file :" + preview.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static BufferedImage updateImage(BufferedImage originalImage, int type, int width, int height) {

        BufferedImage image = new BufferedImage(width, height, type);
        Graphics2D g = image.createGraphics();
        if (type == BufferedImage.TYPE_INT_RGB){
            g.drawImage(originalImage, 0, 0, width, height, Color.WHITE, null);
        } else {
            g.drawImage(originalImage, 0, 0, width, height, null);
        }
        g.dispose();
        return image;
    }
}
