package com.app.ventas.service.impl;

import com.app.ventas.dto.ClientDto;
import com.app.ventas.exception.ApiErrorException;
import com.app.ventas.maper.ClientMapper;
import com.app.ventas.persistence.entity.Client;
import com.app.ventas.persistence.repository.ClientRepository;
import com.app.ventas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    private final ClientMapper clientMapper = new ClientMapper();

    public List<ClientDto> findAll() throws ApiErrorException {
        return clientMapper.toListClientDto(clientRepository.findAll());
    }

    @Override
    public List<ClientDto> findAllName(String name) throws ApiErrorException {
        String search = name.trim();

        //correjir el error que  esta ingresando cuando solo mandan espacion
        if (!search.isEmpty()) {
            Optional<List<Client>> client = clientRepository.findAllName(name);
            if (client.isPresent()) {
                return clientMapper.toListClientDto(client.get());
            }
        }
        throw new ApiErrorException("El nombre está vacío, por favor ingresa un nombre válido para realizar la búsqueda");
    }

    @Override
    public ClientDto save(Client client) throws ApiErrorException {
        return clientMapper.toClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDto update(Long id, Client client) throws ApiErrorException {
        if(id == client.getId()){
            Optional<Client> clientExistente = clientRepository.findById(id);
            if(clientExistente.isPresent()){
                return clientMapper.toClientDto(clientRepository.save(client));
            }else {
                throw new ApiErrorException("Tuvimos algún problema editando al cliente");
            }
        }else{
            throw new ApiErrorException("Verificar el id del cliente por la url");
        }

    }

    @Override
    public Map<String, String> delete(Long id) throws ApiErrorException {
        Map<String, String> response = new HashMap<>();

        if(id > 0 && clientRepository.existsById(id)){
            clientRepository.deleteById(id);
            response.put("statusCode", "200");
            response.put("status", "Exits");
            response.put("message", "Client deleted successfully");
        }else{
            response.put("statusCode", "404");
            response.put("status", "Not Found");
            response.put("message", "Client not deleted");
        }
        return response;
    }

}
