package ecowaste.testevisual;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ecowaste.util.ConversorRGB;

public class TesteConversaoRGBResiduo {

    public static void main(String[] args) {
        try {
            File pasta = new File("imagens-geradas");

            File imagemOriginal = new File(pasta, "residuo-2-monitor-lcd-quebrado.png");

            if (!imagemOriginal.exists()) {
                System.out.println("Imagem PNG não encontrada.");
                System.out.println("Execute primeiro a classe TesteGerenciadorArquivosResiduo.");
                return;
            }

            BufferedImage imagem = ImageIO.read(imagemOriginal);

            ConversorRGB conversorRGB = new ConversorRGB();

            File arquivoCanalVermelho = new File(pasta, "residuo-2-monitor-lcd-quebrado-canal-red.png");
            File arquivoCanalVerde = new File(pasta, "residuo-2-monitor-lcd-quebrado-canal-green.png");
            File arquivoCanalAzul = new File(pasta, "residuo-2-monitor-lcd-quebrado-canal-blue.png");

            conversorRGB.gerarTodosCanaisRGB(
                    imagem,
                    arquivoCanalVermelho,
                    arquivoCanalVerde,
                    arquivoCanalAzul
            );

            System.out.println("Canais RGB gerados com sucesso!");
            System.out.println("Canal vermelho: " + arquivoCanalVermelho.getAbsolutePath());
            System.out.println("Canal verde: " + arquivoCanalVerde.getAbsolutePath());
            System.out.println("Canal azul: " + arquivoCanalAzul.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erro ao gerar canais RGB.");
            e.printStackTrace();
        }
    }
}