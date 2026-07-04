package ecowaste.framework;

import ecowaste.framework.extensions.ResiduoExporter;
import ecowaste.framework.extensions.ResiduoProcessor;
import ecowaste.framework.extensions.ResiduoValidator;
import ecowaste.framework.extensions.ValidadorPadraoResiduo;
import ecowaste.repository.ResiduoRepository;
import ecowaste.service.GerenciadorDocumentosResiduo;
import ecowaste.strategy.DescarteSeguro;
import ecowaste.strategy.DescarteStrategy;

/**
 * Builder responsável por configurar e construir uma instância do
 * EcoWasteFramework.
 *
 * <p>
 * Essa classe fortalece a característica de biblioteca/framework do projeto,
 * pois permite que outro desenvolvedor configure o comportamento do EcoWaste
 * antes de utilizá-lo.
 * </p>
 *
 * Exemplo:
 * <pre>
 * EcoWasteFramework framework = EcoWasteFrameworkBuilder
 *      .novo()
 *      .gerarDocumentosAutomaticamente(true)
 *      .build();
 * </pre>
 *
 * @author Lucas Miles
 */

public class EcoWasteFrameworkBuilder {

    private EcoWasteContext context;

    private EcoWasteFrameworkBuilder() {
        this.context = new EcoWasteContext();

        this.context.setRepository(new ResiduoRepository());
        this.context.setGerenciadorDocumentos(new GerenciadorDocumentosResiduo());
        this.context.setDescarteStrategy(new DescarteSeguro());
        this.context.setGerarDocumentosAutomaticamente(false);
        this.context.adicionarValidador(new ValidadorPadraoResiduo());
    }

    /**
     * Inicia a construção de um novo EcoWasteFramework.
     *
     * @return builder do framework
     */
    public static EcoWasteFrameworkBuilder novo() {
        return new EcoWasteFrameworkBuilder();
    }

    /**
     * Define o repositório utilizado pelo framework.
     *
     * @param repository repositório de resíduos
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder comRepositorio(ResiduoRepository repository) {
        this.context.setRepository(repository);
        return this;
    }

    /**
     * Define o serviço de geração documental.
     *
     * @param gerenciadorDocumentos serviço de documentos
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder comGerenciadorDocumentos(GerenciadorDocumentosResiduo gerenciadorDocumentos) {
        this.context.setGerenciadorDocumentos(gerenciadorDocumentos);
        return this;
    }

    /**
     * Define a estratégia de descarte utilizada pelo framework.
     *
     * @param descarteStrategy estratégia de descarte
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder comDescarteStrategy(DescarteStrategy descarteStrategy) {
        this.context.setDescarteStrategy(descarteStrategy);
        return this;
    }

    /**
     * Define se o framework deve gerar documentos automaticamente após o
     * cadastro de um resíduo.
     *
     * @param valor true para gerar documentos automaticamente
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder gerarDocumentosAutomaticamente(boolean valor) {
        this.context.setGerarDocumentosAutomaticamente(valor);
        return this;
    }

    /**
     * Adiciona um validador customizado ao framework.
     *
     * @param validador validador de resíduos
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder adicionarValidador(ResiduoValidator validador) {
        this.context.adicionarValidador(validador);
        return this;
    }

    /**
     * Adiciona um processador customizado ao framework.
     *
     * @param processador processador de resíduos
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder adicionarProcessador(ResiduoProcessor processador) {
        this.context.adicionarProcessador(processador);
        return this;
    }

    /**
     * Adiciona um exportador customizado ao framework.
     *
     * @param exportador exportador de resíduos
     * @return builder atual
     */
    public EcoWasteFrameworkBuilder adicionarExportador(ResiduoExporter exportador) {
        this.context.adicionarExportador(exportador);
        return this;
    }

    /**
     * Constrói a instância final do EcoWasteFramework.
     *
     * @return instância configurada do framework
     */
    public EcoWasteFramework build() {
        return new EcoWasteFramework(context);
    }
}