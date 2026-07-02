package ecowaste.singleton;

public class ConfiguracaoSistema {

    private static ConfiguracaoSistema instancia;

    private String nomeSistema;
    private String versao;

    private ConfiguracaoSistema() {
        this.nomeSistema = "EcoWaste Framework";
        this.versao = "1.0";
    }

    public static ConfiguracaoSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracaoSistema();
        }

        return instancia;
    }

    public String getNomeSistema() {
        return nomeSistema;
    }

    public String getVersao() {
        return versao;
    }
}