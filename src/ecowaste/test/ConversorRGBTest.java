package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import ecowaste.util.ConversorRGB;

/**
 * Classe de testes unitários para o {@link ecowaste.util.ConversorRGB}.
 *
 * <p>
 * Valida a separação dos canais RGB da imagem, verificando se os arquivos dos
 * canais vermelho, verde e azul são gerados corretamente e preservam apenas o canal
 * esperado.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorRGBTest {

    @Test
    void deveGerarArquivosDosCanaisRGB() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(100, 50, 25).getRGB());
        imagem.setRGB(1, 0, new Color(10, 200, 30).getRGB());
        imagem.setRGB(0, 1, new Color(40, 60, 220).getRGB());
        imagem.setRGB(1, 1, Color.WHITE.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoVermelho = new File(pasta, "teste-canal-red.png");
        File arquivoVerde = new File(pasta, "teste-canal-green.png");
        File arquivoAzul = new File(pasta, "teste-canal-blue.png");

        ConversorRGB conversor = new ConversorRGB();

        conversor.gerarTodosCanaisRGB(
                imagem,
                arquivoVermelho,
                arquivoVerde,
                arquivoAzul
        );

        assertTrue(arquivoVermelho.exists());
        assertTrue(arquivoVerde.exists());
        assertTrue(arquivoAzul.exists());

        assertTrue(arquivoVermelho.length() > 0);
        assertTrue(arquivoVerde.length() > 0);
        assertTrue(arquivoAzul.length() > 0);
    }

    @Test
    void deveManterApenasCanalVermelho() throws Exception {
        BufferedImage imagem = new BufferedImage(
                1,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(100, 50, 25).getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoVermelho = new File(pasta, "teste-apenas-red.png");

        ConversorRGB conversor = new ConversorRGB();

        conversor.gerarCanalVermelho(imagem, arquivoVermelho);

        BufferedImage imagemVermelha = ImageIO.read(arquivoVermelho);

        Color cor = new Color(imagemVermelha.getRGB(0, 0));

        assertEquals(100, cor.getRed());
        assertEquals(0, cor.getGreen());
        assertEquals(0, cor.getBlue());
    }

    @Test
    void deveManterApenasCanalVerde() throws Exception {
        BufferedImage imagem = new BufferedImage(
                1,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(100, 50, 25).getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoVerde = new File(pasta, "teste-apenas-green.png");

        ConversorRGB conversor = new ConversorRGB();

        conversor.gerarCanalVerde(imagem, arquivoVerde);

        BufferedImage imagemVerde = ImageIO.read(arquivoVerde);

        Color cor = new Color(imagemVerde.getRGB(0, 0));

        assertEquals(0, cor.getRed());
        assertEquals(50, cor.getGreen());
        assertEquals(0, cor.getBlue());
    }

    @Test
    void deveManterApenasCanalAzul() throws Exception {
        BufferedImage imagem = new BufferedImage(
                1,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(100, 50, 25).getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoAzul = new File(pasta, "teste-apenas-blue.png");

        ConversorRGB conversor = new ConversorRGB();

        conversor.gerarCanalAzul(imagem, arquivoAzul);

        BufferedImage imagemAzul = ImageIO.read(arquivoAzul);

        Color cor = new Color(imagemAzul.getRGB(0, 0));

        assertEquals(0, cor.getRed());
        assertEquals(0, cor.getGreen());
        assertEquals(25, cor.getBlue());
    }
}