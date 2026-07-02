package ecowaste.flyweight;

/**
 * Representa uma categoria compartilhada de resíduo eletrônico.
 *
 * Flyweight: objetos iguais são reutilizados ao invés de recriados.
 */
public class CategoriaResiduo {

    private String nome;
    private String descricao;

    public CategoriaResiduo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}