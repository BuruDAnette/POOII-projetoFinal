package src.utils;

/**
 * The enum Tipo veiculo.
 */
public enum TipoVeiculo {
    /**
     * The Hatch back.
     */
    HATCH_BACK("HATCH BACK"),
    /**
     * Sedan tipo veiculo.
     */
    SEDAN("SEDAN"),
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
