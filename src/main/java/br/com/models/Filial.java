package br.com.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(unique = true)
    private String codigoFilial;

    @NotBlank
    private String cnpj;

    public Filial(String cnpj, String codigoFilial, Long id, String nome) {
        this.cnpj = cnpj;
        this.codigoFilial = codigoFilial;
        this.id = id;
        this.nome = nome;
    }

    //construtor para sprig boot
    public Filial() {
    }
}
