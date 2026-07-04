package ecowaste.framework;

import java.util.List;

import ecowaste.framework.extensions.ResiduoExporter;
import ecowaste.framework.extensions.ResiduoProcessor;
import ecowaste.framework.extensions.ResiduoValidator;
import ecowaste.model.ResiduoEletronico;
import ecowaste.repository.ResiduoRepository;
import ecowaste.service.GerenciadorDocumentosResiduo;
import ecowaste.service.ResultadoDocumentosResiduo;
import ecowaste.strategy.DescarteStrategy;

/**
 * Classe principal do EcoWaste Framework.
 *
 * <p>
 * Esta classe representa a API pública da biblioteca. Ela centraliza as
 * operações principais disponíveis para aplicações clientes, como cadastro,
 * busca, atualização, remoção, listagem, geração documental e execução de
 * estratégia de descarte.
 * </p>
 *
 * <p>
 * A partir desta classe, a aplicação Swing passa a ser apenas uma demonstração
 * de uso do framework, e não o centro da regra de negócio.
 * </p>
 *
 * @author Lucas Miles
 */
public class EcoWasteFramework {

    private EcoWasteContext context;

    /**
     * Cria o framework a partir de um contexto configurado.
     *
     * @param context contexto do framework
     */
    public EcoWasteFramework(EcoWasteContext context) {
        if (context == null) {
            throw new IllegalArgumentException("O contexto do framework não pode ser nulo.");
        }

        this.context = context;
    }

    /**
     * Cadastra um resíduo eletrônico no framework.
     *
     * <p>
     * O método executa validações, processadores customizados, salva o resíduo no
     * repositório e, se configurado, gera documentos automaticamente.
     * </p>
     *
     * @param residuo resíduo a ser cadastrado
     * @return resultado da geração documental, ou null se a geração automática
     *         estiver desabilitada
     * @throws Exception caso ocorra erro na validação, processamento ou geração
     */
    public ResultadoDocumentosResiduo cadastrarResiduo(ResiduoEletronico residuo) throws Exception {
        validarResiduo(residuo);
        executarProcessadores(residuo);

        getRepository().salvar(residuo);

        ResultadoDocumentosResiduo resultado = null;

        if (context.isGerarDocumentosAutomaticamente()) {
            resultado = gerarDocumentos(residuo);
        }

        executarExportadores(residuo);

        return resultado;
    }

    /**
     * Busca um resíduo eletrônico pelo ID.
     *
     * @param id ID do resíduo
     * @return resíduo encontrado ou null
     */
    
    public ResiduoEletronico buscarResiduoPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        return getRepository().buscarPorId(id);
    }

    /**
     * Atualiza um resíduo eletrônico.
     *
     * @param residuo resíduo com dados atualizados
     * @throws Exception caso a validação falhe
     */
    public void atualizarResiduo(ResiduoEletronico residuo) throws Exception {
        validarResiduo(residuo);
        executarProcessadores(residuo);

        getRepository().atualizar(residuo);
    }

    /**
     * Remove um resíduo eletrônico pelo ID.
     *
     * @param id ID do resíduo
     */
    
    public void removerResiduo(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        ResiduoEletronico residuo = getRepository().buscarPorId(id);

        if (residuo == null) {
            throw new IllegalArgumentException("Nenhum resíduo encontrado com o ID informado.");
        }

        getRepository().remover(residuo);
    }

    /**
     * Lista todos os resíduos cadastrados.
     *
     * @return lista de resíduos
     */
    public List<ResiduoEletronico> listarResiduos() {
        return getRepository().listarTodos();
    }

    /**
     * Gera os documentos associados a um resíduo eletrônico.
     *
     * @param residuo resíduo a ser processado
     * @return resultado contendo arquivos de imagem e XML
     * @throws Exception caso ocorra erro na geração
     */
    public ResultadoDocumentosResiduo gerarDocumentos(ResiduoEletronico residuo) throws Exception {
        validarResiduo(residuo);
        return getGerenciadorDocumentos().processarResiduo(residuo);
    }

    /**
     * Executa a estratégia de descarte configurada para o framework.
     *
     * @param residuo resíduo eletrônico
     * @return mensagem ou resultado da estratégia
     */
    public String executarDescarte(ResiduoEletronico residuo) {
        if (context.getDescarteStrategy() == null) {
            return "Nenhuma estratégia de descarte configurada.";
        }

        return context.getDescarteStrategy().descartar(residuo);
    }

    /**
     * Adiciona um validador em tempo de execução.
     *
     * @param validador validador de resíduos
     */
    public void adicionarValidador(ResiduoValidator validador) {
        context.adicionarValidador(validador);
    }

    /**
     * Adiciona um processador em tempo de execução.
     *
     * @param processador processador de resíduos
     */
    public void adicionarProcessador(ResiduoProcessor processador) {
        context.adicionarProcessador(processador);
    }

    /**
     * Adiciona um exportador em tempo de execução.
     *
     * @param exportador exportador de resíduos
     */
    public void adicionarExportador(ResiduoExporter exportador) {
        context.adicionarExportador(exportador);
    }

    private void validarResiduo(ResiduoEletronico residuo) {
        for (ResiduoValidator validador : context.getValidadores()) {
            if (!validador.validar(residuo)) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }
    }

    private void executarProcessadores(ResiduoEletronico residuo) {
        for (ResiduoProcessor processador : context.getProcessadores()) {
            processador.processar(residuo);
        }
    }

    private void executarExportadores(ResiduoEletronico residuo) throws Exception {
        for (ResiduoExporter exportador : context.getExportadores()) {
            exportador.exportar(residuo);
        }
    }

    private ResiduoRepository getRepository() {
        return context.getRepository();
    }

    private GerenciadorDocumentosResiduo getGerenciadorDocumentos() {
        return context.getGerenciadorDocumentos();
    }

    public DescarteStrategy getDescarteStrategy() {
        return context.getDescarteStrategy();
    }

    public boolean isGerarDocumentosAutomaticamente() {
        return context.isGerarDocumentosAutomaticamente();
    }
}