package ecowaste.framework.extensions;

import ecowaste.model.ResiduoEletronico;

/**
 * Interface de extensão do EcoWaste Framework responsável por validar resíduos
 * antes que eles sejam cadastrados, atualizados ou processados.
 *
 * <p>
 * Essa interface permite que outros desenvolvedores criem regras próprias de
 * validação sem alterar o núcleo do framework.
 * </p>
 *
 * Exemplo de uso:
 * <pre>
 * framework.adicionarValidador(new MeuValidador());
 * </pre>
 *
 * @author Lucas Miles
 */

public interface ResiduoValidator {

    /**
     * Valida um resíduo eletrônico.
     *
     * @param residuo resíduo eletrônico a ser validado
     * @return true se o resíduo for válido; false caso contrário
     */
    boolean validar(ResiduoEletronico residuo);

    /**
     * Retorna a mensagem de erro caso a validação falhe.
     *
     * @return mensagem explicando o motivo da falha
     */
    
    String getMensagemErro();
}