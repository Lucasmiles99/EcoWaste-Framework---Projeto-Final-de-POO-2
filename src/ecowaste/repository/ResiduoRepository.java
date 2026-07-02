package ecowaste.repository;

import java.util.ArrayList;
import java.util.List;

import ecowaste.generics.RepositorioGenerico;
import ecowaste.model.ResiduoEletronico;

/**
 * Repositório responsável pelo gerenciamento dos resíduos eletrônicos cadastrados.
 *
 * <p>
 * Esta classe centraliza as operações de persistência em memória do sistema,
 * permitindo salvar, buscar, atualizar, remover e listar objetos do tipo
 * </p>
 *
 * <p>
 * O {@code ResiduoRepository} aplica o conceito de Repository Pattern, isolando a
 * interface gráfica e os demais serviços da forma como os resíduos são armazenados
 * internamente. Dessa forma, a classe {@code TelaPrincipal} não precisa conhecer
 * os detalhes da estrutura de armazenamento utilizada.
 * </p>
 *
 * <p>
 * Embora a implementação atual utilize armazenamento em memória, esta classe pode
 * futuramente ser adaptada para persistência em banco de dados, arquivos ou APIs,
 * sem alterar drasticamente as demais partes do sistema.
 * </p>
 *
 * <p>
 * No fluxo principal do EcoWaste Framework, este repositório é utilizado para
 * controlar os dados exibidos na tabela, no dashboard e nas operações de CRUD da
 * interface gráfica.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoRepository implements RepositorioGenerico<ResiduoEletronico> {

    private List<ResiduoEletronico> residuos = new ArrayList<>();

    @Override
    public void salvar(ResiduoEletronico objeto) {
        residuos.add(objeto);
    }

    @Override
    public void atualizar(ResiduoEletronico objeto) {
        for (int i = 0; i < residuos.size(); i++) {
            if (residuos.get(i).getId().equals(objeto.getId())) {
                residuos.set(i, objeto);
                return;
            }
        }
    }

    @Override
    public void remover(ResiduoEletronico objeto) {
        residuos.removeIf(r -> r.getId().equals(objeto.getId()));
    }

    @Override
    public ResiduoEletronico buscarPorId(Long id) {
        for (ResiduoEletronico r : residuos) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public List<ResiduoEletronico> listarTodos() {
        return residuos;
    }
}