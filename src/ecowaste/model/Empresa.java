package ecowaste.model;

import ecowaste.generics.Identificavel;

/**
 * Representa uma empresa relacionada ao descarte de resíduos eletrônicos.
 */

public class Empresa implements Identificavel<Long> {

    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;

    public Empresa(Long id, String nome, String cnpj, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | CNPJ: " + cnpj + " | " + endereco;
    }
}