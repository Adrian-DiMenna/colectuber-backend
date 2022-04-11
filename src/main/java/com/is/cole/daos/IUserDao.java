package com.is.cole.daos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.is.cole.entities.Usuarios;

@Repository
public interface IUserDao extends JpaRepository<Usuarios, Integer> {
	
	@Query("SELECT u FROM Usuarios u WHERE u.correo = ?1")
	public  Optional<UserDetails> findByUserName(String key);

}
