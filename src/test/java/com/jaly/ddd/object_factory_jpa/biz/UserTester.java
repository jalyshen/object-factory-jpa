package com.jaly.ddd.object_factory_jpa.biz;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTester {

    @Resource
    private EntityManager entityManager;

    @Test
    public void testUserByID() {
        User user = User.BY_ID.lookup(entityManager, (long)5);
        System.out.println("email => " + user.getEmail());
        Assertions.assertEquals("a@a.com", user.getEmail());
        Assertions.assertEquals("123456", user.getPassword());

        user = User.BY_ID.lookup(entityManager, (long)6);
        System.out.println("email => " + user.getEmail());
        Assertions.assertEquals("b@b.com", user.getEmail());
        Assertions.assertEquals("654321", user.getPassword());
    }

}
