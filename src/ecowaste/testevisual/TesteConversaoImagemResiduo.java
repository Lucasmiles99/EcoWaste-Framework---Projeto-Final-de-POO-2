package ecowaste.testevisual;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ecowaste.util.ConversorPBM;
import ecowaste.util.ConversorPGM;
import ecowaste.util.ConversorPPM;

public class TesteConversaoImagemResiduo {

    public static void main(String[] args) {
        try {
            File pasta = new File("imagens-geradas");

            File imagemOriginal = new File(pasta, "residuo-teste.png");

            if (!imagemOriginal.exists()) {
                System.out.println("Imagem PNG não encontrada.");
                System.out.println("Execute primeiro a classe TesteGeradorImagem.");
                return;
            }

            BufferedImage imagem = ImageIO.read(imagemOriginal);

            ConversorPPM conversorPPM = new ConversorPPM();
            ConversorPGM conversorPGM = new ConversorPGM();
            ConversorPBM conversorPBM = new ConversorPBM();

            File arquivoPPM = new File(pasta, "residuo-teste.ppm");
            File arquivoPGM = new File(pasta, "residuo-teste.pgm");
            File arquivoPBM = new File(pasta, "residuo-teste.pbm");

            conversorPPM.converterParaPPM(imagem, arquivoPPM);
            conversorPGM.converterParaPGM(imagem, arquivoPGM);
            conversorPBM.converterParaPBM(imagem, arquivoPBM);

            System.out.println("Conversões realizadas com sucesso!");
            System.out.println("PPM gerado em: " + arquivoPPM.getAbsolutePath());
            System.out.println("PGM gerado em: " + arquivoPGM.getAbsolutePath());
            System.out.println("PBM gerado em: " + arquivoPBM.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erro ao converter imagem.");
            e.printStackTrace();
        }
    }
}