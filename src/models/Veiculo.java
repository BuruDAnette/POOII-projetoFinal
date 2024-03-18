package src.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import src.utils.TipoVeiculo;

/**
 * The type Veiculo.
 */
public class Veiculo implements Comparable<Veiculo> {
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

    private String placa;
    private String marca;
    private Boolean disponivel;
    private LocalDateTime diaAlugado;
    private LocalDateTime diaDevolucao;
    private TipoVeiculo tipoVeiculo;
    private List<Veiculo> veiculos;

    /**
     * Instantiates a new Veiculo.
     *
     * @param placa       the placa
     * @param marca       the marca
     * @param tipoVeiculo the tipo veiculo
     */
    public Veiculo(String placa, String marca, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.marca = marca;
        this.disponivel = true;
        this.tipoVeiculo = tipoVeiculo;
    }

    // Getters e setters


    /**
     * Gets placa.
     *
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Gets disponivel.
     *
     * @return the disponivel
     */
    public Boolean getDisponivel() {
        return disponivel;
    }

    /**
     * Sets disponivel.
     *
     * @param disponivel the disponivel
     */
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Gets dia alugado.
     *
     * @return the dia alugado
     */
    public LocalDateTime getDiaAlugado() {
        return diaAlugado;
    }

    /**
     * Sets dia alugado.
     *
     * @param diaAlugado the dia alugado
     */
    public void setDiaAlugado(LocalDateTime diaAlugado) {
        this.diaAlugado = diaAlugado;
    }

    /**
     * Gets dia devolucao.
     *
     * @return the dia devolucao
     */
    public LocalDateTime getDiaDevolucao() {
        return diaDevolucao;
    }

    /**
     * Sets dia devolucao.
     *
     * @param diaDevolucao the dia devolucao
     */
    public void setDiaDevolucao(LocalDateTime diaDevolucao) {
        this.diaDevolucao = diaDevolucao;
    }

    /**
     * Gets tipo veiculo.
     *
     * @return the tipo veiculo
     */
    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * Gets marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Is disponivel boolean.
     *
     * @return the boolean
     */
    public boolean isDisponivel() {
        return this.diaAlugado == null;
    }

    /**
     * Listar disponiveis list.
     *
     * @return the list
     */
    public List<Veiculo> listarDisponiveis() {
    List<Veiculo> veiculos = new ArrayList<>();
    for (Veiculo veiculo : this.veiculos) {
        if (veiculo.isDisponivel()) {
            veiculos.add(veiculo);
        }
    }
    return veiculos;
    }

    /**
     * Listar veiculos alugados list.
     *
     * @return the list
     */
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
