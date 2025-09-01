package com.visionrent.controller;


import com.visionrent.domain.ContactMessage;
import com.visionrent.dto.request.ContactMessageRequest;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.mapper.ContactMessageMapper;
import com.visionrent.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {

  private  ContactMessageService contactMessageService;
  private  ContactMessageMapper contactMessageMapper;

    @Autowired
    public ContactMessageController(ContactMessageService contactMessageService ,  ContactMessageMapper contactMessageMapper) {
        this.contactMessageService = contactMessageService;
        this.contactMessageMapper = contactMessageMapper;
    }


    @PostMapping("/visitors")
    public ResponseEntity<VRResponse> createMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {

        ContactMessage contactMessage =
                contactMessageMapper.contactMessageRequestToContactMessage(contactMessageRequest);
        contactMessageService.saveMessage(contactMessage);

        VRResponse response = new VRResponse("ContactMessage successfully created" , true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }





}
