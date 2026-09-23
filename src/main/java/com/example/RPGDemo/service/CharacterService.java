package com.example.RPGDemo.service;

import com.example.RPGDemo.repository.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.RPGDemo.model.Character;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public void initializeCharacter(Character character){

        if (character.getName() == null || character.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if ((character.getLife() != null && character.getLife() < 0) ||
                (character.getAttack() != null && character.getAttack() < 0) ||
                (character.getDefense() != null && character.getDefense() < 0) ||
                (character.getLevel() != null && character.getLevel() < 0)) {
            throw new IllegalArgumentException("Stats cannot be negative");
        }

        character.setLife(100);
        character.setAttack(10);
        character.setDefense(5);
        character.setLevel(1);
    }

    public Character characterSave (Character character){
        initializeCharacter(character);
        characterRepository.save(character);
        return character;
    }

    public List<Character> findAllCharacters(){

        return characterRepository.findAll();
    }

    public Character findCharacterById(Long id){
        return characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found " + id));
    }

    public void deleteCharacterById(Long id){
        if (!characterRepository.existsById(id)) {
            throw new EntityNotFoundException("Character not found " + id);
        }
        characterRepository.deleteById(id);
    }

    public Character updateCharacter(Character character){
        var existing = findCharacterById(character.getId());

        if (character.getName() == null || character.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        existing.setName(character.getName());

        return characterRepository.save(existing);
    }
}
