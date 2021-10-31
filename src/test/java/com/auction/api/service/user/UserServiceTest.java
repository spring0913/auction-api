package com.auction.api.service.user;

import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private String name;

    private Email email;

    private String password;

    @BeforeEach
    void setUp() {
        name = "bmsung";
        email = new Email("test@gmail.com");
        password = "1234";
    }

    @Test
    @Order(1)
    void 사용자_추가(){
        User user = userService.join(name, email, password);
        assertThat(user, is(notNullValue()));
        assertThat(user.getSeq(), is(notNullValue()));
        assertThat(user.getEmail(), is(email));
    }

    @Test
    @Order(2)
    void 이메일로_사용자_조회(){
        User user = userService.findByEmail(email).orElse(null);
        assertThat(user, is(notNullValue()));
        assertThat(user.getEmail(), is(email));
    }
}