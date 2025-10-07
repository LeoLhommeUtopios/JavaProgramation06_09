package dao;

import jdk.jshell.spi.ExecutionControl;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class BaseDAO<T> {
    protected EntityManager _em;

    public BaseDAO(EntityManager em) {
        this._em = em;
    }

    public abstract boolean Add (T element) throws ExecutionControl.NotImplementedException;
    public abstract boolean delete (int id)throws ExecutionControl.NotImplementedException;
    public abstract T findById (int id) throws ExecutionControl.NotImplementedException;
    public abstract List<T> findAll () throws ExecutionControl.NotImplementedException;
}
