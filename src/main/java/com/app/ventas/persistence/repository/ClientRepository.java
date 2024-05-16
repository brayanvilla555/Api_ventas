package com.app.ventas.persistence.repository;

import com.app.ventas.persistence.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.name LIKE CONCAT('%', :name, '%')")
    Optional<List<Client>>findAllName(@Param("name") String name);



}
