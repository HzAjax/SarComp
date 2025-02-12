package ru.volodin.SarComp.entity;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.components.GraphicsCard;
import ru.volodin.SarComp.entity.components.Memory;
import ru.volodin.SarComp.entity.components.Motherboard;
import ru.volodin.SarComp.entity.components.Processor;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "comps") // почему
public class Comp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "не указано имя")
    private String name;
    @NotNull(message = "не указана стоимость")
    private Float cost;

    private Motherboard motherboard;
    private GraphicsCard graphics_card;
    private Processor processor;
    private Memory memory;

    private Date startOrder;
    private Date endOrder;

    @ManyToOne
    private Client client;

    @ManyToMany
    private Photo photos;
}
