package br.com.app.domain.mysql.repository;

import br.com.app.domain.mysql.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

}
