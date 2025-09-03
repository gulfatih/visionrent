package com.visionrent.controller;
import com.visionrent.domain.ContactMessage;
import com.visionrent.dto.ContactMessageDTO;
import com.visionrent.dto.request.ContactMessageRequest;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.mapper.ContactMessageMapper;
import com.visionrent.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {

  private final  ContactMessageService contactMessageService;
  private final  ContactMessageMapper contactMessageMapper;

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

    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllMessage() {
        List<ContactMessage> contactMessageList = contactMessageService.getAll();
        List<ContactMessageDTO> contactMessageDTOList = contactMessageMapper.map(contactMessageList);
        return ResponseEntity.ok(contactMessageDTOList);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<ContactMessageDTO>>getAllContactMessageWithPage(
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("sort") String prop,
                                                    @RequestParam(value = "direction",
                                                                  required = false,
                                                                  defaultValue = "DESC" ) Sort.Direction direction){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction,prop ));
        Page<ContactMessage> contactMessagePage = contactMessageService.getAll(pageable);

        Page<ContactMessageDTO> pageDTO = getPageDTO(contactMessagePage);
        return ResponseEntity.ok(pageDTO);
    }

    private Page<ContactMessageDTO> getPageDTO(Page<ContactMessage> contactMessagePage){
        // page sınıfına ait map methodu kullanılıyor
        Page<ContactMessageDTO> dtoPage = contactMessagePage.map(
                new java.util.function.Function<ContactMessage, ContactMessageDTO>() {
                    @Override
                    public ContactMessageDTO apply(ContactMessage contactMessage) {
                        return contactMessageMapper.contactMessageToDTO(contactMessage);
                    }
                }
        );
        return dtoPage;
    }
}
