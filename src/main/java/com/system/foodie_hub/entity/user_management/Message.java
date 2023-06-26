package com.system.foodie_hub.entity.user_management;

import jakarta.persistence.*;
import lombok.*;

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


    //
//    import javax.persistence.*;
//import java.time.LocalDateTime;
//
//    @Entity
//    @Table(name = "messages")
//    public class Message {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @ManyToOne
//        @JoinColumn(name = "sender_id", nullable = false)
//        private User sender;
//
//        @ManyToOne
//        @JoinColumn(name = "recipient_id", nullable = false)
//        private User recipient;
//
//        @Column(nullable = false)
//        private LocalDateTime timestamp;
//
//        @Column(nullable = false)
//        private String content;
//
//        // Constructors, getters, and setters
//
//        // Constructor without id and timestamp (will be auto-generated by the database)
//        public Message(User sender, User recipient, String content) {
//            this.sender = sender;
//            this.recipient = recipient;
//            this.content = content;
//            this.timestamp = LocalDateTime.now();
//        }
//
//        // Getters and setters
//    }

}
