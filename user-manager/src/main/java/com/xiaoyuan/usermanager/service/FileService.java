package com.xiaoyuan.usermanager.service;

import com.xiaoyuan.usermanager.db.entity.Material;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public interface FileService {

    Material getMaterial(Long id);

    void download(File file, HttpServletResponse res) throws IOException;

    @Transactional(rollbackFor = Exception.class)
    Material uploadFile(MultipartFile file);
}
