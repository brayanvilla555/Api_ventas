package com.app.ventas.service;

import com.app.ventas.dto.security.LoginRequestDto;
import com.app.ventas.dto.security.LoginResponseDto;
import com.app.ventas.persistence.entity.User;
import com.app.ventas.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {

    @Autowired
    private HttpSecurity http;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
        UserDetails user =  userDetailsService.loadUserByUsername(loginRequestDto.username());

        /*las credenciales para iniciar session*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequestDto.username() ,loginRequestDto.password(), user.getAuthorities()
                );


        //si pasamos esta linea entonces quiere decir que hemos iniciado session
        authenticationManager.authenticate(authentication);

        //creacion del jwt
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new LoginResponseDto(jwt);
    }

    private static Map<String, Object> generateExtraClaims(UserDetails user){
        Map<String, Object> extraClaims = new HashMap<>();

        String roleName = ((User) user).getRole().getName().name();
        extraClaims.put("Role", roleName);
        extraClaims.put("Authorization", user.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()));

        return extraClaims;

    }

    //para cerrar sesion
    public void logout() throws Exception {
        http.logout( logoutConfig -> {
            logoutConfig.deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true);
        });
    }
}
