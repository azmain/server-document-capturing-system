package com.azmain.dcs.model;


import com.azmain.dcs.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

@Entity
@Table(name = "document_properties")
public class DocumentProperties extends DateAudit {

    @Id
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "properties", columnDefinition = "json")
    private String properties;

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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypes documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "DocumentProperties{" +
                "id=" + id +
                ", propertyName='" + propertyName + '\'' +
                ", properties='" + properties + '\'' +
                ", documentType=" + documentType +
                '}';
    }
}
