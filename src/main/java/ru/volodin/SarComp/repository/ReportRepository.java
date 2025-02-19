package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.Report;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
