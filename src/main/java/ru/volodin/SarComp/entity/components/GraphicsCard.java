package ru.volodin.SarComp.entity.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "graphics_cards")
public class GraphicsCard extends Component {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
