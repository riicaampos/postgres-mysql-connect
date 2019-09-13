package br.com.app.domain.mysql.service;

import java.util.List;

import br.com.app.domain.mysql.entity.Jogo;
import br.com.app.domain.mysql.repository.JogoRepository;
import br.com.app.domain.utils.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoService implements BasicCrudService<Jogo, Long> {

    @Autowired
    private JogoRepository repo;

    @Override
    public void save(Jogo toSave) {
        repo.save(toSave);

    }

    @Override
    public void delete(Long toDelete) {
        repo.deleteById(toDelete);
    }

    @Override
    public Jogo findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Jogo> findAll() {
        return repo.findAll();
    }

}