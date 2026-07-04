package ecowaste.framework.extensions;

import ecowaste.model.ResiduoEletronico;

/**
 * Interface de extensão do EcoWaste Framework responsável por processar um
 * resíduo eletrônico antes ou depois de operações do framework.
 *
 * <p>
 * Essa interface permite criar comportamentos customizados, como normalização
 * de dados, logs, cálculo de métricas, auditoria ou enriquecimento de
 * informações.
 * </p>
 *
 * @author Lucas Miles
 */

public interface ResiduoProcessor {

    /**
     * Processa um resíduo eletrônico.
     *
     * @param residuo resíduo a ser processado
     */
	
    void processar(ResiduoEletronico residuo);
}