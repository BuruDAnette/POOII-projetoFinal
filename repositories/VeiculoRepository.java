package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.IVeiculoRepository;
import models.Veiculo;

public class VeiculoRepository implements IVeiculoRepository {

    private List<Veiculo> listaVeiculos;
    Scanner sc = new Scanner(System.in);

    public VeiculoRepository(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

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
        System.out.println("Esse veiculo já existe na base de dados.");
        return null;
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        Veiculo consultaVeiculo = consultar(veiculo.getPlaca());

        if (consultaVeiculo == null) {
            throw new IllegalArgumentException("Esse veiculo não existe na base de dados.");
        }
        listaVeiculos.remove(veiculo);
        salvar(veiculo);

    }

    @Override
    public boolean deletar(Veiculo veiculo) {
        if (consultar(veiculo.getPlaca()) == null) {
            return false;
        }
        listaVeiculos.remove(veiculo);
        return true;
    }

    @Override
    public Veiculo consultar(String placa) {
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i).getPlaca().equals(placa)) {
                return listaVeiculos.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return new ArrayList<>(listaVeiculos);
    }

}
