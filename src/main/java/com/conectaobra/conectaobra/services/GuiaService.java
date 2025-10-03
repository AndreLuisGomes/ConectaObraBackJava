package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.repositories.GuiaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GuiaService {

    private final GuiaRepository guiaRepository;

    public List<Guia> obterTodos(){
        return guiaRepository.findAll();
    }

    public Guia salvarGuia(Guia guia){
        return guiaRepository.save(guia);
    }

    public Guia obterGuiaPorNome(String nome){
        return guiaRepository.findByNome(nome);
    }

    public Guia atualizarGuia(Guia guia){
        Optional<Guia> guiaEncontrada = guiaRepository.findById(guia.getId());
        if(guiaEncontrada.isPresent()){
            return guiaRepository.save(guia);
        }
        return guia;
    }

    public void deletarGuia(UUID uuid){
        guiaRepository.deleteById(uuid);
    }
}
