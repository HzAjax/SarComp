package ru.volodin.SarComp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Не введено имя")
    private String name;
    @NotNull(message = "Не указано количество дней аренды")
    private int day_count;
    @NotNull(message = "НЕ указана дата начала аренды")
    private Date startOrder;
    @NotBlank(message = "Не указан контакт")
    private String phone;

    private boolean isDone;

    @ManyToOne
    private Comp comp;
}
