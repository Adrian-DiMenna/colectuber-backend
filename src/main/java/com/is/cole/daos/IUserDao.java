package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.is.cole.entities.Users;

@Repository
public interface IUserDao extends JpaRepository<Users, Integer> {
	public Users findByCorreo(String username);
}
