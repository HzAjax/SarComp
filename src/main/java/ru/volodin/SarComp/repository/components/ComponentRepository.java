package ru.volodin.SarComp.repository.components;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.volodin.SarComp.entity.components.Component;
import ru.volodin.SarComp.entity.enums.ComponentType;

import java.util.List;
import java.util.UUID;


public interface ComponentRepository <T extends Component> extends JpaRepository<T, UUID> {
    List<T> findAllByType (ComponentType type);
}
