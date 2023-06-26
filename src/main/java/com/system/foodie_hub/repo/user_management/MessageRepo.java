package com.system.foodie_hub.repo.user_management;

import com.system.foodie_hub.entity.user_management.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
