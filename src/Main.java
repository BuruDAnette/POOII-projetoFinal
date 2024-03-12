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

        if (listaClientes.consultarCNPJ("03.925.339/0001-97") != null) {
            System.out.println("cpf encontrado com sucesso");
        } else {
            System.out.println("cpf não encontrado");
        }

        if (listaClientes.consultarCNPJ("25.408.179/0001-41") != null) {
            System.out.println("cpf encontrado com sucesso");
        } else {
            System.out.println("cnpj não encontrado");
        }

        Pessoa pessoa = listaClientes.consultarCPF("923.519.448-28");
        Veiculo veiculo = listaVeiculos.consultar("MZU-3079");

        locadora.alugar(listaVeiculos, listaClientes, veiculo.getPlaca(), pessoa instanceof PessoaFisica ? ((PessoaFisica) pessoa).getCpf() : ((PessoaJuridica) pessoa).getCnpj());
        System.out.println();
    }

    private static void cadastrarClientes(PessoaRepository pessoaRepository) {
        pessoaRepository.salvar(new PessoaFisica("Isis Rafaela Valentina Ribeiro", "923.519.448-28"));
        pessoaRepository.salvar(new PessoaFisica("Márcia Melissa Allana Cavalcanti", "090.869.177-71"));
        pessoaRepository.salvar(new PessoaJuridica("Levi e Ana Assessoria Jurídica Ltda", "25.408.179/0001-41"));
        pessoaRepository.salvar(new PessoaJuridica("Jéssica e Jorge Locações de Automóveis ME", "22.415.053/0001-06"));
    }
}