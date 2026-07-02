package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.prototype.ResiduoPrototype;

/**
 * Classe de testes unitários responsável por validar a aplicação de um Design Pattern
 * no EcoWaste Framework.
 *
 * <p>
 * Os testes verificam se a estrutura implementada funciona corretamente e se o padrão
 * contribui para organização, reutilização e manutenção do sistema.
 * </p>
 *
 * @author Lucas Miles
 */

public class PrototypePatternTest {

    @Test
    public void deveClonarResiduo() {
    	ResiduoPrototype original = new ResiduoPrototype(
    	        1L, "Notebook antigo", "Informática", 2.5, "Médio",
    	        800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
    	        "Reuso educacional", "CADASTRADO"
    	);

        ResiduoPrototype clone = original.clonar();

        assertNotSame(original, clone);
        assertEquals(original.getId(), clone.getId());
        assertEquals(original.getNome(), clone.getNome());
        assertEquals(original.getCategoria(), clone.getCategoria());
        assertEquals(original.getPeso(), clone.getPeso());
        assertEquals(original.getRiscoAmbiental(), clone.getRiscoAmbiental());
        assertEquals(original.getValorEstimado(), clone.getValorEstimado());
        assertEquals(original.getMoeda(), clone.getMoeda());
        assertEquals(original.getCidade(), clone.getCidade());
        assertEquals(original.getEstado(), clone.getEstado());
        assertEquals(original.getPais(), clone.getPais());
        assertEquals(original.getContinente(), clone.getContinente());
        assertEquals(original.getDestino(), clone.getDestino());
        assertEquals(original.getStatus(), clone.getStatus());
    }
}