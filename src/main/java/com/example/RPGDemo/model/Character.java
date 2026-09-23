package com.example.RPGDemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer life;
    private Integer attack;
    private Integer defense;
    private Integer level;

    @Enumerated(EnumType.STRING)
    private Type type;
}
