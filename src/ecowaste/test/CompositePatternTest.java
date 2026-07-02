package ecowaste.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecowaste.composite.LoteResiduos;
import ecowaste.composite.ResiduoItem;
import ecowaste.model.ResiduoEletronico;

/**
 * Classe de testes unitários responsável por validar a aplicação de um Design Pattern
 * no EcoWaste Framework.
 *
 * <p>
 * Os testes verificam se a estrutura implementada funciona corretamente e se o padrão
 * contribui para organização, reutilização e manutenção do sistema.
 * </p>
 *
 * @author Lucas Miles
 */

public class CompositePatternTest {

    @Test
    public void deveCalcularPesoTotalDoLote() {
    	ResiduoEletronico notebook = new ResiduoEletronico(
    	        1L, "Notebook antigo", "Informática", 2.5, "Médio",
    	        800.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
    	        "Reuso educacional", "CADASTRADO"
    	);
    	
    	ResiduoEletronico bateria = new ResiduoEletronico(
    	        2L, "Bateria", "Bateria", 0.5, "Alto",
    	        45.0, "BRL", "Blumenau", "SC", "Brasil", "América do Sul",
    	        "Descarte seguro", "RISCO_CRITICO"
    	);

        LoteResiduos lote = new LoteResiduos("Lote 01");
        lote.adicionar(new ResiduoItem(notebook));
        lote.adicionar(new ResiduoItem(bateria));

        assertEquals(3.0, lote.getPesoTotal());
    }

    @Test
    public void deveAdicionarItensNoLote() {
    	ResiduoEletronico celular = new ResiduoEletronico(
    	        3L, "Celular", "Telefonia", 0.3, "Médio",
    	        250.0, "BRL", "Rio do Sul", "SC", "Brasil", "América do Sul",
    	        "Assistência técnica", "AGUARDANDO_TRIAGEM"
    	);

        LoteResiduos lote = new LoteResiduos("Lote 02");
        lote.adicionar(new ResiduoItem(celular));

        assertEquals(1, lote.totalItens());
    }
}