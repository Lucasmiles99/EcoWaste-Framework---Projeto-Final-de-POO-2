package ecowaste.framework.demo;

import ecowaste.framework.EcoWasteFramework;
import ecowaste.framework.EcoWasteFrameworkBuilder;
import ecowaste.model.ResiduoEletronico;
import ecowaste.service.ResultadoDocumentosResiduo;
import ecowaste.strategy.DescarteSeguro;

/**
 * Classe de demonstração do EcoWaste Framework sem uso da interface gráfica.
 *
 * <p>
 * O objetivo desta classe é provar que o EcoWaste pode ser utilizado como
 * biblioteca/framework por outra aplicação Java, sem depender da TelaPrincipal.
 * </p>
 *
 * @author Lucas Miles
 */

public class DemoFrameworkConsole {

    public static void main(String[] args) {
        try {
            EcoWasteFramework framework = EcoWasteFrameworkBuilder
                    .novo()
                    .comDescarteStrategy(new DescarteSeguro())
                    .gerarDocumentosAutomaticamente(true)
                    .build();

            ResiduoEletronico residuo = new ResiduoEletronico(
                    1000L,
                    "Bateria de Notebook",
                    "Bateria",
                    0.8,
                    "Alto",
                    80.0,
                    "BRL",
                    "Rio do Sul",
                    "SC",
                    "Brasil",
                    "América do Sul",
                    "Ecoponto Municipal",
                    "Em Análise"
            );

            ResultadoDocumentosResiduo resultado = framework.cadastrarResiduo(residuo);

            System.out.println("EcoWaste Framework executado com sucesso.");
            System.out.println("Resíduo cadastrado: " + residuo.getNome());
            System.out.println("Total de resíduos: " + framework.listarResiduos().size());
            System.out.println("Estratégia de descarte: " + framework.executarDescarte(residuo));

            if (resultado != null) {
                System.out.println("XML gerado: " + resultado.getArquivoXml().getPath());
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar o EcoWaste Framework:");
            System.err.println(e.getMessage());
        }
    }
}