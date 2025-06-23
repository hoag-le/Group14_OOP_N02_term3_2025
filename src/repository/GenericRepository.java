package repository;

import java.util.*;
import java.util.function.Function;

public class GenericRepository<T> implements CrudRepository<T> {
    private Map<Integer, T> storage = new HashMap<>();
    private Function<T, Integer> idGetter;

    public GenericRepository(Function<T, Integer> idGetter) {
        this.idGetter = idGetter;
    }

    @Override
    public void create(T obj) {
        try {
            storage.put(idGetter.apply(obj), obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public T read(int id) {
        try {
            return storage.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

    @Override
    public void update(T obj) {
        try {
            storage.put(idGetter.apply(obj), obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public void delete(int id) {
        try {
            storage.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public List<T> listAll() {
        try {
            return new ArrayList<>(storage.values());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
        }
    }
}