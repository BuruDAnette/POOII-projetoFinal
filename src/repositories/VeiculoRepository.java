package src.repositories;

import java.util.ArrayList;
import java.util.List;

import src.interfaces.IVeiculoRepository;
import src.models.Veiculo;

/**
 * The type Veiculo repository.
 */
public class VeiculoRepository implements IVeiculoRepository<Veiculo> {
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

    private List<Veiculo> listaVeiculos;

    /**
     * Instantiates a new Veiculo repository.
     */
    public VeiculoRepository() {
        this.listaVeiculos = new ArrayList<>();
    }
    

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        Veiculo consultaVeiculo = consultar(veiculo.getPlaca());

        if (consultaVeiculo == null) {
            listaVeiculos.add(veiculo);
            return veiculo;
        }
        System.out.println(RED_BOLD + "ESSE VEÍCULO JA EXISTE NA BASE DE DADOS: " + veiculo.getPlaca() + RESET);
        return null;
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        Veiculo consultaVeiculo = consultar(veiculo.getPlaca());

        if (consultaVeiculo == null) {
            throw new IllegalArgumentException(RED_BOLD + "ESSE VEÍCULO NÃO EXISTE NA BASE DE DADOS: " + veiculo.getPlaca() + RESET);
        }
        listaVeiculos.remove(veiculo);
        salvar(veiculo);
    }

    @Override
    public boolean deletar(Veiculo veiculo) {
        return listaVeiculos.remove(veiculo);
    }

    @Override
    public Veiculo consultar(String placa) {
        for (Veiculo veiculo : listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(listaVeiculos);
    }
}
