package com.visionrent.mapper;

import com.visionrent.domain.Reservation;
import com.visionrent.dto.request.ReservationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation reservationRequestToReservation(ReservationRequest reservationRequest);


}
