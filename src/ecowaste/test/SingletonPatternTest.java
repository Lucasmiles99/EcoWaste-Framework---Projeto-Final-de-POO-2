package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.singleton.ConfiguracaoSistema;

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

public class SingletonPatternTest {

    @Test
    public void deveRetornarMesmaInstancia() {
        ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstancia();
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstancia();

        assertSame(config1, config2);
    }

    @Test
    public void deveRetornarDadosDoSistema() {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();

        assertEquals("EcoWaste Framework", config.getNomeSistema());
        assertEquals("1.0", config.getVersao());
    }
}