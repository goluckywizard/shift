package com.example.demo.repository;

import com.example.demo.entity.TypeValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends CrudRepository<TypeValue, Long> {
    TypeValue findByVal(String name);
}
