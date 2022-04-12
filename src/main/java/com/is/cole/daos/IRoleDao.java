package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.is.cole.entities.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {
	@Query("SELECT u FROM Role u WHERE u.name = ?1")
	public Role findByName(String key);
	
}
