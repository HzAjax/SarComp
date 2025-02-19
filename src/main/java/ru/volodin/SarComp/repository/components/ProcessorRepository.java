package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.components.Processor;

import java.util.UUID;

public interface ProcessorRepository extends JpaRepository<Processor, UUID> {
}
