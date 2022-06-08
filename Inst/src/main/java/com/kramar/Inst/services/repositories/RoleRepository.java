package com.kramar.Inst.services.repositories;

import com.kramar.Inst.core.models.role.Role;
import com.kramar.Inst.core.models.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Role name);
}
