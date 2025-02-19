package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.components.GraphicsCard;

import java.util.UUID;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, UUID> {
}
