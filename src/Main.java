package src;

import java.time.LocalDateTime;
import java.util.Scanner;

import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;
import src.models.Veiculo;
import src.repositories.PessoaRepository;
import src.repositories.VeiculoRepository;
import src.utils.TipoVeiculo;

/**
 * The type Main.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The constant RESET.
     */

    public static final String RESET = "\033[0m";
    /**
     * The constant RED_BOLD.
     */
    public static final String RED_BOLD = "\033[1;31m";
    /**
     * The constant GREEN_BOLD.
     */
    public static final String GREEN_BOLD = "\033[1;32m";
    /**
     * The constant BLACK_BOLD.
     */
    public static final String BLACK_BOLD = "\033[1;30m";
    /**
     * The constant PURPLE_BOLD.
     */
    public static final String PURPLE_BOLD = "\033[1;35m";
    /**
     * The constant PURPLE_BACKGROUND.
     */
    public static final String PURPLE_BACKGROUND = "\033[45m";

    /**
     * The constant listaVeiculos.
     */
    public static VeiculoRepository listaVeiculos = new VeiculoRepository();
    /**
     * The constant listaClientes.
     */
    public static PessoaRepository<Pessoa> listaClientes = new PessoaRepository<>();
    /**
     * The constant locadora.
     */
    public static Locadora locadora = new Locadora("Brasil");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarPessoa();
                    break;
                case 2:
                    cadastrarVeiculo();
                    break;
                case 3:
                    alugarVeiculo();
                    break;
                case 4:
                    devolverVeiculo();
                    break;
                case 5:
                    atualizarPessoa();
                    break;
                case 6: 
                    atualizarVeiculo();
                    break;
                case 7:
                    buscarPessoa();
                    break;
                case 8:
                    buscarVeiculo();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

    }

    private static void cadastrarPessoa() {

        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD +" CADASTRO DE PESSOA " + RESET);
        System.out.println(PURPLE_BOLD +"1." + BLACK_BOLD + " PESSOA FÍSICA" + RESET);
        System.out.println(PURPLE_BOLD +"2." + BLACK_BOLD + " PESSOA JURÍDICA" + RESET);
        System.out.println();
        System.out.print("DIGITE A OPCÃO:");
        int opcao = scanner.nextInt();
        if (opcao == 1) {
            System.out.println(PURPLE_BOLD + " VOCÊ DIGITOU 1 PARA PESSOA FÍSICA " + RESET);
            System.out.println(PURPLE_BACKGROUND + BLACK_BOLD +" CADASTRO DE PESSOA FÍSICA " + RESET);
            System.out.print("NOME: ");
            scanner.next();
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            listaClientes.salvar(new PessoaFisica(nome, cpf));
            System.out.println(GREEN_BOLD + "PESSOA FÍSICA CADASTRADA COM SUCESSO" + RESET);
        } else if (opcao == 2) {
            System.out.println(PURPLE_BOLD + " VOCÊ DIGITOU 2 PARA PESSOA JURÍDICA " + RESET);
            System.out.println(PURPLE_BACKGROUND + BLACK_BOLD +" CADASTRO DE PESSOA JURÍDICA " + RESET);
            System.out.print("NOME: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            listaClientes.salvar(new PessoaJuridica(nome, cnpj));
            System.out.println(GREEN_BOLD + "PESSOA JURÍDICA CADASTRADA COM SUCESSO" + RESET);
        } else {
            System.out.println(RED_BOLD + "OPCAO INVALIDA" + RESET);
        }
        
    }
    
    private static void cadastrarVeiculo() {

        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " CADASTRO DE VEÍCULO " + RESET);
        System.out.print("PLACA: ");
        String placa = scanner.next();
        System.out.print("MARCA: ");
        String marca = scanner.next();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " TIPOS DE VEÍCULO " + RESET);
        System.out.println(PURPLE_BOLD +"1." + BLACK_BOLD + " PEQUENO" + RESET);
        System.out.println(PURPLE_BOLD +"2." + BLACK_BOLD + " MEDIO" + RESET);
        System.out.println(PURPLE_BOLD +"3." + BLACK_BOLD + " SUV" + RESET);
        System.out.println();
        System.out.print("DIGITE A OPCÃO:");
        int opcao = scanner.nextInt();
        if (opcao == 1) {
            listaVeiculos.salvar(new Veiculo(placa, marca, TipoVeiculo.PEQUENO));
            System.out.println(GREEN_BOLD + "VEÍCULO CADASTRADO COM SUCESSO" + RESET);
        } else if (opcao == 2) {
            listaVeiculos.salvar(new Veiculo(placa, marca, TipoVeiculo.MEDIO));
            System.out.println(GREEN_BOLD + "VEÍCULO CADASTRADO COM SUCESSO" + RESET);
        } else if (opcao == 3) {
            listaVeiculos.salvar(new Veiculo(placa, marca, TipoVeiculo.SUV));
            System.out.println(GREEN_BOLD + "VEÍCULO CADASTRADO COM SUCESSO" + RESET);
        } else {
            System.out.println(RED_BOLD + "OPCAO INVALIDA" + RESET);
            
        }
    }
    
    private static void alugarVeiculo() {

        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " ALUGUEL DE VEÍCULO " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE VEÍCULOS DISPONIVES" + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.print("DIGITE A PLACA DO VEICULO QUE DESEJA ALUGAR: ");
        String placa = scanner.next();
        if (listaVeiculos.consultar(placa) == null) {
            System.out.println(RED_BOLD + "VEICULO NÃO ENCONTRADO" + RESET);
        } else {
            System.out.println(GREEN_BOLD + "VEICULO ENCONTRADO: " + placa + RESET);
        }
        System.out.println();
        System.out.println(PURPLE_BOLD + "LISTA DE PESSOAS CADASTRADAS" + RESET);
        listaClientes.listarTodos().forEach(System.out::println);   
        System.out.println();
        System.out.print("DIGITE CPF OU CNPJ DA PESSOA QUE DESEJA ALUGAR: ");
        String documento = scanner.next();
        
            if (documento.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                System.out.println("PESSOA FISICA: " + listaClientes.consultarCPF(documento));
                locadora.alugar(listaVeiculos, listaClientes, placa, documento);
            } else if (documento.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}")) {
                System.out.println("PESSOA JURIDICA: " + listaClientes.consultarCNPJ(documento));
                locadora.alugar(listaVeiculos, listaClientes, placa, documento);
            } else {
                System.out.println(RED_BOLD + "DOCUMENTO NÃO ENCONTRADO " + RESET);
            }
        
    }
    
    private static void devolverVeiculo() {

        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " DEVOLUCAO DE VEÍCULO " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE VEÍCULOS" + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.print("DIGITE A PLACA DO VEICULO QUE DESEJA DEVOLVER: ");
        String placa = scanner.next();
        if (listaVeiculos.consultar(placa) == null) {
            System.out.println(RED_BOLD + "VEICULO NÃO ENCONTRADO" + RESET);
        } else {
            System.out.println(GREEN_BOLD + "VEICULO ENCONTRADO: " + placa + RESET);
            locadora.devolver(listaVeiculos, placa, LocalDateTime.now());
            System.out.println(GREEN_BOLD + "DEVOLUCAO EFETIVADA COM SUCESSO" + RESET);
        }
    }
    
    private static void atualizarPessoa() {
        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " ATUALIZAR PESSOA " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE PESSOAS CADASTRADAS" + RESET);
        listaClientes.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.print("DIGITE O DOCUMENTO DA PESSOA QUE DESEJA ATUALIZAR: ");
        String documento = scanner.next();
        if (listaClientes.consultarCPF(documento) == null) {
            System.out.println(RED_BOLD + "PESSOA FÍSICA NÃO ENCONTRADA" + RESET);
        } else if (listaClientes.consultarCNPJ(documento) == null) {
            System.out.println(RED_BOLD + "PESSOA JURIDICA NÃO ENCONTRADA" + RESET);
        } else {
            
            System.out.println(GREEN_BOLD + "PESSOA ENCONTRADA " + documento + RESET);
            scanner.nextLine();
            System.out.print("DIGITE O NOVO NOME: ");
            String novoNome = scanner.next();
            scanner.nextLine();
            listaClientes.atualizar(new PessoaJuridica(novoNome, documento));
            
            System.out.println(GREEN_BOLD + "ATUALIZACAO EFETIVADA COM SUCESSO" + RESET);
        }
            
    }
    
    private static void atualizarVeiculo() {

        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " ATUALIZAR VEICULO " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE VEICULOS CADASTRADOS" + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.print("DIGITE A PLACA DO VEICULO QUE DESEJA ATUALIZAR: ");
        String placa = scanner.next();
        if (listaVeiculos.consultar(placa) == null) {
            System.out.println(RED_BOLD + "VEICULO NÃO ENCONTRADO" + RESET);
        } else {

            System.out.println(GREEN_BOLD + "VEICULO ENCONTRADO: " + placa + RESET);
            System.out.println();
            System.out.println(PURPLE_BOLD + "1." + BLACK_BOLD + " ATUALIZAR MARCA" + RESET);
            System.out.println(PURPLE_BOLD + "2." + BLACK_BOLD + " ATUALIZAR TIPO" + RESET);
            System.out.println();
            System.out.print("DIGITE A OPCÃO: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    scanner.nextLine();
                    System.out.print("DIGITE A NOVA MARCA: ");
                    String novaMarca = scanner.nextLine();
                    listaVeiculos.atualizar(new Veiculo(placa, novaMarca, listaVeiculos.consultar(placa).getTipoVeiculo()));
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " TIPOS DE VEÍCULO " + RESET);
                    System.out.println(PURPLE_BOLD +"1." + BLACK_BOLD + " PEQUENO" + RESET);
                    System.out.println(PURPLE_BOLD +"2." + BLACK_BOLD + " MEDIO" + RESET);
                    System.out.println(PURPLE_BOLD +"3." + BLACK_BOLD + " SUV" + RESET);
                    System.out.println();
                    System.out.print("DIGITE O NOVO TIPO:");
                    int novoTipo = scanner.nextInt();
                    if (novoTipo == 1) {
                        listaVeiculos.salvar(new Veiculo(placa, listaVeiculos.consultar(placa).getMarca(), TipoVeiculo.PEQUENO));
                        System.out.println(GREEN_BOLD + "VEÍCULO ATUALIZADO COM SUCESSO" + RESET);
                    } else if (novoTipo == 2) {
                        listaVeiculos.salvar(new Veiculo(placa, listaVeiculos.consultar(placa).getMarca(), TipoVeiculo.MEDIO));
                        System.out.println(GREEN_BOLD + "VEÍCULO ATUALIZADO COM SUCESSO" + RESET);
                    } else if (novoTipo == 3) {
                        listaVeiculos.salvar(new Veiculo(placa, listaVeiculos.consultar(placa).getMarca(), TipoVeiculo.SUV));
                        System.out.println(GREEN_BOLD + "VEÍCULO ATUALIZADO COM SUCESSO" + RESET);
                    } else {
                        System.out.println(RED_BOLD + "TIPO INVALIDO" + RESET);
                        
                    }
                    break;
                default:
                    System.out.println(RED_BOLD + "OPCAO INVALIDA" + RESET);
                    break;
                
            }
            
        }
    }
    
    private static void buscarVeiculo() {
        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " BUSCAR VEICULO " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE VEICULOS CADASTRADOS" + RESET);
        listaVeiculos.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.print("DIGITE A PLACA DO VEICULO QUE DESEJA BUSCAR: ");
        String placa = scanner.next();
        if (listaVeiculos.consultar(placa) == null) {
            System.out.println(RED_BOLD + "VEICULO NÃO ENCONTRADO" + RESET);
        } else {
            System.out.println(GREEN_BOLD + "VEICULO ENCONTRADO: " + placa + RESET);
            System.out.println(listaVeiculos.consultar(placa));
        }
    }


    private static void buscarPessoa() {

        System.out.println();
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + " BUSCAR PESSOA " + RESET);
        System.out.println(PURPLE_BOLD + "LISTA DE PESSOAS CADASTRADAS" + RESET);
        listaClientes.listarTodos().forEach(System.out::println);
        System.out.println();
        System.out.println("DIGITE O CPF OU CNPJ DA PESSOA QUE DESEJA BUSCAR: ");
        String documento = scanner.next();
            System.out.println(GREEN_BOLD + "PESSOA ENCONTRADA: " + documento + RESET);
            if (documento.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                System.out.println("PESSOA FISICA: " + listaClientes.consultarCPF(documento));
            } else {
                System.out.println("PESSOA JURIDICA: " + listaClientes.consultarCNPJ(documento));
            }
            
        
    }

    private static void exibirMenu() {
        System.out.println(PURPLE_BACKGROUND + BLACK_BOLD + "                        LOCADORA BRASIL                        " + RESET);
        System.out.println(PURPLE_BOLD + "1." + BLACK_BOLD + " CADASTRAR PESSOA" + RESET);
        System.out.println(PURPLE_BOLD + "2." + BLACK_BOLD + " CADASTRAR VEÍCULO" + RESET);
        System.out.println(PURPLE_BOLD + "3." + BLACK_BOLD + " ALUGAR VEÍCULO" + RESET);
        System.out.println(PURPLE_BOLD + "4." + BLACK_BOLD + " DEVOLVER VEÍCULO" + RESET);
        System.out.println(PURPLE_BOLD + "5." + BLACK_BOLD + " ATUALIZAR PESSOA" + RESET);
        System.out.println(PURPLE_BOLD + "6." + BLACK_BOLD + " ATUALIZAR VEÍCULO" + RESET);
        System.out.println(PURPLE_BOLD + "7." + BLACK_BOLD + " BUSCAR PESSOA" + RESET);
        System.out.println(PURPLE_BOLD + "8." + BLACK_BOLD + " BUSCAR VEÍCULO" + RESET);
        System.out.println(PURPLE_BOLD + "9." + BLACK_BOLD + " SAIR" + RESET);
        System.out.println();
        System.out.print("DIGITE A OPCÃO: ");
    }
}