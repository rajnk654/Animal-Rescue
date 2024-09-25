package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvb.animalrescue.model.ContactUs;

public interface ContactRepository extends JpaRepository<ContactUs, Long>{

}
