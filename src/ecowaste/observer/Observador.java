package ecowaste.observer;

import ecowaste.model.ResiduoEletronico;

/**
 * Interface que representa um observador interessado em eventos do sistema.
 *
 * Aqui aplicamos o Observer Pattern.
 */

public interface Observador {

    void atualizar(ResiduoEletronico residuo);
}