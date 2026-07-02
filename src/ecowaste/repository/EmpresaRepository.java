package ecowaste.repository;

import java.util.ArrayList;
import java.util.List;

import ecowaste.generics.RepositorioGenerico;
import ecowaste.model.Empresa;

/**
 * Repositório em memória para armazenar diferentes empresas.
 */

public class EmpresaRepository implements RepositorioGenerico<Empresa> {

    private List<Empresa> empresas = new ArrayList<>();

    @Override
    public void salvar(Empresa objeto) {
        empresas.add(objeto);
    }

    @Override
    public void atualizar(Empresa objeto) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getId().equals(objeto.getId())) {
                empresas.set(i, objeto);
                return;
            }
        }
    }

    @Override
    public void remover(Empresa objeto) {
        empresas.removeIf(e -> e.getId().equals(objeto.getId()));
    }

    @Override
    public Empresa buscarPorId(Long id) {
        for (Empresa e : empresas) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public List<Empresa> listarTodos() {
        return empresas;
    }
}