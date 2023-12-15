package br.com.infnet.api_at.service;

import br.com.infnet.api_at.model.AvaliacaoModelo;
import br.com.infnet.api_at.model.FabricanteModelo;
import br.com.infnet.api_at.model.JogosModelo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    Logger logger = LoggerFactory.getLogger(JogoService.class);
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

    public List<JogosModelo> getAll(){
        return jogos;
    }

    public Optional<JogosModelo> getByIdWithOptional(int id) {
        JogosModelo jogo = jogos.get(id);
        if (jogo == null) return Optional.empty();
        return Optional.of(jogo);
    }

    public void save(JogosModelo jogo) {
        jogos.add(jogo);
    }

    public JogosModelo delete(int id) {
        return jogos.remove(id);
    }

    public void update (int id, JogosModelo jogo) {
        jogos.remove(id);
        jogo.setId((int)id);
        jogos.add(id, jogo);
    }
}
