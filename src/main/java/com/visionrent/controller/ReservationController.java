package com.visionrent.controller;

import com.visionrent.domain.Car;
import com.visionrent.domain.User;
import com.visionrent.dto.request.ReservationRequest;
import com.visionrent.dto.response.ResponseMessage;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.service.CarService;
import com.visionrent.service.ReservationService;
import com.visionrent.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<VRResponse> makeReservation(@RequestParam("carId") Long carId,
                                                      @Valid @RequestBody ReservationRequest reservationRequest){
    Car car = carService.getCar(carId);

    User user = userService.getCurrentUser();

    reservationService.createReservation(reservationRequest, user, car);

    VRResponse response = new VRResponse(ResponseMessage.RESERVATION_CREATED_RESPONSE_MESSAGE, true);
    return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
