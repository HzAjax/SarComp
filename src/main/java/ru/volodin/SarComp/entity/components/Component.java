package ru.volodin.SarComp.entity.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

//TO DO Type ENUM
//TO DO cost
    protected String name;
}
