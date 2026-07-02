package ecowaste.testevisual;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ecowaste.model.ResiduoEletronico;

public class TesteGeradorImagem {

    public static void main(String[] args) {
        try {
            ResiduoEletronico residuo = new ResiduoEletronico();

            residuo.setId(1L);
            residuo.setNome("Notebook antigo");
            residuo.setCategoria("Computador");
            residuo.setPeso(2.5);
            residuo.setRiscoAmbiental("Alto");
            residuo.setValorEstimado(800.0);
            residuo.setMoeda("BRL");
            residuo.setCidade("Rio do Sul");
            residuo.setEstado("SC");
            residuo.setPais("Brasil");
            residuo.setContinente("América do Sul");
            residuo.setDestino("Ecoponto");
            residuo.setStatus("Cadastrado");

            GeradorImagemResiduo gerador = new GeradorImagemResiduo();
            BufferedImage imagem = gerador.gerarImagem(residuo);

            File pasta = new File("imagens-geradas");
            pasta.mkdirs();

            File arquivo = new File(pasta, "residuo-teste.png");

            ImageIO.write(imagem, "png", arquivo);

            System.out.println("Imagem gerada com sucesso em: " + arquivo.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}