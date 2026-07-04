package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ecowaste.framework.EcoWasteFramework;
import ecowaste.framework.EcoWasteFrameworkBuilder;
import ecowaste.model.ResiduoEletronico;

/**
 * Testes do núcleo do EcoWaste Framework.
 *
 * <p>
 * Essa classe valida o uso do EcoWaste como biblioteca/framework,
 * independentemente da interface gráfica Swing.
 * </p>
 *
 * @author Lucas Miles
 */
public class EcoWasteFrameworkCoreTest {

    @Test
    public void deveCadastrarResiduoUsandoFrameworkCore() throws Exception {
        EcoWasteFramework framework = EcoWasteFrameworkBuilder
                .novo()
                .gerarDocumentosAutomaticamente(false)
                .build();

        ResiduoEletronico residuo = criarResiduoTeste();

        framework.cadastrarResiduo(residuo);

        List<ResiduoEletronico> residuos = framework.listarResiduos();

        assertEquals(1, residuos.size());
        assertEquals("Bateria Teste Framework", residuos.get(0).getNome());
    }

    @Test
    public void deveBuscarResiduoPorIdUsandoFrameworkCore() throws Exception {
        EcoWasteFramework framework = EcoWasteFrameworkBuilder
                .novo()
                .gerarDocumentosAutomaticamente(false)
                .build();

        ResiduoEletronico residuo = criarResiduoTeste();

        framework.cadastrarResiduo(residuo);

        ResiduoEletronico encontrado = framework.buscarResiduoPorId(5000L);

        assertNotNull(encontrado);
        assertEquals("Bateria Teste Framework", encontrado.getNome());
    }

    @Test
    public void deveRemoverResiduoUsandoFrameworkCore() throws Exception {
        EcoWasteFramework framework = EcoWasteFrameworkBuilder
                .novo()
                .gerarDocumentosAutomaticamente(false)
                .build();

        ResiduoEletronico residuo = criarResiduoTeste();

        framework.cadastrarResiduo(residuo);
        framework.removerResiduo(5000L);

        assertEquals(0, framework.listarResiduos().size());
    }

    @Test
    public void deveValidarResiduoNulo() {
        EcoWasteFramework framework = EcoWasteFrameworkBuilder
                .novo()
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            framework.cadastrarResiduo(null);
        });

        assertTrue(exception.getMessage().contains("não pode ser nulo"));
    }

    private ResiduoEletronico criarResiduoTeste() {
        return new ResiduoEletronico(
                5000L,
                "Bateria Teste Framework",
                "Bateria",
                1.5,
                "Alto",
                120.0,
                "BRL",
                "Rio do Sul",
                "SC",
                "Brasil",
                "América do Sul",
                "Ecoponto",
                "Teste"
        );
    }
}