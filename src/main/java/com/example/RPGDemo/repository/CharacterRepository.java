package com.example.RPGDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.RPGDemo.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
