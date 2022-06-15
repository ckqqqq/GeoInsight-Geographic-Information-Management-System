package com.myproject.manager.db.entity;
import java.util.Date;

public class Material {

    private Long id;

    private String fileShortName;

    private String fileFullName;

    private String fileType;

    private String fileUri;

    private Long fileSize;

    private Date publishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileShortName() {
        return fileShortName;
    }

    public void setFileShortName(String fileShortName) {
        this.fileShortName = fileShortName == null ? null : fileShortName.trim();
    }

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName == null ? null : fileFullName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri == null ? null : fileUri.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}