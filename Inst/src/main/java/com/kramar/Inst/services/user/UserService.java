package com.kramar.Inst.services.user;

import com.kramar.Inst.core.models.user.Gender;
import com.kramar.Inst.services.repositories.RoleRepository;
import com.kramar.Inst.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public void saveUser(
            String login,
            String firstName,
            String lastName,
            String nickname,
            String password,
            Gender gender
            ) {

    }
}
