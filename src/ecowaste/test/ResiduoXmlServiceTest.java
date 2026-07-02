package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorArquivosResiduo;
import ecowaste.service.ResiduoXmlService;
import ecowaste.service.ResultadoArquivosResiduo;

/**
 * Classe de testes unitários para o {@link ecowaste.service.ResiduoXmlService}.
 *
 * <p>
 * Valida a geração automática do arquivo XML contendo os dados do resíduo eletrônico
 * e os caminhos dos arquivos de imagem gerados pelo sistema.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoXmlServiceTest {

    @Test
    void deveGerarXmlDoResiduo() throws Exception {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9201L);
        residuo.setNome("Teste XML Impressora");
        residuo.setCategoria("Impressora");
        residuo.setPeso(5.0);
        residuo.setRiscoAmbiental("Médio");
        residuo.setValorEstimado(180.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Centro de reciclagem");
        residuo.setStatus("Teste XML");

        GerenciadorArquivosResiduo gerenciadorArquivos = new GerenciadorArquivosResiduo();
        ResultadoArquivosResiduo arquivos = gerenciadorArquivos.gerarArquivos(residuo);

        ResiduoXmlService xmlService = new ResiduoXmlService();

        File arquivoXml = xmlService.gerarXml(residuo, arquivos);

        assertNotNull(arquivoXml);
        assertTrue(arquivoXml.exists());

        String conteudo = Files.readString(arquivoXml.toPath());

        assertTrue(conteudo.contains("<residuo>"));
        assertTrue(conteudo.contains("<id>9201</id>"));
        assertTrue(conteudo.contains("<nome>Teste XML Impressora</nome>"));
        assertTrue(conteudo.contains("<categoria>Impressora</categoria>"));
        assertTrue(conteudo.contains("<arquivos>"));
        assertTrue(conteudo.contains("<png>"));
        assertTrue(conteudo.contains("<ppm>"));
        assertTrue(conteudo.contains("<pgm>"));
        assertTrue(conteudo.contains("<pbm>"));
        assertTrue(conteudo.contains("<canalVermelho>"));
        assertTrue(conteudo.contains("<canalVerde>"));
        assertTrue(conteudo.contains("<canalAzul>"));
    }

    @Test
    void deveEscaparCaracteresEspeciaisNoXml() throws Exception {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9202L);
        residuo.setNome("Monitor & Cabo <Teste>");
        residuo.setCategoria("Monitor");
        residuo.setPeso(2.0);
        residuo.setRiscoAmbiental("Baixo");
        residuo.setValorEstimado(50.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Ecoponto");
        residuo.setStatus("Teste");

        GerenciadorArquivosResiduo gerenciadorArquivos = new GerenciadorArquivosResiduo();
        ResultadoArquivosResiduo arquivos = gerenciadorArquivos.gerarArquivos(residuo);

        ResiduoXmlService xmlService = new ResiduoXmlService();

        File arquivoXml = xmlService.gerarXml(residuo, arquivos);

        String conteudo = Files.readString(arquivoXml.toPath());

        assertTrue(conteudo.contains("Monitor &amp; Cabo &lt;Teste&gt;"));
    }

    @Test
    void deveLancarExcecaoQuandoResiduoForNulo() {
        ResiduoXmlService xmlService = new ResiduoXmlService();

        assertThrows(IllegalArgumentException.class, () -> {
            xmlService.gerarXml(null, null);
        });
    }
}