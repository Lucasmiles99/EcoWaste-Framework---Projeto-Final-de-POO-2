package ecowaste.proxy;

/**
 * Representa um usuário que terá acesso ao sistema.
 */

public class UsuarioSistema {

    private String nome;
    private String perfil;

    public UsuarioSistema(String nome, String perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public boolean isAdministrador() {
        return "ADMIN".equalsIgnoreCase(perfil);
    }
}