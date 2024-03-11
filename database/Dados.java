package database;

import java.util.ArrayList;
import java.util.List;

import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import models.Veiculo;
import utils.TipoVeiculo;

/**
 * The type Dados.
 */
public class Dados {
    private List<Pessoa> listaDeClientes;

    public List<Veiculo> gerarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(new Veiculo("NFB-6412", "Volvo", TipoVeiculo.SEDAN));
        veiculos.add(new Veiculo("MZE-4083", "Fiat", TipoVeiculo.SUV));
        // ... Add more vehicles using veiculos.add(new Veiculo(...))
        return veiculos;
    }

    /**
     * Gerar clientes list.
     *
     * @return the list
     */
    public List<Pessoa> gerarClientes() {
        List<Pessoa> clientes = new ArrayList<>();

        clientes.add(new PessoaFisica("Sabrina Olivia Moreira", "326.774.786-41"));
        clientes.add(new PessoaJuridica("Leandro e Nathan Construções ME", "68.835.326/0001-72"));
        return listaDeClientes;
    }
}
