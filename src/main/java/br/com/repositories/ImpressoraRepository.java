package br.com.repositories;

import br.com.models.Impressora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpressoraRepository extends JpaRepository<Impressora, Long> {

    boolean existsByIp(String ip);

    boolean existsByPatrimonio(String patrimonio);

    List<Impressora> findByFilial_Id(Long filialId);


}
