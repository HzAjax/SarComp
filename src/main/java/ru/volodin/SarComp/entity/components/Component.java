package ru.volodin.SarComp.entity.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.enums.ComponentType;

import java.util.UUID;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = GraphicsCard.class, name = "GC"),
        @JsonSubTypes.Type(value = Memory.class, name = "MEM"),
        @JsonSubTypes.Type(value = Processor.class, name = "PROC")
})
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Enumerated(EnumType.STRING)
    protected ComponentType type;

    @NotNull(message = "не указана стоимость")
    private Float cost;
    protected String name;
}
