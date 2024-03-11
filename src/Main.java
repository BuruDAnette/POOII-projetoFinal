package src;
import java.util.ArrayList;

import src.database.Dados;
import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;
import src.models.Veiculo;
import src.repositories.PessoaRepository;
import src.repositories.VeiculoRepository;
import src.utils.TipoVeiculo;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Inicializando Objetos e Repositorios com dados Mockup
        PessoaRepository listaClientes = new PessoaRepository(new ArrayList<>());
        VeiculoRepository listaVeiculos = new VeiculoRepository(new ArrayList<>());

        Locadora locadora = new Locadora("Brasil");

        Pessoa pessoa;
        Veiculo veiculo;

        
        // Cadastrando Cliente PF e PJ em Memoria
        listaClientes.salvar(new PessoaFisica("Isis Rafaela Valentina Ribeiro", "923.519.448-28"));
        listaClientes.salvar(new PessoaFisica("Márcia Melissa Allana Cavalcanti", "090.869.177-71")); // Repetido
        listaClientes.salvar(new PessoaJuridica("Levi e Ana Assessoria Jurídica Ltda", "25.408.179/0001-41"));
        listaClientes.salvar(new PessoaJuridica("Jéssica e Jorge Locações de Automóveis ME", "22.415.053/0001-06")); // Repetido
        
        // Cadastrando Veiculo em Memoria
        listaVeiculos.salvar(new Veiculo("MZU-3079","Toyota",  TipoVeiculo.SUV));
        listaVeiculos.salvar(new Veiculo("MZU-3079","Toyota",  TipoVeiculo.SUV)); // Repetido
        listaVeiculos.salvar(new Veiculo("MRH-1604","Toyota",  TipoVeiculo.SEDAN));
        System.out.println();

        // Listando Veiculo e Clientes em Memoria
        System.out.println("=== LISTA DE CLIENTES ===");
        for (int i = 0; i < listaClientes.listarTodos().size(); i++) {
            System.out.println(listaClientes.listarTodos().get(i));
        }
        System.out.println();
        System.out.println("=== LISTA DE VEICULOS ===");
        for (int i = 0; i < listaVeiculos.listarTodos().size(); i++) {
            System.out.println(listaVeiculos.listarTodos().get(i));
        }
        System.out.println();
        
        
        // Alugando Veiculos Disponiveis PF e PJ
        if (listaClientes.consultarCNPJ("03.925.339/0001-97") != null) {
            System.out.println("cpf encontrado com sucesso");
        } else {
            System.out.println("cpf n encontrado");

        }

        if (listaClientes.consultarCNPJ("25.408.179/0001-41") != null) {
            System.out.println("cpf encontrado com sucesso");
        } else {
            System.out.println("cnpj n encontrado");
        }
        
        // Alugando Veiculos Disponiveis PF e PJ
        pessoa = listaClientes.consultarCPF("923.519.448-28");
        veiculo = listaVeiculos.consultar("MZU-3079");

        locadora.alugar(veiculo, pessoa);
        System.out.println();
    }
}
