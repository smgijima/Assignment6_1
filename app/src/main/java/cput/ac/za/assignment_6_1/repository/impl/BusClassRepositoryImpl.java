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
import cput.ac.za.assignment_6_1.domain.BusClass;
import cput.ac.za.assignment_6_1.repository.BusClassRepository;


/*
    private Long tickets;
    private String type;
    private String one_or_return;
 */

/**
 * Created by mgijma on 2016/04/24.
 */
public class BusClassRepositoryImpl extends SQLiteOpenHelper implements BusClassRepository {


    public static final String TABLE_NAME = "bus_location";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TICKETS = "tickets";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_ONE_OR_RETURN = "one_or_return";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TICKETS + " TEXT NOT NULL , "
            + COLUMN_TYPE + " TEXT NOT NULL , "
            + COLUMN_ONE_OR_RETURN + " TEXT NOT NULL );";


    public BusClassRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }



    @Override
    public BusClass findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TICKETS,
                        COLUMN_TYPE,
                        COLUMN_ONE_OR_RETURN,},
                        COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BusClass bus = new BusClass.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .ticket(cursor.getString(cursor.getColumnIndex(COLUMN_TICKETS)))
                    .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                    .oneWayOrReturn(cursor.getString(cursor.getColumnIndex(COLUMN_ONE_OR_RETURN)))
                    .build();
            return bus;
        } else {
            return null;
        }
    }

    @Override
    public BusClass save(BusClass entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TICKETS, entity.getTickets());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_ONE_OR_RETURN,entity.getOne_or_return());
        long id = db.insertOrThrow(TABLE_NAME, null, values);

        BusClass insertedEntity = new BusClass.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public BusClass update(BusClass entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TICKETS, entity.getTickets());
        values.put(COLUMN_TYPE, entity.getType());
        values.put(COLUMN_ONE_OR_RETURN, entity.getOne_or_return());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public BusClass delete(BusClass entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BusClass> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<BusClass> bus = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BusClass setting = new BusClass.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .ticket(cursor.getString(cursor.getColumnIndex(COLUMN_TICKETS)))
                        .type(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)))
                        .oneWayOrReturn(cursor.getString(cursor.getColumnIndex(COLUMN_ONE_OR_RETURN)))
                        .build();
                bus.add(setting);
            } while (cursor.moveToNext());
        }
        return bus;

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
