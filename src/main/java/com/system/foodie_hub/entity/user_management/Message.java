package com.system.foodie_hub.entity.user_management;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_user_email", columnNames = "email")
})
public class Message {
    @Id
    @SequenceGenerator(name = "msg_seq_gen", sequenceName = "msg_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "msg_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "time", nullable = false)
    private String time;



}

