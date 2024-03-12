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
        locadora = new HashMap<>();
        this.local = local;
    }

    public void alugar(IVeiculoRepository<Veiculo> veiculoRepository, IPessoaRepository<Pessoa> pessoaRepository, String placa, String documento) {
        Veiculo veiculo = veiculoRepository.consultar(placa);
        Pessoa pessoa;

        //regex
        if (documento.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            pessoa = pessoaRepository.consultarCPF(documento);
        } else if (documento.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
            pessoa = pessoaRepository.consultarCNPJ(documento);
        } else {
            System.out.println("Documento inválido.");
            return;
        }

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