package ecowaste.strategy;

import ecowaste.model.ResiduoEletronico;

/**
 * Estratégia para reaproveitamento ou reuso do resíduo eletrônico.
 */

public class DescarteReuso implements DescarteStrategy {

    @Override
    public String descartar(ResiduoEletronico residuo) {
        return "O resíduo " + residuo.getNome() + 
               " será avaliado para reuso ou reaproveitamento.";
    }
}