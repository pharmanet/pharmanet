package com.pharmanet.persistence.repositories;

import com.pharmanet.persistence.entities.user.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
