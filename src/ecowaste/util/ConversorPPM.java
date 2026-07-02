package ecowaste.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Conversor responsável por transformar uma imagem em arquivo PPM.
 *
 * <p>
 * O formato PPM, do conjunto Netpbm, representa imagens coloridas utilizando valores
 * RGB para cada pixel. Nesta implementação, é utilizado o formato textual P3, no qual
 * os valores de vermelho, verde e azul são gravados em texto no arquivo de saída.
 * </p>
 *
 * <p>
 * Esta classe percorre cada pixel de um {@link java.awt.image.BufferedImage}, extrai
 * seus componentes de cor e grava os valores RGB correspondentes no arquivo PPM.
 * </p>
 *
 * <p>
 * No EcoWaste Framework, este conversor é utilizado para demonstrar a representação
 * explícita dos pixels coloridos da imagem gerada para cada resíduo eletrônico.
 * </p>
 *
 * <p>
 * A conversão para PPM reforça conceitos de processamento de imagem, representação
 * matricial e decomposição de cores em canais RGB.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPPM {

    public void converterParaPPM(BufferedImage imagem, File arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            writer.write("P3");
            writer.newLine();

            writer.write(largura + " " + altura);
            writer.newLine();

            writer.write("255");
            writer.newLine();

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    Color cor = new Color(imagem.getRGB(x, y));

                    writer.write(cor.getRed() + " ");
                    writer.write(cor.getGreen() + " ");
                    writer.write(cor.getBlue() + " ");
                }

                writer.newLine();
            }
        }
    }
}