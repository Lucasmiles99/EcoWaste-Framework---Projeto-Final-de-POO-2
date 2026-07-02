package ecowaste.factory;

import ecowaste.model.ResiduoEletronico;

/**
 * Fábrica responsável pela criação padronizada de resíduos eletrônicos.
 *
 * <p>
 * Esta classe aplica o Factory Pattern, centralizando a criação de objetos do tipo
 * {@link ecowaste.model.ResiduoEletronico}. Ela permite criar resíduos com
 * características pré-definidas, evitando repetição de código em diferentes partes
 * do sistema.
 * </p>
 *
 * <p>
 * O padrão Factory contribui para maior organização e flexibilidade, pois novas
 * categorias de resíduos podem ser adicionadas futuramente com regras próprias de
 * criação, sem espalhar essa lógica pela interface gráfica ou pelos serviços.
 * </p>
 *
 * <p>
 * No EcoWaste Framework, essa abordagem é útil para representar diferentes tipos
 * de resíduos eletrônicos, como baterias, celulares, monitores, computadores e
 * impressoras.
 * </p>
 *
 * @author Lucas Miles
 */

public class ResiduoFactory {

    public static ResiduoEletronico criarResiduo(TipoResiduo tipo, Long id) {

        switch (tipo) {

            case NOTEBOOK:
                return new ResiduoEletronico(
                        id,
                        "Notebook antigo",
                        "Informática",
                        2.5,
                        "Médio",
                        800.0,
                        "BRL",
                        "Rio do Sul",
                        "SC",
                        "Brasil",
                        "América do Sul",
                        "Reuso educacional",
                        "CADASTRADO"
                );

            case CELULAR:
                return new ResiduoEletronico(
                        id,
                        "Smartphone com tela quebrada",
                        "Telefonia",
                        0.3,
                        "Médio",
                        250.0,
                        "BRL",
                        "Rio do Sul",
                        "SC",
                        "Brasil",
                        "América do Sul",
                        "Assistência técnica parceira",
                        "AGUARDANDO_TRIAGEM"
                );

            case BATERIA:
                return new ResiduoEletronico(
                        id,
                        "Bateria de notebook estufada",
                        "Bateria",
                        0.4,
                        "Alto",
                        45.0,
                        "BRL",
                        "Blumenau",
                        "SC",
                        "Brasil",
                        "América do Sul",
                        "Descarte seguro certificado",
                        "RISCO_CRITICO"
                );

            case MONITOR:
                return new ResiduoEletronico(
                        id,
                        "Monitor LCD antigo",
                        "Imagem",
                        4.0,
                        "Médio",
                        180.0,
                        "BRL",
                        "Itajaí",
                        "SC",
                        "Brasil",
                        "América do Sul",
                        "Centro de reciclagem eletrônico",
                        "EM_ANALISE"
                );

            case IMPRESSORA:
                return new ResiduoEletronico(
                        id,
                        "Impressora multifuncional obsoleta",
                        "Periférico",
                        6.0,
                        "Alto",
                        120.0,
                        "BRL",
                        "Florianópolis",
                        "SC",
                        "Brasil",
                        "América do Sul",
                        "Ecoponto municipal",
                        "AGUARDANDO_DESCARTE"
                );

            default:
                throw new IllegalArgumentException("Tipo de resíduo inválido.");
        }
    }
}