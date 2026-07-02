package ecowaste.strategy;

import ecowaste.model.ResiduoEletronico;

/**
 * Interface que define uma estratégia de descarte para resíduos eletrônicos.
 * 
 * Aqui aplicamos:
 * OCP - Aberto para extensão e fechado para modificação.
 * DIP - As classes dependerão da abstração DescarteStrategy.
 */

public interface DescarteStrategy {

    String descartar(ResiduoEletronico residuo);
}