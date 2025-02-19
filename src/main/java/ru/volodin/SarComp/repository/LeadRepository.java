package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.Lead;

import java.util.UUID;

public interface LeadRepository extends JpaRepository<Lead, UUID> {
}
