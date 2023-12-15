package br.com.infnet.api_at.controllers;

import br.com.infnet.api_at.exception.ResourceNotFoundException;
import br.com.infnet.api_at.model.FabricanteModelo;
import br.com.infnet.api_at.model.AvaliacaoModelo;
import br.com.infnet.api_at.model.JogosModelo;
import br.com.infnet.api_at.model.payload.ResponsePayLoad;
import br.com.infnet.api_at.service.JogoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogosController {
    @Autowired
    JogoService jogoService;
    Logger logger = LoggerFactory.getLogger(JogosController.class);
    List<JogosModelo> jogos = initJogos();
    private List<JogosModelo> initJogos() {
        FabricanteModelo fabricante1 = new FabricanteModelo("Logitec", 1983);
        FabricanteModelo fabricante2 = new FabricanteModelo("Midway Games", 1993);
        FabricanteModelo fabricante3 = new FabricanteModelo("Capcom", 1989);

        AvaliacaoModelo avaliar1 = new AvaliacaoModelo("Excelente", "9.0");
        AvaliacaoModelo avaliar2 = new AvaliacaoModelo("Bom", "7.0");
        AvaliacaoModelo avaliar3 = new AvaliacaoModelo("Excelente", "10.0");

        JogosModelo jogo1 = new JogosModelo(0, "Space Invaders", "Atari", fabricante1, avaliar1);
        JogosModelo jogo2 = new JogosModelo(1, "Mortal Kombat II", "Mega Drive", fabricante2, avaliar2);
        JogosModelo jogo3 = new JogosModelo(2, "Yo Noid", "Nitendo 8 Bits", fabricante3, avaliar3);
        ArrayList<JogosModelo> jogos = new ArrayList<>();
        jogos.add(jogo1);
        jogos.add(jogo2);
        jogos.add(jogo3);
        return jogos;
    }

    @GetMapping
    public List<JogosModelo> getAll(){
        logger.info("Get All Jogos");
        return jogoService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        logger.info("Get Jogo: " + id);
        try{
            JogosModelo jogo = jogoService.getByIdWithOptional(id)
                    .orElseThrow(() ->new ResourceNotFoundException("Jogo Não Disponível"));
            return ResponseEntity.ok(jogo);
        } catch (ResourceNotFoundException ex){
            ResponsePayLoad responsePayLoad = new ResponsePayLoad(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responsePayLoad);
        }
    }
    @PostMapping
    public void save(@RequestBody JogosModelo jogo){
        logger.info("Create Jogos" + jogo);
        jogoService.save(jogo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        logger.info("Delete Jogo" + id);
        JogosModelo removed = jogoService.delete(id);
        logger.info("Jogo Deletado: " + removed);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody JogosModelo jogo){
        logger.info("Update Jogo: " + id + " " + "Novo Jogo: " + jogo);
        jogoService.update(id, jogo);
    }

    @GetMapping("/avaliacao")
    public String avaliacao(@RequestParam Optional<String> avaliar, @RequestParam Optional<String> nota){
        String avaliarValue = avaliar.orElse("Sem Avaliação");
        avaliarValue = avaliarValue.toUpperCase();
        logger.info("Avaliação: " + avaliar);

        String notaValue = nota.orElse("Sem Nota");
        notaValue = notaValue.toUpperCase();
        logger.info("Nota do Jogo: " + nota);
        return avaliarValue + " " + notaValue;
    }

}
