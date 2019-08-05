package com.fan3bian.elephant.mvc;


import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.List;

@Slf4j
public class UserApplicationTest extends MvcApplicationTest{


    @Value("server.servlet.context-path")
    private String webContext;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/elephant/user");
    }

    @Test
    public void testAddUser() throws Exception{
        this.base = new URL("http://localhost:" + port + "/elephant/user/addUser");
        User user = new User();
        user.setUsername("fan3bian");
        user.setPassword("quao");
        ResponseEntity<Result> response = template.postForEntity(base.toString(), user, Result.class);
        log.error(response.toString());
//        assertEquals(response.getBody(), "Hello World");
    }
    @Test
    public void queryUsers() throws Exception{
        this.base = new URL("http://localhost:" + port + "/elephant/user/queryUsers");
//        ResponseEntity<List<User>> response2 = template.exchange(base.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
//        });
        //todo 不知道为啥报错
//        log.error(response2.toString());

    }
}
