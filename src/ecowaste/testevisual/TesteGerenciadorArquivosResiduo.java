package ecowaste.testevisual;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorArquivosResiduo;
import ecowaste.service.ResultadoArquivosResiduo;

public class TesteGerenciadorArquivosResiduo {

    public static void main(String[] args) {
        try {
            ResiduoEletronico residuo = new ResiduoEletronico();

            residuo.setId(3L);
            residuo.setNome("Bateria de notebook usada");
            residuo.setCategoria("Bateria");
            residuo.setPeso(0.8);
            residuo.setRiscoAmbiental("Alto");
            residuo.setValorEstimado(90.0);
            residuo.setMoeda("BRL");
            residuo.setCidade("Rio do Sul");
            residuo.setEstado("SC");
            residuo.setPais("Brasil");
            residuo.setContinente("América do Sul");
            residuo.setDestino("Ecoponto especializado");
            residuo.setStatus("Separado para descarte");

            GerenciadorArquivosResiduo gerenciador = new GerenciadorArquivosResiduo();

            ResultadoArquivosResiduo resultado = gerenciador.gerarArquivos(residuo);

            System.out.println("Arquivos do resíduo gerados com sucesso!");
            System.out.println("PNG: " + resultado.getArquivoPNG().getAbsolutePath());
            System.out.println("PPM: " + resultado.getArquivoPPM().getAbsolutePath());
            System.out.println("PGM: " + resultado.getArquivoPGM().getAbsolutePath());
            System.out.println("PBM: " + resultado.getArquivoPBM().getAbsolutePath());
            System.out.println("Canal vermelho: " + resultado.getArquivoCanalVermelho().getAbsolutePath());
            System.out.println("Canal verde: " + resultado.getArquivoCanalVerde().getAbsolutePath());
            System.out.println("Canal azul: " + resultado.getArquivoCanalAzul().getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Erro ao gerar arquivos do resíduo.");
            e.printStackTrace();
        }
    }
}