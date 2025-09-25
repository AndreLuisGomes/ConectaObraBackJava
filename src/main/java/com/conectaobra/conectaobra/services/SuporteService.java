package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.models.Suporte;
import com.conectaobra.conectaobra.repositories.SuporteRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class SuporteService {

    // Injeção de dependências

    private final SuporteRepository suporteRepository;

    // Métodos para obtenção

    public List<Suporte> obterTodos() {
        return suporteRepository.findAll();
    }

    public List<Suporte> obterPorTag(String tag){
        return suporteRepository.findByTag();
    }

    public void salvarSuporte(Suporte suporte){
        suporteRepository.save(suporte);
    }
}
