package com.system.foodie_hub.repo.user_management;

import com.system.foodie_hub.entity.user_management.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
