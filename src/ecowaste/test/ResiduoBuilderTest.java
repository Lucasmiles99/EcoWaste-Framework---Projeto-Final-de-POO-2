package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.builder.ResiduoBuilder;
import ecowaste.model.ResiduoEletronico;

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

public class ResiduoBuilderTest {

    @Test
    public void deveCriarResiduoComBuilder() {
        ResiduoEletronico residuo = new ResiduoBuilder()
                .id(1L)
                .nome("Notebook antigo")
                .categoria("Informática")
                .peso(2.5)
                .riscoAmbiental("Médio")
                .build();

        assertNotNull(residuo);
        assertEquals(1L, residuo.getId());
        assertEquals("Notebook antigo", residuo.getNome());
        assertEquals("Informática", residuo.getCategoria());
        assertEquals(2.5, residuo.getPeso());
        assertEquals("Médio", residuo.getRiscoAmbiental());
    }

    @Test
    public void deveCriarBateriaComBuilder() {
        ResiduoEletronico residuo = new ResiduoBuilder()
                .id(2L)
                .nome("Bateria danificada")
                .categoria("Bateria")
                .peso(0.4)
                .riscoAmbiental("Alto")
                .build();

        assertNotNull(residuo);
        assertEquals("Bateria danificada", residuo.getNome());
        assertEquals("Alto", residuo.getRiscoAmbiental());
    }
}