package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
