package com.visionrent.repository;

import com.visionrent.domain.Reservation;
import com.visionrent.domain.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

     @Query("""
     SELECT r FROM Reservation r
     JOIN FETCH r.car c
     WHERE
         c.id = :carId AND r.status NOT IN :status
     AND (
            :pickUpTime BETWEEN r.pickUpTime AND r.dropOffTime
         OR :dropOffTime BETWEEN r.pickUpTime AND r.dropOffTime
         OR r.pickUpTime BETWEEN :pickUpTime AND r.dropOffTime
     )
     """)
    List<Reservation> checkCarStatus(@Param("carId") Long carId,
                                     @Param("pickUpTime")LocalDateTime pickUpTime,
                                     @Param("dropOffTime") LocalDateTime dropOffTime,
                                     @Param("status") ReservationStatus[] status);

}
