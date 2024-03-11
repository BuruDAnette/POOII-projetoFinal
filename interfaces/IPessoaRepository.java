package interfaces;

import java.util.List;

import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;

/**
 * The interface Pessoa repository.
 */
public interface IPessoaRepository {
    /**
     * Salvar pessoa.
     *
     * @param pessoa the pessoa
     * @return the pessoa
     */
    Pessoa salvar(Pessoa pessoa);

    /**
     * Atualizar.
     *
     * @param pessoa the pessoa
     */
    void atualizar(Pessoa pessoa);

    /**
     * Deletar boolean.
     *
     * @param pessoa the pessoa
     * @return the boolean
     */
    boolean deletar(Pessoa pessoa);

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
     * @param cpf the cpf
     * @return the pessoa juridica
     */
    PessoaJuridica consultarCNPJ(String cpf);

    /**
     * Listar todos list.
     *
     * @return the list
     */
    List<Pessoa> listarTodos();
}