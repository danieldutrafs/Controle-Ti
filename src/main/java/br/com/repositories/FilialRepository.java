package br.com.repositories;

import br.com.models.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    boolean existsByCodigoFilial(String codigoFilial);

}
