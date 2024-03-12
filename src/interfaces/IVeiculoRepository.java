package src.interfaces;

import java.util.List;

import src.models.Veiculo;

/**
 * The interface Veiculo repository.
 *
 * @param <T> the type parameter
 */
public interface IVeiculoRepository<T extends Veiculo> {
    /**
     * Salvar t.
     *
     * @param veiculo the veiculo
     * @return the t
     */
    T salvar(T veiculo);

    /**
     * Atualizar.
     *
     * @param veiculo the veiculo
     */
    void atualizar(T veiculo);

    /**
     * Deletar boolean.
     *
     * @param veiculo the veiculo
     * @return the boolean
     */
    boolean deletar(T veiculo);

    /**
     * Consultar t.
     *
     * @param placa the placa
     * @return the t
     */
    T consultar(String placa);

    /**
     * Listar todos list.
     *
     * @return the list
     */
    List<T> listarTodos();
}
