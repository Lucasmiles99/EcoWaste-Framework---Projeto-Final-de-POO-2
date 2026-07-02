package ecowaste.proxy;

import java.util.List;

import ecowaste.model.ResiduoEletronico;
import ecowaste.repository.ResiduoRepository;

/**
 * Proxy Pattern.
 *
 * Controla o acesso ao repositório de resíduos.
 */

public class ResiduoRepositoryProxy {

    private ResiduoRepository repository;
    private UsuarioSistema usuario;

    public ResiduoRepositoryProxy(UsuarioSistema usuario) {
        this.repository = new ResiduoRepository();
        this.usuario = usuario;
    }

    public void salvar(ResiduoEletronico residuo) {
        repository.salvar(residuo);
    }

    public List<ResiduoEletronico> listarTodos() {
        return repository.listarTodos();
    }

    public ResiduoEletronico buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public void remover(ResiduoEletronico residuo) {
        if (!usuario.isAdministrador()) {
            throw new SecurityException("Apenas administradores podem remover resíduos.");
        }

        repository.remover(residuo);
    }
}