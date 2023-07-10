package com.hackathon.ehealthcareproject.service.auth;

import com.hackathon.ehealthcareproject.dto.AuthResponseDto;
import com.hackathon.ehealthcareproject.dto.RegisterResponseDto;
import com.hackathon.ehealthcareproject.dto.email.EmailDetails;
import com.hackathon.ehealthcareproject.dto.users.UserLoginRequestDto;
import com.hackathon.ehealthcareproject.dto.users.UserRegisterRequestDto;
import com.hackathon.ehealthcareproject.entity.RolesEntity;
import com.hackathon.ehealthcareproject.entity.TokenEntity;
import com.hackathon.ehealthcareproject.entity.TokenType;
import com.hackathon.ehealthcareproject.entity.UserEntity;
import com.hackathon.ehealthcareproject.exceptions.BadRequestException;
import com.hackathon.ehealthcareproject.repository.RolesRepository;
import com.hackathon.ehealthcareproject.repository.TokenRepository;
import com.hackathon.ehealthcareproject.repository.UserRepository;
import com.hackathon.ehealthcareproject.securityConfig.JWTService;
import com.hackathon.ehealthcareproject.service.emails.EmailServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthServiceInterface {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private JWTService jwtService;
    private EmailServiceInterface emailServiceInterface;

    @Override
    public RegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        //check if username or email exist
        if (userRepository.existsByUsernameOrEmail(userRegisterRequestDto.getUsername(), userRegisterRequestDto.getEmail())) {
            throw new BadRequestException("Username or email already exists. Choose another one.");
        } else {

            RolesEntity roles = rolesRepository.findByName("USER");
            UserEntity user = UserEntity.builder()
                    .firstName(userRegisterRequestDto.getFirstName())
                    .lastName(userRegisterRequestDto.getLastName())
                    .username(userRegisterRequestDto.getUsername())
                    .email(userRegisterRequestDto.getEmail())
                    .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                    .roles(Collections.singleton(roles))
                    .build();

            userRepository.save(user);
            //send email upon registration
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(user.getEmail())
                    .subject("Welcome to SkinLikeMilk")
                    .messageBody("Hi " + user.getUsername() + ", Thank you for choosing SkinLikeMilk. We can’t wait to help you take charge of your health. So, let’s get started.\n" +
                            "\n" +
                            "             One app for total health\n" +
                            "             SkinLikeMilk is packed with healthy features, waiting to support you and your health. We are so glad to have you here.  ")
                    .build();

            emailServiceInterface.sendSimpleMessage(emailDetails);


            return RegisterResponseDto.builder().response("User successfully registered").build();

        }
    }

    @Override
    public AuthResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        //get authentication object from the
        //get context from security context holder and set the authentication object with the authentication object gotten from the authentication manager.
        //return string login successful
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication.getName());
        UserEntity user = userRepository.findUserByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        revokeValidTokens(user);
        TokenEntity tokenEntity = TokenEntity.builder()
                .user(user)
                .token(token)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(tokenEntity);

        return AuthResponseDto.builder().token(token).userId(user.getId()).username(user.getUsername()).build();
    }

    private void revokeValidTokens(UserEntity users) {
        List<TokenEntity> tokenEntityList = tokenRepository.findAllValidTokensByUser(users.getId());
        if (tokenEntityList.isEmpty())
            return;
        tokenEntityList.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(tokenEntityList);
    }
}

