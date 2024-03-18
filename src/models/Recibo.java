package src.models;

import java.time.format.DateTimeFormatter;

/**
 * The type Recibo.
 */
public class Recibo {
    /**
     * The constant RESET.
     */
//------------------------------CORES------------------------------//
    public static final String RESET = "\033[0m";
    /**
     * The constant RED_BOLD.
     */
    public static final String RED_BOLD = "\033[1;31m";
    /**
     * The constant GREEN_BOLD.
     */
    public static final String GREEN_BOLD = "\033[1;32m";
    /**
     * The constant BLACK_BOLD.
     */
    public static final String BLACK_BOLD = "\033[1;30m";
    /**
     * The constant PURPLE_BOLD.
     */
    public static final String PURPLE_BOLD = "\033[1;35m";
    /**
     * The constant PURPLE_BACKGROUND.
     */
    public static final String PURPLE_BACKGROUND = "\033[45m";

    /**
     * Listar informacoes de aluguel.
     *
     * @param <T>     the type parameter
     * @param veiculo the veiculo
     * @param pessoa  the pessoa
     * @param local   the local
     */
    public static <T extends Pessoa> void listarInformacoesDeAluguel(Veiculo veiculo, T pessoa, String local) {
        System.out.println(GREEN_BOLD + "VEÍCULO ALUGADO COM SUCESSO" + RESET);
        System.out.println("CARRO: " + veiculo.getMarca());
        System.out.println("CLIENTE: " + pessoa.getNome());
        System.out.println("DATA: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("HORÁRIO: " + veiculo.getDiaAlugado().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("LOCAL: " + local);
        System.out.println();
    }
}
