package com.visionrent.repository;

import com.visionrent.domain.ImageFile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageFileRepository extends  JpaRepository<ImageFile, UUID> {

    @EntityGraph(attributePaths = "id") // parametreye id değeri girildiği zaman aynı seviyedeki datalar gelir,
    // bağlı olduğu image data'lar gelmemiş olacak
    List<ImageFile> findAll();

    @EntityGraph(attributePaths = "id")
    Optional<ImageFile> findImageById(UUID id);
}
