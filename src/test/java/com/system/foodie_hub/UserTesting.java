package com.system.foodie_hub;

import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.repo.user_management.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTesting {

    @Autowired
    private UserRepo userRepo;


    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCustomerTest() {

        User users = User.builder()
                .fullName("Risesh Sama Shrestha")
                .email("risesh@gmail.com")
                .mobileNo("987654324")
                .password("123456")
                .build();

        userRepo.save(users);

        Assertions.assertThat(users.getId()).isGreaterThan(0);
    }


    @Test
    @Order(2)
    public  void getCustomerTest(){
        User customerCreated= userRepo.findById(1).get();
        Assertions.assertThat(customerCreated.getId()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void getListOfCustomerTest(){
        List<User> users = userRepo.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCustomerTest(){

        User user = userRepo.findById(1).get();

        user.setFullName("Rocky Sth");

        User customerUpdated =  userRepo.save(user);

        Assertions.assertThat(customerUpdated.getFullName()).isEqualTo("Rocky Sht");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest(){
        User user = userRepo.findById(1).get();
        userRepo.delete(user);

        User user1 = null;
        Optional<User> opUser = userRepo.findById(1);
        if(opUser.isPresent()){
            user1 = opUser.get();
        }
        Assertions.assertThat(user1).isNull();
    }

}
