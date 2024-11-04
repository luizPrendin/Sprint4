package br.com.AdOptimize.api.dto;

import lombok.Data;

@Data
public class AnuncioResponse {

    private Long id;
    private String titulo;
    private double preco;
    private String descricao;
    private String nome_campanha;


}
