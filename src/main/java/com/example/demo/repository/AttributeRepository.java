package com.example.demo.repository;

import com.example.demo.entity.TypeAttribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends CrudRepository<TypeAttribute, Long> {
    Optional<TypeAttribute> findByName(String name);
}
