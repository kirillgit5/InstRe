package com.kramar.Inst.services.userDetails;

import com.kramar.Inst.core.models.role.Role;
import com.kramar.Inst.core.models.role.RoleEntity;
import com.kramar.Inst.core.models.user.Gender;
import com.kramar.Inst.core.models.user.UserEntity;
import com.kramar.Inst.services.repositories.RoleRepository;
import com.kramar.Inst.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(name);

        return UserDetailsImpl.build(user);
    }

    public void saveUser(
            String login,
            String firstName,
            String lastName,
            String nickname,
            String password,
            Gender gender
    ) {
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(Role.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        UserEntity user = new UserEntity();

        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNickname(nickname);
        user.setGender(gender);
        user.setPassword(encoder().encode(password));
        user.setRoles(roles);
        userRepository.save(user);
    }
}