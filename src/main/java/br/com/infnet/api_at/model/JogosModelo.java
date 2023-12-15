package br.com.infnet.api_at.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@NoArgsConstructor@Builder
public class JogosModelo {
    private int id;
    private String nome;
    private String console;
    private FabricanteModelo fabricante;
    private AvaliacaoModelo avaliar;

}
