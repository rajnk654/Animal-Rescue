package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvb.animalrescue.model.BookRescuer;

public interface BookRescuerRepository extends JpaRepository<BookRescuer, Integer>{
public BookRescuer findByRazorPayOrderID(String orderId);

}


