package ecowaste.testevisual;

import java.io.File;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorArquivosResiduo;
import ecowaste.service.ResiduoXmlService;
import ecowaste.service.ResultadoArquivosResiduo;

public class TesteResiduoXmlService {

    public static void main(String[] args) {
        try {
            ResiduoEletronico residuo = new ResiduoEletronico();

            residuo.setId(4L);
            residuo.setNome("Impressora antiga");
            residuo.setCategoria("Impressora");
            residuo.setPeso(6.2);
            residuo.setRiscoAmbiental("Médio");
            residuo.setValorEstimado(220.0);
            residuo.setMoeda("BRL");
            residuo.setCidade("Rio do Sul");
            residuo.setEstado("SC");
            residuo.setPais("Brasil");
            residuo.setContinente("América do Sul");
            residuo.setDestino("Centro de reciclagem");
            residuo.setStatus("Cadastrado");

            GerenciadorArquivosResiduo gerenciador = new GerenciadorArquivosResiduo();

            ResultadoArquivosResiduo arquivos = gerenciador.gerarArquivos(residuo);

            ResiduoXmlService xmlService = new ResiduoXmlService();

            File arquivoXml = xmlService.gerarXml(residuo, arquivos);

            System.out.println("XML gerado com sucesso!");
            System.out.println("Arquivo XML: " + arquivoXml.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erro ao gerar XML do resíduo.");
            e.printStackTrace();
        }
    }
}