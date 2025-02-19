package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.volodin.SarComp.entity.Comp;

import java.util.List;
import java.util.UUID;

public interface CompRepository extends JpaRepository<Comp, UUID> {
    @Query(value = "SELECT * FROM comp WHERE name LIKE %:name%", nativeQuery = true)
    List<Comp> findByName(@Param("name") String name);
}
