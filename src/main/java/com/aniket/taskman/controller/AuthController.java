package com.aniket.taskman.controller;


import com.aniket.taskman.dto.LoginRequestDTO;
import com.aniket.taskman.dto.LoginResponseDTO;
import com.aniket.taskman.dto.SignupRequestDTO;
import com.aniket.taskman.dto.SignupResponseDTO;
import com.aniket.taskman.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {

        return ResponseEntity.ok(authService.login(loginRequestDTO));

    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody @Valid SignupRequestDTO signupRequestDTO) {

        return ResponseEntity.ok(authService.signup(signupRequestDTO));

    }

}
