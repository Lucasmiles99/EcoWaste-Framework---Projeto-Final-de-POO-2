package ecowaste.observer;

import java.util.ArrayList;
import java.util.List;

import ecowaste.model.ResiduoEletronico;

/**
 * Classe responsável por gerenciar observadores e notificá-los.
 */

public class CentralNotificacoes {

    private List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificar(ResiduoEletronico residuo) {
        for (Observador observador : observadores) {
            observador.atualizar(residuo);
        }
    }

    public int totalObservadores() {
        return observadores.size();
    }
}