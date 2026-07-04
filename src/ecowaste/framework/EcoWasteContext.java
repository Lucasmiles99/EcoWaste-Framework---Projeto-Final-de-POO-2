package ecowaste.framework;

import java.util.ArrayList;
import java.util.List;

import ecowaste.framework.extensions.ResiduoExporter;
import ecowaste.framework.extensions.ResiduoProcessor;
import ecowaste.framework.extensions.ResiduoValidator;
import ecowaste.repository.ResiduoRepository;
import ecowaste.service.GerenciadorDocumentosResiduo;
import ecowaste.strategy.DescarteStrategy;

/**
 * Classe de contexto do EcoWaste Framework.
 *
 * <p>
 * O contexto armazena as configurações utilizadas pelo núcleo do framework,
 * como repositório, serviços, estratégia de descarte, validadores,
 * processadores, exportadores e opção de geração automática de documentos.
 * </p>
 *
 * @author Lucas Miles
 */

public class EcoWasteContext {

    private ResiduoRepository repository;
    private GerenciadorDocumentosResiduo gerenciadorDocumentos;
    private DescarteStrategy descarteStrategy;
    private boolean gerarDocumentosAutomaticamente;

    private List<ResiduoValidator> validadores;
    private List<ResiduoProcessor> processadores;
    private List<ResiduoExporter> exportadores;

    /**
     * Cria um contexto vazio do framework.
     */
    public EcoWasteContext() {
        this.validadores = new ArrayList<>();
        this.processadores = new ArrayList<>();
        this.exportadores = new ArrayList<>();
    }

    public ResiduoRepository getRepository() {
        return repository;
    }

    public void setRepository(ResiduoRepository repository) {
        this.repository = repository;
    }

    public GerenciadorDocumentosResiduo getGerenciadorDocumentos() {
        return gerenciadorDocumentos;
    }

    public void setGerenciadorDocumentos(GerenciadorDocumentosResiduo gerenciadorDocumentos) {
        this.gerenciadorDocumentos = gerenciadorDocumentos;
    }

    public DescarteStrategy getDescarteStrategy() {
        return descarteStrategy;
    }

    public void setDescarteStrategy(DescarteStrategy descarteStrategy) {
        this.descarteStrategy = descarteStrategy;
    }

    public boolean isGerarDocumentosAutomaticamente() {
        return gerarDocumentosAutomaticamente;
    }

    public void setGerarDocumentosAutomaticamente(boolean gerarDocumentosAutomaticamente) {
        this.gerarDocumentosAutomaticamente = gerarDocumentosAutomaticamente;
    }

    public List<ResiduoValidator> getValidadores() {
        return validadores;
    }

    public List<ResiduoProcessor> getProcessadores() {
        return processadores;
    }

    public List<ResiduoExporter> getExportadores() {
        return exportadores;
    }

    public void adicionarValidador(ResiduoValidator validador) {
        this.validadores.add(validador);
    }

    public void adicionarProcessador(ResiduoProcessor processador) {
        this.processadores.add(processador);
    }

    public void adicionarExportador(ResiduoExporter exportador) {
        this.exportadores.add(exportador);
    }
}