package mx.ivajotha.desarrollo.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ivajotha.desarrollo.model.ModelItem;
import mx.ivajotha.desarrollo.model.ModelItemList;
import mx.ivajotha.desarrollo.model.ModelUser;
import mx.ivajotha.desarrollo.util.PreferenceUtil;

/**
 * Created by jonathan on 25/06/16.
 */
public class ItemDataSource {
    private final SQLiteDatabase db;
    private PreferenceUtil preferenceUtil;


    public ItemDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }


    public void saveItem(ModelItem modelItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_ITEM_USR,modelItem.data_usr);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_PWD,modelItem.data_pwd);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_LLOGIN,modelItem.data_lllog);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resourceId);
        db.insert(MySqliteHelper.TABLE_NAME,null,contentValues);
    }

    public Boolean searchUser(ModelItem modelItem)
    {
        Boolean existUser = false;
        int count;

        String myUser = modelItem.data_usr;
        String selection = MySqliteHelper.COLUMN_ITEM_USR + "=?";
        String[] selectionArgs = {myUser};
        Cursor c = db.query(MySqliteHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        count = c.getCount();

        if (count > 0 )
            existUser = true;

        return existUser;
    }

    public ModelUser isRegistered(ModelItem modelItem)
    {
        String myUser = modelItem.data_usr;
        String myPasword = modelItem.data_pwd;
        String selection = MySqliteHelper.COLUMN_ITEM_USR + "=? AND " + MySqliteHelper.COLUMN_ITEM_PWD + "=?" ;
        String[] selectionArgs = {myUser,myPasword};
        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);

        if(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String llogin = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_LLOGIN));
            UpdateLlogin(id);
            return new ModelUser(myUser, myPasword, llogin, id);

        }else{

            return null;
        }
    }

    public void deleteItem(ModelItem modelItem)
    {
        db.delete(MySqliteHelper.TABLE_NAME,MySqliteHelper.COLUMN_ID+"=?",
                new String[]{String.valueOf(modelItem.id)});
    }

    public void UpdateLlogin(Integer id)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());


        ContentValues cv = new ContentValues();
        cv.put(MySqliteHelper.COLUMN_ITEM_LLOGIN,strDate);

        String selection = "_id="+id;
        db.update(MySqliteHelper.TABLE_NAME, cv, selection, null);
    }


    /*** ITEMS ***/
    /** Agrega un Item a la BD **/

    public void saveItemList(ModelItemList modelItemList) {
        ContentValues cv = new ContentValues();
        cv.put(MySqliteHelper.COLUMN_TL_NAME, modelItemList.item);
        cv.put(MySqliteHelper.COLUMN_TL_DESC, modelItemList.description);
        cv.put(MySqliteHelper.COLUMN_TL_RESOURCE, modelItemList.resourceId);
        db.insert(MySqliteHelper.TABLE_NAME_TL, null, cv);
    }


    public List<ModelItemList> getallItems(){
        List<ModelItemList> modelItemLists = new ArrayList<>();
        Cursor cursor= db.query(MySqliteHelper.TABLE_NAME_TL,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
            int id_item = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String item_name=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_TL_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_TL_DESC));
            int resourceId = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_TL_RESOURCE));

            ModelItemList modelItemList = new ModelItemList();
            modelItemList.id = id_item;
            modelItemList.item = item_name;
            modelItemList.description = description;
            modelItemList.resourceId = resourceId;
            modelItemLists.add(modelItemList);

        }

        return  modelItemLists;
    }
}
