package com.azmain.dcs.service;

import com.azmain.dcs.model.Documents;
import com.azmain.dcs.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;



@Service
public class DocumentsService {

    @Autowired
    private DocumentsRepository documentsRepository;


    public Boolean deleteDocument(Long documentId){

        boolean success = false;

        // later file will be saved in server
        String location = "H://Angular/dcs/dcs/src/assets/img";

        Documents document = documentsRepository.getOne(documentId);

        File file = new File(location+"/"+document.getImageFile());

        if(file.delete()){
            documentsRepository.delete(document);
            success = true;
        }

        return success;
    }

}
