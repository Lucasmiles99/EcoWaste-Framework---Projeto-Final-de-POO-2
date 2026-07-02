package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.model.ResiduoEletronico;
import ecowaste.service.GerenciadorDescarte;
import ecowaste.strategy.DescarteReciclagem;
import ecowaste.strategy.DescarteReuso;
import ecowaste.strategy.DescarteSeguro;

/**
 * Classe de testes unitários para o {@link ecowaste.service.GerenciadorDescarte}.
 *
 * <p>
 * Esta classe valida o comportamento do gerenciador de descarte do EcoWaste Framework,
 * verificando se diferentes estratégias de descarte podem ser aplicadas corretamente
 * sobre objetos do tipo {@link ecowaste.model.ResiduoEletronico}.
 * </p>
 *
 * <p>
 * Os testes demonstram a aplicação do Strategy Pattern no sistema, permitindo que
 * o descarte de um resíduo eletrônico varie conforme a estratégia utilizada, como
 * reciclagem, reuso ou descarte seguro.
 * </p>
 *
 * <p>
 * Essa validação é importante para garantir que o framework consiga tratar diferentes
 * formas de destinação sustentável para resíduos eletrônicos, mantendo o código
 * flexível, extensível e alinhado aos princípios da Programação Orientada a Objetos.
 * </p>
 *
 * @author Lucas Miles
 */

public class GerenciadorDescarteTest {

    @Test
    public void deveExecutarDescartePorReciclagem() {
    	ResiduoEletronico residuo = new ResiduoEletronico(
    	        1L, "Notebook antigo", "Informática", 2.5, "Médio",
    	        800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
    	        "Reuso educacional", "CADASTRADO"
    	);

        GerenciadorDescarte gerenciador = new GerenciadorDescarte(new DescarteReciclagem());

        String resultado = gerenciador.executarDescarte(residuo);

        assertTrue(resultado.contains("reciclagem"));
    }

    @Test
    public void deveExecutarDescarteSeguro() {
    	ResiduoEletronico residuo = new ResiduoEletronico(
    	        2L, "Bateria danificada", "Bateria", 0.4, "Alto",
    	        45.0, "BRL", "Blumenau", "SC", "Brasil", "América do Sul",
    	        "Descarte seguro certificado", "RISCO_CRITICO"
    	);

        GerenciadorDescarte gerenciador = new GerenciadorDescarte(new DescarteSeguro());

        String resultado = gerenciador.executarDescarte(residuo);

        assertTrue(resultado.contains("descarte seguro"));
    }

    @Test
    public void deveExecutarDescartePorReuso() {
    	ResiduoEletronico residuo = new ResiduoEletronico(
    	        3L, "Monitor usado", "Imagem", 3.0, "Baixo",
    	        150.0, "BRL", "Itajaí", "SC", "Brasil", "América do Sul",
    	        "Centro de reciclagem", "EM_ANALISE"
    	);

        GerenciadorDescarte gerenciador = new GerenciadorDescarte(new DescarteReuso());

        String resultado = gerenciador.executarDescarte(residuo);

        assertTrue(resultado.contains("reuso"));
    }
}