package br.com.app.domain.mysql.controller;

import java.util.List;

import br.com.app.domain.mysql.entity.Jogo;
import br.com.app.domain.mysql.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jogo")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public void salvarJogo(@RequestBody Jogo[] jogo) {

        for (int i = 0; i < jogo.length; i++) {
            jogoService.save(jogo[i]);
        }

    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Jogo> getJogos() {
        return jogoService.findAll();
    }

}
