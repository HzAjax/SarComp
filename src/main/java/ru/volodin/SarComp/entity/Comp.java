package ru.volodin.SarComp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.components.GraphicsCard;
import ru.volodin.SarComp.entity.components.Memory;
import ru.volodin.SarComp.entity.components.Processor;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Comp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "не указано имя")
    private String name;
    @NotNull(message = "не указана стоимость")
    private Float cost;

    @ManyToOne
    @JoinColumn(name = "graphicscard_id")
    private GraphicsCard graphicsCard;
    @ManyToOne
    private Processor processor;
    @ManyToOne
    private Memory memory;

    private Date startOrder;
    private Date endOrder;

    @ManyToOne
    private Client client;

    @OneToOne
    private Photo photos;
}
