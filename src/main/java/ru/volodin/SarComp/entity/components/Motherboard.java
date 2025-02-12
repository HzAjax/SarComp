package ru.volodin.SarComp.entity.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.enums.MemoryType;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "motherboards")
public class Motherboard extends Component {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MemoryType memoryType;
}
