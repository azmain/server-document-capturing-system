package com.azmain.dcs.service;


import com.azmain.dcs.model.UserNID;
import com.azmain.dcs.repository.UserNidRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserNidService {

    @Autowired
    UserNidRepository userNidRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    public void storeNidInDatabase(MultipartFile file, String jsondata)
            throws Exception{

        System.out.println("Store Data in Database.");


        UserNID userNID = new UserNID();
        userNID.setFullName("ABC");
        userNID.setDateOfBirth(new Date());
        userNID.setNidNumber(Long.parseLong("123456789"));

        userNID.setImageLocation(file.getName());

        userNID.toString();
        userNID.setCreatedAt(Instant.now());
        userNID.setUpdatedAt(Instant.now());
        System.out.println(userNID.getFullName());
        System.out.println(userNID.getCreatedAt());

        userNidRepository.save(userNID);
    }


    public void storeNidInFileSystem(MultipartFile file, String jsondata) throws Exception{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Path fileStorageLocation = Paths.get("C://Test")
                .toAbsolutePath().normalize();
        Files.createDirectories(fileStorageLocation);
        Path targetLocation = fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


    }

    public List<UserNID> getNids(){
        return userNidRepository.findAll();
    }
}
