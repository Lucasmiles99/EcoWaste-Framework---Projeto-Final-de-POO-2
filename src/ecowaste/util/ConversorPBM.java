package ecowaste.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Conversor responsável por transformar uma imagem em arquivo PBM.
 *
 * <p>
 * O formato PBM, da família Netpbm, representa imagens binárias em preto e branco.
 * Nesta implementação, é utilizado o formato textual P1, no qual cada pixel é
 * representado por 0 ou 1.
 * </p>
 *
 * <p>
 * Para realizar a conversão, a imagem colorida é analisada pixel por pixel. Cada
 * pixel é convertido para uma intensidade de cinza e, em seguida, submetido a um
 * processo de binarização.
 * </p>
 *
 * <p>
 * A regra aplicada considera um limiar de intensidade: pixels abaixo do limite são
 * classificados como pretos, enquanto pixels acima ou iguais ao limite são
 * classificados como brancos.
 * </p>
 *
 * <p>
 * No EcoWaste Framework, esta classe demonstra o conceito de binarização de imagens,
 * muito utilizado em processamento de imagens e visão computacional para segmentação,
 * simplificação e análise visual.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorPBM {

    public void converterParaPBM(BufferedImage imagem, File arquivoSaida) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {

            int largura = imagem.getWidth();
            int altura = imagem.getHeight();

            writer.write("P1");
            writer.newLine();

            writer.write(largura + " " + altura);
            writer.newLine();

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    Color cor = new Color(imagem.getRGB(x, y));

                    int cinza = calcularCinza(cor);

                    int binario = cinza < 128 ? 1 : 0;

                    writer.write(binario + " ");
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