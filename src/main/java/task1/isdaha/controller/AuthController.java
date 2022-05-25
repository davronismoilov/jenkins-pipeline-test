package task1.isdaha.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task1.isdaha.payload.RegisterUserReq;
import task1.isdaha.service.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final UserService userservice;

    public AuthController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("register")
    public  ResponseEntity<?> register(@RequestBody @Validated RegisterUserReq userReq){
        return ResponseEntity.ok(userservice.register(userReq));
    }
}
