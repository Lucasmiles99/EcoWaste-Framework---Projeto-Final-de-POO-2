package ecowaste.strategy;

import ecowaste.model.ResiduoEletronico;

/**
 * Estratégia de descarte seguro para resíduos de alto risco ambiental.
 */

public class DescarteSeguro implements DescarteStrategy {

    @Override
    public String descartar(ResiduoEletronico residuo) {
        return "O resíduo " + residuo.getNome() + 
               " exige descarte seguro devido ao risco ambiental: " + residuo.getRiscoAmbiental();
    }
}