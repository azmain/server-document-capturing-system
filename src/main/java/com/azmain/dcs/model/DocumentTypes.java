package com.azmain.dcs.model;


import com.azmain.dcs.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "document_types")
public class DocumentTypes extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    @OneToMany(
            mappedBy = "documentType",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<DocumentProperties> properties = new ArrayList<>();


    @OneToMany(
            mappedBy = "documentType",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Documents> documents = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "DocumentTypes{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
