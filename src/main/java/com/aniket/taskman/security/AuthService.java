package com.aniket.taskman.security;

import com.aniket.taskman.dto.LoginRequestDTO;
import com.aniket.taskman.dto.LoginResponseDTO;
import com.aniket.taskman.dto.SignupRequestDTO;
import com.aniket.taskman.dto.SignupResponseDTO;
import com.aniket.taskman.entity.User;
import com.aniket.taskman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.jspecify.annotations.Nullable;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {

        User user = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User Already Exists");

        user = userRepository.save(User.builder()
                .username(signupRequestDTO.getUsername())
                .password(signupRequestDTO.getPassword())
                .build()
        );

        return modelMapper.map(user, SignupResponseDTO.class);

    }
}
