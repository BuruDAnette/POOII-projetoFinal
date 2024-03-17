package src.repositories;

import java.util.ArrayList;
import java.util.List;

import src.interfaces.IPessoaRepository;
import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;

/**
 * The type Pessoa repository.
 *
 * @param <T> the type parameter
 */
public class PessoaRepository<T extends Pessoa> implements IPessoaRepository<T> {
    //------------------------------CORES------------------------------//
    public static final String RESET = "\033[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String PURPLE_BACKGROUND = "\033[45m";

    private List<T> listaDeClientes;

    /**
     * Instantiates a new Pessoa repository.
     */
    public PessoaRepository() {
        this.listaDeClientes = new ArrayList<>();
    }

    @Override
    public T salvar(T pessoa) {
        if (validaClienteNaBaseDeDados(pessoa)) {
            System.out.println(pessoa instanceof PessoaFisica ? RED_BOLD + "CPF JÁ EXISTE NA BASE DE DADOS" : "CNPJ JÁ EXISTE NA BASE DE DADOS" + RESET);
            return null;
        }

        listaDeClientes.add(pessoa);
        return pessoa;
    }

    @Override
    public void atualizar(T pessoa) {
        if (!validaClienteNaBaseDeDados(pessoa)) {
            listaDeClientes.remove(pessoa);
            salvar(pessoa);
        }
    }

    @Override
    public boolean deletar(T pessoa) {
        return listaDeClientes.remove(pessoa);
    }

    @Override
    public PessoaFisica consultarCPF(String cpf) {
        for (T cliente : listaDeClientes) {
            if (cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getCpf().equalsIgnoreCase(cpf)) {
                return (PessoaFisica) cliente;
            }
        }
        return null;
    }

    @Override
    public PessoaJuridica consultarCNPJ(String cnpj) {
        for (T cliente : listaDeClientes) {
            if (cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getCnpj().equalsIgnoreCase(cnpj)) {
                return (PessoaJuridica) cliente;
            }
        }
        return null;
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(listaDeClientes);
    }

    private boolean validaClienteNaBaseDeDados(T pessoa) {
        if (pessoa instanceof PessoaFisica) {
            return listaDeClientes.stream()
                    .anyMatch(cliente -> cliente instanceof PessoaFisica && ((PessoaFisica) cliente).getCpf().equalsIgnoreCase(((PessoaFisica) pessoa).getCpf()));
        } else if (pessoa instanceof PessoaJuridica) {
            return listaDeClientes.stream()
                    .anyMatch(cliente -> cliente instanceof PessoaJuridica && ((PessoaJuridica) cliente).getCnpj().equalsIgnoreCase(((PessoaJuridica) pessoa).getCnpj()));
        }
        return false;
    }
}
