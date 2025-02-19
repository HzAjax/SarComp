package ru.volodin.SarComp.repository.autorization;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.authorization.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLogin(String login);
}
