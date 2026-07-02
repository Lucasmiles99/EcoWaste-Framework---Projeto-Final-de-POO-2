package ecowaste.generics;

import java.util.List;

/**
 * Interface genérica para operações básicas de CRUD em memória.
 *
 * @param <T> tipo da entidade manipulada pelo repositório.
 */

public interface RepositorioGenerico<T> {

    void salvar(T objeto);

    void atualizar(T objeto);

    void remover(T objeto);

    T buscarPorId(Long id);

    List<T> listarTodos();
}