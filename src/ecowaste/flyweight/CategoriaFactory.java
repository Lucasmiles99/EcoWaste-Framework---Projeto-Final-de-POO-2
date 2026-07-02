package ecowaste.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory responsável por reutilizar categorias já criadas.
 *
 * Aqui aplicamos o Flyweight Pattern.
 */
public class CategoriaFactory {

    private static Map<String, CategoriaResiduo> categorias = new HashMap<>();

    public static CategoriaResiduo obterCategoria(String nome, String descricao) {
        if (!categorias.containsKey(nome)) {
            categorias.put(nome, new CategoriaResiduo(nome, descricao));
        }

        return categorias.get(nome);
    }

    public static int totalCategoriasCriadas() {
        return categorias.size();
    }

    public static void limparCategorias() {
        categorias.clear();
    }
}