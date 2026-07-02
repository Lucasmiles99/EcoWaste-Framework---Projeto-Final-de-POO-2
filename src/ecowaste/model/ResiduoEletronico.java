package ecowaste.model;

import ecowaste.generics.Identificavel;

/**
 * Representa um resíduo eletrônico cadastrado no sistema EcoWaste.
  *
 * <p>
 * Esta classe modela os dados essenciais de um item eletrônico descartado ou em
 * processo de descarte, como identificador, nome, categoria, peso, risco ambiental,
 * valor estimado, localização, destino e status.
 * </p>
 *
 * <p>
 * O objeto {@code ResiduoEletronico} é utilizado em praticamente todas as camadas
 * do sistema, incluindo a interface gráfica, o repositório, os serviços de geração
 * de documentos, os conversores de imagem e a geração de XML.
 * </p>
 *
 * <p>
 * No contexto do projeto, esta classe permite representar resíduos como baterias,
 * celulares, computadores, monitores, cabos, impressoras e outros equipamentos
 * eletrônicos que exigem controle adequado para descarte sustentável.
 * </p>
 *
 * <p>
 * Esta classe está diretamente relacionada ao domínio do problema estudado no
 * projeto, conectando a Programação Orientada a Objetos ao tema de sustentabilidade
 * e gerenciamento de lixo eletrônico.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoEletronico implements Identificavel<Long> {

    private Long id;
    private String nome;
    private String categoria;
    private double peso;
    private String riscoAmbiental;
    private double valorEstimado;
    private String moeda;
    private String cidade;
    private String estado;
    private String pais;
    private String continente;
    private String destino;
    private String status;
    
    public ResiduoEletronico() {
    }

    public ResiduoEletronico(
            Long id,
            String nome,
            String categoria,
            double peso,
            String riscoAmbiental,
            double valorEstimado,
            String moeda,
            String cidade,
            String estado,
            String pais,
            String continente,
            String destino,
            String status) {

        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.peso = peso;
        this.riscoAmbiental = riscoAmbiental;

        this.valorEstimado = valorEstimado;
        this.moeda = moeda;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.continente = continente;
        this.destino = destino;
        this.status = status;
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

    public String getCategoria() {
        return categoria;
    }

    public double getPeso() {
        return peso;
    }

    public String getRiscoAmbiental() {
        return riscoAmbiental;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public String getMoeda() {
        return moeda;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getContinente() {
        return continente;
    }

    public String getDestino() {
        return destino;
    }

    public String getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRiscoAmbiental(String riscoAmbiental) {
        this.riscoAmbiental = riscoAmbiental;
    }

    public void setValorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Categoria: " + categoria +
                " | Peso: " + peso + " kg" +
                " | Risco: " + riscoAmbiental +
                " | Valor: " + moeda + " " + valorEstimado +
                " | Cidade: " + cidade +
                " | Estado: " + estado +
                " | País: " + pais +
                " | Continente: " + continente +
                " | Destino: " + destino +
                " | Status: " + status;
    }
}