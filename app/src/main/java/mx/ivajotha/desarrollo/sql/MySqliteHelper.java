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
    private final static String DATABASE_NAME ="sqlitebd";
    private final static int DATABASE_VERSION=1;
    public static final String TABLE_NAME ="user";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ITEM_USR = "user";
    public static final String COLUMN_ITEM_PWD = "password";
    public static final String COLUMN_ITEM_LLOGIN = "sqltime";
    public static final String COLUMN_ITEM_RESOURCE = "resource_id";

    private static final String CREATE_TABLE ="create table "+TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            COLUMN_ITEM_USR+" TEXT NOT NULL,"+
            COLUMN_ITEM_PWD+ " TEXT NOT NULL,"+
            COLUMN_ITEM_LLOGIN+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"+
            COLUMN_ITEM_RESOURCE+" INTEGER NOT NULL)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);

    }
}
