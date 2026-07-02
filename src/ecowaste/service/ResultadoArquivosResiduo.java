package ecowaste.service;

import java.io.File;

/**
 * Armazena as referências para os arquivos de imagem gerados a partir de um resíduo.
 *
 * <p>
 * Esta classe funciona como um objeto de transporte de dados, reunindo em uma única
 * estrutura todos os arquivos produzidos pelo
 * {@link ecowaste.service.GerenciadorArquivosResiduo}.
 * </p>
 *
 * <p>
 * Os arquivos armazenados por esta classe representam diferentes formas de visualizar
 * e processar a imagem do resíduo eletrônico, incluindo PNG, PPM, PGM, PBM e os
 * canais RGB separados.
 * </p>
 *
 * <p>
 * Esta classe facilita a comunicação entre os serviços do sistema, principalmente
 * entre o gerenciador de arquivos e o serviço de geração de XML, que utiliza os
 * caminhos desses arquivos para registrar os metadados do resíduo.
 * </p>
 *
 * <p>
 * O uso desta classe contribui para organização, legibilidade e manutenção do código,
 * evitando o retorno de vários arquivos separados em métodos complexos.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResultadoArquivosResiduo {

    private final File arquivoPNG;
    private final File arquivoPPM;
    private final File arquivoPGM;
    private final File arquivoPBM;

    private final File arquivoCanalVermelho;
    private final File arquivoCanalVerde;
    private final File arquivoCanalAzul;

    public ResultadoArquivosResiduo(
            File arquivoPNG,
            File arquivoPPM,
            File arquivoPGM,
            File arquivoPBM,
            File arquivoCanalVermelho,
            File arquivoCanalVerde,
            File arquivoCanalAzul
    ) {
        this.arquivoPNG = arquivoPNG;
        this.arquivoPPM = arquivoPPM;
        this.arquivoPGM = arquivoPGM;
        this.arquivoPBM = arquivoPBM;
        this.arquivoCanalVermelho = arquivoCanalVermelho;
        this.arquivoCanalVerde = arquivoCanalVerde;
        this.arquivoCanalAzul = arquivoCanalAzul;
    }

    public File getArquivoPNG() {
        return arquivoPNG;
    }

    public File getArquivoPPM() {
        return arquivoPPM;
    }

    public File getArquivoPGM() {
        return arquivoPGM;
    }

    public File getArquivoPBM() {
        return arquivoPBM;
    }

    public File getArquivoCanalVermelho() {
        return arquivoCanalVermelho;
    }

    public File getArquivoCanalVerde() {
        return arquivoCanalVerde;
    }

    public File getArquivoCanalAzul() {
        return arquivoCanalAzul;
    }

    @Override
    public String toString() {
        return "ResultadoArquivosResiduo{" +
                "arquivoPNG=" + arquivoPNG.getAbsolutePath() +
                ", arquivoPPM=" + arquivoPPM.getAbsolutePath() +
                ", arquivoPGM=" + arquivoPGM.getAbsolutePath() +
                ", arquivoPBM=" + arquivoPBM.getAbsolutePath() +
                ", arquivoCanalVermelho=" + arquivoCanalVermelho.getAbsolutePath() +
                ", arquivoCanalVerde=" + arquivoCanalVerde.getAbsolutePath() +
                ", arquivoCanalAzul=" + arquivoCanalAzul.getAbsolutePath() +
                '}';
    }
}