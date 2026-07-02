package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorDocumentosResiduo;
import ecowaste.service.ResultadoDocumentosResiduo;

/**
 * Classe de testes unitários para o {@link ecowaste.service.GerenciadorDocumentosResiduo}.
 *
 * <p>
 * Valida o fluxo completo de geração documental do EcoWaste Framework, verificando
 * se um resíduo eletrônico gera corretamente os arquivos de imagem, os canais RGB
 * e o arquivo XML final.
 * </p>
 *
 * @author Lucas Miles
 */

public class GerenciadorDocumentosResiduoTest {

    @Test
    void deveGerarTodosOsDocumentosDoResiduo() throws Exception {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9001L);
        residuo.setNome("Teste JUnit Celular");
        residuo.setCategoria("Celular");
        residuo.setPeso(0.4);
        residuo.setRiscoAmbiental("Alto");
        residuo.setValorEstimado(100.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Ecoponto");
        residuo.setStatus("Teste");

        GerenciadorDocumentosResiduo gerenciador = new GerenciadorDocumentosResiduo();

        ResultadoDocumentosResiduo resultado = gerenciador.processarResiduo(residuo);

        assertNotNull(resultado);
        assertNotNull(resultado.getArquivosResiduo());
        assertNotNull(resultado.getArquivoXml());

        assertTrue(resultado.getArquivosResiduo().getArquivoPNG().exists());
        assertTrue(resultado.getArquivosResiduo().getArquivoPPM().exists());
        assertTrue(resultado.getArquivosResiduo().getArquivoPGM().exists());
        assertTrue(resultado.getArquivosResiduo().getArquivoPBM().exists());

        assertTrue(resultado.getArquivosResiduo().getArquivoCanalVermelho().exists());
        assertTrue(resultado.getArquivosResiduo().getArquivoCanalVerde().exists());
        assertTrue(resultado.getArquivosResiduo().getArquivoCanalAzul().exists());

        assertTrue(resultado.getArquivoXml().exists());
    }

    @Test
    void deveGerarXmlComDadosDoResiduo() throws Exception {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9002L);
        residuo.setNome("Teste JUnit Bateria");
        residuo.setCategoria("Bateria");
        residuo.setPeso(0.8);
        residuo.setRiscoAmbiental("Alto");
        residuo.setValorEstimado(80.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Coleta especializada");
        residuo.setStatus("Gerado em teste");

        GerenciadorDocumentosResiduo gerenciador = new GerenciadorDocumentosResiduo();

        ResultadoDocumentosResiduo resultado = gerenciador.processarResiduo(residuo);

        File xml = resultado.getArquivoXml();

        String conteudoXml = Files.readString(xml.toPath());

        assertTrue(conteudoXml.contains("<residuo>"));
        assertTrue(conteudoXml.contains("<id>9002</id>"));
        assertTrue(conteudoXml.contains("<nome>Teste JUnit Bateria</nome>"));
        assertTrue(conteudoXml.contains("<categoria>Bateria</categoria>"));
        assertTrue(conteudoXml.contains("<riscoAmbiental>Alto</riscoAmbiental>"));
        assertTrue(conteudoXml.contains("<arquivos>"));
        assertTrue(conteudoXml.contains("<png>"));
        assertTrue(conteudoXml.contains("<ppm>"));
        assertTrue(conteudoXml.contains("<pgm>"));
        assertTrue(conteudoXml.contains("<pbm>"));
        assertTrue(conteudoXml.contains("<canalVermelho>"));
        assertTrue(conteudoXml.contains("<canalVerde>"));
        assertTrue(conteudoXml.contains("<canalAzul>"));
    }

    @Test
    void deveLancarExcecaoQuandoResiduoForNulo() {
        GerenciadorDocumentosResiduo gerenciador = new GerenciadorDocumentosResiduo();

        assertThrows(IllegalArgumentException.class, () -> {
            gerenciador.processarResiduo(null);
        });
    }
}