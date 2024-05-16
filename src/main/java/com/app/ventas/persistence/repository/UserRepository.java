package com.app.ventas.persistence.repository;

import com.app.ventas.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>  findByUsername(String email);
    boolean existsByUsername(String email);

}
