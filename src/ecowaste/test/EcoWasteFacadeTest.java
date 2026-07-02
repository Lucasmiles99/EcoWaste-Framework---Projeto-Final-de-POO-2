package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.facade.EcoWasteFacade;
import ecowaste.model.ResiduoEletronico;
import ecowaste.strategy.DescarteReciclagem;

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

public class EcoWasteFacadeTest {

    @Test
    public void deveCadastrarResiduoPelaFacade() {
        EcoWasteFacade facade = new EcoWasteFacade();

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Notebook antigo", "Informática", 2.5, "Médio",
                800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Reuso educacional", "CADASTRADO"
        );

        facade.cadastrarResiduo(residuo);

        assertEquals(1, facade.listarResiduos().size());
    }

    @Test
    public void deveBuscarResiduoPelaFacade() {
        EcoWasteFacade facade = new EcoWasteFacade();

        ResiduoEletronico residuo = new ResiduoEletronico(
                2L, "Celular quebrado", "Telefonia", 0.3, "Médio",
                250.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Assistência técnica", "AGUARDANDO_TRIAGEM"
        );

        facade.cadastrarResiduo(residuo);

        ResiduoEletronico encontrado = facade.buscarResiduoPorId(2L);

        assertNotNull(encontrado);
        assertEquals("Celular quebrado", encontrado.getNome());
    }

    @Test
    public void deveExecutarDescartePelaFacade() {
        EcoWasteFacade facade = new EcoWasteFacade();

        ResiduoEletronico residuo = new ResiduoEletronico(
                3L, "Monitor antigo", "Imagem", 4.0, "Médio",
                180.0, "BRL", "Itajaí", "SC", "Brasil", "América do Sul",
                "Centro de reciclagem eletrônico", "EM_ANALISE"
        );

        String resultado = facade.executarDescarte(residuo, new DescarteReciclagem());

        assertTrue(resultado.contains("reciclagem"));
    }
}