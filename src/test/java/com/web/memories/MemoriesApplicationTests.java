package com.web.memories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemoriesApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    void restTemplate(){
        testRestTemplate.getForObject("/api/v1/memories", ArrayList.class);
    }

}
