package br.com.app.domain.utils;

import java.util.List;

/**
 *
 * @param <O> Object
 * @param <I> Identifier
 */

public interface BasicCrudService <O,I> {

    public void save(O toSave);

    public void delete(I id);

    public O findById(I id);

    public List<O> findAll();

}
