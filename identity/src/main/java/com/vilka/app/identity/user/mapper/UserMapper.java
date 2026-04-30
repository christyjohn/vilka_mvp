package com.vilka.app.identity.user.mapper;

import com.vilka.app.identity.user.dto.UserResponse;
import com.vilka.app.identity.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

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
