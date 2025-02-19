package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.volodin.SarComp.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query(value = "SELECT * FROM client WHERE name LIKE %:name%", nativeQuery = true)
    List<Client> findByName(@Param("name") String name);
}
