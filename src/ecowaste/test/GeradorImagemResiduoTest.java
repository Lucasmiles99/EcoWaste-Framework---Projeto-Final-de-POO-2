package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.util.GeradorImagemResiduo;

/**
 * Classe de testes unitários para o {@link ecowaste.util.GeradorImagemResiduo}.
 *
 * <p>
 * Verifica se a imagem visual do resíduo eletrônico é criada corretamente, validando
 * dimensões, tipo da imagem e integridade básica do objeto gerado.
 * </p>
 *
 * @author Lucas Miles
 */

public class GeradorImagemResiduoTest {

    @Test
    void deveGerarImagemDoResiduoComDimensoesValidas() {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9301L);
        residuo.setNome("Teste Imagem Notebook");
        residuo.setCategoria("Computador");
        residuo.setPeso(2.5);
        residuo.setRiscoAmbiental("Médio");
        residuo.setValorEstimado(500.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Ecoponto");
        residuo.setStatus("Teste");

        GeradorImagemResiduo gerador = new GeradorImagemResiduo();

        BufferedImage imagem = gerador.gerarImagem(residuo);

        assertNotNull(imagem);
        assertEquals(900, imagem.getWidth());
        assertEquals(500, imagem.getHeight());
    }

    @Test
    void deveGerarImagemComTipoRgb() {
        ResiduoEletronico residuo = new ResiduoEletronico();

        residuo.setId(9302L);
        residuo.setNome("Teste Imagem Celular");
        residuo.setCategoria("Celular");
        residuo.setPeso(0.3);
        residuo.setRiscoAmbiental("Alto");
        residuo.setValorEstimado(100.0);
        residuo.setMoeda("BRL");
        residuo.setCidade("Rio do Sul");
        residuo.setEstado("SC");
        residuo.setPais("Brasil");
        residuo.setContinente("América do Sul");
        residuo.setDestino("Coleta especializada");
        residuo.setStatus("Teste");

        GeradorImagemResiduo gerador = new GeradorImagemResiduo();

        BufferedImage imagem = gerador.gerarImagem(residuo);

        assertNotNull(imagem);
        assertEquals(BufferedImage.TYPE_INT_RGB, imagem.getType());
    }
}