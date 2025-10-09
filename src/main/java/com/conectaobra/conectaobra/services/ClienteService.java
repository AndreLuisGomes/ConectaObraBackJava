package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.dtos.ClienteDTO;
import com.conectaobra.conectaobra.models.Cliente;
import com.conectaobra.conectaobra.repositories.ClienteRepository;
import com.conectaobra.conectaobra.repositories.specs.ClienteSpecs;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@Data
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> obterClientes(){
        return clienteRepository.findAll();
    }

    public List<Cliente> obterClientesPorNome(String nome){
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deletarClientePorId(UUID clienteId){
        clienteRepository.deleteById(clienteId);
    }

    public List<Cliente> obterClientesPorParametros(ClienteDTO clienteDTO) {

        Specification<Cliente> specs = Specification.where(null);

        if(StringUtils.hasText(clienteDTO.nome())){
            specs = specs.and(ClienteSpecs.nomeLike(clienteDTO.nome()));
        }
        if(StringUtils.hasText(clienteDTO.contato())){
            specs = specs.and(ClienteSpecs.contatoLike(clienteDTO.contato()));
        }
        if(StringUtils.hasText(clienteDTO.localizacaoSede())){
            specs = specs.and(ClienteSpecs.localizacaoLike(clienteDTO.localizacaoSede()));
        }
        return clienteRepository.findAll(specs);
    }
}
