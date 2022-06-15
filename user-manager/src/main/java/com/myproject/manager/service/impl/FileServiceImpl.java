package com.myproject.manager.service.impl;

import com.myproject.manager.config.Constant;
import com.myproject.manager.db.entity.Material;
import com.myproject.manager.db.mapper.MaterialMapper;
import com.myproject.manager.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    public MaterialMapper materialMapper;

    public void download(File file, HttpServletResponse res) throws IOException {

        //buff输入流
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        FileInputStream fis = null;

        // 读取file内容
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);

            //res输出流
            OutputStream outputStream = res.getOutputStream();

            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, i);
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Material uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                return null;
            }
        }
        try {
            file.transferTo(destFile.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Material material = new Material();
        material.setFileShortName(fileName);
        material.setFileFullName(newFileName);
        material.setFileUri(destFile.getAbsolutePath());
        material.setFileSize(file.getSize());
        material.setFileType(file.getContentType());
        material.setPublishTime(new Date());
        this.materialMapper.insertSelective(material);
        return material;
    }

    @Override
    public Material getMaterial(Long id) {
        Material material = this.materialMapper.selectByPrimaryKey(id);
        return material;
    }
}