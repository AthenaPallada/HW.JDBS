package com.example.mockito;

import com.example.mockito.exceptions.UserNonUniqueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    private final User user1 = new User("wolf", "13wolf564");
    private final User user2 = new User("banana", "45banana67");


    @Test
    public void emptyLoginsList() {
        Assertions.assertArrayEquals(userService.getAllLogins().toArray(), new String[]{});
    }

    @Test
    public void completedLoginsList() {
        Mockito.when(userRepository.getAllUsers()).thenReturn(new ArrayList<User>(List.of(user1, user2)));
        String[] logins = {"wolf", "banana"};
        Assertions.assertArrayEquals(userService.getAllLogins().toArray(), logins);
    }

    @Test
    public void createUserWithCorrectLoginAndPassword() {
        userService.createUser("starlight", "999starlight666");
        Mockito.verify(userRepository).addUser(Mockito.any(User.class));
    }

    @Test
    public void createUserWithBlankLogin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("", "999starlight666"));
    }

    @Test
    public void createUserWithBlankPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("starlight", null));
    }

    @Test
    public void createUserWithBlankLoginAndPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.createUser("", null));
    }

    @Test
    public void createUserWithTheSameLogin() {
        Mockito.when(userRepository.getAllUsers()).thenReturn(new ArrayList<User>(List.of(user1, user2)));
        Assertions.assertThrows(UserNonUniqueException.class, () -> userService.createUser("wolf", "13wolf564"));
    }
}
