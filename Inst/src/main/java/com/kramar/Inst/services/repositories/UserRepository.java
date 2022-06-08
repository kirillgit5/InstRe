package com.kramar.Inst.services.repositories;

import com.kramar.Inst.core.models.user.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByNickname(String nickname);
    UserEntity findByLogin(String login);

    Boolean existsByLogin(String login);
    Boolean existsByNickname(String username);

    Optional<UserEntity> findById(Long id);
}
