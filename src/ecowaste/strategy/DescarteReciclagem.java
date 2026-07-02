package ecowaste.strategy;

import ecowaste.model.ResiduoEletronico;

/**
 * Estratégia de descarte por reciclagem.
 */

public class DescarteReciclagem implements DescarteStrategy {

    @Override
    public String descartar(ResiduoEletronico residuo) {
        return "O resíduo " + residuo.getNome() + 
               " será encaminhado para reciclagem especializada.";
    }
}