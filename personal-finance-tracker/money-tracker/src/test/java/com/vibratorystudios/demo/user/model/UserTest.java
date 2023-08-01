package com.vibratorystudios.demo.user.model;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGettersAndSetters(){
        Long id = 1L;

        String userName = "justin_kani";

        String password = "thisisapassword";

        //creates mock user object
        User user = new User(id,userName, password);

        //Test getter methods
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(userName, user.getUserName());
        Assertions.assertEquals(password, user.getPassword());


        Long newId = 2L;
        String newUserName = "sahiizzy_fasizzy";
        String newPassword = "thisisanotherpassword";


        user.setId(newId);
        user.setUserName(newUserName);
        user.setPassword(newPassword);

        Assertions.assertEquals(newId,user.getId());
        Assertions.assertEquals(newUserName, user.getUserName());
        Assertions.assertEquals(newPassword, user.getPassword());


    }
}
