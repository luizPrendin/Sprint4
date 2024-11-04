package br.com.AdOptimize.api.dto;

import br.com.AdOptimize.api.model.Anuncio;
import lombok.Data;

import java.util.List;

@Data
public class CampanhaResponse {

    private Long id;
    private String nome;
    private List<AnuncioResponse> anuncios;

}
