package mx.ivajotha.desarrollo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import mx.ivajotha.desarrollo.model.ModelUser;

/**
 * Created by jonathan on 25/06/16.
 */
public class PreferenceUtil {
    private static final String FILE_NAME ="unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil(Context context)
    {
        sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }
    public void saveUser(ModelUser modelUser)
    {
        sp.edit().putString("user_name",modelUser.userName).apply();
        sp.edit().putString("user_password",modelUser.password).apply();
    }

    public ModelUser getUser()
    {
        Integer mId = sp.getInt("user_id", Integer.parseInt(null));
        String mUser=sp.getString("user_name",null);
        String mPassword=sp.getString("user_password",null);
        String mllogin=sp.getString("user_llogin",null);

        if(TextUtils.isEmpty(mUser) || TextUtils.isEmpty(mPassword))
            return null;

        return new ModelUser(mUser,mPassword, mllogin, mId);

    }


}