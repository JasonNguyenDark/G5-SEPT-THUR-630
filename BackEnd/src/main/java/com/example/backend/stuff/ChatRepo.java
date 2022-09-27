package com.example.backend.stuff;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepo extends CrudRepository<Record,Integer> {
    Record findByEmail(String email);
}
