package com.app.ventas.web.controller;

import com.app.ventas.dto.security.LoginRequestDto;
import com.app.ventas.dto.security.LoginResponseDto;
import com.app.ventas.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticacionController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDto> authenticate(
            @Valid @RequestBody LoginRequestDto loginRequestDto
            ){
        return ResponseEntity.ok().body(authenticationService.authenticate(loginRequestDto));
    }

    @PreAuthorize("permitAll")
        @PostMapping("/logout")
    public void logout() throws Exception {
        authenticationService.logout();
    }
}
