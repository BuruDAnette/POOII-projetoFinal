package src.models;

/**
 * The type Pessoa.
 */
public abstract class Pessoa implements Comparable<Pessoa> {
    private String nome;

    /**
     * Instantiates a new Pessoa.
     *
     * @param nome the nome
     */
    public Pessoa(String nome) {
        this.nome = nome;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int compareTo(Pessoa outraPessoa) {
        return this.getNome().compareTo(outraPessoa.getNome());
    }
}