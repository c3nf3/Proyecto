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
    private int life;
    private int attack;
    private int defense;
    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;
}
