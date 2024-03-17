package src;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import src.interfaces.IPessoaRepository;
import src.interfaces.IVeiculoRepository;
import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;
import src.models.Recibo;
import src.models.Veiculo;
import src.utils.TipoVeiculo;

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

    public void devolver(IVeiculoRepository<Veiculo> veiculoRepository, String placa, LocalDateTime dataDevolucao) {
        Veiculo veiculo = veiculoRepository.consultar(placa);
        
        if (veiculo == null || !locadora.containsKey(veiculo)) {
            System.out.println("Veículo não encontrado ou não está alugado.");
            return;
        }
        
        Pessoa cliente = locadora.get(veiculo);
        locadora.remove(veiculo);
        
        int quantidadeDiarias = calcularQuantidadeDiarias(veiculo.getDiaAlugado(), dataDevolucao);
        double valorTotal = calcularValorTotal(veiculo.getTipoVeiculo(), quantidadeDiarias);
        
        double desconto = calcularDesconto(cliente, quantidadeDiarias);
        double valorFinal = valorTotal - desconto;
        
        emitirRecibo(veiculo, cliente, local, quantidadeDiarias, valorTotal, desconto, valorFinal);
        
        veiculo.setDiaAlugado(null);
        veiculo.setDiaDevolucao(dataDevolucao);
        veiculo.setDisponivel(true);
    }
    
    private int calcularQuantidadeDiarias(LocalDateTime dataAlugado, LocalDateTime dataDevolucao) {
        if (dataDevolucao.isBefore(dataAlugado)) {
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de aluguel.");
        }
    
        // Calcular diferença em dias
        long diasDiferenca = ChronoUnit.DAYS.between(dataAlugado, dataDevolucao);
    
        // Se a diferença for zero, considerar uma diária
        if (diasDiferenca == 0) {
            return 1;
        }
    
        // Se a hora de devolução for menor que a hora de aluguel, considerar mais uma diária
        if (dataDevolucao.getHour() < dataAlugado.getHour()) {
            diasDiferenca++;
        }
    
        return (int) diasDiferenca;
    }

    private double calcularValorTotal(TipoVeiculo tipoVeiculo, int quantidadeDiarias) {
        double valorDiario = 0;
        
        switch (tipoVeiculo) {
            case PEQUENO:
                valorDiario = 100.0;
                break;
            case MEDIO:
                valorDiario = 150.0;
                break;
            case SUV:
                valorDiario = 200.0;
                break;
        }
        
        return valorDiario * quantidadeDiarias;
    }
    
    private double calcularDesconto(Pessoa cliente, int quantidadeDiarias) {
        if (cliente instanceof PessoaFisica && quantidadeDiarias > 5) {
            return 0.05;
        } else if (cliente instanceof PessoaJuridica && quantidadeDiarias > 3) {
            return 0.1;
        }
        return 0;
    }
    
    private void emitirRecibo(Veiculo veiculo, Pessoa cliente, String local, int quantidadeDiarias, double valorTotal, double desconto, double valorFinal) {
        // Implementação específica para gerar o recibo
        // Exemplo: gerar um PDF, enviar por email, etc.

        System.out.println("### Recibo de Devolução ###");
        System.out.println("Veículo: " + veiculo.getPlaca() + " - " + veiculo.getMarca());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Local: " + local);
        System.out.println("Data de Devolução: " + LocalDateTime.now());
        System.out.println("Quantidade de Diárias: " + quantidadeDiarias);
        System.out.println("Valor Total: R$" + valorTotal);
        System.out.println("Desconto: R$" + desconto);
        System.out.println("Valor Final: R$" + valorFinal);
        System.out.println("--------------------------");
    }


}