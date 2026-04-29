package com.vilka.app.identity.mapper;

import com.vilka.app.identity.dto.CreateUserRequest;
import com.vilka.app.identity.dto.UserResponse;
import com.vilka.app.identity.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static User toEntity(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setName(user.getUsername());
        res.setEnabled(user.isEnabled());
        res.setCreatedAt(user.getCreatedAt());
        return res;
    }
}
