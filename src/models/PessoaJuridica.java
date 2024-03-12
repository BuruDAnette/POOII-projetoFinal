package src.models;

/**
 * The type Pessoa juridica.
 */
public class PessoaJuridica extends Pessoa {
    private String cnpj;

    /**
     * Instantiates a new Pessoa juridica.
     *
     * @param nome the nome
     * @param cnpj the cnpj
     */
    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    /**
     * Gets cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets cnpj.
     *
     * @param cnpj the cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "PJ: " + getNome() + " | CNPJ: " + cnpj;
    }
}