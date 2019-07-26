package com.azmain.dcs.repository;

import com.azmain.dcs.model.UserNID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNidRepository extends JpaRepository<UserNID,Long> {
}
