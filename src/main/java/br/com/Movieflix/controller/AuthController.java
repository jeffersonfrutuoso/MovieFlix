package br.com.Movieflix.controller;

import br.com.Movieflix.config.TokenService;
import br.com.Movieflix.controller.request.LoginRequest;
import br.com.Movieflix.controller.request.UserRequest;
import br.com.Movieflix.controller.response.LoginResponse;
import br.com.Movieflix.controller.response.UserResponse;
import br.com.Movieflix.entity.User;
import br.com.Movieflix.exception.UsernameOrPasswordInvalidException;
import br.com.Movieflix.mapper.UserMapper;
import br.com.Movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest){
        User savedUser = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest userRequest){

        try {
            UsernamePasswordAuthenticationToken userAndPass =  new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);
            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("usuario ou senha invalido");
        }
    }
}
