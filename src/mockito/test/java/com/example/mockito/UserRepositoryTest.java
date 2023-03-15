package com.example.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepository();

    @Test
    public void emptyList() {
        Assertions.assertArrayEquals(userRepository.getAllUsers().toArray(), new User[]{});
    }

    @Test
    public void completedList() {
        User user1 = new User("wolf", "13wolf564");
        User user2 = new User("banana", "45banana67");
        User user3 = new User("starlight", "999starlight666");
        User[] users = {user1, user2, user3};

        User user4 = new User("wolf", "13wolf564");
        User user5 = new User("banana", "45banana67");
        User user6 = new User("starlight", "999starlight666");
        userRepository.addUser(user4);
        userRepository.addUser(user5);
        userRepository.addUser(user6);
        Assertions.assertArrayEquals(userRepository.getAllUsers().toArray(), users);
    }

    @Test
    public void userWithLoginExist() {
        userRepository.addUser(new User("wolf", "13wolf564"));
        Assertions.assertNotNull(userRepository.searchByLogin("wolf").orElse(null));
    }

    @Test
    public void userWithLoginDoesNotExist() {
        userRepository.addUser(new User("banana", "45banana67"));
        Assertions.assertNull(userRepository.searchByLogin("wolf").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordExist() {
        userRepository.addUser(new User("starlight", "999starlight666"));
        Assertions.assertNotNull(userRepository.searchByLoginAndPassword("starlight", "999starlight666").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExist() {
        userRepository.addUser(new User("banana", "45banana67"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("starlight", "999starlight666").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExistTwo() {
        userRepository.addUser(new User("banana", "45banana67"));
        userRepository.addUser(new User("starlight", "999starlight666"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("wolf", "999starlight666").orElse(null));
    }

    @Test
    public void userWithLoginAndPasswordDoesNotExistThree() {
        userRepository.addUser(new User("banana", "45banana67"));
        userRepository.addUser(new User("starlight", "999starlight666"));
        Assertions.assertNull(userRepository.searchByLoginAndPassword("banana", "13wolf564").orElse(null));
    }
}