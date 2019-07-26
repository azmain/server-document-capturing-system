package com.azmain.dcs.payload;

import com.azmain.dcs.model.UserDL;
import com.azmain.dcs.model.UserNID;

public class DocumentUploadRequest {

    private Integer documentType;

    private UserNID userNID;

    private UserDL userDl;

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
    }

    public UserNID getUserNID() {
        return userNID;
    }

    public void setUserNID(UserNID userNID) {
        this.userNID = userNID;
    }

    public UserDL getUserDl() {
        return userDl;
    }

    public void setUserDl(UserDL userDl) {
        this.userDl = userDl;
    }
}
