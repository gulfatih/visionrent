package com.visionrent.repository;

import com.visionrent.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query(  "SELECT count(*) FROM Car c join c.image img WHERE img.id=:id")
    Integer findCarCountByImageId(@Param("id") UUID imageId);

    @EntityGraph(attributePaths = {"image"})
    List<Car> findAll();

    @EntityGraph(attributePaths = {"image"})
    Page<Car> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "image")
    Optional<Car> findCarById(Long id);

    @Query("SELECT c FROM Car c join c.image img WHERE img.id=:id")
    List<Car> findCarsByImageId(@Param("id") UUID id);
}
