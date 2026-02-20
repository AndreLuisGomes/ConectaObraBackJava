package com.conectaobra.services;

import com.conectaobra.models.Suporte;
import com.conectaobra.repositories.SuporteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SuporteService {

    // Dependências \\

    private final SuporteRepository suporteRepository;

    // Métodos para obter \\

    public List<Suporte> obterTodos() {
        return suporteRepository.findAll();
    }

    public List<Suporte> obterPorTag(String tag){
        return suporteRepository.findByTag(tag);
    }

    // Métodos para salvar \\

    @Transactional
    public void salvarSuporte(Suporte suporte){
        suporteRepository.save(suporte);
    }
}
