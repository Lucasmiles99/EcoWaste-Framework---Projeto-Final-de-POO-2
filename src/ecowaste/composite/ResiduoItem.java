package ecowaste.composite;

import ecowaste.model.ResiduoEletronico;

/**
 * Representa um resíduo individual dentro do Composite Pattern.
 */

public class ResiduoItem implements ComponenteResiduo {

    private ResiduoEletronico residuo;

    public ResiduoItem(ResiduoEletronico residuo) {
        this.residuo = residuo;
    }

    @Override
    public String getDescricao() {
        return residuo.getNome();
    }

    @Override
    public double getPesoTotal() {
        return residuo.getPeso();
    }
}