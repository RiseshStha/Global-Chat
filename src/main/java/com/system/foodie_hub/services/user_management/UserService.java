package com.system.foodie_hub.services.user_management;

import com.system.foodie_hub.dto.AuthenticationResponse;
import com.system.foodie_hub.dto.LoginDto;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.UserPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserPojo save(UserPojo userPojo) throws IOException;
    void update(UserPojo userPojo) throws IOException;
    void update2(UserPojo userPojo) throws IOException;

    List<User> fetchAll();

    User fetchById(Integer id);
    User fetchByEmail(String email);

    void deleteById(Integer id);

    void sendEmail();

//    AuthenticationResponse authenticate(LoginDto loginDto);
}
