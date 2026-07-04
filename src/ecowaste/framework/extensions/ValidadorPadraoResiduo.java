package ecowaste.framework.extensions;

import ecowaste.model.ResiduoEletronico;

/**
 * Validador padrão do EcoWaste Framework.
 *
 * <p>
 * Essa classe garante que um resíduo eletrônico possua os dados mínimos
 * necessários para ser cadastrado e processado pelo framework.
 * </p>
 *
 * @author Lucas Miles
 */

public class ValidadorPadraoResiduo implements ResiduoValidator {

    private String mensagemErro = "";

    @Override
    public boolean validar(ResiduoEletronico residuo) {
        if (residuo == null) {
            mensagemErro = "O resíduo não pode ser nulo.";
            return false;
        }

        if (residuo.getId() == null) {
            mensagemErro = "O ID do resíduo é obrigatório.";
            return false;
        }

        if (residuo.getNome() == null || residuo.getNome().trim().isEmpty()) {
            mensagemErro = "O nome do resíduo é obrigatório.";
            return false;
        }

        if (residuo.getCategoria() == null || residuo.getCategoria().trim().isEmpty()) {
            mensagemErro = "A categoria do resíduo é obrigatória.";
            return false;
        }

        if (residuo.getPeso() < 0) {
            mensagemErro = "O peso do resíduo não pode ser negativo.";
            return false;
        }

        mensagemErro = "";
        return true;
    }

    @Override
    public String getMensagemErro() {
        return mensagemErro;
    }
}