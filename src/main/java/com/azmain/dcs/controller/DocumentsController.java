package com.azmain.dcs.controller;


import com.azmain.dcs.payload.SuccessResponse;
import com.azmain.dcs.repository.DocumentsRepository;
import com.azmain.dcs.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    @Autowired
    private DocumentsService documentsService;

    @Autowired
    private DocumentsRepository documentsRepository;

    /**
     * return all saved documents
     * @return
     */
    @GetMapping
    public ResponseEntity getAllDocuments(){

        return ResponseEntity
                .ok()
                .body(documentsRepository.findAll());
    }


    /**
     * throwing error
     * @return
     */
    @GetMapping("/doc")
    public ResponseEntity getAllDocs(){

        return ResponseEntity
                .ok()
                .body(documentsRepository.getListOfDocuments());
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity deleteDocument(@PathVariable Long documentId){

        boolean result = false;
        String message = "Could not delete the document.";

        try {

            result = documentsService.deleteDocument(documentId);
            if(result) {
                message = "Successfully deleted document.";
            }

        } catch(Exception e){

            result = false;
        }

        return ResponseEntity.ok()
                .body(new SuccessResponse(result, message));




    }


}
