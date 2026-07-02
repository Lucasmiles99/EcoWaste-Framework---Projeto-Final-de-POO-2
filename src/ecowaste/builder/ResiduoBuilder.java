package ecowaste.builder;

import ecowaste.model.ResiduoEletronico;

/**
 * Builder responsável por facilitar a criação de objetos {@link ecowaste.model.ResiduoEletronico}.
 *
 * <p>
 * Esta classe aplica o Builder Pattern, permitindo construir objetos complexos de
 * forma mais legível, organizada e flexível. Em vez de utilizar diretamente um
 * construtor com muitos parâmetros, o builder permite definir os atributos passo a
 * passo antes de criar o objeto final.
 * </p>
 *
 * <p>
 * O uso deste padrão é especialmente útil no EcoWaste Framework porque um resíduo
 * eletrônico possui vários atributos, como nome, categoria, peso, risco ambiental,
 * valor estimado, localização, destino e status.
 * </p>
 *
 * <p>
 * Com o Builder Pattern, o código de criação se torna mais expressivo, reduzindo
 * erros de ordem de parâmetros e facilitando futuras expansões da classe modelo.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoBuilder {

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

    public ResiduoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ResiduoBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ResiduoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ResiduoBuilder peso(double peso) {
        this.peso = peso;
        return this;
    }

    public ResiduoBuilder riscoAmbiental(String riscoAmbiental) {
        this.riscoAmbiental = riscoAmbiental;
        return this;
    }
    
    public ResiduoBuilder valorEstimado(double valorEstimado) {
        this.valorEstimado = valorEstimado;
        return this;
    }

    public ResiduoBuilder moeda(String moeda) {
        this.moeda = moeda;
        return this;
    }

    public ResiduoBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public ResiduoBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public ResiduoBuilder pais(String pais) {
        this.pais = pais;
        return this;
    }

    public ResiduoBuilder continente(String continente) {
        this.continente = continente;
        return this;
    }

    public ResiduoBuilder destino(String destino) {
        this.destino = destino;
        return this;
    }

    public ResiduoBuilder status(String status) {
        this.status = status;
        return this;
    }

    public ResiduoEletronico build() {
        return new ResiduoEletronico(
                id,
                nome,
                categoria,
                peso,
                riscoAmbiental,
                valorEstimado,
                moeda,
                cidade,
                estado,
                pais,
                continente,
                destino,
                status
        );
    }
}