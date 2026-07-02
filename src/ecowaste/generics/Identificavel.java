package ecowaste.generics;

/**
 * Interface genérica para entidades que possuem identificador.
 *
 * @param <T> tipo do identificador, como Integer, Long ou String.
 */

public interface Identificavel<T> {

    T getId();

    void setId(T id);
}