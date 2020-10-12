package ps.jawwal.dbexample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {
    private final DatabaseHelper databaseHelper;

    public UserDAO(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<User> loadAllByIds(String[] userIds) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_USERS +" WHERE "+DatabaseHelper.KEY_ID+" = ?",null);
        List<User> users=getObjectFromCursor(c);
        db.close();
        return users;
    }

    @Override
    public List<User> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_USERS,null);
        List<User> users=getObjectFromCursor(c);
        db.close();
        return users;
    }



    @Override
    public void insert(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put(DatabaseHelper.KEY_FNAME, user.getFName());
            c.put(DatabaseHelper.KEY_LNAME, user.getLName());
            c.put(DatabaseHelper.KEY_ID, user.getId());
            c.put(DatabaseHelper.KEY_ADDRESS, user.getAddress());
            db.insert(DatabaseHelper.TABLE_USERS, null, c);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    @Override
    public void delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_USERS,DatabaseHelper.KEY_ID+" = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void update(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(DatabaseHelper.KEY_FNAME,user.getFName());
        c.put(DatabaseHelper.KEY_LNAME,user.getLName());
        c.put(DatabaseHelper.KEY_ID,user.getId());
        c.put(DatabaseHelper.KEY_ADDRESS,user.getAddress());
        db.update(DatabaseHelper.TABLE_USERS,c,DatabaseHelper.KEY_ID+" = ?",new String[]{String.valueOf(user.getId())});
        db.close();
    }

    @Override
    List<User> getObjectFromCursor(Cursor c) {
        ArrayList<User> user = new ArrayList<User>();
        while (c.moveToNext()){
            user.add(new User(c.getInt(c.getColumnIndex(DatabaseHelper.KEY_ID)),
                    c.getString(c.getColumnIndex(DatabaseHelper.KEY_FNAME)),
                    c.getString(c.getColumnIndex(DatabaseHelper.KEY_LNAME)),
                    c.getString(c.getColumnIndex(DatabaseHelper.KEY_ADDRESS))));
        }
        return user;
    }
}
