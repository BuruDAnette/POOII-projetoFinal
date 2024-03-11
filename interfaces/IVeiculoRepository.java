package interfaces;

import java.util.List;

import models.Veiculo;

public interface IVeiculoRepository {
    Veiculo salvar(Veiculo veiculo);
    void atualizar(Veiculo veiculo);
    boolean deletar(Veiculo veiculo);
    Veiculo consultar(String placa);
    List<Veiculo> listarTodos();
}
