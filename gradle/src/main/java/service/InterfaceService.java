package service;

import java.util.List;

public interface InterfaceService<E> {
    List<E> findAll();
    E findById(int id);
    boolean add(E e);
    boolean update(int id, E e);
    boolean remove(int id);
}
