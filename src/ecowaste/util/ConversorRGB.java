package ecowaste.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Conversor responsável pela separação dos canais RGB de uma imagem.
 *
 * <p>
 * Esta classe recebe uma imagem colorida e gera três novas imagens independentes:
 * uma contendo apenas o canal vermelho, outra contendo apenas o canal verde e outra
 * contendo apenas o canal azul.
 * </p>
 *
 * <p>
 * Para cada pixel da imagem original, o conversor extrai os valores dos canais
 * vermelho, verde e azul. Em seguida, cria imagens separadas mantendo apenas um
 * canal ativo por vez e zerando os demais.
 * </p>
 *
 * <p>
 * O resultado permite visualizar a contribuição de cada canal de cor na imagem
 * original, reforçando o entendimento do modelo RGB utilizado em computação gráfica,
 * processamento de imagens e visão computacional.
 * </p>
 *
 * <p>
 * No EcoWaste Framework, esta classe é aplicada sobre a imagem gerada para cada
 * resíduo eletrônico, criando representações visuais complementares aos formatos
 * PPM, PGM e PBM.
 * </p>
 *
 * @author Lucas Miles
 */

public class ConversorRGB {

    public void gerarCanalVermelho(BufferedImage imagemOriginal, File arquivoSaida) throws IOException {
        BufferedImage imagemVermelha = new BufferedImage(
                imagemOriginal.getWidth(),
                imagemOriginal.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < imagemOriginal.getHeight(); y++) {
            for (int x = 0; x < imagemOriginal.getWidth(); x++) {
                Color corOriginal = new Color(imagemOriginal.getRGB(x, y));

                int r = corOriginal.getRed();

                Color novaCor = new Color(r, 0, 0);

                imagemVermelha.setRGB(x, y, novaCor.getRGB());
            }
        }

        ImageIO.write(imagemVermelha, "png", arquivoSaida);
    }

    public void gerarCanalVerde(BufferedImage imagemOriginal, File arquivoSaida) throws IOException {
        BufferedImage imagemVerde = new BufferedImage(
                imagemOriginal.getWidth(),
                imagemOriginal.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < imagemOriginal.getHeight(); y++) {
            for (int x = 0; x < imagemOriginal.getWidth(); x++) {
                Color corOriginal = new Color(imagemOriginal.getRGB(x, y));

                int g = corOriginal.getGreen();

                Color novaCor = new Color(0, g, 0);

                imagemVerde.setRGB(x, y, novaCor.getRGB());
            }
        }

        ImageIO.write(imagemVerde, "png", arquivoSaida);
    }

    public void gerarCanalAzul(BufferedImage imagemOriginal, File arquivoSaida) throws IOException {
        BufferedImage imagemAzul = new BufferedImage(
                imagemOriginal.getWidth(),
                imagemOriginal.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < imagemOriginal.getHeight(); y++) {
            for (int x = 0; x < imagemOriginal.getWidth(); x++) {
                Color corOriginal = new Color(imagemOriginal.getRGB(x, y));

                int b = corOriginal.getBlue();

                Color novaCor = new Color(0, 0, b);

                imagemAzul.setRGB(x, y, novaCor.getRGB());
            }
        }

        ImageIO.write(imagemAzul, "png", arquivoSaida);
    }

    public void gerarTodosCanaisRGB(
            BufferedImage imagemOriginal,
            File arquivoCanalVermelho,
            File arquivoCanalVerde,
            File arquivoCanalAzul
    ) throws IOException {

        gerarCanalVermelho(imagemOriginal, arquivoCanalVermelho);
        gerarCanalVerde(imagemOriginal, arquivoCanalVerde);
        gerarCanalAzul(imagemOriginal, arquivoCanalAzul);
    }
}