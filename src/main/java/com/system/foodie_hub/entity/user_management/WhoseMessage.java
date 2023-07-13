package com.system.foodie_hub.entity.user_management;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WhoseMessage {
    @Id
    private Long id;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
