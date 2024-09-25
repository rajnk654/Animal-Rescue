package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.FosterCare;

@Repository
public interface FosterCareRepository extends JpaRepository<FosterCare, Integer >{

}
