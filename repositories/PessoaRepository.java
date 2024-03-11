package repositories;

import java.util.ArrayList;
import java.util.List;

import interfaces.IPessoaRepository;
import models.Pessoa;
import models.PessoaFisica;
import models.PessoaJuridica;

public class PessoaRepository implements IPessoaRepository {
    List<Pessoa> listaDeClientes;

    public PessoaRepository(List<Pessoa> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    public PessoaRepository() {
        this.listaDeClientes = new ArrayList<>();
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        if (validaClienteNaBaseDeDados(pessoa)) {
            if(pessoa instanceof PessoaFisica){
                System.out.println("CPF Já Existe na Base de Dados");
            }
            if(pessoa instanceof PessoaJuridica){
                System.out.println("CNPJ Já Existe na Base de Dados");
            }
            return null;
        }

        listaDeClientes.add(pessoa);
        return pessoa;
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        if (!validaClienteNaBaseDeDados(pessoa)) {
            listaDeClientes.remove(pessoa);
            salvar(pessoa);
        }

    }

    @Override
    public boolean deletar(Pessoa pessoa) {
        listaDeClientes.remove(pessoa);
        return true;
    }

    @Override
    public PessoaFisica consultarCPF(String cpf) {
        PessoaFisica pessoaFisica;
        for (int i = 0; i < listaDeClientes.size(); i++) {
            if (listaDeClientes.get(i) instanceof PessoaFisica) {
                pessoaFisica = (PessoaFisica) listaDeClientes.get(i);
                if (pessoaFisica.getCpf().equalsIgnoreCase(cpf)) {
                    return pessoaFisica;
                }
            }

        }

        return null;
    }

    @Override
    public PessoaJuridica consultarCNPJ(String cpf) {
        PessoaJuridica pessoaJuridica;
        for (int i = 0; i < listaDeClientes.size(); i++) {
            if (listaDeClientes.get(i) instanceof PessoaJuridica) {
                pessoaJuridica = (PessoaJuridica) listaDeClientes.get(i);
                if (pessoaJuridica.getCnpj().equalsIgnoreCase(cpf)) {
                    return pessoaJuridica;
                }
            }

        }

        return null;
    }

    @Override
    public List<Pessoa> listarTodos() {
        return new ArrayList<>(listaDeClientes);
    }

    private boolean validaClienteNaBaseDeDados(Pessoa pessoa) {
        PessoaFisica pf;
        if (pessoa instanceof PessoaFisica) {
            pf = (PessoaFisica) pessoa;
            String cpf = pf.getCpf();
            pf = consultarCPF(cpf);
            if (pf != null) {
                return true;
            }
        }

        PessoaJuridica pj;
        if (pessoa instanceof PessoaJuridica) {
            pj = (PessoaJuridica) pessoa;
            String cnpj = pj.getCnpj();
            pj = consultarCNPJ(cnpj);
            if (pj != null) {
                return true;
            }
        }

        return false;
    }

}
