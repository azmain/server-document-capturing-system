package com.azmain.dcs.repository;

import com.azmain.dcs.dao.DocProperties;
import com.azmain.dcs.dao.DocumentShortView;
import com.azmain.dcs.model.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface DocumentsRepository extends JpaRepository<Documents, Long> {


    /**
     * returning an error like
     * No converter found capable of converting from type
     * [org.springframework.data.jpa.repository.query.AbstractJpaQuery$TupleConverter$TupleBackedMap]
     * @return
     */
    @Query(
            value = "SELECT dt.type_name as docType, IFNULL(d.document_data->>\"$.name\",'No Name') as docHead," +
                    " d.image_file as imagePath FROM Documents d INNER JOIN " +
                    "document_types dt ON dt.id = d.document_type_id", nativeQuery = true)
    Collection<DocumentShortView> getListOfDocuments();

}
