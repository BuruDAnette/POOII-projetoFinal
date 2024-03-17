package src;

import java.time.LocalDateTime;

import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;
import src.models.Veiculo;
import src.repositories.PessoaRepository;
import src.repositories.VeiculoRepository;
import src.utils.TipoVeiculo;

public class Main {
    //------------------------------CORES------------------------------//
    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

    public static void main(String[] args) {
        PessoaRepository<Pessoa> listaClientes = new PessoaRepository<>();
        VeiculoRepository listaVeiculos = new VeiculoRepository();
        Locadora locadora = new Locadora("Brasil");
        
        // Inserindo dados de teste

        // Pessoas 
        listaClientes.salvar(new PessoaFisica("Bruna Castro Morais", "123.543.478-98"));
        listaClientes.salvar(new PessoaFisica("Bruna Castro Carvalho", "190.192.191-00")); // Repetido
        listaClientes.salvar(new PessoaJuridica("Leonardo e Advocacia LL", "25.408.179/0001-41"));
        listaClientes.salvar(new PessoaJuridica("Automóveis MG", "22.415.053/0001-06")); // Repetido

        // Veiculos
        listaVeiculos.salvar(new Veiculo("MZU-3079", "Toyota", TipoVeiculo.SUV));
        listaVeiculos.salvar(new Veiculo("MZU-3079", "Toyota", TipoVeiculo.SUV)); // Repetido
        listaVeiculos.salvar(new Veiculo("MRH-1604", "Toyota", TipoVeiculo.MEDIO));

        // Lista de Clientes 
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + "                       LISTA DE CLIENTES                       " + RESET);
        listaClientes.listarTodos().forEach(System.out::println);
        System.out.println();

        // Lista de Veiculos    
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + "                       LISTA DE VEÍCULOS                       " + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();


        // Consultas
        if (listaClientes.consultarCNPJ("22.415.053/0001-07") != null) {
            System.out.println(GREEN_BOLD + "CNPJ ENCONTRADA COM SUCESSO" + RESET);
        } else {
            System.out.println(RED_BOLD + "CNPJ NÃO ENCONTRADA" + RESET);
        } //n acha

        if (listaClientes.consultarCNPJ("25.408.179/0001-41") != null) {
            System.out.println(GREEN_BOLD + "CNPJ ENCONTRADA COM SUCESSO" + RESET);
        } else {
            System.out.println(RED_BOLD + "CNPJ NÃO ENCONTRADA" + RESET);
        }//acha


        System.out.println();

        // Aluguel
        locadora.alugar(listaVeiculos, listaClientes, "MRH-1602", "22.415.053/0001-06"); //placa não existe
        locadora.alugar(listaVeiculos, listaClientes, "MRH-1604", "190.192.191-00"); //vai alugar

        // Lista de Veiculos    
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + "                       LISTA DE VEÍCULOS                       " + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();

        // Devolução
        locadora.devolver(listaVeiculos, "MRH-1604", LocalDateTime.now());


    }
    
}