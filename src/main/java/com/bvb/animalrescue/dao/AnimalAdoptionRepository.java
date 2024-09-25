package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.AnimalAdoption;

@Repository
public interface AnimalAdoptionRepository extends JpaRepository<AnimalAdoption,Integer>{

	AnimalAdoption findByRazorPayOrderID(String razorPayOrderId);

}
