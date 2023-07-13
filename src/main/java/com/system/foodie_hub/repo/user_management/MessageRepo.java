package com.system.foodie_hub.repo.user_management;

import com.system.foodie_hub.entity.user_management.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
//    @Query(value = "DELETE FROM Message  m WHERE m.email = :email")
    void deleteMessageByEmail(@Param("email") String email);
    @Modifying
    @Query("UPDATE Message m SET m.sender=?1 Where m.email = ?2")
    void updateSender(@Param("sender")String sender, @Param("email")String email);
}
