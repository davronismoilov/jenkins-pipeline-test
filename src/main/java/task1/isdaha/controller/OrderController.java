package task1.isdaha.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task1.isdaha.payload.BookingDto;
import task1.isdaha.service.BookingService;

@RestController
@RequestMapping("api/v1/booking")
@RequiredArgsConstructor
public class OrderController {
     private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> bookingProgram(@RequestBody BookingDto bookingDto){
        return ResponseEntity.ok(bookingService.booking(bookingDto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")

    public  ResponseEntity<?> changeStatus(@RequestParam("id") long id , @RequestParam String status){
        return  ResponseEntity.ok(bookingService.changeStatus(id, status));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public  ResponseEntity<?> allTicket(){
        return  ResponseEntity.ok(bookingService.allTicket());
    }

    @GetMapping("/{id}")
    public String pdfTicket(@PathVariable("id") long id){
        bookingService.savePdfTicket(id);
        return "SAve";    }
}
