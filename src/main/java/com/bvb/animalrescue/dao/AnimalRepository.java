package com.bvb.animalrescue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer> {

	List<Animal> findByFosterCareId(Integer fosterCareId);
	

}
