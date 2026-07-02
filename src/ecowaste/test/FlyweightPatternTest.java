package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.flyweight.CategoriaFactory;
import ecowaste.flyweight.CategoriaResiduo;

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

public class FlyweightPatternTest {

    @Test
    public void deveReutilizarCategoriaExistente() {
        CategoriaFactory.limparCategorias();

        CategoriaResiduo categoria1 = CategoriaFactory.obterCategoria(
                "Informática",
                "Resíduos relacionados a computadores e notebooks"
        );

        CategoriaResiduo categoria2 = CategoriaFactory.obterCategoria(
                "Informática",
                "Resíduos relacionados a computadores e notebooks"
        );

        assertSame(categoria1, categoria2);
        assertEquals(1, CategoriaFactory.totalCategoriasCriadas());
    }

    @Test
    public void deveCriarCategoriasDiferentes() {
        CategoriaFactory.limparCategorias();

        CategoriaResiduo categoria1 = CategoriaFactory.obterCategoria(
                "Informática",
                "Computadores e notebooks"
        );

        CategoriaResiduo categoria2 = CategoriaFactory.obterCategoria(
                "Bateria",
                "Baterias e componentes químicos"
        );

        assertNotSame(categoria1, categoria2);
        assertEquals(2, CategoriaFactory.totalCategoriasCriadas());
    }
}