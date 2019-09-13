package br.com.app.domain.postgres.service;

import br.com.app.domain.postgres.entity.Carro;
import br.com.app.domain.postgres.repository.CarroRepository;
import br.com.app.domain.utils.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService implements BasicCrudService<Carro, Long> {

    @Autowired
    private CarroRepository repo;

    @Override
    public void save(Carro toSave) {
        repo.save(toSave);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Carro findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Carro> findAll() {
        return repo.findAll();
    }
}
