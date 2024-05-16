package com.app.ventas.maper;

import com.app.ventas.dto.ClientDto;
import com.app.ventas.dto.ProductDto;
import com.app.ventas.persistence.entity.Client;
import com.app.ventas.persistence.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {
    public static ClientDto toClientDto(Client client){
        ClientDto clientDto = new ClientDto();
        return clientDto.builder().id(client.getId())
                .name(client.getName())
                .lastname(client.getLastname())
                //.sales(client.getSales())
                .identificationType(client.getIdentificationType())
                .identificationNumber(client.getIdentificationNumber())
                .stateClient(client.getStateClient())
                .created_at(client.getCreated_at())
                .build();
    }


    public static Client toClient(ClientDto clientDto){
        Client client = new Client();
        return client.builder().id(clientDto.getId())
                .name(clientDto.getName())
                .lastname(clientDto.getLastname())
                //.sales(clientDto.getSales())
                .identificationType(clientDto.getIdentificationType())
                .identificationNumber(clientDto.getIdentificationNumber())
                .stateClient(clientDto.getStateClient())
                .created_at(clientDto.getCreated_at())
                .build();
    }

    public static List<ClientDto> toListClientDto(List<Client> clients){
        List<ClientDto> clientsDto = new ArrayList<>();
        clients.stream().forEach(client -> {
            clientsDto.add(toClientDto(client));
        });
        return clientsDto;
    }


}
