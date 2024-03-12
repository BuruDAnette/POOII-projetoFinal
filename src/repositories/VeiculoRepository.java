package src.repositories;

import java.util.ArrayList;
import java.util.List;

import src.interfaces.IVeiculoRepository;
import src.models.Veiculo;

public class VeiculoRepository implements IVeiculoRepository<Veiculo> {
    private List<Veiculo> listaVeiculos;

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
        System.out.println("Esse veículo já existe na base de dados.");
        return null;
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        Veiculo consultaVeiculo = consultar(veiculo.getPlaca());

        if (consultaVeiculo == null) {
            throw new IllegalArgumentException("Esse veículo não existe na base de dados.");
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
