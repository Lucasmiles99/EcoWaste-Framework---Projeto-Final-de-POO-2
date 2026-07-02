package ecowaste.service;

import ecowaste.model.ResiduoEletronico;
import ecowaste.strategy.DescarteStrategy;

/**
 * Classe responsável por executar o descarte usando uma estratégia.
 *
 * Aqui aplicamos:
 * SRP - Esta classe só gerencia descarte.
 * DIP - Depende da interface DescarteStrategy, não de classes concretas.
 */

public class GerenciadorDescarte {

    private DescarteStrategy estrategia;

    public GerenciadorDescarte(DescarteStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(DescarteStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public String executarDescarte(ResiduoEletronico residuo) {
        return estrategia.descartar(residuo);
    }
}