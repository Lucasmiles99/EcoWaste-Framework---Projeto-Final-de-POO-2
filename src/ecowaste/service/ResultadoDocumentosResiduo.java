package ecowaste.service;

import java.io.File;

/**
 * Representa o resultado final do processamento documental de um resíduo eletrônico.
 *
 * <p>
 * Esta classe armazena as referências para os arquivos gerados durante o processo
 * completo de documentação de um resíduo. Ela agrupa tanto os arquivos de imagem
 * quanto o arquivo XML produzido pelo sistema.
 * </p>
 *
 * <p>
 * O objeto {@code ResultadoDocumentosResiduo} é retornado pelo
 * {@link ecowaste.service.GerenciadorDocumentosResiduo} após o processamento de
 * um {@link ecowaste.model.ResiduoEletronico}.
 * </p>
 *
 * <p>
 * A classe permite que outras partes do sistema, como a interface gráfica ou os
 * testes automatizados, acessem de forma organizada o resultado do processamento,
 * incluindo o XML final e os arquivos derivados da imagem do resíduo.
 * </p>
 *
 * <p>
 * Esta classe é importante para manter a clareza do código, evitando o retorno de
 * múltiplos objetos soltos e facilitando a manutenção futura do framework.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResultadoDocumentosResiduo {

    private final ResultadoArquivosResiduo arquivosResiduo;
    private final File arquivoXml;

    public ResultadoDocumentosResiduo(
            ResultadoArquivosResiduo arquivosResiduo,
            File arquivoXml
    ) {
        this.arquivosResiduo = arquivosResiduo;
        this.arquivoXml = arquivoXml;
    }

    public ResultadoArquivosResiduo getArquivosResiduo() {
        return arquivosResiduo;
    }

    public File getArquivoXml() {
        return arquivoXml;
    }

    @Override
    public String toString() {
        return "ResultadoDocumentosResiduo{" +
                "arquivosResiduo=" + arquivosResiduo +
                ", arquivoXml=" + arquivoXml.getAbsolutePath() +
                '}';
    }
}