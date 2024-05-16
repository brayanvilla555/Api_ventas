package com.app.ventas.dto;

import com.app.ventas.persistence.entity.Client;
import com.app.ventas.persistence.entity.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    private Long id;
    private String name;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Client.IdentificationType identificationType;

    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    private Client.StateClient stateClient;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime created_at;

    private List<Sale> sales;

}