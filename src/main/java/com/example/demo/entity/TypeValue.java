package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TypeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long identifier;
    @ManyToOne
    TypeAttribute attribute;
    String val;
    @ManyToOne
    Product product;
}
