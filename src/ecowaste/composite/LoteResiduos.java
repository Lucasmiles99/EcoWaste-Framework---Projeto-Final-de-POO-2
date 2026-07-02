package ecowaste.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um conjunto de resíduos.
 * Esta classe é o Composite, pois pode armazenar vários componentes.
 */

public class LoteResiduos implements ComponenteResiduo {

    private String nome;
    private List<ComponenteResiduo> componentes = new ArrayList<>();

    public LoteResiduos(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteResiduo componente) {
        componentes.add(componente);
    }

    public void remover(ComponenteResiduo componente) {
        componentes.remove(componente);
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public double getPesoTotal() {
        double total = 0;

        for (ComponenteResiduo componente : componentes) {
            total += componente.getPesoTotal();
        }

        return total;
    }

    public int totalItens() {
        return componentes.size();
    }
}