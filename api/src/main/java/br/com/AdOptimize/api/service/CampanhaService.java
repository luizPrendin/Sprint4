package br.com.AdOptimize.api.service;

import br.com.AdOptimize.api.dto.AnuncioResponse;
import br.com.AdOptimize.api.dto.CampanhaRequest;
import br.com.AdOptimize.api.dto.CampanhaResponse;
import br.com.AdOptimize.api.model.Campanha;
import br.com.AdOptimize.api.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CampanhaService {

    private final CampanhaRepository campanhaRepository;

    public List<CampanhaResponse> getAllCampanhas() {
        return campanhaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CampanhaResponse getCampanhaById(Long id) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));
        return convertToResponse(campanha);
    }

    public CampanhaResponse createCampanha(CampanhaRequest request) {
        Campanha campanha = new Campanha();
        campanha.setNome(request.getNome());
        campanha = campanhaRepository.save(campanha);
        return convertToResponse(campanha);
    }

    public CampanhaResponse updateCampanha(Long id, CampanhaRequest request) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));
        campanha.setNome(request.getNome());
        return convertToResponse(campanhaRepository.save(campanha));
    }

    public void deleteCampanha(Long id) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));
        campanhaRepository.delete(campanha);
    }

    private CampanhaResponse convertToResponse(Campanha campanha) {
        CampanhaResponse response = new CampanhaResponse();
        response.setId(campanha.getId());
        response.setNome(campanha.getNome());

        // Verifica se a lista de anuncios não é nula
        if (campanha.getAnuncios() != null) {
            response.setAnuncios(campanha.getAnuncios().stream()
                    .map(anuncio -> {
                        AnuncioResponse anuncioResponse = new AnuncioResponse();
                        anuncioResponse.setId(anuncio.getId());
                        anuncioResponse.setTitulo(anuncio.getTitulo());
                        anuncioResponse.setPreco(anuncio.getPreco());
                        anuncioResponse.setDescricao(anuncio.getDescricao());
                        anuncioResponse.setNome_campanha(campanha.getNome());
                        return anuncioResponse;
                    }).collect(Collectors.toList()));
        } else {
            // Se for nula, inicializa a lista de alunos vazia
            response.setAnuncios(new ArrayList<>());
        }

        return response;
    }



}
