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
import cput.ac.za.assignment_6_1.domain.Ticket;
import cput.ac.za.assignment_6_1.repository.TicketRepository;

/**
 * Created by mgijma on 2016/04/28.
 */
public class TicketRepositoryImp extends SQLiteOpenHelper implements TicketRepository {

    public static final String TABLE_NAME = "passenger_details";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TICKET_ID = "ticket_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_BUS_NO = "bus_no";
    public static final String COLUMN_SEAT_NO = "seat_no";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_CHECK_STATUS = "check_status";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TICKET_ID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_DATE + " TEXT NOT NULL , "
            + COLUMN_BUS_NO + " TEXT NOT NULL , "
            + COLUMN_SEAT_NO + " TEXT  NOT NULL , "
            + COLUMN_STATUS + " TEXT NOT NULL , "
            + COLUMN_CHECK_STATUS + " TEXT NOT NULL );";

    public TicketRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Ticket findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TICKET_ID,
                        COLUMN_DATE,
                        COLUMN_BUS_NO,
                        COLUMN_SEAT_NO,
                        COLUMN_STATUS,
                        COLUMN_CHECK_STATUS,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Ticket details = new Ticket.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .ticketid(cursor.getString(cursor.getColumnIndex(COLUMN_TICKET_ID)))
                    .Date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .BusNo(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_NO)))
                    .SeatNo(cursor.getString(cursor.getColumnIndex(COLUMN_SEAT_NO)))
                    .Status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                    .CheckingStatus(cursor.getString(cursor.getColumnIndex(COLUMN_SEAT_NO)))
                    .build();
            return details;
        } else {
            return null;
        }
    }

    @Override
    public Ticket save(Ticket entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TICKET_ID, entity.getTicketId());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_BUS_NO, entity.getBusNo());
        values.put(COLUMN_SEAT_NO, entity.getSeatNo());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_CHECK_STATUS, entity.getCheckingStatus());
        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Ticket insertedEntity = new Ticket.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public Ticket update(Ticket entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TICKET_ID, entity.getTicketId());
        values.put(COLUMN_DATE, entity.getDate());
        values.put(COLUMN_BUS_NO, entity.getBusNo());
        values.put(COLUMN_SEAT_NO, entity.getSeatNo());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_CHECK_STATUS, entity.getCheckingStatus());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public Ticket delete(Ticket entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Ticket> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Ticket> details = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Ticket setting = new Ticket.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .ticketid(cursor.getString(cursor.getColumnIndex(COLUMN_TICKET_ID)))
                        .Date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .Date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .BusNo(cursor.getString(cursor.getColumnIndex(COLUMN_BUS_NO)))
                        .SeatNo(cursor.getString(cursor.getColumnIndex(COLUMN_SEAT_NO)))
                        .Status(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)))
                        .CheckingStatus(cursor.getString(cursor.getColumnIndex(COLUMN_CHECK_STATUS)))
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
