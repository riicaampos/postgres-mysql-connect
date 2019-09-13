package br.com.app.domain.postgres.repository;

import br.com.app.domain.postgres.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
