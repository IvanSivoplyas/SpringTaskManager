package com.silvan.taskmanager.testservices;

import com.silvan.taskmanager.models.User;
import com.silvan.taskmanager.repositories.UserRepository;
import com.silvan.taskmanager.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        // Подготовка данных
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        // Вызов метода
        User registeredUser = userService.registerUser(user);

        // Проверки
        assertNotNull(registeredUser);
        assertEquals("encodedPassword", registeredUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void testCheckPassword() {
        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        boolean isMatch = userService.checkPassword(rawPassword, encodedPassword);

        assertTrue(isMatch);
        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
    }
}