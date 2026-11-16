package br.com.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomeUsuario;

    @NotBlank
    @Column(unique = true)
    private String user;

    @NotBlank
    private String senhas;

    @NotBlank
    private boolean isAdmin;
}
