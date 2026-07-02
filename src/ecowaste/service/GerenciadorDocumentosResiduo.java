package ecowaste.service;

import java.io.File;
import java.io.IOException;

import ecowaste.model.ResiduoEletronico;

/**
 * Serviço responsável por coordenar a geração completa dos documentos de um resíduo.
 *
 * <p>
 * Esta classe atua como uma fachada para o processo de geração documental do
 * EcoWaste Framework. A partir de um objeto {@link ecowaste.model.ResiduoEletronico},
 * ela aciona os serviços responsáveis pela geração das imagens, conversões e arquivo
 * XML correspondente.
 * </p>
 *
 * <p>
 * O fluxo executado por esta classe inclui:
 * </p>
 *
 * <ul>
 *     <li>geração da imagem principal em PNG;</li>
 *     <li>conversão da imagem para PPM;</li>
 *     <li>conversão da imagem para PGM;</li>
 *     <li>conversão da imagem para PBM;</li>
 *     <li>separação dos canais RGB em imagens independentes;</li>
 *     <li>geração do XML com os dados do resíduo e os caminhos dos arquivos.</li>
 * </ul>
 *
 * <p>
 * Esta classe fortalece a aplicação do Facade Pattern, pois simplifica o uso de
 * várias operações complexas em uma única chamada. A interface gráfica não precisa
 * conhecer os detalhes dos conversores ou do gerador de XML; ela apenas solicita
 * o processamento do resíduo.
 * </p>
 *
 * <p>
 * O uso desta classe contribui para baixo acoplamento, melhor organização do código
 * e maior clareza na divisão de responsabilidades do sistema.
 * </p>
 *
 * @author Lucas Miles
 */

public class GerenciadorDocumentosResiduo {

    private final GerenciadorArquivosResiduo gerenciadorArquivosResiduo;
    private final ResiduoXmlService residuoXmlService;

    public GerenciadorDocumentosResiduo() {
        this.gerenciadorArquivosResiduo = new GerenciadorArquivosResiduo();
        this.residuoXmlService = new ResiduoXmlService();
    }

    public ResultadoDocumentosResiduo processarResiduo(ResiduoEletronico residuo) throws IOException {
        validarResiduo(residuo);

        ResultadoArquivosResiduo arquivos = gerenciadorArquivosResiduo.gerarArquivos(residuo);

        File arquivoXml = residuoXmlService.gerarXml(residuo, arquivos);

        return new ResultadoDocumentosResiduo(arquivos, arquivoXml);
    }

    private void validarResiduo(ResiduoEletronico residuo) {
        if (residuo == null) {
            throw new IllegalArgumentException("O resíduo eletrônico não pode ser nulo.");
        }

        if (residuo.getNome() == null || residuo.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do resíduo eletrônico é obrigatório.");
        }
    }
}