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
import cput.ac.za.assignment_6_1.domain.BusDriver;
import cput.ac.za.assignment_6_1.repository.BusDriverRepository;

/**
 * Created by mgijma on 2016/04/24.
 */
public class BusDriverRepositoryImp extends SQLiteOpenHelper implements BusDriverRepository {


    public static final String TABLE_NAME = "bus_driver";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMP_NUM = "emp_num";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EMP_NUM + " TEXT UNIQUE NOT NULL , "
            + COLUMN_NAME + " TEXT UNIQUE NOT NULL , "
            + COLUMN_SURNAME + " TEXT UNIQUE NOT NULL );";

    public BusDriverRepositoryImp(Context context) {
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
    public BusDriver findById(Long aLong) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_EMP_NUM,
                        COLUMN_NAME,
                        COLUMN_SURNAME,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(aLong)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final BusDriver driver = new BusDriver.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .empNum(cursor.getString(cursor.getColumnIndex(COLUMN_EMP_NUM)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();
            return driver;
        } else {
            return null;
        }

    }

    @Override
    public BusDriver save(BusDriver entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMP_NUM, entity.getEmp_Num());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        BusDriver insertedEntity = new BusDriver.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public BusDriver update(BusDriver entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMP_NUM, entity.getEmp_Num());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );

        return entity;
    }

    @Override
    public BusDriver delete(BusDriver entity) {

        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<BusDriver> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<BusDriver> driver = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final BusDriver setting = new BusDriver.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .empNum(cursor.getString(cursor.getColumnIndex(COLUMN_EMP_NUM)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .build();
                driver.add(setting);
            } while (cursor.moveToNext());
        }
        return driver;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;

    }
}
