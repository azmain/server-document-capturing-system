package com.azmain.dcs.model;

import com.azmain.dcs.model.audit.DateAudit;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "driving_licences")
public class UserDL extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private Date dateOfBirth;

    private Date issueDate;

    private Date expirayDate;

    @NotNull
    private String dlNumber;

    private String issueAuthority;

    @NotNull
    private String imageLocation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpirayDate() {
        return expirayDate;
    }

    public void setExpirayDate(Date expirayDate) {
        this.expirayDate = expirayDate;
    }

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }


    @Override
    public String toString() {
        return "UserDL{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", issueDate=" + issueDate +
                ", expirayDate=" + expirayDate +
                ", dlNumber='" + dlNumber + '\'' +
                ", issueAuthority='" + issueAuthority + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }
}
