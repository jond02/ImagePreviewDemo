package com.jdann.util;

import org.springframework.stereotype.Component;

/**
 * Created by jdann on 1/3/17.
 */

@Component
public class Utils {

    public static String getExtension(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    public static String removeExtension(String filename){
        return filename.substring(0, filename.lastIndexOf("."));
    }

}
