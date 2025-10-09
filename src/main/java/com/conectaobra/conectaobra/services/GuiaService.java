package com.conectaobra.conectaobra.services;

import com.conectaobra.conectaobra.dtos.GuiaDTO;
import com.conectaobra.conectaobra.models.Guia;
import com.conectaobra.conectaobra.repositories.GuiaRepository;
import com.conectaobra.conectaobra.repositories.specs.GuiaSpecs;
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

    private final GuiaRepository guiaRepository;

    public List<Guia> obterGuiaPorParametros(GuiaDTO guiaDTO){

        Specification<Guia> specs = Specification.where(null);

        if(StringUtils.hasText(guiaDTO.nome())){
            specs = specs.and(GuiaSpecs.nomeLike(guiaDTO.nomeCliente()));
        }

        if(StringUtils.hasText(guiaDTO.local())){
            specs = specs.and(GuiaSpecs.localLike(guiaDTO.local()));
        }

        if (StringUtils.hasText(guiaDTO.nomeCliente())) {
            specs = specs.and(GuiaSpecs.nomeClienteLike(guiaDTO.nomeCliente()));
        }
        if(guiaDTO.guiaStatus() != null){
            specs = specs.and(GuiaSpecs.guiaStatusEqual(guiaDTO.guiaStatus()));
        }

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
