package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> extends AutoCloseable{

    void add(T obj) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    void delete(T obj) throws SQLException;
    void update(T obj) throws SQLException;

}
