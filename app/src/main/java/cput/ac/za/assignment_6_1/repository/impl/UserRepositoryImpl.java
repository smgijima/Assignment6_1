package cput.ac.za.assignment_6_1.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

import cput.ac.za.assignment_6_1.config.database.DBConstants;
import cput.ac.za.assignment_6_1.domain.User;
import cput.ac.za.assignment_6_1.repository.UserRepository;

/**
 * Created by mgijma on 2016/04/28.
 */
public class UserRepositoryImpl extends SQLiteOpenHelper implements UserRepository {

    public static final String TABLE_NAME = "user_details";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USER_TYPE = "user_type";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_ID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_PASSWORD + " TEXT UNIQUE NOT NULL , "
            + COLUMN_USER_TYPE + " TEXT NOT NULL );";


    public UserRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public User findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USER_ID,
                        COLUMN_PASSWORD,
                        COLUMN_USER_TYPE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User details = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Userid(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .Password(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)))
                    .UserType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)))
                    .build();
            return details;
        } else {
            return null;
        }
    }

    @Override
    public User save(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USER_ID, entity.getUserID());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_USER_TYPE, entity.getUserType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);

        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public User update(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USER_ID, entity.getUserID());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_USER_TYPE, entity.getUserType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }


    @Override
    public User delete(User entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;

    }

    @Override
    public Set<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<User> details = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final User setting = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Userid(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)))
                        .Password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .UserType(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)))
                        .build();
                details.add(setting);
            } while (cursor.moveToNext());
        }
        return details;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
