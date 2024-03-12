package src.interfaces;

import java.util.List;

import src.models.Pessoa;
import src.models.PessoaFisica;
import src.models.PessoaJuridica;

/**
 * The interface Pessoa repository.
 */
public interface IPessoaRepository<T extends Pessoa> {
    T salvar(T pessoa);
    void atualizar(T pessoa);
    boolean deletar(T pessoa);
    PessoaFisica consultarCPF(String cpf);
    PessoaJuridica consultarCNPJ(String cnpj);
    List<T> listarTodos();
}