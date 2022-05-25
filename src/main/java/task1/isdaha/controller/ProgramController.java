package task1.isdaha.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import task1.isdaha.payload.ProgramDto;
import task1.isdaha.service.ProgramService;

@RestController
@RequestMapping("api/v1/program")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getProgramById(@PathVariable("id") long id){
        return ResponseEntity.ok(programService.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProgram(@RequestParam int pageSize , @RequestParam int pageNumber){
        return  ResponseEntity.ok(programService.all(pageSize, pageNumber));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<?> addProgram(
        @RequestBody ProgramDto programDto
        ){
        return  ResponseEntity.ok(programService.add(programDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProgram(@PathVariable("id") long id,@RequestBody ProgramDto programDto){
        return ResponseEntity.ok(programService.update(id ,programDto));
    }



}
