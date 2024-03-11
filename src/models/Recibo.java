package src.models;

import java.time.format.DateTimeFormatter;

public class Recibo {
    public static void listarInformacoesDeAluguel(Veiculo veiculo, Pessoa pessoa, String local) {
        System.out.println("=== VEICULO ALUGADO COM SUCESSO ===");
        System.out.println("Carro: " + veiculo.getMarca());
        System.out.println("Cliente: " + pessoa.getNome());
        System.out.println("Data: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Horario: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("Local: " + local);
    }
}
