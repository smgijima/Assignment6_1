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
import cput.ac.za.assignment_6_1.domain.BusStation;
import cput.ac.za.assignment_6_1.repository.BusStationRepository;

/**
 * Created by mgijma on 2016/04/28.
 */
public class BusStationRepositoryImpl extends SQLiteOpenHelper implements BusStationRepository {


    public static final String TABLE_NAME = "Station";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATION_CODE = "station_code";
    public static final String COLUMN_NAME = "station_name";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_CITY_CODE = "city_code";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STATION_CODE + " TEXT UNIQUE NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_CITY + " TEXT NOT NULL , "
            + COLUMN_CITY_CODE + " TEXT NOT NULL );";

    public BusStationRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }



    @Override
    public BusStation findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_STATION_CODE,
                        COLUMN_NAME,
                        COLUMN_CITY,
                        COLUMN_CITY_CODE,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BusStation details = new BusStation.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .busStationCode(cursor.getString(cursor.getColumnIndex(COLUMN_STATION_CODE)))
                    .Name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .City(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .Code(cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE)))
                    .build();
            return details;
        } else {
            return null;
        }


    }

    @Override
    public BusStation save(BusStation entity) {


        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_STATION_CODE, entity.getBusStationCode());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_CITY,entity.getCity());
        values.put(COLUMN_CITY_CODE,entity.getCode());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        BusStation insertedEntity = new BusStation.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public BusStation update(BusStation entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_STATION_CODE, entity.getBusStationCode());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_CITY_CODE, entity.getCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public BusStation delete(BusStation entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BusStation> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<BusStation> details = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BusStation setting = new BusStation.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .busStationCode(cursor.getString(cursor.getColumnIndex(COLUMN_STATION_CODE)))
                        .Name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .City(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .Code(cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE)))
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
