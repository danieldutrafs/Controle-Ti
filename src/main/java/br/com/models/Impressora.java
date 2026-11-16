package br.com.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Impressora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Filial filial;

    @NotBlank
    private String localizacao;

    @NotBlank
    @Column(unique = true)
    private String ip;

    @NotBlank
    @Column(unique = true)
    private String patrimonio;

    @NotBlank
    private String modelo;
}