package mx.ivajotha.desarrollo.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by jonathan on 25/06/16.
 */
public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME ="sqlite_bd";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME ="user";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_USR = "user";
    public static final String COLUMN_ITEM_PWD = "password";
    public static final String COLUMN_ITEM_LLOGIN = "sqltime";
    public static final String COLUMN_ITEM_RESOURCE = "resource_id";


    public final static String TABLE_NAME_TL = "items";
    public final static String COLUMN_TL_ID = BaseColumns._ID;
    public final static String COLUMN_TL_NAME = "name";
    public final static String COLUMN_TL_DESC = "description";
    public final static String COLUMN_TL_RESOURCE = "resource";


    private static final String CREATE_TABLE ="create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_USR+" TEXT NOT NULL,"+
            COLUMN_ITEM_PWD+ " TEXT NOT NULL,"+
            COLUMN_ITEM_LLOGIN+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
            COLUMN_ITEM_RESOURCE+" INTEGER NOT NULL)";


    private static final String CREATE_TABLE_TL = "create table " + TABLE_NAME_TL +
            "("+COLUMN_TL_ID+" integer primary key autoincrement,"+
            COLUMN_TL_NAME +" TEXT NOT NULL,"+
            COLUMN_TL_DESC + " TEXT NOT NULL,"+
            COLUMN_TL_RESOURCE +" TEXT NOT NULL)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);
        db.delete(CREATE_TABLE, null, null);
        db.delete(CREATE_TABLE_TL, null, null);

    }
}
