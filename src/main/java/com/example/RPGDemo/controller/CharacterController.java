package com.example.RPGDemo.controller;

import com.example.RPGDemo.model.Character;
import com.example.RPGDemo.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public Character createCharacter(@RequestBody Character character) {
        return characterService.characterSave(character);
    }

    @GetMapping
    public List<Character> listAllCharacters(){
        return characterService.findAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable Long id){
        return characterService.findCharacterById(id);
    }

    @PutMapping("/{id}")
    public Character updateCharacter(@PathVariable Long id, @RequestBody Character character){
        character.setId(id);
        return characterService.updateCharacter(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterById(@PathVariable Long id){
        characterService.deleteCharacterById(id);
    }


}
