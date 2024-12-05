package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.user.AuthCreateUserRequest;
import com.pharmanet.presentation.dto.user.AuthLoginRequest;
import com.pharmanet.presentation.dto.user.AuthResponse;
import com.pharmanet.service.user.UserDetailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Login y registrar un usuario para obtener un token")
public class AuthenticationController {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Operation(
            summary = "Usuario de inicio de sesión",
            description = "Autenticar a un usuario y devolver el token de autenticación.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Solicitud de autenticación con nombre usuario y contraseña",
                    required = true
            )
    )
    @PostMapping("/log-in")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Registro de usuario",
            description = "Registrar a un usuario y devolver el token de autenticación.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Solicitud de registro con nombre de usuario y contraseña",
                    required = true
            )
    )
    @PostMapping("/sign-up")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }
}
