package com.sparta.projectmovie1.movienightplanner.services;

import com.mongodb.assertions.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTests {
    UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }



    @ParameterizedTest
    @ValueSource(strings = {"lowercase","has space", "stop12H!","test"})
    void shouldReturnFalseForInvalidPasswords(String password){
        Assertions.assertFalse(userService.isValidPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"vaLid12#Pass&", "newP7%pas*", "Lemon1234!", "leMon1234()", "Lemo$752", "lime-Lem+^12", "f=Green23"})
    void shouldReturnTrueForValidPasswords(String password){
        Assertions.assertTrue(userService.isValidPassword(password));
    }


}
