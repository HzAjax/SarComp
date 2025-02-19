package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.components.Motherboard;

import java.util.UUID;

public interface MotherboardRepository extends JpaRepository<Motherboard, UUID> {
}
