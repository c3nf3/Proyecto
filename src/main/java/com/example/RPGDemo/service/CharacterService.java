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
        characterRepository.deleteById(id);
    }

    public Character updateCharacter(Character character){
        var existing = findCharacterById(character.getId());
        existing.setName(character.getName());
        existing.setType(character.getType());
        return characterRepository.save(character);
    }
}
