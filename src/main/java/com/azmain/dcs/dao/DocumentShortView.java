package com.azmain.dcs.dao;

public class DocumentShortView {

    private String docType;
    private String docHead;
    private String imagePath;

    public DocumentShortView(String docType, String docHead, String imagePath) {
        this.docType = docType;
        this.docHead = docHead;
        this.imagePath = imagePath;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocHead() {
        return docHead;
    }

    public void setDocHead(String docHead) {
        this.docHead = docHead;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
