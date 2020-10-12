package ps.jawwal.dbexample.db;

import android.database.Cursor;

import java.util.List;

public abstract class DAO<T> {
    public abstract List<T> loadAllByIds(String[] userIds);
    public abstract List<T> getAll();
    public abstract void insert(T t);
    public abstract void delete(int id);
    public abstract void update(T t);
    abstract List<T> getObjectFromCursor(Cursor c) ;

}
