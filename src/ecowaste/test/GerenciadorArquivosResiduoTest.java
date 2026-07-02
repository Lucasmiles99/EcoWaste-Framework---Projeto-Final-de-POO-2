package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorArquivosResiduo;
import ecowaste.service.ResultadoArquivosResiduo;

/**
 * Classe de testes unitários para o {@link ecowaste.service.GerenciadorArquivosResiduo}.
 *
 * <p>
 * Verifica se os arquivos de imagem relacionados a um resíduo eletrônico são
 * gerados corretamente, incluindo PNG, PPM, PGM, PBM e os canais RGB separados.
 * </p>
 *
 * @author Lucas Miles
 */

public class GerenciadorArquivosResiduoTest {

    @Test
    void deveGerarArquivosDeImagemDoResiduo() throws Exception {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9101L);
        residuo.setNome("Teste JUnit Monitor");
        residuo.setCategoria("Monitor");
        residuo.setPeso(3.5);
        residuo.setRiscoAmbiental("Médio");
        residuo.setValorEstimado(200.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Cooperativa");
        residuo.setStatus("Teste");

        GerenciadorArquivosResiduo gerenciador = new GerenciadorArquivosResiduo();

        ResultadoArquivosResiduo resultado = gerenciador.gerarArquivos(residuo);

        assertNotNull(resultado);

        assertTrue(resultado.getArquivoPNG().exists());
        assertTrue(resultado.getArquivoPPM().exists());
        assertTrue(resultado.getArquivoPGM().exists());
        assertTrue(resultado.getArquivoPBM().exists());

        assertTrue(resultado.getArquivoCanalVermelho().exists());
        assertTrue(resultado.getArquivoCanalVerde().exists());
        assertTrue(resultado.getArquivoCanalAzul().exists());
    }

    @Test
    void deveLancarExcecaoQuandoResiduoForNulo() {
        GerenciadorArquivosResiduo gerenciador = new GerenciadorArquivosResiduo();

        assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.gerarArquivos(null);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        ResiduoEletronico residuo = new ResiduoEletronico();
        residuo.setId(9102L);
        residuo.setNome("");

        GerenciadorArquivosResiduo gerenciador = new GerenciadorArquivosResiduo();

        assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.gerarArquivos(residuo);
        });
    }
}