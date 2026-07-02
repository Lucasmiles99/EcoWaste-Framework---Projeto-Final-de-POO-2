package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import ecowaste.util.ConversorPGM;

/**
 * Classe de testes unitários para o {@link ecowaste.util.ConversorPGM}.
 *
 * <p>
 * Valida a conversão de uma imagem para o formato PGM, verificando a criação do
 * arquivo, o cabeçalho P2 e a transformação dos pixels RGB para escala de cinza.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPGMTest {

    @Test
    void deveConverterImagemParaArquivoPGM() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, Color.RED.getRGB());
        imagem.setRGB(1, 0, Color.GREEN.getRGB());
        imagem.setRGB(0, 1, Color.BLUE.getRGB());
        imagem.setRGB(1, 1, Color.WHITE.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPGM = new File(pasta, "teste-conversor.pgm");

        ConversorPGM conversor = new ConversorPGM();

        conversor.converterParaPGM(imagem, arquivoPGM);

        assertTrue(arquivoPGM.exists());
        assertTrue(arquivoPGM.length() > 0);
    }

    @Test
    void deveGerarCabecalhoPGMCorreto() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, Color.RED.getRGB());
        imagem.setRGB(1, 0, Color.GREEN.getRGB());
        imagem.setRGB(0, 1, Color.BLUE.getRGB());
        imagem.setRGB(1, 1, Color.WHITE.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPGM = new File(pasta, "teste-cabecalho.pgm");

        ConversorPGM conversor = new ConversorPGM();

        conversor.converterParaPGM(imagem, arquivoPGM);

        List<String> linhas = Files.readAllLines(arquivoPGM.toPath());

        assertEquals("P2", linhas.get(0));
        assertEquals("2 2", linhas.get(1));
        assertEquals("255", linhas.get(2));
    }

    @Test
    void deveConverterPixelRgbParaCinza() throws Exception {
        BufferedImage imagem = new BufferedImage(
                1,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(10, 20, 30).getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPGM = new File(pasta, "teste-cinza.pgm");

        ConversorPGM conversor = new ConversorPGM();

        conversor.converterParaPGM(imagem, arquivoPGM);

        String conteudo = Files.readString(arquivoPGM.toPath());

        /*
         * Cálculo esperado:
         * cinza = 10 * 0.299 + 20 * 0.587 + 30 * 0.114
         * cinza = 2.99 + 11.74 + 3.42
         * cinza = 18.15
         * Como no código é convertido para int, vira 18.
         */
        assertTrue(conteudo.contains("18"));
    }
}