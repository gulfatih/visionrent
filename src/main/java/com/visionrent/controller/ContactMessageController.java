package com.visionrent.controller;
import com.visionrent.domain.ContactMessage;
import com.visionrent.dto.ContactMessageDTO;
import com.visionrent.dto.request.ContactMessageRequest;
import com.visionrent.dto.response.ResponseMessage;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.mapper.ContactMessageMapper;
import com.visionrent.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ContactMessageDTO>> getAllMessage() {
        List<ContactMessage> contactMessageList = contactMessageService.getAll();
        List<ContactMessageDTO> contactMessageDTOList = contactMessageMapper.map(contactMessageList);
        return ResponseEntity.ok(contactMessageDTOList);
    }

    @GetMapping("/pages")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactMessageDTO> getMessageWithPath(@PathVariable("id") Long id){
        ContactMessage contactMessage = contactMessageService.getContactMessage(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToDTO(contactMessage);

        return ResponseEntity.ok(contactMessageDTO);
    }

    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContactMessageDTO> getMessageWithRequestParam(@RequestParam("id") Long id){
        ContactMessage contactMessage = contactMessageService.getContactMessage(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToDTO(contactMessage);

        return ResponseEntity.ok(contactMessageDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VRResponse> deleteContactMessage(@PathVariable Long id){
        contactMessageService.deleteContactMessage(id);
        VRResponse vrResponse = new VRResponse(ResponseMessage.CONTACTMESSAGE_DELETE_RESPONSE, true);

        return ResponseEntity.ok(vrResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VRResponse>  updateContactMessage(@PathVariable Long id, @Valid @RequestBody ContactMessageRequest contactMessageRequest  ){
        ContactMessage contactMessage = contactMessageMapper.contactMessageRequestToContactMessage(contactMessageRequest);
        contactMessageService.updateContactMessage(id, contactMessage);

        VRResponse vrResponse =
                new VRResponse(ResponseMessage.CONTACTMESSAGE_UPDATE_RESPONSE, true);

        return ResponseEntity.ok(vrResponse);
    }
}
