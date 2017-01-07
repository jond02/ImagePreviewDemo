package com.jdann.controllers;

import com.jdann.services.ImageUtil;
import com.jdann.util.Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping(value = "api/v1/")
public class IndexRestController {

    @ResponseBody
    @RequestMapping(value = "createfiles", method = RequestMethod.POST, produces = "application/json")
    public boolean createFiles(@RequestParam("file") MultipartFile uploadFile) {


        try {
            // Get the filename and build the local file path
            String filename = uploadFile.getOriginalFilename();
            String ext = Utils.getExtension(filename);

            File file = File.createTempFile(Utils.removeExtension(filename), "." + ext);
            System.out.println("Temp file : " + file.getAbsolutePath());

            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
            stream.write(uploadFile.getBytes());
            stream.close();

            ImageUtil.createPreview(file);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}