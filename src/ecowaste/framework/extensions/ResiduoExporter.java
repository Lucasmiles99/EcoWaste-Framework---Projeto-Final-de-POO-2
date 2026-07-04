package ecowaste.framework.extensions;

import ecowaste.model.ResiduoEletronico;

/**
 * Interface de extensão do EcoWaste Framework responsável por exportar dados de
 * resíduos eletrônicos para formatos externos.
 *
 * <p>
 * A implementação padrão do projeto já gera XML e imagens, mas esta interface
 * permite que outros formatos sejam adicionados futuramente, como CSV, JSON,
 * relatórios em texto ou integração com banco de dados.
 * </p>
 *
 * @author Lucas Miles
 */

public interface ResiduoExporter {

    /**
     * Exporta um resíduo eletrônico.
     *
     * @param residuo resíduo a ser exportado
     * @throws Exception caso ocorra erro durante a exportação
     */
	
    void exportar(ResiduoEletronico residuo) throws Exception;
}