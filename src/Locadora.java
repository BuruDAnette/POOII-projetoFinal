package src;

import java.time.LocalDateTime;
import java.util.HashMap;

import src.interfaces.IPessoaRepository;
import src.interfaces.IVeiculoRepository;
import src.models.Pessoa;
import src.models.Recibo;
import src.models.Veiculo;

public class Locadora {
    private HashMap<Veiculo, Pessoa> locadora;
    private String local;

    public Locadora(String local) {
        this.locadora = new HashMap<>();
        this.local = local;
    }

    public void alugar(IVeiculoRepository<Veiculo> veiculoRepository, IPessoaRepository<Pessoa> pessoaRepository, String placa, String documento) {
        Veiculo veiculo = veiculoRepository.consultar(placa);
        Pessoa pessoa = documento.length() == 11 ? pessoaRepository.consultarCPF(documento) : pessoaRepository.consultarCNPJ(documento);

        if (veiculo == null || pessoa == null) {
            System.out.println("Veículo ou Pessoa não encontrado.");
            return;
        }

        if (locadora.containsKey(veiculo) || !veiculo.getDisponivel()) {
            System.out.println("Veículo não está disponível: " + veiculo.getMarca());
        } else {
            locadora.put(veiculo, pessoa);
            veiculo.setDiaAlugado(LocalDateTime.now());
            veiculo.setDisponivel(false);
            Recibo.listarInformacoesDeAluguel(veiculo, pessoa, local);
        }
    }
}
