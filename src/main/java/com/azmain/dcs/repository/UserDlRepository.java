package com.azmain.dcs.repository;


import com.azmain.dcs.model.UserDL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDlRepository extends JpaRepository<UserDL,Long> {

}
