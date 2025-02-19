package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.components.Memory;

import java.util.UUID;

public interface MemoryRepository extends JpaRepository<Memory, UUID> {
}
