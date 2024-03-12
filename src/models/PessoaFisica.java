package src.models;

/**
 * The type Pessoa fisica.
 */
public class PessoaFisica extends Pessoa {
    private String cpf;

    /**
     * Instantiates a new Pessoa fisica.
     *
     * @param nome the nome
     * @param cpf  the cpf
     */
    public PessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    /**
     * Gets cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets cpf.
     *
     * @param cpf the cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "PF: " + getNome() + " | CPF: " + cpf;
    }
}
