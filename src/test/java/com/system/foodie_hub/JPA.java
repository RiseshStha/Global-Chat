package com.system.foodie_hub;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

@Entity

@Table(name = "user")
public class JPA {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;


    @Column(name = "fullname", nullable = false)

    private String fullname;

    @Column(name = "mobile_no", nullable = false)

    private String mobile_no;

    @Column(name = "email", nullable = false)

    private String email;


    @Column(name = "password", nullable = false)

    private String password;




}
