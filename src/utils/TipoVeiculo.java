package src.utils;

public enum TipoVeiculo {
    HATCH_BACK("HATCH BACK"), 
    SEDAN("SEDAN"), 
    SUV("SUV");

    private final String descricao;

    private TipoVeiculo(String nomeTipo){
        descricao = nomeTipo;
    }

    public String getNomeTipo(){
        return descricao;
    }
}
