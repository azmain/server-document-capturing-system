package com.azmain.dcs.controller;


import com.azmain.dcs.model.UserNID;
import com.azmain.dcs.service.DocumentCaptureService;
import com.azmain.dcs.service.UserNidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentUploadController {

    @Autowired
    UserNidService userNidService;

    @Autowired
    private DocumentCaptureService documentCaptureService;


    @CrossOrigin(origins = "*")
    @PostMapping("/document")
    public ResponseEntity<?> uploadDocument(
            @RequestParam(required=true, value="file") MultipartFile file,
            @RequestParam(required=true, value="jsondata")String jsondata)
            throws Exception{

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getBytes());
        System.out.println("Json Data");
        System.out.println(jsondata);

        ResponseEntity<?> responseEntity =
                documentCaptureService.storeDocument(file, jsondata);

        return responseEntity;
    }

    /*@PostMapping("/document")
    public String uploadDocument(
            @RequestParam(required=true, value="file") MultipartFile file,
            @RequestParam(required=true, value="jsondata")String jsondata)
            throws Exception{

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getBytes());
        System.out.println("Json Data");
        System.out.println(jsondata);

        userNidService.storeNidInDatabase(file,jsondata);

        return "Success";
    }*/


    @CrossOrigin(origins = "*")
    @PostMapping("/document/file")
    public String uploadDocumentInFileSystem(
            @RequestParam(required=true, value="file") MultipartFile file,
            @RequestParam(required=true, value="jsondata")String jsondata)
            throws Exception{

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getBytes());
        System.out.println("Json Data");
        System.out.println(jsondata);

        userNidService.storeNidInFileSystem(file,jsondata);

        return "Success";
    }



    @GetMapping("/nids")
    public ResponseEntity<?> getNids() {
        // Load file from database
        List<UserNID> userNids = userNidService.getNids();

        return new ResponseEntity<List<UserNID>>(userNids,new HttpHeaders(),HttpStatus.OK);
    }
}
