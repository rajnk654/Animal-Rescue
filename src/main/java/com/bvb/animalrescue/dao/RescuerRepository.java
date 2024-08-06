package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.Rescuer;

@Repository
public interface RescuerRepository extends JpaRepository<Rescuer, Integer> {

}
