package ru.volodin.SarComp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.volodin.SarComp.entity.enums.ReportType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Не указан клиент")
    @ManyToOne
    private Client client;
    @NotNull(message = "Не указан ПК")
    @ManyToOne
    private Comp comp;

    @ManyToMany
    private List<Addition> additions;

    @NotNull(message = "Не указан тип отчета")
    @Enumerated(EnumType.STRING)
    private ReportType type;
    private LocalDateTime createTs;

    @NotNull(message = "Не указан номер договора")
    private Integer docNumber;
    private String reportName;
    private String filePath;
}
