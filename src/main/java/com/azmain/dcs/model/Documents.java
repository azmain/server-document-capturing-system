package com.azmain.dcs.model;

import com.azmain.dcs.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Documents extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image_file")
    private String imageFile;

    @Column(name = "document_data", columnDefinition = "json")
    private String documentData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DocumentTypes documentType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getDocumentData() {
        return documentData;
    }

    public void setDocumentData(String documentData) {
        this.documentData = documentData;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypes documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", imageFile='" + imageFile + '\'' +
                ", documentData='" + documentData + '\'' +
                ", documentType=" + documentType +
                '}';
    }
}
