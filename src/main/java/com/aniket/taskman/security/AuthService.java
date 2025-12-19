package com.aniket.taskman.security;

import com.aniket.taskman.dto.LoginRequestDTO;
import com.aniket.taskman.dto.LoginResponseDTO;
import com.aniket.taskman.dto.SignupRequestDTO;
import com.aniket.taskman.dto.SignupResponseDTO;
import com.aniket.taskman.entity.User;
import com.aniket.taskman.entity.type.AuthProviderType;
import com.aniket.taskman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.jspecify.annotations.Nullable;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

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
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .build()
        );

        return modelMapper.map(user, SignupResponseDTO.class);

    }

    @Transactional
    public ResponseEntity<LoginResponseDTO> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        AuthProviderType providerType = authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = (User) userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");

        String emailUser = String.valueOf(userRepository.findByUsername(email).orElse(null));

        if(user == null && emailUser == null) {
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
//            SignupResponseDTO signupResponseDTO = signup(new SignupRequestDTO(username, null));
            user = signupInternal(new SignupRequestDTO(username, null));
        } else if(user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered with provider likely: " + email);
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDTO);
//
//        fetch providerId and providerType, and save these pieces of info with user
//        if user already got an account -> direct login
//        else, first signup and thne login

    }

    public User signupInternal(SignupRequestDTO signupRequestDTO) {
        User user = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if(user != null) throw new IllegalArgumentException("User Already Exists");

        user = userRepository.save(User.builder()
                .username(signupRequestDTO.getUsername())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .build()
        );

        return user;
    }
}
