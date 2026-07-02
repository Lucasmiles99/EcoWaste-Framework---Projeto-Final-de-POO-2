package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.proxy.ResiduoRepositoryProxy;
import ecowaste.proxy.UsuarioSistema;

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

public class ProxyPatternTest {

    @Test
    public void usuarioComumNaoDeveRemoverResiduo() {
        UsuarioSistema usuario = new UsuarioSistema("Lucas", "USER");
        ResiduoRepositoryProxy proxy = new ResiduoRepositoryProxy(usuario);

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Notebook antigo", "Informática", 2.5, "Médio",
                800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Reuso educacional", "CADASTRADO"
        );

        proxy.salvar(residuo);

        assertThrows(SecurityException.class, () -> {
            proxy.remover(residuo);
        });
    }

    @Test
    public void administradorDeveRemoverResiduo() {
        UsuarioSistema admin = new UsuarioSistema("Administrador", "ADMIN");
        ResiduoRepositoryProxy proxy = new ResiduoRepositoryProxy(admin);

        ResiduoEletronico residuo = new ResiduoEletronico(
                2L, "Bateria danificada", "Bateria", 0.4, "Alto",
                45.0, "BRL", "Blumenau", "SC", "Brasil", "América do Sul",
                "Descarte seguro certificado", "RISCO_CRITICO"
        );

        proxy.salvar(residuo);
        proxy.remover(residuo);

        assertEquals(0, proxy.listarTodos().size());
    }

    @Test
    public void usuarioComumPodeSalvarEListar() {
        UsuarioSistema usuario = new UsuarioSistema("Daniel", "USER");
        ResiduoRepositoryProxy proxy = new ResiduoRepositoryProxy(usuario);

        ResiduoEletronico residuo = new ResiduoEletronico(
                3L, "Celular quebrado", "Telefonia", 0.3, "Médio",
                250.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Assistência técnica", "AGUARDANDO_TRIAGEM"
        );

        proxy.salvar(residuo);

        assertEquals(1, proxy.listarTodos().size());
    }
}