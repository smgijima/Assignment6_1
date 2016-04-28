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
import cput.ac.za.assignment_6_1.domain.BusDetails;
import cput.ac.za.assignment_6_1.repository.BusClassRepository;
import cput.ac.za.assignment_6_1.repository.BusDetailsRepository;

/**
 * Created by mgijma on 2016/04/24.
 */
public class BusDetailsRepositoryImp extends SQLiteOpenHelper implements BusDetailsRepository {


    public static final String TABLE_NAME = "bus_details";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BUS_NO = "bus_no";
    public static final String COLUMN_BUS_TYPE = "bus_type";
    public static final String COLUMN_NO_OF_SEATS = "no_of_seats";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BUS_NO + " TEXT UNIQUE NOT NULL , "
            + COLUMN_BUS_TYPE + " TEXT NOT NULL , "
            + COLUMN_NO_OF_SEATS + " TEXT  NOT NULL );";

    public BusDetailsRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
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


    @Override
    public BusDetails findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_BUS_NO,
                        COLUMN_BUS_TYPE,
                        COLUMN_NO_OF_SEATS,},
                COLUMN_BUS_NO + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BusDetails details = new BusDetails.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .Busno(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_NO)))
                    .Bustype(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_TYPE)))
                    .Noofseats(cursor.getString(cursor.getColumnIndex(COLUMN_NO_OF_SEATS)))
                    .build();
            return details;
        } else {
            return null;
        }
    }


    @Override
    public BusDetails save(BusDetails entity) {


        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BUS_NO, entity.getBusNo());
        values.put(COLUMN_BUS_TYPE, entity.getBusType());
        values.put(COLUMN_NO_OF_SEATS,entity.getNoOfSeats());
        long id = db.insertOrThrow(TABLE_NAME, null, values);

        BusDetails insertedEntity = new BusDetails.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public BusDetails update(BusDetails entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BUS_NO, entity.getBusNo());
        values.put(COLUMN_BUS_TYPE, entity.getBusType());
        values.put(COLUMN_NO_OF_SEATS, entity.getNoOfSeats());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;

    }

    @Override
    public BusDetails delete(BusDetails entity) {

        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BusDetails> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<BusDetails> details = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BusDetails setting = new BusDetails.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .Busno(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_NO)))
                        .Bustype(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_TYPE)))
                        .Noofseats(cursor.getString(cursor.getColumnIndex(COLUMN_NO_OF_SEATS)))
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
}
