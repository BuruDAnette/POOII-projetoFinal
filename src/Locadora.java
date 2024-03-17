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
    //------------------------------CORES------------------------------//
    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

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
            System.out.println(RED_BOLD + "DOCUMENTO INVÁLIDO" + RESET);
            return;
        }

        if (veiculo == null || pessoa == null) {
            System.out.println(RED_BOLD + "VEÍCULO OU PESSOA NÃO ENCONTRADO" + RESET);
            return;
        }

        if (locadora.containsKey(veiculo) || !veiculo.getDisponivel()) {
            System.out.println(RED_BOLD + "ESSE VEÍCULO JA ESTÁ ALUGADO: " + veiculo.getMarca() + veiculo.getPlaca() + RESET);
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
            System.out.println(RED_BOLD + "VEÍCULO NÃO ENCONTRADO OU NÃO ESTÁ ALUGADO" + RESET);
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
            throw new IllegalArgumentException(RED_BOLD + "DATA DE DEVOLução DEVE SER POSTERIOR A DATA DE ALUGUEL" + RESET);
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

        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + "                      RECIBO DE DEVOLUÇÃO                      " + RESET);
        System.out.println("VEÍCULO: " + veiculo.getPlaca() + " - " + veiculo.getMarca());
        System.out.println("CLIENTE: " + cliente.getNome());
        System.out.println("LOCAL: " + local);
        System.out.println("DATA DE DEVOLUÇÃO: " + LocalDateTime.now());
        System.out.println("NÚMERO DE DIÁRIAS: " + quantidadeDiarias);
        System.out.println("VALOR TOTAL: " +  GREEN_BOLD + "R$" + valorTotal + RESET);
        System.out.println("DESCONTO: " + RED_BOLD + "R$" + desconto + RESET);
        System.out.println("VALOR FINAL: R$" + valorFinal);
        System.out.println();
    }


}