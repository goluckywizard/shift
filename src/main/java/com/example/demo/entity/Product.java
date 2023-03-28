package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long identifier;
    String name;
    Long batch;
    String producer;
    Double cost;
    Long count;
    @OneToMany
    List<TypeValue> valueList;

}
