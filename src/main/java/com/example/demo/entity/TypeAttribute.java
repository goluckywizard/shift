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
public class TypeAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long identifier;
    String name;
    @OneToMany
    List<TypeValue> valueList;

}
