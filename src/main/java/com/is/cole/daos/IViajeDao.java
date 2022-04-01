package com.is.cole.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.is.cole.entities.Viaje;

@Repository
public interface IViajeDao extends JpaRepository<Viaje,Integer>{

}
