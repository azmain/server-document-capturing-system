package com.azmain.dcs.controller;


import com.azmain.dcs.exception.BadRequestException;
import com.azmain.dcs.model.Documents;
import com.azmain.dcs.payload.SuccessResponse;
import com.azmain.dcs.repository.DocumentPropertiesRepository;
import com.azmain.dcs.repository.DocumentTypesRepository;
import com.azmain.dcs.service.DocumentTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/document-types")
public class DocumentTypesController {

    @Autowired
    private DocumentTypesRepository documentTypesRepository;

    @Autowired
    private DocumentPropertiesRepository documentPropertiesRepository;

    @Autowired
    private DocumentTypesService documentTypesService;


    /**
     * get all document types
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getDocumentTypes(){

        return ResponseEntity
                .ok()
                .body(documentTypesRepository.findAll());

    }


    /**
     * return all properties of a particular document type
     * @param documentTypeId
     * @return
     */
    @GetMapping("{documentTypeId}/prop")
    public ResponseEntity<?> getDocumentTypeProperties(@PathVariable Long documentTypeId){

        return ResponseEntity
                .ok(documentPropertiesRepository.getAllPropertiesByDocumentType_Id(documentTypeId));

    }

    /**
     * return all properties of a particular document type
     * @param documentTypeId
     * @return
     */
    @GetMapping("{documentTypeId}/properties")
    public ResponseEntity<?> getDocProperties(@PathVariable Long documentTypeId){

        return ResponseEntity
                .ok(documentPropertiesRepository.getListOfProperties(documentTypeId));

    }


    /**
     * save a document for a particular document type
     * @param documentTypeId
     * @param file
     * @param formData
     * @return
     */
    @PostMapping("{documentTypeId}/documents")
    public ResponseEntity<?> uploadDocument(
            @PathVariable Long documentTypeId,
            @RequestParam(required=true, value="file") MultipartFile file,
            @RequestParam(required=true, value="formData")String formData
    ){

        try{

            Documents document =
                    documentTypesService.saveDocument(file, formData, documentTypeId);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("documents/{documentId}")
                    .buildAndExpand(document.getId()).toUri();

            return ResponseEntity
                    .created(location)
                    .body(new SuccessResponse(true,"Document uploaded successfully."));

        } catch (Exception e){

            System.out.println(e.getMessage());

            return ResponseEntity.badRequest()
                    .body(new BadRequestException("Document Upload Failed."));
        }


    }

}
