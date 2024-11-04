package br.com.AdOptimize.api.dto;

import br.com.AdOptimize.api.model.Campanha;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnuncioRequest {

    @NotBlank(message = "O titulo é obrigatório")
    private String titulo;

    @NotNull
    private double preco;

    private String descricao;

    private Long campanha_id;

}
