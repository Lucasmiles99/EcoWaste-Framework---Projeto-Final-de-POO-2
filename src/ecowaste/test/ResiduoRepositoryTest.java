package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.repository.ResiduoRepository;

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

public class ResiduoRepositoryTest {

    @Test
    public void deveSalvarResiduo() {
        ResiduoRepository repository = new ResiduoRepository();

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Notebook antigo", "Informática", 2.5, "Alto",
                800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Reuso educacional", "CADASTRADO"
        );

        repository.salvar(residuo);

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    public void deveBuscarResiduoPorId() {
        ResiduoRepository repository = new ResiduoRepository();

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Celular quebrado", "Telefonia", 0.3, "Médio",
                250.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
                "Assistência técnica", "AGUARDANDO_TRIAGEM"
        );

        repository.salvar(residuo);

        ResiduoEletronico encontrado = repository.buscarPorId(1L);

        assertNotNull(encontrado);
        assertEquals("Celular quebrado", encontrado.getNome());
    }

    @Test
    public void deveRemoverResiduo() {
        ResiduoRepository repository = new ResiduoRepository();

        ResiduoEletronico residuo = new ResiduoEletronico(
                1L, "Monitor antigo", "Imagem", 4.0, "Médio",
                180.0, "BRL", "Itajaí", "SC", "Brasil", "América do Sul",
                "Centro de reciclagem eletrônico", "EM_ANALISE"
        );

        repository.salvar(residuo);
        repository.remover(residuo);

        assertEquals(0, repository.listarTodos().size());
    }
}