package ru.volodin.SarComp.repository.autorization;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.authorization.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
