package com.system.foodie_hub.services.user_management.impl;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.repo.user_management.MessageRepo;
import com.system.foodie_hub.services.user_management.MessageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    private final EntityManager entityManager;

    @Override
    public MessagePojo save(MessagePojo messagePojo) throws IOException {
        Message message = new Message();
        message.setId(messagePojo.getId());
        message.setTime(messagePojo.getTime());
        message.setSender(messagePojo.getSender());
        message.setContent(messagePojo.getContent());
        message.setEmail(messagePojo.getEmail());
        messageRepo.save(message);
        return new MessagePojo(message);
    }
    public List<Message> fetchAll() {
        return messageRepo.findAll();
    }

    @Override
    public void deleteByEmail(String email) {
        Query query = entityManager.createQuery("DELETE FROM Message m WHERE m.email = :email");
        query.setParameter("email", email);
        query.executeUpdate();
        messageRepo.deleteMessageByEmail(email);
    }

    @Override
    public void updateSender(String sender, String email) {
//        Query query = entityManager.createQuery("UPDATE Message m SET m.sender= :sender WHERE m.email= :email");
//        query.setParameter("email", email);
//        query.setParameter("sender", sender);
//        query.executeUpdate();
        messageRepo.updateSender(sender, email);
    }
}
