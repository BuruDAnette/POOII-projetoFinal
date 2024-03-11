package src;

import java.time.LocalDateTime;
import java.util.HashMap;

import src.models.Pessoa;
import src.models.Recibo;
import src.models.Veiculo;

public class Locadora {
    private HashMap<Veiculo, Pessoa> locadora;
    private String local;

    public Locadora(String local) {
        locadora = new HashMap<>();
        this.local = local;
    }

    public void alugar(Veiculo veiculo, Pessoa pessoa) {
        if (locadora.containsKey(veiculo) || veiculo.getDisponivel() == false) {
            System.out.println("Veiculo não está disponivel: " + veiculo.getMarca());
        } else {
            locadora.put(veiculo, pessoa);
            veiculo.setDiaAlugado(LocalDateTime.now());
            veiculo.setDisponivel(false);
            Recibo.listarInformacoesDeAluguel(veiculo, pessoa, local);
        }
    }
}
