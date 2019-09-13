package br.com.app.domain.postgres.controller;

import br.com.app.domain.postgres.entity.Carro;
import br.com.app.domain.postgres.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carro")
public class CarroController {

    @Autowired
    private CarroService service;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    private void salvar(@RequestBody Carro[] carro){
        for (int i = 0; i < carro.length; i++) {
            service.save(carro[i]);
        }
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    private List<Carro> listar(){
        return service.findAll();
    }

    @RequestMapping(value = "/deletar", method = RequestMethod.DELETE)
    public void deletar(@RequestParam(name = "id") Long id){
        service.delete(id);
    }

    @RequestMapping(value = "encontrar-por-id", method = RequestMethod.GET)
    public Carro encontrarPorId(@RequestParam(name = "id") Long id){
      return service.findById(id);
    }

}
