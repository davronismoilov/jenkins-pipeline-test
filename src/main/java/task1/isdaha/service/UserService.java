package task1.isdaha.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task1.isdaha.response.Response;
import task1.isdaha.entity.Role;
import task1.isdaha.entity.User;
import task1.isdaha.enums.RoleEnum;
import task1.isdaha.exception.CustomException;
import task1.isdaha.payload.RegisterUserReq;
import task1.isdaha.repository.RoleRepository;
import task1.isdaha.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService  {


    private final UserRepository userRepository;
    private final  ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Response register(RegisterUserReq userReq) {
        if (userRepository.existsByEmail(userReq.getEmail())){
            return new Response("This email already exist" , -10);
        }
        User map = modelMapper.map(userReq, User.class);
        Role role = roleRepository.findByRole(RoleEnum.USER)
                .orElseThrow(() -> new CustomException("role not found"));
        map.setRole(Collections.singleton(role));
        map.setPassword(passwordEncoder.encode(userReq.getPassword()));
        userRepository.save(map);
        return  new Response("Successfully" , 10);

    }
}
