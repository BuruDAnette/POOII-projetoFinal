package models;

import java.time.LocalDateTime;

import utils.TipoVeiculo;

public class Veiculo {
    
    private String placa;
    private String marca;
    private Boolean disponivel;
    private LocalDateTime diaAlugado;
    private LocalDateTime diaDevolucao;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.disponivel = true;
        this.tipoVeiculo = tipoVeiculo;
    }

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

    @Override
    public String toString() {
        return "Placa: " + placa
               + " | Marca: " + marca
               + " | Tipo: " + tipoVeiculo.getNomeTipo()
               + " | Status: " + (getDisponivel() ? "DISPONIVEL" : "NÃO DISPONIVEL");
    }
}
