package com.example.dto;

import java.io.Serializable;

public class ImageData implements Serializable {
    private String imageId;
    private String fileName;
    private byte[] imageBytes;

    public ImageData(String imageId, String fileName, byte[] imageBytes) {
        this.imageId = imageId;
        this.fileName = fileName;
        this.imageBytes = imageBytes;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
