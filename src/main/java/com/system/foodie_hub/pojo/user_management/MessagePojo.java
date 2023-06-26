package com.system.foodie_hub.pojo.user_management;

import com.system.foodie_hub.entity.user_management.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagePojo {

    private Integer id;

    private String content;

    private String sender;

    private String time;

    private String email;

    public MessagePojo(Message message){
        this.id = message.getId();
        this.content = message.getContent();
        this.sender = message.getSender();
        this.time = message.getTime();
        this.email =  message.getEmail();
    }
}
