package br.com.AdOptimize.api.service;

import br.com.AdOptimize.api.dto.AnuncioRequest;
import br.com.AdOptimize.api.dto.AnuncioResponse;
import br.com.AdOptimize.api.model.Anuncio;
import br.com.AdOptimize.api.model.Campanha;
import br.com.AdOptimize.api.repository.AnuncioRepository;
import br.com.AdOptimize.api.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final CampanhaRepository campanhaRepository;

    public List<AnuncioResponse> getAllAnuncio() {
        return anuncioRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AnuncioResponse getAnuncioById(Long id) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio não encontrado"));
        return convertToResponse(anuncio);
    }

    public AnuncioResponse createAnuncio(AnuncioRequest request) {
        Campanha campanha = campanhaRepository.findById(request.getCampanha_id())
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(request.getTitulo());
        anuncio.setPreco(request.getPreco());
        anuncio.setDescricao(request.getDescricao());
        anuncio.setCampanha(campanha);

        // Adiciona o anúncio à campanha e salva a campanha para persistência
        campanha.getAnuncios().add(anuncio);
        anuncio= anuncioRepository.save(anuncio);
        campanhaRepository.save(campanha);

        return convertToResponse(anuncio);
    }


    public AnuncioResponse updateAnuncio(Long id, AnuncioRequest request) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio não encontrado"));

        Campanha campanha = campanhaRepository.findById(request.getCampanha_id())
                .orElseThrow(() -> new RuntimeException("Campanha não encontrado"));

        anuncio.setTitulo(request.getTitulo());
        anuncio.setPreco(request.getPreco());
        anuncio.setDescricao(request.getDescricao());
        anuncio.setCampanha(campanha);
        return convertToResponse(anuncioRepository.save(anuncio));
    }

    public void deleteAnuncio(Long id) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio não encontrado"));
        anuncioRepository.delete(anuncio);
    }

    private AnuncioResponse convertToResponse(Anuncio anuncio) {
        AnuncioResponse response = new AnuncioResponse();
        response.setId(anuncio.getId());
        response.setTitulo(anuncio.getTitulo());
        response.setPreco(anuncio.getPreco());
        response.setDescricao(anuncio.getDescricao());
        response.setNome_campanha(anuncio.getCampanha().getNome());
        return response;
    }


}
