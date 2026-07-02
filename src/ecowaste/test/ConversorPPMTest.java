package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import ecowaste.util.ConversorPPM;

/**
 * Classe de testes unitários para o {@link ecowaste.util.ConversorPPM}.
 *
 * <p>
 * Valida a conversão de uma imagem para o formato PPM, verificando a criação do
 * arquivo, o cabeçalho P3 e a gravação dos valores RGB dos pixels.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPPMTest {

    @Test
    void deveConverterImagemParaArquivoPPM() throws Exception {
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

        File arquivoPPM = new File(pasta, "teste-conversor.ppm");

        ConversorPPM conversor = new ConversorPPM();

        conversor.converterParaPPM(imagem, arquivoPPM);

        assertTrue(arquivoPPM.exists());
        assertTrue(arquivoPPM.length() > 0);
    }

    @Test
    void deveGerarCabecalhoPPMCorreto() throws Exception {
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

        File arquivoPPM = new File(pasta, "teste-cabecalho.ppm");

        ConversorPPM conversor = new ConversorPPM();

        conversor.converterParaPPM(imagem, arquivoPPM);

        List<String> linhas = Files.readAllLines(arquivoPPM.toPath());

        assertEquals("P3", linhas.get(0));
        assertEquals("2 2", linhas.get(1));
        assertEquals("255", linhas.get(2));
    }

    @Test
    void deveConterValoresRgbDosPixels() throws Exception {
        BufferedImage imagem = new BufferedImage(
                1,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, new Color(10, 20, 30).getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPPM = new File(pasta, "teste-rgb.ppm");

        ConversorPPM conversor = new ConversorPPM();

        conversor.converterParaPPM(imagem, arquivoPPM);

        String conteudo = Files.readString(arquivoPPM.toPath());

        assertTrue(conteudo.contains("10"));
        assertTrue(conteudo.contains("20"));
        assertTrue(conteudo.contains("30"));
    }
}