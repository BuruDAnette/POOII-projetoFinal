package interfaces;

import java.util.List;

import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;

public interface IPessoaRepository {
    Pessoa salvar(Pessoa pessoa);
    void atualizar(Pessoa pessoa);
    boolean deletar(Pessoa pessoa);
    PessoaFisica consultarCPF(String cpf);
    PessoaJuridica consultarCNPJ(String cpf);
    List<Pessoa> listarTodos();
}