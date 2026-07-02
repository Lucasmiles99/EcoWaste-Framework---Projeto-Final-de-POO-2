package ecowaste.testevisual;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorDocumentosResiduo;
import ecowaste.service.ResultadoDocumentosResiduo;

public class TesteGerenciadorDocumentosResiduo {

    public static void main(String[] args) {
        try {
            ResiduoEletronico residuo = new ResiduoEletronico();

            residuo.setId(5L);
            residuo.setNome("Celular antigo quebrado");
            residuo.setCategoria("Celular");
            residuo.setPeso(0.3);
            residuo.setRiscoAmbiental("Alto");
            residuo.setValorEstimado(120.0);
            residuo.setMoeda("BRL");
            residuo.setCidade("Rio do Sul");
            residuo.setEstado("SC");
            residuo.setPais("Brasil");
            residuo.setContinente("América do Sul");
            residuo.setDestino("Ponto de coleta de eletrônicos");
            residuo.setStatus("Aguardando descarte");

            GerenciadorDocumentosResiduo gerenciador = new GerenciadorDocumentosResiduo();

            ResultadoDocumentosResiduo resultado = gerenciador.processarResiduo(residuo);

            System.out.println("Documentos do resíduo gerados com sucesso!");
            System.out.println("PNG: " + resultado.getArquivosResiduo().getArquivoPNG().getAbsolutePath());
            System.out.println("PPM: " + resultado.getArquivosResiduo().getArquivoPPM().getAbsolutePath());
            System.out.println("PGM: " + resultado.getArquivosResiduo().getArquivoPGM().getAbsolutePath());
            System.out.println("PBM: " + resultado.getArquivosResiduo().getArquivoPBM().getAbsolutePath());
            System.out.println("Canal vermelho: " + resultado.getArquivosResiduo().getArquivoCanalVermelho().getAbsolutePath());
            System.out.println("Canal verde: " + resultado.getArquivosResiduo().getArquivoCanalVerde().getAbsolutePath());
            System.out.println("Canal azul: " + resultado.getArquivosResiduo().getArquivoCanalAzul().getAbsolutePath());
            System.out.println("XML: " + resultado.getArquivoXml().getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erro ao gerar documentos do resíduo.");
            e.printStackTrace();
        }
    }
}