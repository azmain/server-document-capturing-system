package com.azmain.dcs.service;

import com.azmain.dcs.exception.ResourceNotFoundException;
import com.azmain.dcs.model.DocumentTypes;
import com.azmain.dcs.model.Documents;
import com.azmain.dcs.repository.DocumentTypesRepository;
import com.azmain.dcs.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Service
public class DocumentTypesService {


    @Autowired
    private DocumentTypesRepository documentTypesRepository;

    @Autowired
    private DocumentsRepository documentsRepository;


    /**
     * save document in database along with image path
     * @param file
     * @param formData
     * @param documentTypeId
     * @return
     */
    public Documents saveDocument(MultipartFile file, String formData, Long documentTypeId)
            throws Exception{


        System.out.println(formData);
        System.out.println(file.getOriginalFilename());
        System.out.println(documentTypeId);

        Documents document = new Documents();

        String imageName = "";

        try {

            imageName = storeFileInFileSystem(file);

            /**
             * type of document
             */
            DocumentTypes docType = documentTypesRepository
                    .findById(documentTypeId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Document Type","id",documentTypeId)
                    );

            /**
             * setting values to model
             */
            document.setDocumentType(docType);
            document.setDocumentData(formData);
            document.setImageFile(imageName);


            return documentsRepository.save(document);

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }


    }


    /**
     * store uploaded file in local file system/server and
     * return the path
     * @param file
     * @return
     * @throws Exception
     */
    private String storeFileInFileSystem(@NotNull MultipartFile file) throws Exception{

        // later file will be saved in server
        String location = "H://Angular/dcs/dcs/src/assets/img";


        // Get a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));

        // Create a filename from a format string.
        // ... Apply date formatting codes.
        String fileName = String.format(
                "file-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$ts-%1$tp"+".jpeg", cal);

        Path fileStorageLocation = Paths.get(location)
                .toAbsolutePath().normalize();
        System.out.println("File Storage Location: " + fileStorageLocation.toString());

        /*
         * if storage location doesn't exist
         * then create directory
         */
        Files.createDirectories(fileStorageLocation);
        Path targetLocation = fileStorageLocation.resolve(fileName);
        System.out.println("Full File Name with Directory: " + targetLocation.toString());

        /*
         * save the file in the directory
         */
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


        return fileName;

    }
}
