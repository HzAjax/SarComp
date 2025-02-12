package ru.volodin.SarComp.entity.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "processors")
public class Processor extends Component {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
