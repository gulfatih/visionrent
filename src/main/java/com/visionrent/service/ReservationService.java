package com.visionrent.service;

import com.visionrent.domain.Car;
import com.visionrent.domain.Reservation;
import com.visionrent.domain.User;
import com.visionrent.domain.enums.ReservationStatus;
import com.visionrent.dto.ReservationDTO;
import com.visionrent.dto.UserDTO;
import com.visionrent.dto.request.ReservationRequest;
import com.visionrent.exception.BadRequestException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.mapper.ReservationMapper;
import com.visionrent.repository.ReservationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    // ************************ CREATE *************************

    public void createReservation(ReservationRequest reservationRequest, User user, Car car) {

        checkReservationTimeIsCorrect(reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());
        boolean carStatus = checkCarAvailability(car, reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());

        Reservation reservation = reservationMapper.reservationRequestToReservation(reservationRequest);

        if (carStatus){
            reservation.setStatus(ReservationStatus.CREATED);
        }else{
            throw new BadRequestException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
        }

        reservation.setCar(car);
        reservation.setUser(user);

        Double totalPrice = getTotalPrice(car,reservationRequest.getPickUpTime(), reservationRequest.getDropOffTime());

        reservation.setTotalPrice(totalPrice);

        reservationRepository.save(reservation);
    }

    // ********************************

    public void checkReservationTimeIsCorrect(LocalDateTime pickUpTime, LocalDateTime dropOffTime){

        LocalDateTime now = LocalDateTime.now();

        if(pickUpTime.isBefore(now)){
            throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }

        boolean isEqual = pickUpTime.isEqual(dropOffTime);
        boolean isBefore = pickUpTime.isBefore(dropOffTime);

        if (isEqual || !isBefore) {
            throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }
    }

    // ------------->
    public boolean checkCarAvailability(Car car, LocalDateTime pickUpTime, LocalDateTime dropOffTime){
        List<Reservation> existReservations = getConflictReservations(car, pickUpTime, dropOffTime);
        return existReservations.isEmpty();
    }

    // ------------->
    public Double getTotalPrice(Car car, LocalDateTime pickUpTime, LocalDateTime dropOffTime){

        // Java.time'dan gelen ChronoUnit --> iki zaman arasındaki farkı bulmaya yarıyor.
        Long minutes = ChronoUnit.MINUTES.between(pickUpTime, dropOffTime);
        double hours = Math.ceil(minutes/60.0);
        return car.getPricePerHour() * hours;
    }

    // ------------>
    private List<Reservation> getConflictReservations(Car car, LocalDateTime pickUpTime, LocalDateTime dropOffTime){
        if (pickUpTime.isAfter(dropOffTime)) {
            throw new BadRequestException(ErrorMessage.RESERVATION_TIME_INCORRECT_MESSAGE);
        }

        ReservationStatus[] status = {ReservationStatus.CANCELED, ReservationStatus.DONE};

        List<Reservation> existReservations = reservationRepository.checkCarStatus(car.getId(), pickUpTime, dropOffTime, status);

        return existReservations;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.map(reservations);
    }

    public Page<ReservationDTO> getReservationPage(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(reservationMapper::reservationToReservationDTO);
    }
}
