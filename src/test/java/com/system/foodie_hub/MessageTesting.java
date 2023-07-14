package com.system.foodie_hub;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.repo.user_management.MessageRepo;
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
public class MessageTesting {

    @Autowired
    private MessageRepo  msgRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        Message msg= Message.builder()
                .time("2:2")
                .content("hello")
                .sender("Risesh Sama Shrestha")
                .email("risesh@gmail.com")
                .build();

        
        msgRepo.save(msg);

        Assertions.assertThat(msg.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public  void getCustomerTest(){
        Message msgSend= msgRepo.findById(1L).get();
        Assertions.assertThat(msgSend.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfCustomerTest(){
        List<Message> msg = msgRepo.findAll();
        Assertions.assertThat(msg.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCustomerTest(){

        Message msg = msgRepo.findById(1L).get();

        msg.setContent("Hi");

        Message customerUpdated =  msgRepo.save(msg);

        Assertions.assertThat(customerUpdated.getContent()).isEqualTo("Hi");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest(){
        Message msg = msgRepo.findById(1L).get();
        msgRepo.delete(msg);

        Message msg1 = null;
        Optional<Message> isMsg = msgRepo.findById(1L);
        if(isMsg.isPresent()){
            msg1 = isMsg.get();
        }
        Assertions.assertThat(msg1).isNull();
    }


}
