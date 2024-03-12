package src.models;

import java.time.format.DateTimeFormatter;

/**
 * The type Recibo.
 */
public class Recibo {
    /**
     * Listar informacoes de aluguel.
     *
     * @param <T>     the type parameter
     * @param veiculo the veiculo
     * @param pessoa  the pessoa
     * @param local   the local
     */
    public static <T extends Pessoa> void listarInformacoesDeAluguel(Veiculo veiculo, T pessoa, String local) {
        System.out.println("=== VEÍCULO ALUGADO COM SUCESSO ===");
        System.out.println("Carro: " + veiculo.getMarca());
        System.out.println("Cliente: " + pessoa.getNome());
        System.out.println("Data: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Horário: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("Local: " + local);
    }
}
