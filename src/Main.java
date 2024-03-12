package src;

import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;
import src.models.Veiculo;
import src.repositories.PessoaRepository;
import src.repositories.VeiculoRepository;
import src.utils.TipoVeiculo;

public class Main {

    public static void main(String[] args) {

        PessoaRepository<Pessoa> listaClientes = new PessoaRepository<>();
        VeiculoRepository listaVeiculos = new VeiculoRepository();
        Locadora locadora = new Locadora("Brasil");
        

        listaClientes.salvar(new PessoaFisica("Isis Rafaela Valentina Ribeiro", "923.519.448-28"));
        listaClientes.salvar(new PessoaFisica("Márcia Melissa Allana Cavalcanti", "090.869.177-71")); // Repetido
        listaClientes.salvar(new PessoaJuridica("Levi e Ana Assessoria Jurídica Ltda", "25.408.179/0001-41"));
        listaClientes.salvar(new PessoaJuridica("Jéssica e Jorge Locações de Automóveis ME", "22.415.053/0001-06")); // Repetido

        listaVeiculos.salvar(new Veiculo("MZU-3079", "Toyota", TipoVeiculo.SUV));
        listaVeiculos.salvar(new Veiculo("MZU-3079", "Toyota", TipoVeiculo.SUV)); // Repetido
        listaVeiculos.salvar(new Veiculo("MRH-1604", "Toyota", TipoVeiculo.SEDAN));

        System.out.println("=== LISTA DE CLIENTES ===");
        listaClientes.listarTodos().forEach(System.out::println);
        System.out.println();

        System.out.println("=== LISTA DE VEÍCULOS ===");
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();

        if (listaClientes.consultarCNPJ("22.415.053/0001-07") != null) {
            System.out.println("CNPJ encontrado com sucesso");
        } else {
            System.out.println("CNPJ não encontrado");
        } //n acha

        if (listaClientes.consultarCNPJ("25.408.179/0001-41") != null) {
            System.out.println("CNPJ encontrado com sucesso");
        } else {
            System.out.println("CNPJ não encontrado");
        }//acha

        locadora.alugar(listaVeiculos, listaClientes, "MRH-1602", "923.519.448-28");
        locadora.alugar(listaVeiculos, listaClientes, "MRH-1604", "923.519.448-28");
    }
}