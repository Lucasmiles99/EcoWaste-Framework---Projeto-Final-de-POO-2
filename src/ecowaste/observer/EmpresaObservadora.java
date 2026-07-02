package ecowaste.observer;

import ecowaste.model.ResiduoEletronico;

/**
 * Observador que simula uma empresa sendo notificada
 * quando um novo resíduo é cadastrado.
 */

public class EmpresaObservadora implements Observador {

    private String nomeEmpresa;
    private String ultimaNotificacao;

    public EmpresaObservadora(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    @Override
    public void atualizar(ResiduoEletronico residuo) {
        this.ultimaNotificacao = "Empresa " + nomeEmpresa +
                " notificada sobre o resíduo: " + residuo.getNome();
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }
}