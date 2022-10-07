package com.example.seguridadjwt.dao.repository;

import com.example.seguridadjwt.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    Optional<UserEntity> findByUsername(String username);
}
