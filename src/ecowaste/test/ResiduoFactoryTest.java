package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.factory.ResiduoFactory;
import ecowaste.factory.TipoResiduo;
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

public class ResiduoFactoryTest {

    @Test
    public void deveCriarNotebook() {
        ResiduoEletronico residuo = ResiduoFactory.criarResiduo(TipoResiduo.NOTEBOOK, 1L);

        assertNotNull(residuo);
        assertEquals("Notebook antigo", residuo.getNome());
        assertEquals("Informática", residuo.getCategoria());
        assertEquals("BRL", residuo.getMoeda());
        assertEquals("Brasil", residuo.getPais());
    }

    @Test
    public void deveCriarBateriaComRiscoAlto() {
        ResiduoEletronico residuo = ResiduoFactory.criarResiduo(TipoResiduo.BATERIA, 2L);

        assertNotNull(residuo);
        assertEquals("Bateria de notebook estufada", residuo.getNome());
        assertEquals("Bateria", residuo.getCategoria());
        assertEquals("Alto", residuo.getRiscoAmbiental());
        assertEquals("RISCO_CRITICO", residuo.getStatus());
    }

    @Test
    public void deveCriarCelular() {
        ResiduoEletronico residuo = ResiduoFactory.criarResiduo(TipoResiduo.CELULAR, 3L);

        assertNotNull(residuo);
        assertEquals("Smartphone com tela quebrada", residuo.getNome());
        assertEquals("Telefonia", residuo.getCategoria());
        assertEquals(0.3, residuo.getPeso());
        assertEquals("AGUARDANDO_TRIAGEM", residuo.getStatus());
    }
}