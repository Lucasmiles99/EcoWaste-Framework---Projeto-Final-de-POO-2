package ecowaste.composite;

/**
 * Interface base do Composite Pattern.
 * Permite tratar resíduos individuais e lotes de resíduos de forma uniforme.
 */

public interface ComponenteResiduo {

    String getDescricao();

    double getPesoTotal();
}