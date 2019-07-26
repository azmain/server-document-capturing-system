package com.azmain.dcs.repository;

import com.azmain.dcs.dao.DocProperties;
import com.azmain.dcs.model.DocumentProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentPropertiesRepository extends JpaRepository<DocumentProperties,Long> {

    @Query("SELECT d from DocumentProperties d where d.documentType.id = :documentId")
    List<DocumentProperties> getAllPropertiesByDocumentType_Id(@Param("documentId") Long documentId);

    @Query("SELECT NEW com.azmain.dcs.dao.DocProperties(d.properties) FROM DocumentProperties d where d.documentType.id = :documentId")
    List<DocProperties> getListOfProperties(@Param("documentId") Long documentId);

}
