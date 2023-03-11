package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.mapper.UserMapper;
import com.example.java.test.junior.developer.model.User;
import com.example.java.test.junior.developer.repository.UserRepository;
import com.example.java.test.junior.developer.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserMapper userMapper = new UserMapper();
    private final UserService userService = new UserService(userRepository, userMapper);

    @Test
    public void createUser_ValidDto_ReturnsDtoWithId() {
        // Arrange
        UserDto userDto = new UserDto(1, "Tom", "Disney", "tom@email");
        User user = User.builder()
                .id(1)
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDto result = userService.createUser(userDto);

        // Assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getSurname(), result.getSurname());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void getAllUsers_NoUsers_ReturnsEmptyList() {
        // Arrange
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<UserDto> result = userService.getAllUsers();

        // Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void updateUser_ValidIdAndDto_ReturnsDtoWithUpdatedFields() {
        // Arrange
        int id = 1;
        UserDto userDto = new UserDto(id, "Tom", "Disney", "tom_updated@email");
        User existingUser = User.builder()
                .id(id)
                .name("Tom")
                .surname("Disney")
                .email("tom@email")
                .build();
        User updatedUser = User.builder()
                .id(id)
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        UserDto result = userService.updateUser(id, userDto);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(userDto.getName(), result.getName());
        assertEquals(userDto.getSurname(), result.getSurname());
        assertEquals(userDto.getEmail(), result.getEmail());
    }

    @Test
    public void deleteUser_ValidId_DeletesUser() {
        // Arrange
        int id = 1;

        // Act
        userService.deleteUser(id);

        // Assert
        Mockito.verify(userRepository).deleteById(id);
    }
}
