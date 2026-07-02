package ecowaste.facade;

import java.util.List;

import ecowaste.model.ResiduoEletronico;
import ecowaste.repository.ResiduoRepository;
import ecowaste.service.GerenciadorDescarte;
import ecowaste.strategy.DescarteStrategy;

/**
 * Fachada responsável por simplificar o acesso às funcionalidades principais do EcoWaste Framework.
 *
 * <p>
 * Esta classe aplica o Facade Pattern, oferecendo uma interface simplificada para
 * operações que podem envolver diferentes classes internas do sistema.
 * </p>
 *
 * <p>
 * O objetivo da fachada é reduzir o acoplamento entre o código cliente e os detalhes
 * internos do framework. Dessa forma, quem utiliza o sistema pode executar operações
 * principais sem precisar conhecer todas as classes envolvidas.
 * </p>
 *
 * <p>
 * No contexto do EcoWaste Framework, a fachada representa uma camada de acesso mais
 * simples para funcionalidades relacionadas ao gerenciamento de resíduos eletrônicos,
 * descarte, relatórios ou outros serviços do sistema.
 * </p>
 *
 * <p>
 * O uso deste padrão melhora a organização arquitetural do projeto e facilita sua
 * apresentação como framework reutilizável.
 * </p>
 *
 * @author Lucas Miles
 */

public class EcoWasteFacade {

    private ResiduoRepository residuoRepository;

    public EcoWasteFacade() {
        this.residuoRepository = new ResiduoRepository();
    }

    public void cadastrarResiduo(ResiduoEletronico residuo) {
        residuoRepository.salvar(residuo);
    }

    public void atualizarResiduo(ResiduoEletronico residuo) {
        residuoRepository.atualizar(residuo);
    }

    public void removerResiduo(ResiduoEletronico residuo) {
        residuoRepository.remover(residuo);
    }

    public ResiduoEletronico buscarResiduoPorId(Long id) {
        return residuoRepository.buscarPorId(id);
    }

    public List<ResiduoEletronico> listarResiduos() {
        return residuoRepository.listarTodos();
    }

    public String executarDescarte(ResiduoEletronico residuo, DescarteStrategy estrategia) {
        GerenciadorDescarte gerenciador = new GerenciadorDescarte(estrategia);
        return gerenciador.executarDescarte(residuo);
    }
}
