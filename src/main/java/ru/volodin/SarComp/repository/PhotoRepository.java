package ru.volodin.SarComp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volodin.SarComp.entity.Photo;

import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
