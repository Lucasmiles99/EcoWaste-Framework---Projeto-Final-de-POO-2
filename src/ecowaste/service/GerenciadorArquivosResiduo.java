package ecowaste.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ecowaste.model.ResiduoEletronico;
import ecowaste.util.ConversorPBM;
import ecowaste.util.ConversorPGM;
import ecowaste.util.ConversorPPM;
import ecowaste.util.ConversorRGB;
import ecowaste.util.GeradorImagemResiduo;

/**
 * Serviço responsável pela geração dos arquivos de imagem de um resíduo eletrônico.
 *
 * <p>
 * Esta classe coordena a criação da imagem visual do resíduo e suas respectivas
 * conversões para diferentes formatos e representações. Ela recebe um objeto
 * {@link ecowaste.model.ResiduoEletronico} e gera arquivos relacionados ao
 * processamento de imagem.
 * </p>
 *
 * <p>
 * Os arquivos gerados por este serviço incluem:
 * </p>
 *
 * <ul>
 *     <li>imagem principal em PNG;</li>
 *     <li>imagem colorida em formato PPM;</li>
 *     <li>imagem em escala de cinza em formato PGM;</li>
 *     <li>imagem binarizada em formato PBM;</li>
 *     <li>imagem contendo apenas o canal vermelho;</li>
 *     <li>imagem contendo apenas o canal verde;</li>
 *     <li>imagem contendo apenas o canal azul.</li>
 * </ul>
 *
 * <p>
 * Esta classe integra conceitos de Programação Orientada a Objetos com fundamentos
 * de processamento de imagens, como canais RGB, escala de cinza, binarização e
 * representação matricial de pixels.
 * </p>
 *
 * <p>
 * Ao centralizar esse processo, a classe evita que a interface gráfica tenha que
 * lidar diretamente com detalhes de arquivos, imagens, conversões e manipulação
 * de pixels.
 * </p>
 *
 * @author Lucas Miles
 */

public class GerenciadorArquivosResiduo {

    private final GeradorImagemResiduo geradorImagem;
    private final ConversorPPM conversorPPM;
    private final ConversorPGM conversorPGM;
    private final ConversorPBM conversorPBM;
    private final ConversorRGB conversorRGB;

    public GerenciadorArquivosResiduo() {
        this.geradorImagem = new GeradorImagemResiduo();
        this.conversorPPM = new ConversorPPM();
        this.conversorPGM = new ConversorPGM();
        this.conversorPBM = new ConversorPBM();
        this.conversorRGB = new ConversorRGB();
    }

    public ResultadoArquivosResiduo gerarArquivos(ResiduoEletronico residuo) throws IOException {
        validarResiduo(residuo);

        File pasta = new File("imagens-geradas");

        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        String nomeBaseArquivo = gerarNomeBaseArquivo(residuo);

        File arquivoPNG = new File(pasta, nomeBaseArquivo + ".png");
        File arquivoPPM = new File(pasta, nomeBaseArquivo + ".ppm");
        File arquivoPGM = new File(pasta, nomeBaseArquivo + ".pgm");
        File arquivoPBM = new File(pasta, nomeBaseArquivo + ".pbm");

        File arquivoCanalVermelho = new File(pasta, nomeBaseArquivo + "-canal-red.png");
        File arquivoCanalVerde = new File(pasta, nomeBaseArquivo + "-canal-green.png");
        File arquivoCanalAzul = new File(pasta, nomeBaseArquivo + "-canal-blue.png");

        BufferedImage imagem = geradorImagem.gerarImagem(residuo);

        ImageIO.write(imagem, "png", arquivoPNG);

        conversorPPM.converterParaPPM(imagem, arquivoPPM);
        conversorPGM.converterParaPGM(imagem, arquivoPGM);
        conversorPBM.converterParaPBM(imagem, arquivoPBM);

        conversorRGB.gerarTodosCanaisRGB(
                imagem,
                arquivoCanalVermelho,
                arquivoCanalVerde,
                arquivoCanalAzul
        );

        return new ResultadoArquivosResiduo(
                arquivoPNG,
                arquivoPPM,
                arquivoPGM,
                arquivoPBM,
                arquivoCanalVermelho,
                arquivoCanalVerde,
                arquivoCanalAzul
        );
    }

    private void validarResiduo(ResiduoEletronico residuo) {
        if (residuo == null) {
            throw new IllegalArgumentException("O resíduo eletrônico não pode ser nulo.");
        }

        if (residuo.getNome() == null || residuo.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do resíduo eletrônico é obrigatório.");
        }
    }

    private String gerarNomeBaseArquivo(ResiduoEletronico residuo) {
        String id = residuo.getId() != null
                ? String.valueOf(residuo.getId())
                : "sem-id";

        String nomeTratado = residuo.getNome()
                .toLowerCase()
                .replace("ç", "c")
                .replace("ã", "a")
                .replace("á", "a")
                .replace("à", "a")
                .replace("é", "e")
                .replace("ê", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ô", "o")
                .replace("ú", "u")
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        return "residuo-" + id + "-" + nomeTratado;
    }
}