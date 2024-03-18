package src.utils;

/**
 * The enum Tipo veiculo.
 */
public enum TipoVeiculo {
    /**
     * Pequeno tipo veiculo.
     */
    PEQUENO("PEQUENO"),
    /**
     * Medio tipo veiculo.
     */
    MEDIO("MEDIO"),
    /**
     * Suv tipo veiculo.
     */
    SUV("SUV");

    private final String descricao;

    private TipoVeiculo(String nomeTipo){
        descricao = nomeTipo;
    }

    /**
     * Get nome tipo string.
     *
     * @return the string
     */
    public String getNomeTipo(){
        return descricao;
    }
}
