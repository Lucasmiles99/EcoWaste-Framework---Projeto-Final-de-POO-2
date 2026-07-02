package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.observer.CentralNotificacoes;
import ecowaste.observer.EmpresaObservadora;

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

public class ObserverPatternTest {

    @Test
    public void deveAdicionarObservador() {
        CentralNotificacoes central = new CentralNotificacoes();
        EmpresaObservadora empresa = new EmpresaObservadora("EcoTech");

        central.adicionarObservador(empresa);

        assertEquals(1, central.totalObservadores());
    }

    @Test
    public void deveNotificarEmpresaQuandoResiduoForCadastrado() {
        CentralNotificacoes central = new CentralNotificacoes();
        EmpresaObservadora empresa = new EmpresaObservadora("EcoTech");

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Notebook antigo", "Informática", 2.5, "Médio",
                800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Reuso educacional", "CADASTRADO"
        );

        central.adicionarObservador(empresa);
        central.notificar(residuo);

        assertNotNull(empresa.getUltimaNotificacao());
        assertTrue(empresa.getUltimaNotificacao().contains("Notebook antigo"));
    }

    @Test
    public void deveRemoverObservador() {
        CentralNotificacoes central = new CentralNotificacoes();
        EmpresaObservadora empresa = new EmpresaObservadora("EcoTech");

        central.adicionarObservador(empresa);
        central.removerObservador(empresa);

        assertEquals(0, central.totalObservadores());
    }
}