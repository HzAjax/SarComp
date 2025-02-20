package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.components.Component;

import java.util.UUID;


public interface ComponentRepository <T extends Component> extends JpaRepository<T, UUID> {
}
