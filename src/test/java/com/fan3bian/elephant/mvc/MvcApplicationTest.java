package com.fan3bian.elephant.mvc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.net.URL;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MvcApplicationTest {

    @LocalServerPort
    protected int port;
    protected URL base;
    @Resource
    protected TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
//        this.base = new URL("http://localhost:" + port + "/");
    }
}
