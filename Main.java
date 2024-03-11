import java.util.ArrayList;

import database.Dados;
import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;
import repositories.PessoaRepository;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Inicializando Objetos e Repositorios com dados Mockup
        PessoaRepository listaClientes = new PessoaRepository(new ArrayList<>());

        Pessoa pessoa;

        
        // Cadastrando Cliente PF e PJ em Memoria
        listaClientes.salvar(new PessoaFisica("Isis Rafaela Valentina Ribeiro", "923.519.448-28"));
        listaClientes.salvar(new PessoaFisica("Márcia Melissa Allana Cavalcanti", "090.869.177-71")); // Repetido
        listaClientes.salvar(new PessoaJuridica("Levi e Ana Assessoria Jurídica Ltda", "25.408.179/0001-41"));
        listaClientes.salvar(new PessoaJuridica("Jéssica e Jorge Locações de Automóveis ME", "22.415.053/0001-06")); // Repetido
        
        // Listando Veiculo e Clientes em Memoria
        System.out.println("=== LISTA DE CLIENTES ===");
        for (int i = 0; i < listaClientes.listarTodos().size(); i++) {
            System.out.println(listaClientes.listarTodos().get(i));
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
        
    }
}
