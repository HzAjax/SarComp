package ru.volodin.SarComp.entity.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.enums.MemoryType;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Memory extends Component {
    @Enumerated(EnumType.STRING)
    private MemoryType memoryType;
}
