package src.interfaces;

import java.util.List;

import src.models.Veiculo;

public interface IVeiculoRepository<T extends Veiculo> {
    T salvar(T veiculo);
    void atualizar(T veiculo);
    boolean deletar(T veiculo);
    T consultar(String placa);
    List<T> listarTodos();
}
