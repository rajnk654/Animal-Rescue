package com.bvb.animalrescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bvb.animalrescue.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

	Role findByName(String string);

}
