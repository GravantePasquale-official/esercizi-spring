package it.catalogo.catalogo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdottoForm {
    private String nome;
    private String categoria;
    private int anno_produzione;
    private int quantita;
}
