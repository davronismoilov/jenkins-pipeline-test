package task1.isdaha.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task1.isdaha.payload.SeatDto;
import task1.isdaha.service.SeatsService;

@RestController
@RequestMapping("api/v1/seats")
@RequiredArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<?> getSeatsById(@PathVariable("id") long id) {
        return ResponseEntity.ok(seatsService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllSeats(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.ok(seatsService.all(pageSize, pageNumber));
    }

    @PostMapping
    public ResponseEntity<?> add(
        @RequestBody SeatDto seatDto
    ) {
        return ResponseEntity.ok(seatsService.add(seatDto));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateProgram(@PathVariable("id") long id,@RequestBody SeatDto se){
//        return ResponseEntity.ok(seatsService.update(id ,se));
//    }


}
