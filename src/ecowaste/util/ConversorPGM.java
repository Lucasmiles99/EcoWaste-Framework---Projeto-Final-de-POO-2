package ecowaste.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Conversor responsável por transformar uma imagem colorida em arquivo PGM.
 *
 * <p>
 * O formato PGM, pertencente à família Netpbm, representa imagens em escala de cinza.
 * Nesta implementação, é utilizado o formato textual P2, no qual cada pixel é
 * representado por um valor de intensidade entre 0 e 255.
 * </p>
 *
 * <p>
 * A conversão é realizada percorrendo os pixels de um
 * {@link java.awt.image.BufferedImage} e calculando a intensidade de cinza a partir
 * dos canais RGB.
 * </p>
 *
 * <p>
 * A fórmula utilizada considera pesos diferentes para os canais vermelho, verde e
 * azul, aproximando a percepção visual humana:
 * </p>
 *
 * <pre>
 * cinza = R * 0.299 + G * 0.587 + B * 0.114
 * </pre>
 *
 * <p>
 * No EcoWaste Framework, esta classe demonstra a aplicação prática de fundamentos
 * de visão computacional, especialmente a conversão de imagens coloridas para escala
 * de cinza.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPGM {

    public void converterParaPGM(BufferedImage imagem, File arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            writer.write("P2");
            writer.newLine();

            writer.write(largura + " " + altura);
            writer.newLine();

            writer.write("255");
            writer.newLine();

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    Color cor = new Color(imagem.getRGB(x, y));

                    int cinza = calcularCinza(cor);

                    writer.write(cinza + " ");
                }

                writer.newLine();
            }
        }
    }

    private int calcularCinza(Color cor) {
        return (int) (
                cor.getRed() * 0.299 +
                cor.getGreen() * 0.587 +
                cor.getBlue() * 0.114
        );
    }
}