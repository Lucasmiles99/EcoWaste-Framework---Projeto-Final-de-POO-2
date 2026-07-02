package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

import ecowaste.util.ConversorPBM;

/**
 * Classe de testes unitários para o {@link ecowaste.util.ConversorPBM}.
 *
 * <p>
 * Valida a conversão de uma imagem para o formato PBM, verificando a criação do
 * arquivo, o cabeçalho P1 e a geração de valores binários 0 e 1.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPBMTest {

    @Test
    void deveConverterImagemParaArquivoPBM() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, Color.BLACK.getRGB());
        imagem.setRGB(1, 0, Color.WHITE.getRGB());
        imagem.setRGB(0, 1, Color.GRAY.getRGB());
        imagem.setRGB(1, 1, Color.RED.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPBM = new File(pasta, "teste-conversor.pbm");

        ConversorPBM conversor = new ConversorPBM();

        conversor.converterParaPBM(imagem, arquivoPBM);

        assertTrue(arquivoPBM.exists());
        assertTrue(arquivoPBM.length() > 0);
    }

    @Test
    void deveGerarCabecalhoPBMCorreto() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                2,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, Color.BLACK.getRGB());
        imagem.setRGB(1, 0, Color.WHITE.getRGB());
        imagem.setRGB(0, 1, Color.GRAY.getRGB());
        imagem.setRGB(1, 1, Color.RED.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPBM = new File(pasta, "teste-cabecalho.pbm");

        ConversorPBM conversor = new ConversorPBM();

        conversor.converterParaPBM(imagem, arquivoPBM);

        List<String> linhas = Files.readAllLines(arquivoPBM.toPath());

        assertEquals("P1", linhas.get(0));
        assertEquals("2 2", linhas.get(1));
    }

    @Test
    void deveGerarValoresBinariosZeroEOuUm() throws Exception {
        BufferedImage imagem = new BufferedImage(
                2,
                1,
                BufferedImage.TYPE_INT_RGB
        );

        imagem.setRGB(0, 0, Color.BLACK.getRGB());
        imagem.setRGB(1, 0, Color.WHITE.getRGB());

        File pasta = new File("imagens-geradas/testes-junit");
        pasta.mkdirs();

        File arquivoPBM = new File(pasta, "teste-binario.pbm");

        ConversorPBM conversor = new ConversorPBM();

        conversor.converterParaPBM(imagem, arquivoPBM);

        String conteudo = Files.readString(arquivoPBM.toPath());

        assertTrue(conteudo.contains("1"));
        assertTrue(conteudo.contains("0"));
    }
}