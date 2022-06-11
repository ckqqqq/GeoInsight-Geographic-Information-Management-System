package com.xiaoyuan.usermanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

    public static String FILE_UPLOAD_DIR;
    public static String FILE_ACCESS_URL;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }


    @Value("${file.accesses.url}")
    public void setFileAccessUrl(String fileAccessUrl) {
        FILE_ACCESS_URL = fileAccessUrl;
    }
}
