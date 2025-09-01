package com.visionrent.service;

import com.visionrent.domain.ContactMessage;
import com.visionrent.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactMessageService {

    private ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public void saveMessage(ContactMessage contactMessage) {
        contactMessageRepository.save(contactMessage);
    }
}
