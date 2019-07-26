package com.azmain.dcs.model;


import com.azmain.dcs.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "nids")
public class UserNID extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    private Date dateOfBirth;

    @NotNull
    private Long nidNumber;

    @NotNull
    private String imageLocation;

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getNidNumber() {
        return nidNumber;
    }

    public void setNidNumber(Long nidNumber) {
        this.nidNumber = nidNumber;
    }

    @Override
    public String toString() {
        return "UserNID{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nidNumber=" + nidNumber +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
