package ru.volodin.SarComp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.enums.CustomerType;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Не указан тип клиента")
    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @NotEmpty(message = "Не указаны ФИО клиента")
    private String name;
    @NotEmpty(message = "Не указан телефон клиента")
    private String phone;
    private Date regDate;
    private String idSerialOrInn;
    private String idNumberOrOgrn;
    private String issued;
    private String regAddress;
}
