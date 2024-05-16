package com.app.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VentasApplication.class, args);
    }

    //creamos contraseñas de prueba
/*
   @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createPasswordsCommand(){
        return args -> {
            System.out.println(passwordEncoder.encode("12345"));
            System.out.println(passwordEncoder.encode("67890"));
        };
    }

*/
}
