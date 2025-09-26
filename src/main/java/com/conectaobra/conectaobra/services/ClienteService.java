package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.models.Cliente;
import com.conectaobra.conectaobra.repositories.ClienteRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> obterClientes(){
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
