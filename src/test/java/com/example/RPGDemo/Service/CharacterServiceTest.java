package com.example.RPGDemo.Service;

import com.example.RPGDemo.model.Character;
import com.example.RPGDemo.service.CharacterService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.example.RPGDemo.repository.CharacterRepository;
import org.mockito.Mockito;


public class CharacterServiceTest {

    @Test
    void characterShouldBeInitialized() {
        var repository = Mockito.mock(CharacterRepository.class);
        var service = new CharacterService(repository);
        var player = new Character();

        service.initializeCharacter(player);

        assertEquals(100, player.getLife());
        assertEquals(10, player.getAttack());
        assertEquals(5, player.getDefense());
        assertEquals(1, player.getLevel());
    }

    @Test
    void characterShouldRequireName() {
        var player = new Character();
        player.setName("");

        assertThrows(IllegalArgumentException.class,
                () -> new CharacterService(null).initializeCharacter(player));
    }

    @Test
    void characterShouldNotHaveNegativeStats() {
        var player = new Character();
        player.setName("Warrior");
        player.setLife(-10);

        assertThrows(IllegalArgumentException.class,
                () -> new CharacterService(null).initializeCharacter(player));
    }

    @Test
    void shouldNotUpdateNonExistingCharacter() {
        var character = new Character();
        character.setId(999L);
        character.setName("Unknown");

        var repository = Mockito.mock(CharacterRepository.class);

        Mockito.when(repository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        var service = new CharacterService(repository);

        assertThrows(EntityNotFoundException.class,
                () -> service.updateCharacter(character));
    }

    @Test
    void shouldNotDeleteNonExistingCharacter() {
        var repository = Mockito.mock(CharacterRepository.class);
        var service = new CharacterService(repository);

        Mockito.when(repository.existsById(999L))
                .thenReturn(false);

        assertThrows(EntityNotFoundException.class,
                () -> service.deleteCharacterById(999L));
    }

    @Test
    void shouldNotUpdateWithEmptyName() {
        var character = new Character();
        character.setId(1L);
        character.setName("");

        var repository = Mockito.mock(CharacterRepository.class);
        var service = new CharacterService(repository);

        Mockito.when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(character));

        assertThrows(IllegalArgumentException.class,
                () -> service.updateCharacter(character));
    }


}
