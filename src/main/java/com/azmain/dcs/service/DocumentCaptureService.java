package com.azmain.dcs.service;


import com.azmain.dcs.model.UserDL;
import com.azmain.dcs.model.UserNID;
import com.azmain.dcs.payload.DocumentUploadRequest;
import com.azmain.dcs.repository.UserDlRepository;
import com.azmain.dcs.repository.UserNidRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Service
public class DocumentCaptureService {

    @Autowired
    private UserNidRepository userNidRepository;

    @Autowired
    private UserDlRepository userDlRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<?> storeDocument(MultipartFile file, String jsondata) throws Exception{

        DocumentUploadRequest documentUploadRequest =
                objectMapper.readValue(jsondata,DocumentUploadRequest.class);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String imgLocation = "H://Angular/dcs/dcs/src/assets/Img";



        if(documentUploadRequest.getDocumentType() == 1){

            System.out.println(documentUploadRequest.getUserNID().toString());
            String location = storeFileInFileSystem(file, imgLocation);

            UserNID userNID = new UserNID();
            System.out.println(userNID.getCreatedAt());
            System.out.println(userNID.getUpdatedAt());
            userNID.setFullName(documentUploadRequest.getUserNID().getFullName());
            userNID.setDateOfBirth(documentUploadRequest.getUserNID().getDateOfBirth());
            userNID.setNidNumber(documentUploadRequest.getUserNID().getNidNumber());
            userNID.setImageLocation(location);
            userNidRepository.save(userNID);


        } else if(documentUploadRequest.getDocumentType() == 2){

            System.out.println(documentUploadRequest.getUserDl().toString());
            String location = storeFileInFileSystem(file, imgLocation);

            UserDL userDL = new UserDL();
            userDL.setName(documentUploadRequest.getUserDl().getName());
            userDL.setDateOfBirth(documentUploadRequest.getUserDl().getDateOfBirth());
            userDL.setDlNumber(documentUploadRequest.getUserDl().getDlNumber());
            userDL.setIssueDate(documentUploadRequest.getUserDl().getIssueDate());
            userDL.setExpirayDate(documentUploadRequest.getUserDl().getExpirayDate());
            userDL.setIssueAuthority(documentUploadRequest.getUserDl().getIssueAuthority());
            userDL.setImageLocation(location);
            userDlRepository.save(userDL);

        } else if(documentUploadRequest.getDocumentType() == 3){

            System.out.println(documentUploadRequest.getUserNID().toString());
            String location = storeFileInFileSystem(file, imgLocation);
        }

        return null;
    }


    private String storeFileInFileSystem(MultipartFile file, String location) throws Exception{

        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Get a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));

        // Create a filename from a format string.
        // ... Apply date formatting codes.
        String fileName = String.format(
                "file-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp"+".jpeg", cal);

        Path fileStorageLocation = Paths.get(location)
                .toAbsolutePath().normalize();
        System.out.println(fileStorageLocation.toString());

        Files.createDirectories(fileStorageLocation);
        Path targetLocation = fileStorageLocation.resolve(fileName);
        System.out.println(targetLocation.toString());

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        System.out.println(targetLocation);

        return fileName;

    }
}
