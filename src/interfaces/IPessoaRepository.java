package src.interfaces;

import java.util.List;

import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;

/**
 * The interface Pessoa repository.
 *
 * @param <T> the type parameter
 */
public interface IPessoaRepository<T extends Pessoa> {
    /**
     * Salvar t.
     *
     * @param pessoa the pessoa
     * @return the t
     */
    T salvar(T pessoa);

    /**
     * Atualizar.
     *
     * @param pessoa the pessoa
     */
    void atualizar(T pessoa);

    /**
     * Deletar boolean.
     *
     * @param pessoa the pessoa
     * @return the boolean
     */
    boolean deletar(T pessoa);

    /**
     * Consultar cpf pessoa fisica.
     *
     * @param cpf the cpf
     * @return the pessoa fisica
     */
    PessoaFisica consultarCPF(String cpf);

    /**
     * Consultar cnpj pessoa juridica.
     *
     * @param cnpj the cnpj
     * @return the pessoa juridica
     */
    PessoaJuridica consultarCNPJ(String cnpj);

    /**
     * Listar todos list.
     *
     * @return the list
     */
    List<T> listarTodos();
}