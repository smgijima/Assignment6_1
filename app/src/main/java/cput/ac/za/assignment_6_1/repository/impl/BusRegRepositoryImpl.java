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
import cput.ac.za.assignment_6_1.domain.BusRegistration;
import cput.ac.za.assignment_6_1.repository.BusRegRepository;

/**
 * Created by mgijma on 2016/04/28.
 */
public class BusRegRepositoryImpl extends SQLiteOpenHelper implements BusRegRepository {


    public static final String TABLE_NAME = "Registration";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_REG = "reg";
    public static final String COLUMN_DATE = "date";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_REG + " TEXT NOT NULL , "
            + COLUMN_DATE + " TEXT NOT NULL);";

    public BusRegRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public BusRegistration findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_REG,
                        COLUMN_DATE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BusRegistration details = new BusRegistration.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .busRegNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REG)))
                    .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();
            return details;
        } else {
            return null;
        }

    }

    @Override
    public BusRegistration save(BusRegistration entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_REG, entity.getBusRegNumber());
        values.put(COLUMN_DATE, entity.getDate());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        BusRegistration insertedEntity = new BusRegistration.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public BusRegistration update(BusRegistration entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_REG, entity.getBusRegNumber());
        values.put(COLUMN_DATE, entity.getDate());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;

    }

    @Override
    public BusRegistration delete(BusRegistration entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BusRegistration> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<BusRegistration> details = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BusRegistration setting = new BusRegistration.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .busRegNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REG)))
                        .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
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
