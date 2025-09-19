package com.visionrent.service;

import com.visionrent.domain.Car;
import com.visionrent.domain.Reservation;
import com.visionrent.domain.User;
import com.visionrent.domain.enums.ReservationStatus;
import com.visionrent.dto.ReservationDTO;
import com.visionrent.dto.request.ReservationRequest;
import com.visionrent.dto.request.ReservationUpdateRequest;
import com.visionrent.exception.BadRequestException;
import com.visionrent.exception.ConflictException;
import com.visionrent.exception.ResourceNotFoundException;
import com.visionrent.exception.message.ErrorMessage;
import com.visionrent.mapper.ReservationMapper;
import com.visionrent.repository.ReservationRepository;
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
    @Autowired
    private UserService userService;

    // ************************ CREATE RESERVATION *************************

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

    public Page<ReservationDTO> getReservationDTOPage(Pageable pageable) {
        return reservationRepository.findAll(pageable).map(reservationMapper::reservationToReservationDTO);
    }

    // -------------------------- UPDATE RESERVATION ---------------------------------------

    public void updateReservation(Long reservationId, Car car, ReservationUpdateRequest reservationUpdateRequest) {
        Reservation reservation = getById(reservationId);

        if (reservation.getStatus().equals(ReservationStatus.CANCELED) ||
                reservation.getStatus().equals(ReservationStatus.DONE)) {
            throw new BadRequestException(ErrorMessage.RESERVATION_STATUS_CANT_CHANGE_MESSAGE);
        }

        if (reservationUpdateRequest.getStatus()!=null && reservationUpdateRequest.getStatus().equals(ReservationStatus.CREATED)){

            checkReservationTimeIsCorrect(reservationUpdateRequest.getPickUpTime(), reservationUpdateRequest.getDropOffTime());

            List<Reservation> conflictReservations = getConflictReservations(car, reservationUpdateRequest.getPickUpTime(),
                    reservationUpdateRequest.getDropOffTime());
            if (!conflictReservations.isEmpty()) {
                if (!(conflictReservations.size() == 1 && conflictReservations.get(0).getId().equals(reservationId))){
                    throw new ConflictException(ErrorMessage.CAR_NOT_AVAILABLE_MESSAGE);
                }
            }

            Double totalPrice = getTotalPrice(car, reservationUpdateRequest.getPickUpTime(),
                                                   reservationUpdateRequest.getDropOffTime());

            reservation.setTotalPrice(totalPrice);
            reservation.setCar(car);

        }

        reservation.setPickUpTime(reservationUpdateRequest.getPickUpTime());
        reservation.setDropOffTime(reservationUpdateRequest.getDropOffTime());
        reservation.setPickUpLocation(reservationUpdateRequest.getPickUpLocation());
        reservation.setDropOffLocation(reservationUpdateRequest.getDropOffLocation());
        reservation.setStatus(reservation.getStatus());
        reservationRepository.save(reservation);
    }


    public Reservation getById(Long id){
        return reservationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESERVATION_NOT_FOUND_MESSAGE, id)));
    }

    public ReservationDTO getReservationDTO(Long id) {

        Reservation reservation = getById(id);
        return reservationMapper.reservationToReservationDTO(reservation);
    }

    public Page<ReservationDTO> findReservationPageByUser(User user, Pageable pageable) {
       Page<Reservation> reservationPage = reservationRepository.findAllByUser(user, pageable);
        return reservationPage.map(reservationMapper::reservationToReservationDTO);
    }

    public ReservationDTO findByIdAndUser(Long id, User user) {
        Reservation reservation = reservationRepository.findByIdAndUser(id, user).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESERVATION_NOT_FOUND_MESSAGE, id)));
        return reservationMapper.reservationToReservationDTO(reservation);
    }

    public void removeById(Long id) {
        boolean exist = reservationRepository.existsById(id);

        if (!exist) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.RESERVATION_NOT_FOUND_MESSAGE, id));
        }
        reservationRepository.deleteById(id);
    }

    public boolean existsByCar(Car car) {
        return reservationRepository.existsByCar(car);
    }

    public boolean existsByUser(User user) {
        return reservationRepository.existsByUser(user);
    }
}
