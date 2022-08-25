package com.example.backend.stuff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email, String password);
}
