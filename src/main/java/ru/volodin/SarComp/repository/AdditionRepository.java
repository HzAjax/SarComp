package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.Addition;

import java.util.UUID;

public interface AdditionRepository extends JpaRepository<Addition, UUID> {
}
