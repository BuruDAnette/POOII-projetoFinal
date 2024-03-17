package src.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import src.utils.TipoVeiculo;

public class Veiculo implements Comparable<Veiculo> {
    //------------------------------CORES------------------------------//
    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

    private String placa;
    private String marca;
    private Boolean disponivel;
    private LocalDateTime diaAlugado;
    private LocalDateTime diaDevolucao;
    private TipoVeiculo tipoVeiculo;
    private List<Veiculo> veiculos;

    public Veiculo(String placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.disponivel = true;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Getters e setters

    
    public String getPlaca() {
        return placa;
    }
    
    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDateTime getDiaAlugado() {
        return diaAlugado;
    }
    
    public void setDiaAlugado(LocalDateTime diaAlugado) {
        this.diaAlugado = diaAlugado;
    }
    
    public LocalDateTime getDiaDevolucao() {
        return diaDevolucao;
    }
    
    public void setDiaDevolucao(LocalDateTime diaDevolucao) {
        this.diaDevolucao = diaDevolucao;
    }
    
    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }
    
    public String getMarca() {
        return marca;
    }

    public boolean isDisponivel() {
        return this.diaAlugado == null;
    }
    
    public List<Veiculo> listarDisponiveis() {
    List<Veiculo> veiculos = new ArrayList<>();
    for (Veiculo veiculo : this.veiculos) {
        if (veiculo.isDisponivel()) {
            veiculos.add(veiculo);
        }
    }
    return veiculos;
    }

    public List<Veiculo> listarVeiculosAlugados() {
        List<Veiculo> veiculos = new ArrayList<>();
        for (Veiculo veiculo : this.veiculos) {
            if (!veiculo.isDisponivel()) {
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }
    
    @Override
    public int compareTo(Veiculo outroVeiculo) {
        return this.placa.compareTo(outroVeiculo.placa);
    }
    @Override
    public String toString() {
        return "PLACA: " + placa
            + " | MARCA: " + marca
            + " | TIPO: " + tipoVeiculo.getNomeTipo()
            + " | STATUS: " + (getDisponivel() ? "DISPONIVEL" : "NÃO DISPONIVEL");
    }
}
