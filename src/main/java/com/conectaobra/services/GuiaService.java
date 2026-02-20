package com.conectaobra.services;

import com.conectaobra.dtos.GuiaDTO;
import com.conectaobra.models.Guia;
import com.conectaobra.repositories.GuiaRepository;
import com.conectaobra.repositories.specs.GuiaSpecs;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GuiaService {

    // Dependências \\

    private final GuiaRepository guiaRepository;

    // Métodos para obter \\

    public List<Guia> obterGuiaPorParametros(GuiaDTO guiaDTO){

        Specification<Guia> specs = Specification.where(null);

        if(StringUtils.hasText(guiaDTO.nome())){
            specs = specs.and(GuiaSpecs.nomeLike(String.valueOf(guiaDTO.clienteId())));
        }

        if(StringUtils.hasText(guiaDTO.local())){
            specs = specs.and(GuiaSpecs.localLike(guiaDTO.local()));
        }

        if (StringUtils.hasText(String.valueOf(guiaDTO.clienteId()))) {
            specs = specs.and(GuiaSpecs.nomeClienteLike(String.valueOf(guiaDTO.clienteId())));
        }

        return guiaRepository.findAll();
    }

    public Guia obterGuiaPorNome(String nome){
        return guiaRepository.findByNome(nome);
    }

    // Métodos para salvar \\

    @Transactional
    public Guia salvarGuia(Guia guia){
        return guiaRepository.save(guia);
    }

    // Métodos para atualizar \\

    @Transactional
    public Guia atualizarGuia(Guia guia){
        Optional<Guia> guiaEncontrada = guiaRepository.findById(guia.getId());
        if(guiaEncontrada.isPresent()){
            return guiaRepository.save(guia);
        }
        return guia;
    }

    // Métodos para deletar \\

    @Transactional
    public void deletarGuia(UUID uuid){
        guiaRepository.deleteById(uuid);
    }
}
